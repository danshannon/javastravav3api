package javastrava.api.v3.service.impl.retrofit;

import java.io.IOException;
import java.util.StringTokenizer;

import javastrava.config.Strava;
import lombok.NoArgsConstructor;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

import com.squareup.okhttp.OkHttpClient;

/**
 * <p>
 * Overrides the OkHttp client in order to intercept the rate limit data returned by the API in headers
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@NoArgsConstructor
public class RetrofitClientResponseInterceptor extends OkClient {

	/**
	 * @param client The client to use
	 */
	public RetrofitClientResponseInterceptor(final OkHttpClient client) {
		super(client);
	}

	/**
	 * <p>
	 * Gets and stores the values of the rate limit information headers returned by Strava with each response
	 * </p>
	 * 
	 * @see retrofit.client.OkClient#execute(retrofit.client.Request)
	 */
	@Override
	public Response execute(final Request request) throws IOException {
		Response response = super.execute(request);

		for (Header header : response.getHeaders()) {
			if (header.getName().equals(Strava.stringProperty("strava.rate-limit-usage-header-name"))) { //$NON-NLS-1$
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ","); //$NON-NLS-1$
				StravaServiceImpl.requestRate = new Integer(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRateDaily = new Integer(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRatePercentage();
			}
			if (header.getName().equals(Strava.stringProperty("strava.rate-limit-limit-header-name"))) { //$NON-NLS-1$
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ","); //$NON-NLS-1$
				Strava.RATE_LIMIT = new Integer(tokenizer.nextToken()).intValue();
				Strava.RATE_LIMIT_DAILY = new Integer(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRateDailyPercentage();
			}
		}

		return response;
	}
}
