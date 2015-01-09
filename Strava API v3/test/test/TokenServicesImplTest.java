package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.TokenServices;
import com.danshannon.strava.api.auth.impl.retrofit.TokenServicesImpl;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.service.AthleteServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.AthleteServicesImpl;

public class TokenServicesImplTest {
	private static TestHttpUtils HTTP_UTILITIES;

	/**
	 * <p>Loads the properties from the test configuration file</p>
	 * 
	 * @throws java.lang.Exception
	 * @throws UnauthorizedException Cannot log in successfully to Strava
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception, UnauthorizedException {
		// Set up an HTTP utility session, this will maintain a single session / session cookies etc. for you
		HTTP_UTILITIES = TestUtils.HTTP_UTILS;
		HTTP_UTILITIES.loginToSession(TestUtils.USERNAME,TestUtils.PASSWORD);
	}
		
	/**
	 * <p>Test deauthorisation of a valid token</p>
	 * 
	 * <p>Should succeed; token should no longer be able to be used for access</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testDeauthorise_validToken() throws UnauthorizedException {
		// 1. Get a deauthorised token
		String token = TestUtils.getRevokedToken();
		
		// 2. Attempt to use the token to get a service implementation
		AthleteServices athleteServices = null;
		try {
			athleteServices = AthleteServicesImpl.implementation(token);
		} catch (UnauthorizedException e) {
			// This is expected behaviour
		}
		
		// 3. We should NOT get a service implementation
		assertNull("Got a service implementation despite successfully deauthorising the token",athleteServices);
	}
	
	/**
	 * <p>Test deauthorisation of an invalid (i.e. non-existent) token</p>
	 * 
	 * <p>Should fail</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testDeauthorise_invalidToken() throws UnauthorizedException {
		// 1. Get a valid token
		String token = TestUtils.getValidToken();
		
		// 2. Get a service implementation for the valid token
		TokenServices service = TokenServicesImpl.implementation(token);
		
		// 3. Get an INVALID token
		String invalidToken = TestUtils.INVALID_TOKEN;
		
		// 4. Attempt to deauthorise the invalid token
		try {
		@SuppressWarnings("unused")
		TokenResponse response = service.deauthorise(invalidToken);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
	}
	
	/**
	 * <p>Test behaviour when a token is deauthorised TWICE</p>
	 * 
	 * <p>Should fail the second time</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testDeauthorise_deauthorisedToken() throws UnauthorizedException {
		// 1. Get a deauthorised token
		String token = TestUtils.getValidToken();
		
		// 2. Attempt to deauthorise it twice
		TokenServices service = TokenServicesImpl.implementation(token);
		@SuppressWarnings("unused")
		TokenResponse response = service.deauthorise(token);
		try {
			response = service.deauthorise(token);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
	}
	
	/**
	 * <p>Test behaviour when attempting to deauthorise another account's token</p>
	 * 
	 * <p>Should fail to deauthorise!</p>
	 */
	@Test
	public void testDeauthorise_otherUsersToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

}
