package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.message.BasicNameValuePair;
import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.impl.retrofit.AuthorisationServicesImpl;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.service.Strava;

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
	 * @throws IOException 
	 */
	@Test
	public void fullLoginTest() throws IOException {
		// Get a service implementation
		AuthorisationServices service = new AuthorisationServicesImpl();
		TestHttpUtils httpUtils = new TestHttpUtils();
		
		// Get the login page and find the authenticity token that Strava cunningly hides in there :)
		String authenticityToken = httpUtils.getLoginAuthenticityToken();
		assertNotNull("Strava login page didn't seem to hand out an authenticity_token",authenticityToken);
		
		// Log in - success should send a redirect to the dashboard
		String location = httpUtils.login(USERNAME, PASSWORD, authenticityToken);
		assertEquals("Login failed",location,Strava.AUTH_ENDPOINT + "/dashboard");
		BasicNameValuePair[] params = null;
		httpUtils.get(location, params);
		
		// Get the auth page
		authenticityToken = httpUtils.getAuthorisationPage(STRAVA_APPLICATION_ID,AuthorisationResponseType.CODE,"http://localhost",AuthorisationApprovalPrompt.FORCE);
		
		// Post an approval to the request
		String code = httpUtils.acceptApplication(STRAVA_APPLICATION_ID, "http://localhost", AuthorisationResponseType.CODE, authenticityToken);
		
		// Perform the token exchange
		TokenResponse tokenResponse = service.tokenExchange(STRAVA_APPLICATION_ID, CLIENT_SECRET, code);
		assertNotNull("Token not successfully returned by Strava",tokenResponse);

		// TODO Use the token to get something useful that proves it's working
		fail("Not yet implemented");
	}
}
