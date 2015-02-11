package stravajava.api.v3.auth.impl.retrofit;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import stravajava.api.v3.auth.model.TokenResponse;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>
 * Retrofit implementation of the Strava REST interface for authorisation
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface AuthorisationServicesRetrofit {
    /**
     * @see stravajava.api.v3.auth.AuthorisationServices#tokenExchange(java.lang.Integer,
     *      java.lang.String, java.lang.String)
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    public TokenResponse tokenExchange(@Field("client_id") final Integer clientId,
	    @Field("client_secret") final String clientSecret, @Field("code") final String code)
	    throws BadRequestException, UnauthorizedException;
}
