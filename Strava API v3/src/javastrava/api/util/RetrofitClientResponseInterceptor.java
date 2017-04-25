package javastrava.api.util;

import java.io.IOException;
import java.util.StringTokenizer;

import com.squareup.okhttp.OkHttpClient;

import javastrava.config.StravaConfig;
import javastrava.service.Strava;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * <p>
 * Overrides the OkHttp client in order to intercept the rate limit data returned by the API in headers
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class RetrofitClientResponseInterceptor extends OkClient {

	/**
	 * No-args constructor
	 */
	public RetrofitClientResponseInterceptor() {
		super();
	}
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
				Strava.REQUEST_RATE_CURRENT = Integer.valueOf(tokenizer.nextToken()).intValue();
				Strava.REQUEST_RATE_DAILY = Integer.valueOf(tokenizer.nextToken()).intValue();
				Strava.requestRateCurrentPercentage();
			}
			if (header.getName().equals(StravaConfig.string("strava.rate-limit-limit-header-name"))) { //$NON-NLS-1$
				String values = header.getValue();
				StringTokenizer tokenizer = new StringTokenizer(values, ","); //$NON-NLS-1$
				Strava.RATE_LIMIT_CURRENT = Integer.valueOf(tokenizer.nextToken()).intValue();
				Strava.RATE_LIMIT_DAILY = Integer.valueOf(tokenizer.nextToken()).intValue();
				Strava.requestRateDailyPercentage();
			}
		}

		return response;
	}
}
