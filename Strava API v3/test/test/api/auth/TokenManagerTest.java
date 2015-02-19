package test.api.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaAthlete;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class TokenManagerTest {
	@Test
	public void testStoreToken_normal() {
		Token token = getValidToken();
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		tokenManager.storeToken(token);
		Token retrieved = tokenManager.retrieveTokenWithScope(token.getAthlete().getEmail());
		assertEquals(token, retrieved);
	}

	@Test
	public void testStoreToken_nullToken() {
		Token token = null;
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		try {
			tokenManager.storeToken(token);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Successfully saved a null token, that shouldn't work!");
	}

	@Test
	public void testStoreToken_nullScopes() {
		Token token = getValidToken();
		token.setScopes(null);
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		try {
			tokenManager.storeToken(token);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Stored a null token which had null scopes");
	}

	@Test
	public void testStoreToken_noAthleteInToken() {
		Token token = getValidToken();
		token.setAthlete(null);
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		try {
			tokenManager.storeToken(token);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Stored a null token which had no athlete");

	}

	@Test
	public void testStoreToken_noAthleteEmailInToken() {
		Token token = getValidToken();
		token.getAthlete().setEmail(null);
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		try {
			tokenManager.storeToken(token);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Stored a null token which had no athlete email");
	}

	@Test
	public void testRetrieveTokenWithScope_normal() {
		Token token = getValidToken();
		TokenManager tokenManager = TokenManager.implementation();
		token.setScopes(getAllScopes());
		tokenManager.clearTokenCache();
		tokenManager.storeToken(token);

		String username = token.getAthlete().getEmail();
		Token retrieved = tokenManager.retrieveTokenWithScope(username, AuthorisationScope.WRITE);
		assertEquals(token, retrieved);
	}

	@Test
	public void testRetrieveTokenWithScope_nullUsername() {
		Token token = getValidToken();
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		tokenManager.storeToken(token);

		String username = null;
		Token retrieved = tokenManager.retrieveTokenWithScope(username, AuthorisationScope.WRITE);
		assertNull(retrieved);
	}

	@Test
	public void testRetrieveTokenWithScope_nullScopes() {
		Token token = getValidToken();
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		tokenManager.storeToken(token);

		String username = token.getAthlete().getEmail();
		Token retrieved = tokenManager.retrieveTokenWithScope(username, (AuthorisationScope[]) null);
		assertEquals(token, retrieved);
	}

	@Test
	public void testRetrieveTokenWithExactScope_normal() {
		Token token = getValidToken();
		token.setScopes(getAllScopes());
		TokenManager tokenManager = TokenManager.implementation();
		tokenManager.clearTokenCache();
		tokenManager.storeToken(token);

		String username = token.getAthlete().getEmail();
		Token retrieved = tokenManager.retrieveTokenWithExactScope(username, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
		assertEquals(token, retrieved);

	}

	@Test
	public void testRetrieveTokenWithExactScope_tokenHasTooLittleScope() {
		Token token = getValidToken();
		token.setScopes(getPrivateScope());
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.storeToken(token);

		String username = token.getAthlete().getEmail();
		Token retrieved = manager.retrieveTokenWithExactScope(username, getAllScopes());
		assertNull(retrieved);
	}

	/**
	 * @return
	 */
	private Token getValidToken() {
		Token token = new Token();
		token.setAthlete(new StravaAthlete());
		token.getAthlete().setEmail("a@example.com");
		token.setScopes(getNoScope());
		return token;
	}

	private List<AuthorisationScope> getAllScopes() {
		return Arrays.asList(new AuthorisationScope[] { AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE });
	}

	private List<AuthorisationScope> getPrivateScope() {
		return Arrays.asList(new AuthorisationScope[] { AuthorisationScope.VIEW_PRIVATE });
	}

	private List<AuthorisationScope> getNoScope() {
		return Arrays.asList(new AuthorisationScope[] {});
	}

	@Test
	public void testRetrieveTokenWithExactScope_tokenHasTooMuchScope() {
		Token token = getValidToken();
		token.setScopes(getAllScopes());
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.storeToken(token);

		String username = token.getAthlete().getEmail();
		Token retrieved = manager.retrieveTokenWithExactScope(username, getPrivateScope());
		assertNull(retrieved);
	}

	@Test
	public void testRetrieveTokenWithExactScope_nullUsername() {
		Token token = getValidToken();
		token.setScopes(getPrivateScope());
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.storeToken(token);

		String username = null;
		Token retrieved = manager.retrieveTokenWithExactScope(username, getPrivateScope());
		assertNull(retrieved);
	}

	@Test
	public void testRetrieveTokenWithExactScope_nullScope() {
		Token token = getValidToken();
		token.setScopes(getPrivateScope());
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.storeToken(token);

		String username = token.getAthlete().getEmail();
		List<AuthorisationScope> listOfScopes = null;
		Token retrieved = manager.retrieveTokenWithExactScope(username, listOfScopes);
		assertNull(retrieved);
	}

	@Test
	public void testRetrieveTokenWithExactScope_noTokenRetrieved() {
		String username = "b@example.com";
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		Token token = manager.retrieveTokenWithExactScope(username, getAllScopes());
		assertNull(token);
	}

	@Test
	public void testRemoveToken_validToken() {
		Token token = getValidToken();
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.storeToken(token);
		manager.revokeToken(token);
		manager.retrieveTokenWithScope(token.getAthlete().getEmail(), getNoScope());
	}

	@Test
	public void testRemoveToken_tokenNotInCache() {
		Token token = getValidToken();
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();

		manager.revokeToken(token);
	}

	@Test
	public void testRemoveToken_nullToken() {
		Token token = getValidToken();
		TokenManager manager = TokenManager.implementation();
		manager.clearTokenCache();
		manager.revokeToken(token);
	}

}
