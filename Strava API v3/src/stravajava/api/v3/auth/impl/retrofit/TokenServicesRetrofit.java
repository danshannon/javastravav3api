package stravajava.api.v3.auth.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import stravajava.api.v3.auth.model.TokenResponse;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>Retrofit implementation of the Strava REST interface for token management</p>
 * 
 * @author Dan Shannon
 *
 */
public interface TokenServicesRetrofit  {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;

	/**
	 * @see stravajava.api.v3.auth.TokenServices#deauthorise(java.lang.String)
	 */
	@FormUrlEncoded
	@POST("/oauth/deauthorize")
	public TokenResponse deauthorise(@Field("access_token") String accessToken) throws UnauthorizedException;

}
