package test;

import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.impl.retrofit.AuthorisationServicesImpl;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;

public class AuthorisationServicesImplTest {
	private static String VALID_TOKEN; 
	private static String INVALID_TOKEN;
	private static String VALID_TOKEN_WITHOUT_WRITE_ACCESS;
	private static String USERNAME;
	private static String PASSWORD;
	private static Integer STRAVA_APPLICATION_ID;
	private static String CLIENT_SECRET;
	private static final String PROPERTIES_FILE = "test-config.properties";

	/**
	 * <p>Loads the properties from the test configuration file</p>
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = TestUtils.loadPropertiesFile(PROPERTIES_FILE);
		VALID_TOKEN = properties.getProperty("test.activityServicesImplTest.validToken");
		INVALID_TOKEN = properties.getProperty("test.activityServicesImplTest.invalidToken");
		USERNAME = properties.getProperty("username");
		PASSWORD = properties.getProperty("password");
		CLIENT_SECRET = properties.getProperty("client_secret");
		STRAVA_APPLICATION_ID = new Integer(properties.getProperty("strava_application_id"));
		VALID_TOKEN_WITHOUT_WRITE_ACCESS = properties.getProperty("test.activityServicesImplTest.validTokenWithoutWriteAccess");
	}
	
	/**
	 * <p>Simulate the full (successful) login and authorisation OAuth process</p>
	 */
	@Test
	public void fullLoginTest() {
		// Get a service implementation
		AuthorisationServices service = AuthorisationServicesImpl.implementation();
		
		// Log in
		service.login(USERNAME, PASSWORD);
		
		// Get the auth page
		service.requestAccess(STRAVA_APPLICATION_ID, "http://localhost", AuthorisationResponseType.CODE, AuthorisationApprovalPrompt.FORCE, null, null);
		
		// Post an approval to the request
		String code = service.acceptApplication(STRAVA_APPLICATION_ID, "http://localhost", AuthorisationResponseType.CODE);
		
		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		
		
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
}
