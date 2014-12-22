package com.danshannon.strava.api.auth.impl.retrofit;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code) {
		// TODO Not yet implemented
		return null;
	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	public TokenResponse deauthorise(String accessToken) {
		// TODO Not yet implemented
		return null;
	}
	
}
