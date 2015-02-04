package stravajava.api.v3.auth.impl.retrofit;

import java.util.HashMap;

import stravajava.api.v3.auth.TokenManager;
import stravajava.api.v3.auth.TokenServices;
import stravajava.api.v3.auth.model.Token;
import stravajava.api.v3.auth.model.TokenResponse;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.Retrofit;

/**
 * @author Dan Shannon
 *
 */
public class TokenServicesImpl implements TokenServices {
	private TokenServicesImpl(final String token) {
		this.restService = Retrofit.retrofit(TokenServicesRetrofit.class, token, TokenServicesRetrofit.LOG_LEVEL);
	}
	
	/**
	 * <p>Returns an implementation of {@link TokenServices token services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the token services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static TokenServices implementation(final String token) throws UnauthorizedException {
		TokenServices restService = restServices.get(token);
		if (restService == null) {
			restService = new TokenServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,TokenServices> restServices = new HashMap<String,TokenServices>();

	
	private TokenServicesRetrofit restService;
	
	/**
	 * @see stravajava.api.v3.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	public TokenResponse deauthorise(Token accessToken) throws UnauthorizedException {
		// Remove the token / service instance from the cached list as it's no longer any use
		restServices.remove(accessToken);
		
		// Deauthorise
		TokenResponse response = restService.deauthorise(accessToken.getToken());
		TokenManager.implementation().revokeToken(accessToken);
		return response;
	}
	
}
