package javastrava.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javastrava.auth.model.Token;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.config.Messages;

/**
 * <p>
 * Manages the caching of tokens
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class TokenManager {
	/**
	 * <p>
	 * The singleton instance of {@link TokenManager}
	 * </p>
	 */
	private static TokenManager instance = new TokenManager();

	/**
	 * @return Singleton instance of the TokenManager
	 */
	public static TokenManager instance() {
		return instance;
	}

	/**
	 * Cached tokens, mapped by username
	 */
	private final Map<String, Token> tokens;

	/**
	 * <p>
	 * Private constructor allows only for instantiation as a singleton via {@link #instance}
	 * </p>
	 */
	private TokenManager() {
		// Initialise as a singleton
		this.tokens = new HashMap<String, Token>();
	}

	/**
	 * <p>
	 * Removes all tokens from the cache
	 * </p>
	 */
	public void clearTokenCache() {
		for (final Token token : this.tokens.values()) {
			revokeToken(token);
		}
	}

	/**
	 * <p>
	 * Retrieve a cached token which has <strong>exactly</strong> the given set
	 * of scopes
	 * </p>
	 *
	 * @param username
	 *            The username
	 * @param requiredScopes
	 *            This list of scopes which must match the scopes of the token
	 * @return The token with the matching list of scopes, or <code>null</code>
	 *         if there is no such token
	 */
	public Token retrieveTokenWithExactScope(final String username, final AuthorisationScope... requiredScopes) {
		// Get the token from the cache
		final Token token = this.tokens.get(username);

		// If there's no such token, or it has no scopes, then return null
		if ((token == null) || (token.getScopes() == null)) {
			return null;
		}

		// Check that all the required scopes are in the token
		for (final AuthorisationScope scope : requiredScopes) {
			if (!token.getScopes().contains(scope)) {
				return null;
			}
		}

		// Check that all the scopes in the token are required
		for (final AuthorisationScope scope : token.getScopes()) {
			boolean match = false;
			for (final AuthorisationScope requiredScope : requiredScopes) {
				if (scope == requiredScope) {
					match = true;
				}
			}
			if (match == false) {
				return null;
			}
		}

		// OK we're happy now, so return the token
		return token;
	}

	/**
	 * <p>
	 * Retrieve a cached token which has <strong>exactly</strong> the given set
	 * of scopes
	 * </p>
	 *
	 * @param username The user to look up for a cached token
	 * @param scopes The set of scopes the token must have
	 * @return The matching token from the cache, or <code>null</code> if there is no matching token
	 */
	public Token retrieveTokenWithExactScope(final String username, final List<AuthorisationScope> scopes) {
		if (scopes == null) {
			return retrieveTokenWithExactScope(username, new AuthorisationScope[] {});
		}
		final AuthorisationScope[] array = new AuthorisationScope[scopes.size()];
		for (int i = 0; i < scopes.size(); i++) {
			array[i] = scopes.get(i);
		}
		return retrieveTokenWithExactScope(username, array);
	}

	/**
	 * <p>
	 * Retrieve a token which has <strong>at least</strong> the given set of
	 * scopes.
	 * </p>
	 *
	 * @param username
	 *            The username
	 * @param scopes
	 *            The list of scopes which are required to be in the token
	 * @return The token, or <code>null</code> if there is no cached token, or
	 *         the cached token doesn't have all the required scopes
	 */
	public Token retrieveTokenWithScope(final String username, final AuthorisationScope... scopes) {
		// Get the token from cache
		final Token token = this.tokens.get(username);
		AuthorisationScope[] authScopes = scopes;

		// If scopes = null
		if (authScopes == null) {
			authScopes = new AuthorisationScope[0];
		}

		// If there's no cached token, or it doesn't have any scopes (which
		// shouldn't happen) then return null
		if ((token == null) || (token.getScopes() == null)) {
			return null;
		}

		// Check that all the required scopes are in the token
		for (final AuthorisationScope scope : authScopes) {
			if (!token.getScopes().contains(scope)) {
				return null;
			}
		}
		return token;
	}

	/**
	 * <p>
	 * Retrieve a token which has <strong>at least</strong> the given set of
	 * scopes.
	 * </p>
	 *
	 * @param username
	 *            The username
	 * @param scopes
	 *            The list of scopes which are required to be in the token
	 * @return The token, or <code>null</code> if there is no cached token, or
	 *         the cached token doesn't have all the required scopes
	 */
	public Token retrieveTokenWithScope(final String username, final List<AuthorisationScope> scopes) {

		if (scopes == null) {
			return retrieveTokenWithScope(username, new AuthorisationScope[] {});
		}
		final AuthorisationScope[] array = new AuthorisationScope[scopes.size()];
		for (int i = 0; i < scopes.size(); i++) {
			array[i] = scopes.get(i);
		}
		return retrieveTokenWithExactScope(username, array);

	}

	/**
	 * <p>
	 * Revoke an access token - that is, remove it from the cache of tokens.
	 * </p>
	 *
	 * @param token The token to be removed from the cache
	 */
	public void revokeToken(final Token token) {
		this.tokens.remove(token.getAthlete().getEmail());
	}

	/**
	 * <p>
	 * Place a token in the cache
	 * </p>
	 *
	 * @param token The token to be stored in the cache.
	 * @throws IllegalArgumentException If the token is null, or the athlete contained in it is null or has a null email, or there are no authorisation scopes, then
	 */
	public void storeToken(final Token token) {
		String username = null;
		if (token == null) {
			throw new IllegalArgumentException(Messages.string("TokenManager.0")); //$NON-NLS-1$
		}

		if (token.getAthlete() == null) {
			throw new IllegalArgumentException(Messages.string("TokenManager.1")); //$NON-NLS-1$
		}
		if (token.getAthlete().getEmail() == null) {
			throw new IllegalArgumentException(Messages.string("TokenManager.2")); //$NON-NLS-1$
		}
		if (token.getScopes() == null) {
			throw new IllegalArgumentException(Messages.string("TokenManager.3")); //$NON-NLS-1$
		}
		username = token.getAthlete().getEmail();
		this.tokens.put(username, token);
	}

}
