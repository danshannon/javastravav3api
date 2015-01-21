package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.impl.retrofit.AuthorisationServicesImpl;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

public class AuthorisationServicesImplTest {
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
	 * <p>Test getting a token with all valid settings and no scope</p>
	 * 
	 * <p>Should return a token successfully which can be used to get athlete public data</p>
	 * @throws IOException 
	 * @throws BadRequestException 
	 * @throws UnauthorizedException 
	 */
	@Test 
	public void testTokenExchange_noScope() throws IOException, BadRequestException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication();

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
	}
	
	/**
	 * <p>Test getting a token but with an invalid application identifier</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testTokenExchange_invalidClientId() throws IOException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication();

		// Perform the token exchange
		TokenResponse tokenResponse;
		try {
			tokenResponse = service.tokenExchange(0, TestUtils.STRAVA_CLIENT_SECRET, code);
		} catch (BadRequestException e) {
			// Expected behaviour
			return;
		}
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test getting a token but with an invalid client secret</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 * @throws BadRequestException 
	 */
	@Test
	public void testTokenExchange_invalidClientSecret() throws IOException, BadRequestException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication();

		// Perform the token exchange
		TokenResponse tokenResponse;
		try {
			tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, "", code);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test performing a token exchange with the wrong code (which is returned by Strava when access is granted)</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testTokenExchange_invalidCode() throws IOException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		@SuppressWarnings("unused")
		String code = HTTP_UTILITIES.approveApplication();

		// Perform the token exchange
		TokenResponse tokenResponse = null;
		try {
			tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, "oops wrong code!");
		} catch (BadRequestException e) {
			// Expected behaviour
			return;
		}
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for view_private scope</p>
	 * 
	 * <p>Should return a token successfully, this should grant access to private data for the authenticated athlete</p>
	 * 
	 * @throws IOException 
	 * @throws BadRequestException 
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testTokenExchange_viewPrivateScope() throws IOException, BadRequestException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(AuthorisationScope.VIEW_PRIVATE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for write access</p>
	 * 
	 * <p>Should return a token successfully, this token should grant write access to the authenticated user's data</p>
	 * 
	 * @throws IOException 
	 * @throws BadRequestException 
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testTokenExchange_writeScope() throws IOException, BadRequestException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(AuthorisationScope.WRITE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got write access
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for both view_private and write access</p>
	 * 
	 * <p>Should return a token successfully, this token should grant write access to the authenticated user</p>
	 * 
	 * @throws IOException 
	 * @throws BadRequestException 
	 * @throws UnauthorizedException If client secret is invalid
	 */
	@Test
	public void testTokenExchange_writeAndViewPrivateScope() throws IOException, BadRequestException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
	}

	/**
	 * <p>Test performing a token exchange which includes request for an invalid scope</p>
	 * 
	 * <p>Should not return a token successfully</p>
	 * @throws IOException 
	 * @throws UnauthorizedException If client secret is invalid
	 */
	@Test
	public void testTokenExchange_invalidScope() throws IOException, UnauthorizedException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(AuthorisationScope.UNKNOWN);

		// Perform the token exchange
		TokenResponse tokenResponse = null;
		try {
			tokenResponse = service.tokenExchange(TestUtils.STRAVA_APPLICATION_ID, TestUtils.STRAVA_CLIENT_SECRET, code);
		} catch (BadRequestException e) {
			// Expected behaviour
			return;
		}
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}

}
