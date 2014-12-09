package com.danshannon.strava.api.auth.impl.retrofit;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;

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
	@GET("/oauth/authorize")
	public void requestAccess(@Query("client_id") Integer clientId, @Query("redirect_uri") String redirectURI, @Query("response_type") AuthorisationResponseType responseType,
			@Query("approval_prompt") AuthorisationApprovalPrompt approvalPrompt, @Query("scope") AuthorisationScope[] scope, @Query("state") String state);
	
	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
	 */
	@POST("/oauth/token")
	public TokenResponse tokenExchange(@Field("client_id") Integer clientId, @Field("client_secret") String clientSecret, @Field("code") String code);

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@POST("/oauth/deauthorize")
	public TokenResponse deauthorise(@Field("access_token") String accessToken);

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#login(java.lang.String, java.lang.String)
	 */
	@FormUrlEncoded
	@POST("/session")
	public boolean login(@Field("email") String email, @Field("password") String password);
	
	/**
	 * @return 
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#acceptApplication(java.lang.Integer, java.lang.String, AuthorisationResponseType)
	 */
	@POST("/oauth/accept_application")
	public String acceptApplication(@Field("client_id") Integer clientId, @Field("redirect_uri") String redirectURI, @Field("response_type") AuthorisationResponseType responseType);

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#rejectApplication(java.lang.Integer, java.lang.String, AuthorisationResponseType)
	 */
	@POST("/oauth/reject_application")
	public void rejectApplication(@Field("client_id") Integer clientId, @Field("redirect_uri") String redirectURI, @Field("response_type") AuthorisationResponseType responseType);


}
