package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.auth.model.Token;
import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.impl.gson.JsonUtilImpl;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * <p>
 * Provides a static method {@link #retrofit(Class, Token)} which constructs a standard retrofit service with all the required options.
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
	 * @param <T> Class of Retrofit interface to be instantiated (one of the *Retrofit.java interfaces)
	 * @return A REST service
	 */
	public static <T> T retrofit(final Class<T> class1, final Token token) {
		return new RestAdapter.Builder()
				// Client overrides handling of Strava-specific headers in the response, to deal with rate limiting
				.setClient(new RetrofitClientResponseInterceptor())
				// Converter is a GSON implementation with custom converters
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				// Log level is determined per Retrofit service
				.setLogLevel(Strava.logLevel(class1))
				// Endpoint is the same for all services
				.setEndpoint(Strava.ENDPOINT)
				// Request interceptor adds the access token into headers for each request
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(final RequestFacade request) {
						request.addHeader(Messages.getString("Retrofit.authorizationHeaderName"), Messages.getString("Retrofit.bearer") + token.getToken()); //$NON-NLS-1$ //$NON-NLS-2$
					}
				})
				// Error handler deals with Strava's implementations of 400, 401, 403, 404 errors etc.
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(class1);
	}

}
