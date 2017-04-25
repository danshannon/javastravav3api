package javastrava.auth.impl;

import javastrava.api.API;
import javastrava.api.AuthorisationAPI;
import javastrava.auth.AuthorisationService;
import javastrava.auth.TokenManager;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.RestAdapter;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServiceImpl implements AuthorisationService {

	/**
	 * Authorisation API instance
	 */
	private final AuthorisationAPI api;

	/**
	 * <p>
	 * Default constructor creates a {@link RestAdapter} which is the actual implementation of the REST interface
	 * </p>
	 */
	public AuthorisationServiceImpl() {
		this.api = API.authorisationInstance();
	}

	/**
	 * @see javastrava.auth.AuthorisationService#tokenExchange(java.lang.Integer, java.lang.String, java.lang.String, AuthorisationScope...)
	 */
	@Override
	public Token tokenExchange(final Integer clientId, final String clientSecret, final String code, final AuthorisationScope... scopes) throws BadRequestException, UnauthorizedException {
		final TokenResponse response = this.api.tokenExchange(clientId, clientSecret, code);
		final Token token = new Token(response, scopes);
		TokenManager.instance().storeToken(token);
		return token;
	}

}
