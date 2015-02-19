package test.api.auth.impl.retrofit;

import static org.junit.Assert.fail;
import javastrava.api.v3.auth.TokenServices;
import javastrava.api.v3.auth.impl.retrofit.TokenServicesImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.service.AthleteServices;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.AthleteServicesImpl;

import org.junit.BeforeClass;
import org.junit.Test;

import test.utils.TestHttpUtils;
import test.utils.TestUtils;

public class TokenServicesImplTest {
	private static TestHttpUtils HTTP_UTILITIES;

	/**
	 * <p>
	 * Loads the properties from the test configuration file
	 * </p>
	 * 
	 * @throws java.lang.Exception
	 * @throws UnauthorizedException
	 *             Cannot log in successfully to Strava
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception, UnauthorizedException {
		// Set up an HTTP utility session, this will maintain a single session / session cookies etc. for you
		HTTP_UTILITIES = TestUtils.HTTP_UTILS;
		HTTP_UTILITIES.loginToSession(TestUtils.USERNAME, TestUtils.PASSWORD);
	}

	/**
	 * <p>
	 * Test deauthorisation of a valid token
	 * </p>
	 * 
	 * <p>
	 * Should succeed; token should no longer be able to be used for access
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testDeauthorise_validToken() throws UnauthorizedException {
		// 1. Get a deauthorised token
		String token = TestUtils.getRevokedToken();

		// 2. Attempt to use the token to get a service implementation
		AthleteServices athleteServices = null;
		athleteServices = AthleteServicesImpl.implementation(token);
		try {
			athleteServices.getAuthenticatedAthlete();
		} catch (UnauthorizedException e) {
			// This is expected behaviour
			return;
		}

		// 3. We should NOT get a service implementation
		fail("Got a usable service implementation despite successfully deauthorising the token");
	}

	/**
	 * <p>
	 * Test deauthorisation of an invalid (i.e. non-existent) token
	 * </p>
	 * 
	 * <p>
	 * Should fail
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testDeauthorise_invalidToken() throws UnauthorizedException {
		// 1. Get a valid token
		Token token = TestUtils.getValidTokenAsToken();

		// 2. Get a service implementation for the valid token
		TokenServices service = TokenServicesImpl.implementation(token.getToken());

		// 3. Get an INVALID token
		token.setToken(TestUtils.INVALID_TOKEN);

		// 4. Attempt to deauthorise the invalid token
		try {
			service.deauthorise(token);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
	}

	/**
	 * <p>
	 * Test behaviour when a token is deauthorised TWICE
	 * </p>
	 * 
	 * <p>
	 * Should fail the second time
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testDeauthorise_deauthorisedToken() throws UnauthorizedException {
		// 1. Get a deauthorised token
		Token token = TestUtils.getValidTokenAsToken();

		// 2. Attempt to deauthorise it twice
		TokenServices service = TokenServicesImpl.implementation(token.getToken());
		@SuppressWarnings("unused")
		TokenResponse response = service.deauthorise(token);
		try {
			response = service.deauthorise(token);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
	}
}
