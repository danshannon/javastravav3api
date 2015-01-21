package com.danshannon.strava.api.auth.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.auth.TokenServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.RetrofitErrorHandler;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class TokenServicesImpl implements TokenServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;

	private TokenServicesImpl(TokenServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * <p>Returns an implementation of {@link TokenServices token services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the token services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static TokenServices implementation(final String token) throws UnauthorizedException {
		TokenServices restService = restServices.get(token);
		if (restService == null) {
			restService = new TokenServicesImpl(new RestAdapter.Builder()
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(LOG_LEVEL)
				.setEndpoint(Strava.ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(TokenServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,TokenServices> restServices = new HashMap<String,TokenServices>();

	
	private TokenServicesRetrofit restService;
	
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	public TokenResponse deauthorise(String accessToken) throws UnauthorizedException {
		// Remove the token / service instance from the cached list as it's no longer any use
		restServices.remove(accessToken);
		
		// Deauthorise
		TokenResponse response = restService.deauthorise(accessToken);
		return response;
	}
	
}
