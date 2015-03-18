package javastrava.api.v3.rest.util;

import java.io.IOException;
import java.util.StringTokenizer;

import javastrava.api.v3.service.impl.StravaServiceImpl;
import javastrava.config.StravaConfig;
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
			if (header.getName().equals(StravaConfig.string("strava.rate-limit-usage-header-name"))) { //$NON-NLS-1$
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ","); //$NON-NLS-1$
				StravaServiceImpl.requestRate = Integer.valueOf(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRateDaily = Integer.valueOf(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRatePercentage();
			}
			if (header.getName().equals(StravaConfig.string("strava.rate-limit-limit-header-name"))) { //$NON-NLS-1$
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ","); //$NON-NLS-1$
				StravaConfig.RATE_LIMIT = Integer.valueOf(tokenizer.nextToken()).intValue();
				StravaConfig.RATE_LIMIT_DAILY = Integer.valueOf(tokenizer.nextToken()).intValue();
				StravaServiceImpl.requestRateDailyPercentage();
			}
		}

		return response;
	}
}
