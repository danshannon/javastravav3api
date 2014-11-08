package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.POST;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;

/**
 * <p>To get an instance, use {@link AuthorisationServicesImpl#REST_SERVICE}</p>
 * 
 * @author Dan Shannon
 *
 */
public interface AuthorisationServicesImpl extends AuthorisationServices {
	public static final AuthorisationServicesImpl REST_SERVICE = new RestAdapter.Builder().setEndpoint(ENDPOINT).build().create(AuthorisationServicesImpl.class);
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#requestAccess(java.lang.Integer, java.lang.String,
	 *      com.danshannon.strava.api.auth.ref.AuthorisationResponseType,
	 *      com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt, com.danshannon.strava.api.auth.ref.AuthorisationScope[],
	 *      java.lang.String)
	 */
	@Override
	public void requestAccess(Integer clientId, String redirectURI, AuthorisationResponseType responseType,
			AuthorisationApprovalPrompt approvalPrompt, AuthorisationScope[] scope, String state);
	/**
	 * <p>
	 * Usage: <code>AuthorisationServicesImpl.REST_SERVICE.tokenExchange(...)</code>
	 * </p>
	 *
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	@POST("/oauth/token")
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code);

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	@POST("oauth/deauthorize")
	public TokenResponse deauthorise(String accessToken);

}
