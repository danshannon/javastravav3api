package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.impl.retrofit.RetrofitErrorHandler;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	private AuthorisationServicesRetrofit restService;
	
	public AuthorisationServicesImpl() {
		this.restService = new RestAdapter.Builder()
		.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
		.setLogLevel(LOG_LEVEL)
		.setEndpoint(Strava.AUTH_ENDPOINT)
		.setErrorHandler(new RetrofitErrorHandler())
		.build()
		.create(AuthorisationServicesRetrofit.class);
	}
	
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
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
