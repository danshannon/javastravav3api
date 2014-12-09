/**
 * 
 */
package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
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
	
	private static AuthorisationServices implementation = null;
	
	private AuthorisationServicesImpl(AuthorisationServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
	 * <p>Returns an implementation of {@link AuthorisationServices segment effort services}</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the segment effort services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static AuthorisationServices implementation() {
		// Acts as a singleton, so if there is one already just return it
		if (implementation != null) {
			return implementation;
		}
		
		// In case there isn't an existing one, create one and return that
		implementation = new AuthorisationServicesImpl(new RestAdapter.Builder()
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(LOG_LEVEL)
				.setEndpoint(Strava.AUTH_ENDPOINT)
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(AuthorisationServicesRetrofit.class));
		
		return implementation;
	}
	
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

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#requestAccess(Integer, String, AuthorisationResponseType, AuthorisationApprovalPrompt, AuthorisationScope[], String)
	 */
	@Override
	public void requestAccess(Integer clientId, String redirectURI, AuthorisationResponseType responseType,
			AuthorisationApprovalPrompt approvalPrompt, AuthorisationScope[] scope, String state) {
		restService.requestAccess(clientId, redirectURI, responseType, approvalPrompt, scope, state);
		
	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#login(String, String)
	 */
	@Override
	public boolean login(String email, String password) {
		return restService.login(email, password);
	}

	@Override
	public String acceptApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType) {
		return restService.acceptApplication(clientId, redirectURI, responseType);
		
	}

	@Override
	public void rejectApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType) {
		restService.rejectApplication(clientId, redirectURI, responseType);
	}

}
