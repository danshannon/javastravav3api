package stravajava.api.v3.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stravajava.api.v3.auth.model.Token;
import stravajava.api.v3.auth.ref.AuthorisationScope;

/**
 * <p>Manages the cache of tokens
 * 
 * @author Dan Shannon
 *
 */
public class TokenManager {
	private static TokenManager implementation = new TokenManager();
	private Map<String,Token> tokens;
	
	private TokenManager() {
		// Initialise as a singleton
		this.tokens = new HashMap<String,Token>();
	}
	
	public static TokenManager implementation() {
		return implementation;
	}
	
	public void storeToken(Token token) {
		String username = null;
		if (token == null) {
			throw new IllegalArgumentException("Cannot store null token");
		}
		
		if (token.getAthlete() == null) {
			throw new IllegalArgumentException("Cannot store a token if it has no athlete");
		}
		if (token.getAthlete().getEmail() == null) {
			throw new IllegalArgumentException("Cannot store a token if the athlete has no email");
		}
		if (token.getScopes() == null) {
			throw new IllegalArgumentException("Cannot store a token with <null> scopes");
		}
		username = token.getAthlete().getEmail();
		this.tokens.put(username,token);
	}
	
	/**
	 * <p>Retrieve a token which has <strong>at least</strong> the given set of scopes.</p>
	 * @param username The username
	 * @param scopes The list of scopes which are required to be in the token
	 * @return The token, or <code>null</code> if there is no cached token, or the cached token doesn't have all the required scopes
	 */
	public Token retrieveTokenWithScope(String username, AuthorisationScope... scopes) {
		// Get the token from cache
		Token token = tokens.get(username);
		
		// If scopes = null
		if (scopes == null) {
			scopes = new AuthorisationScope[0];
		}
		
		// If there's no cached token, or it doesn't have any scopes (which shouldn't happen) then return null
		if (token == null || token.getScopes() == null) { 
			return null;
		}
		
		// Check that all the required scopes are in the token
		for (AuthorisationScope scope : scopes) {
			if (!token.getScopes().contains(scope)) {
				return null;
			}
		}
		return token;
	}
	
	/**
	 * <p>Retrieve a cached token which has <strong>exactly</strong> the given set of scopes</p>
	 * @param username The username
	 * @param requiredScopes This list of scopes which must match the scopes of the token
	 * @return The token with the matching list of scopes, or <code>null</code> if there is no such token
	 */
	public Token retrieveTokenWithExactScope(String username, AuthorisationScope... requiredScopes) {
		// Get the token from the cache
		Token token = tokens.get(username);
		
		// If there's no such token, or it has no scopes, then return null
		if (token == null || token.getScopes() == null) {
			return null;
		}
		
		// Check that all the required scopes are in the token
		for (AuthorisationScope scope : requiredScopes) { 
			if (!token.getScopes().contains(scope)) {
				return null;
			}
		}
		
		// Check that all the scopes in the token are required
		for (AuthorisationScope scope : token.getScopes()) {
			boolean match = false;
			for (AuthorisationScope requiredScope : requiredScopes) {
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
	
	public void revokeToken(Token token) {
		this.tokens.remove(token.getAthlete().getEmail());
	}

	/**
	 * @param username
	 * @param allScopes
	 * @return
	 */
	public Token retrieveTokenWithExactScope(String username, List<AuthorisationScope> allScopes) {
		if (allScopes == null) { return retrieveTokenWithExactScope(username, new AuthorisationScope[]{}); }
		AuthorisationScope[] array = new AuthorisationScope[allScopes.size()];
		for (int i = 0; i < allScopes.size(); i++) {
			array[i] = allScopes.get(i);
		}
		return retrieveTokenWithExactScope(username, array);
	}

	/**
	 * @param email
	 * @param noScope
	 */
	public Token retrieveTokenWithScope(String username, List<AuthorisationScope> scope) {
		
		if (scope == null) { return retrieveTokenWithScope(username, new AuthorisationScope[]{}); }
		AuthorisationScope[] array = new AuthorisationScope[scope.size()];
		for (int i = 0; i < scope.size(); i++) {
			array[i] = scope.get(i);
		}
		return retrieveTokenWithExactScope(username, array);
		
	}
	
	public void clearTokenCache() {
		for (Token token : tokens.values()) {
			revokeToken(token);
		}
	}
	
}
