package javastrava.auth.impl;

import javastrava.auth.TokenManager;
import javastrava.auth.TokenService;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.service.StravaService;
import javastrava.service.exception.UnauthorizedException;
import javastrava.service.impl.StravaServiceImpl;

/**
 * @author Dan Shannon
 *
 */
public class TokenServiceImpl extends StravaServiceImpl implements TokenService {
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
		TokenService restService = token.getService(TokenService.class);
		if (restService == null) {
			restService = new TokenServiceImpl(token);

			// Store the service for later retrieval so that there's only one
			// service per token
			token.addService(TokenService.class, restService);

		}
		return restService;
	}

	/**
	 * <p>
	 * Private constructor allows for the {@link #instance} method to only be given access to a single instance per {@link Token}
	 * </p>
	 * @param token The token to use for this token service
	 */
	private TokenServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		// Nothing to do - there is no cache
	}

	/**
	 * @see TokenService#deauthorise(Token)
	 */
	@Override
	public TokenResponse deauthorise(final Token token) throws UnauthorizedException {
		final TokenResponse response = this.api.deauthoriseToken(token.getToken());
		for (final StravaService service : token.getServices().values()) {
			service.clearCache();
		}
		TokenManager.instance().revokeToken(token);
		return response;
	}

}
