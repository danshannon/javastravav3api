package javastrava.api.v3.auth.impl.retrofit;

import javastrava.api.v3.auth.TokenServices;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * <p>
 * Retrofit implementation of the Strava REST interface for token management
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface TokenServicesRetrofit {
	public static RestAdapter.LogLevel	LOG_LEVEL	= RestAdapter.LogLevel.NONE;

	/**
	 * @see TokenServices#deauthorise(javastrava.api.v3.auth.model.Token)
	 * 
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 */
	@FormUrlEncoded
	@POST("/oauth/deauthorize")
	public TokenResponse deauthorise(@Field("access_token") final String accessToken) throws UnauthorizedException;

}
