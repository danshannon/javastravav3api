package stravajava.api.v3.service.impl.retrofit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.service.Strava;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * <p>
 * Provides a static method {@link #retrofit(Class, String, retrofit.RestAdapter.LogLevel)} which constructs a standard retrofit service with all the required options.
 * </p>
 *  
 * @author Dan Shannon
 *
 */
public class Retrofit {
	private Retrofit() {
		// You can't have one of these
	}

	/**
	 * <p>
	 * Creates and returns a new Retrofit RestAdapter instance.
	 * </p>
	 * 
	 * @param class1 The class to be returned
	 * @param token The access token required for authentication of requests to the Strava API
	 * @param logLevel Log level required for the service
	 * @return A REST service
	 */
	public static <T> T retrofit(final Class<T> class1, final String token, final RestAdapter.LogLevel logLevel) {
		return new RestAdapter.Builder()
				// Client overrides handling of Strava-specific headers in the response, to deal with rate limiting
				.setClient(new RetrofitClientResponseInterceptor())
				// Converter is a GSON implementation with custom converters
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				// Log level is determined per Retrofit service
				.setLogLevel(logLevel)
				// Endpoint is the same for all services
				.setEndpoint(Strava.ENDPOINT)
				// Request interceptor adds the access token into headers for each request
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(final RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				// Error handler deals with Strava's implementations of 400, 401, 403, 404 errors etc.
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(class1);
	}

}
