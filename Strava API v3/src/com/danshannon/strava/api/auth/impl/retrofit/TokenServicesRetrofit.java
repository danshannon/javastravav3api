package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * <p>Retrofit implementation of the Strava REST interface for token management</p>
 * 
 * @author Dan Shannon
 *
 */
public interface TokenServicesRetrofit  {
	/**
	 * @see com.danshannon.strava.api.auth.TokenServices#deauthorise(java.lang.String)
	 */
	@FormUrlEncoded
	@POST("/oauth/deauthorize")
	public TokenResponse deauthorise(@Field("access_token") String accessToken) throws UnauthorizedException;

}
