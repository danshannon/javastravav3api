package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.http.POST;

import com.danshannon.strava.api.auth.model.TokenResponse;

/**
 * <p>To get an instance, use {@link AuthorisationServicesRetrofit#REST_SERVICE}</p>
 * 
 * @author Dan Shannon
 *
 */
public interface AuthorisationServicesRetrofit  {
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#requestAccess(java.lang.Integer, java.lang.String,
	 *      com.danshannon.strava.api.auth.ref.AuthorisationResponseType,
	 *      com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt, com.danshannon.strava.api.auth.ref.AuthorisationScope[],
	 *      java.lang.String)
	 */
	// TODO Do we need this at all??
	//public void requestAccess(Integer clientId, String redirectURI, AuthorisationResponseType responseType,
	//		AuthorisationApprovalPrompt approvalPrompt, AuthorisationScope[] scope, String state);
	/**
	 * <p>
	 * Usage: <code>AuthorisationServicesImpl.REST_SERVICE.tokenExchange(...)</code>
	 * </p>
	 *
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
	 */
	@POST("/token")
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code);

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@POST("/deauthorize")
	public TokenResponse deauthorise(String accessToken);

}
