package javastrava.api.v3.auth.impl.retrofit;

import java.util.HashMap;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.TokenServices;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.Retrofit;
import javastrava.api.v3.service.impl.retrofit.StravaServiceImpl;

/**
 * @author Dan Shannon
 *
 */
public class TokenServicesImpl extends StravaServiceImpl implements TokenServices {
	/**
	 * <p>
	 * Returns an implementation of {@link TokenServices token services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the token services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static TokenServices implementation(final Token token) throws UnauthorizedException {
		TokenServices restService = restServices.get(token);
		if (restService == null) {
			restService = new TokenServicesImpl(token);

			// Store the token for later retrieval so that there's only one
			// service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<Token, TokenServices> restServices = new HashMap<Token, TokenServices>();

	private final TokenServicesRetrofit restService;

	private TokenServicesImpl(final Token token) {
		super(token);
		this.restService = Retrofit.retrofit(TokenServicesRetrofit.class, token, TokenServicesRetrofit.LOG_LEVEL);
	}

	/**
	 * @see TokenServices#deauthorise(Token)
	 */
	@Override
	public TokenResponse deauthorise(final Token accessToken) throws UnauthorizedException {
		// Remove the token / service instance from the cached list as it's no
		// longer any use
		restServices.remove(accessToken);

		// Deauthorise
		final TokenResponse response = this.restService.deauthorise(accessToken.getToken());
		TokenManager.implementation().revokeToken(accessToken);
		return response;
	}

}
