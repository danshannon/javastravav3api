package javastrava.api.v3.auth.impl.retrofit;

import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.RestAdapter;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServiceImpl implements AuthorisationService {

	private final AuthorisationAPI restService;

	/**
	 * <p>
	 * Default constructor creates a {@link RestAdapter} which is the actual implementation of the REST interface
	 * </p>
	 */
	public AuthorisationServiceImpl() {
		this.restService = API.authorisationInstance();
	}

	/**
	 * @see javastrava.api.v3.auth.AuthorisationService#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String, AuthorisationScope...)
	 */
	@Override
	public Token tokenExchange(final Integer clientId, final String clientSecret, final String code, final AuthorisationScope... scopes) throws BadRequestException, UnauthorizedException {
		TokenResponse response = this.restService.tokenExchange(clientId, clientSecret, code);
		Token token = new Token(response, scopes);
		TokenManager.instance().storeToken(token);
		return token;
	}

}
