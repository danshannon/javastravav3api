package javastrava.api.v3.auth.impl.retrofit;

import java.util.HashMap;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.TokenService;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.TokenAPI;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.StravaServiceImpl;

/**
 * @author Dan Shannon
 *
 */
public class TokenServiceImpl extends StravaServiceImpl<TokenAPI> implements TokenService {
	/**
	 * <p>
	 * Returns an instance of {@link TokenService token services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the token services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static TokenService instance(final Token token) throws UnauthorizedException {
		TokenService restService = restServices.get(token);
		if (restService == null) {
			restService = new TokenServiceImpl(token);

			// Store the token for later retrieval so that there's only one
			// service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<Token, TokenService> restServices = new HashMap<Token, TokenService>();

	private TokenServiceImpl(final Token token) {
		super(TokenAPI.class, token);
	}

	/**
	 * @see TokenService#deauthorise(Token)
	 */
	@Override
	public TokenResponse deauthorise(final Token accessToken) throws UnauthorizedException {
		// Remove the token / service instance from the cached list as it's no
		// longer any use
		restServices.remove(accessToken);

		// Deauthorise
		final TokenResponse response = this.restService.deauthorise(accessToken.getToken());
		TokenManager.instance().revokeToken(accessToken);
		return response;
	}

}
