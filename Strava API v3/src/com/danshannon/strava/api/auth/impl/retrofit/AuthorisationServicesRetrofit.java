package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * <p>Retrofit implementation of the Strava REST interface for authorisation</p>
 * 
 * @author Dan Shannon
 *
 */
public interface AuthorisationServicesRetrofit  {
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@FormUrlEncoded
	@POST("/oauth/token")
	public TokenResponse tokenExchange(@Field("client_id") Integer clientId, @Field("client_secret") String clientSecret, @Field("code") String code) throws BadRequestException, UnauthorizedException;
}
