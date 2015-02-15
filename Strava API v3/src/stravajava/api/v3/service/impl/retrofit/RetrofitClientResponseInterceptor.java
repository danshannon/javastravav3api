package stravajava.api.v3.service.impl.retrofit;

import java.io.IOException;
import java.util.StringTokenizer;

import lombok.NoArgsConstructor;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

import com.squareup.okhttp.OkHttpClient;

@NoArgsConstructor
public class RetrofitClientResponseInterceptor extends OkClient {

	public RetrofitClientResponseInterceptor(final OkHttpClient client) {
		super(client);
	}

	@Override
	public Response execute(final Request request) throws IOException {
		Response response = super.execute(request);

		for (Header header : response.getHeaders()) {
			if (header.getName().equals("X-RateLimit-Usage")) {
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ",");
				StravaServiceImpl.requestRate = new Integer(tokenizer.nextToken());
				StravaServiceImpl.requestRateDaily = new Integer(tokenizer.nextToken());
				StravaServiceImpl.requestRatePercentage();
			}
			if (header.getName().equals("X-RateLimit-Limit")) {
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ",");
				StravaServiceImpl.requestLimit = new Integer(tokenizer.nextToken());
				StravaServiceImpl.requestLimitDaily = new Integer(tokenizer.nextToken());
				StravaServiceImpl.requestRateDailyPercentage();
			}
		}

		return response;
	}
}
