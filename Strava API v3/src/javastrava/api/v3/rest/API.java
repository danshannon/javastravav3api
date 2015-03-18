package javastrava.api.v3.rest;

import java.util.MissingResourceException;

import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.rest.util.RetrofitClientResponseInterceptor;
import javastrava.api.v3.rest.util.RetrofitErrorHandler;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.JsonUtilImpl;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;

/**
 * <p>
 * Provides a static method {@link #instance(Class, Token)} which constructs a standard retrofit service with all the required options.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class API {
	private API() {
		// You can't have one of these
	}

	private static AuthorisationAPI authorisationAPI;

	/**
	 * <p>
	 * Creates and returns a new API RestAdapter instance.
	 * </p>
	 * 
	 * @param class1
	 *            The class to be returned
	 * @param token
	 *            The access token required for authentication of requests to the Strava API
	 * @param <T>
	 *            Class of API interface to be instantiated (one of the *API.java interfaces)
	 * @return A REST service
	 */
	public static <T> T instance(final Class<T> class1, final Token token) {
		return new RestAdapter.Builder()
		// Client overrides handling of Strava-specific headers in the response, to deal with rate limiting
				.setClient(new RetrofitClientResponseInterceptor())
				// Converter is a GSON implementation with custom converters
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				// Log level is determined per API service
				.setLogLevel(API.logLevel(class1))
				// Endpoint is the same for all services
				.setEndpoint(StravaConfig.ENDPOINT)
				// Request interceptor adds the access token into headers for each request
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(final RequestFacade request) {
						request.addHeader(StravaConfig.string("strava.authorization_header_name"), token.getTokenType() + " " + token.getToken()); //$NON-NLS-1$ //$NON-NLS-2$
					}
				})
				// Error handler deals with Strava's implementations of 400, 401, 403, 404 errors etc.
				.setErrorHandler(new RetrofitErrorHandler()).build().create(class1);
	}

	/**
	 * @param class1
	 *            Class for which log level is to be determined
	 * @return The appropriate log level for the class
	 */
	public static LogLevel logLevel(final Class<?> class1) {
		String propertyName = "retrofit." + class1.getName() + ".log_level"; //$NON-NLS-1$ //$NON-NLS-2$
		RestAdapter.LogLevel logLevel = null;
		try {
			logLevel = RestAdapter.LogLevel.valueOf(StravaConfig.string(propertyName));
		} catch (MissingResourceException e) {
			logLevel = RestAdapter.LogLevel.valueOf(StravaConfig.string("retrofit.log_level")); //$NON-NLS-1$
		}
		return logLevel;
	}

	/**
	 * <p>
	 * Get an instance of the authorisation API (cached)
	 * </p>
	 * 
	 * @return Instance of the authorisation API
	 */
	public static AuthorisationAPI authorisationInstance() {
		if (authorisationAPI == null) {
			authorisationAPI = new RestAdapter.Builder().setClient(new RetrofitClientResponseInterceptor())
					.setConverter(new GsonConverter(new JsonUtilImpl().getGson())).setLogLevel(API.logLevel(AuthorisationServiceImpl.class))
					.setEndpoint(StravaConfig.AUTH_ENDPOINT).setErrorHandler(new RetrofitErrorHandler()).build().create(AuthorisationAPI.class);
		}
		return authorisationAPI;
	}

}
