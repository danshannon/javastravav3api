/**
 * 
 */
package com.danshannon.strava.api.auth.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.RetrofitErrorHandler;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {

	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private AuthorisationServicesImpl(AuthorisationServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
	 * <p>Returns an implementation of {@link AuthorisationServices segment effort services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the segment effort services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static AuthorisationServices implementation(String token) throws UnauthorizedException {
		AuthorisationServices restService = restServices.get(token);
		if (restService == null) {
			restService = new AuthorisationServicesImpl(new RestAdapter.Builder()
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
				.create(AuthorisationServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,AuthorisationServices> restServices = new HashMap<String,AuthorisationServices>();
	
	private AuthorisationServicesRetrofit restService;
	
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code) {
		return restService.tokenExchange(clientId, clientSecret, code);
	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	public TokenResponse deauthorise(String accessToken) {
		return restService.deauthorise(accessToken);
	}

}
