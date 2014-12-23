package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.impl.retrofit.AuthorisationServicesImpl;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

public class AuthorisationServicesImplTest {
	private static String USERNAME;
	private static String PASSWORD;
	private static Integer STRAVA_APPLICATION_ID;
	private static String CLIENT_SECRET;
	private static String REDIRECT_URI = "http://localhost";
	private static TestHttpUtils HTTP_UTILITIES;
	private static final String PROPERTIES_FILE = "test-config.properties";

	/**
	 * <p>Loads the properties from the test configuration file</p>
	 * 
	 * @throws java.lang.Exception
	 * @throws UnauthorizedException Cannot log in successfully to Strava
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception, UnauthorizedException {
		// Get required properties from the properties file
		Properties properties = TestUtils.loadPropertiesFile(PROPERTIES_FILE);
		USERNAME = properties.getProperty("username");
		PASSWORD = properties.getProperty("password");
		CLIENT_SECRET = properties.getProperty("client_secret");
		STRAVA_APPLICATION_ID = new Integer(properties.getProperty("strava_application_id"));
		
		// Set up an HTTP utility session, this will maintain a single session / session cookies etc. for you
		HTTP_UTILITIES = new TestHttpUtils();
		HTTP_UTILITIES.loginToSession(USERNAME,PASSWORD);
	}
		
	/**
	 * <p>Test getting a token with all valid settings and no scope</p>
	 * 
	 * <p>Should return a token successfully which can be used to get athlete public data</p>
	 * @throws IOException 
	 */
	@Test 
	public void testTokenExchange_noScope() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test getting a token but with an invalid application identifier</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_invalidClientId() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(0,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(0, CLIENT_SECRET, code);
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test getting a token but with an invalid client secret</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_invalidClientSecret() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, "", code);
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test performing a token exchange with the wrong code (which is returned by Strava when access is granted)</p>
	 * 
	 * <p>Should fail to get a token at all</p>
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_invalidCode() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		@SuppressWarnings("unused")
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, "oops wrong code!");
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for view_private scope</p>
	 * 
	 * <p>Should return a token successfully, this should grant access to private data for the authenticated athlete</p>
	 * 
	 * TODO work out a test case that proves we've got access to private data?
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_viewPrivateScope() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE, AuthorisationScope.VIEW_PRIVATE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for write access</p>
	 * 
	 * <p>Should return a token successfully, this token should grant write access to the authenticated user's data</p>
	 * 
	 * TODO work out a test case that proves we've got write access
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_writeScope() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE, AuthorisationScope.WRITE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test performing a token exchange which includes request for both view_private and write access</p>
	 * 
	 * <p>Should return a token successfully, this token should grant write access to the authenticated user</p>
	 * 
	 * TODO Need test cases (as for testTokenExchange_writeScope() and testTokenExchange_viewPrivateScope())
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_writeAndViewPrivateScope() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Add a test case to prove we've got a public data view ONLY
		fail("Not yet implemented");
	}

	/**
	 * <p>Test performing a token exchange which includes request for an invalid scope</p>
	 * 
	 * <p>Should not return a token successfully</p>
	 * @throws IOException 
	 */
	@Test
	public void testTokenExchange_invalidScope() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();

		// Authorise
		String code = HTTP_UTILITIES.approveApplication(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,REDIRECT_URI,AuthorisationApprovalPrompt.FORCE, AuthorisationScope.UNKNOWN);

		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNull("Token unexpectedly returned by Strava",tokenResponse);
	}

	/**
	 * <p>Test deauthorisation of a valid token</p>
	 * 
	 * <p>Should succeed; token should no longer be able to be used for access</p>
	 */
	@Test
	public void testDeauthorise_validToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test deauthorisation of an invalid (i.e. non-existent) token</p>
	 * 
	 * <p>Should fail</p>
	 */
	@Test
	public void testDeauthorise_invalidToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test behaviour when a token is deauthorised TWICE</p>
	 * 
	 * <p>Should fail the second time</p>
	 */
	@Test
	public void testDeauthorise_deauthorisedToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
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
