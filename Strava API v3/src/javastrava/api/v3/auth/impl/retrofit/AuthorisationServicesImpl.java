package javastrava.api.v3.auth.impl.retrofit;

import javastrava.api.v3.auth.AuthorisationServices;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.service.Strava;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.RetrofitClientResponseInterceptor;
import javastrava.api.v3.service.impl.retrofit.RetrofitErrorHandler;
import javastrava.util.impl.gson.JsonUtilImpl;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {

	private final AuthorisationServicesRetrofit restService;

	/**
	 * <p>
	 * Default constructor creates a {@link RestAdapter} which is the actual implementation of the REST interface
	 * </p>
	 */
	public AuthorisationServicesImpl() {
		this.restService = new RestAdapter.Builder()
				.setClient(new RetrofitClientResponseInterceptor())
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(AuthorisationServicesRetrofit.LOG_LEVEL)
				.setEndpoint(Strava.AUTH_ENDPOINT)
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(AuthorisationServicesRetrofit.class);
	}

	/**
	 * @see javastrava.api.v3.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public TokenResponse tokenExchange(final Integer clientId, final String clientSecret, final String code) throws BadRequestException, UnauthorizedException {
		return this.restService.tokenExchange(clientId, clientSecret, code);
	}

}
