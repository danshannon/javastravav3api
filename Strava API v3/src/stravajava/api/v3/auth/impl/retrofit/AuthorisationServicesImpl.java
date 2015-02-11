package stravajava.api.v3.auth.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.auth.AuthorisationServices;
import stravajava.api.v3.auth.model.TokenResponse;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.RetrofitErrorHandler;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.BASIC;

	private final AuthorisationServicesRetrofit restService;

	/**
	 * <p>
	 * Default constructor creates a {@link RestAdapter} which is the actual implementation of the REST interface
	 * </p>
	 */
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
	 * @see stravajava.api.v3.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public TokenResponse tokenExchange(final Integer clientId, final String clientSecret, final String code) throws BadRequestException, UnauthorizedException {
		return this.restService.tokenExchange(clientId, clientSecret, code);
	}

}
