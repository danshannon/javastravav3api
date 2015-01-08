package test;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.reference.ActivityType;

/**
 * @author Dan Shannon
 *
 */
public class TestUtils {
	public static String USERNAME;
	public static String PASSWORD;
	
	public static TestHttpUtils HTTP_UTILS;
	
	public static Integer STRAVA_APPLICATION_ID;
	public static String STRAVA_CLIENT_SECRET;

//	public static String VALID_TOKEN; 
	public static String INVALID_TOKEN;
//	public static String VALID_TOKEN_WITHOUT_WRITE_ACCESS;
	public static Integer ACTIVITY_WITH_EFFORTS;
	public static Integer ACTIVITY_WITH_PHOTOS;
	public static Integer ACTIVITY_WITHOUT_PHOTOS;
	public static Integer ACTIVITY_FOR_AUTHENTICATED_USER;
	public static Integer ACTIVITY_FOR_UNAUTHENTICATED_USER;
	public static Integer ACTIVITY_INVALID;
	public static Integer ACTIVITY_WITH_COMMENTS;
	public static Integer ACTIVITY_WITHOUT_COMMENTS;
	public static Integer ACTIVITY_WITH_KUDOS;
	public static Integer ACTIVITY_WITHOUT_KUDOS;
	public static Integer ACTIVITY_WITH_LAPS;
	public static Integer ACTIVITY_WITHOUT_LAPS;
	public static Integer ACTIVITY_WITH_ZONES;
	public static Integer ACTIVITY_WITHOUT_ZONES;
	public static Activity ACTIVITY_DEFAULT_FOR_CREATE;
	private static final String PROPERTIES_FILE = "test-config.properties";
	
	static {
		Properties properties;
		try {
			properties = loadPropertiesFile(PROPERTIES_FILE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		HTTP_UTILS = new TestHttpUtils();
		USERNAME = properties.getProperty("username");
		PASSWORD = properties.getProperty("password");
		STRAVA_APPLICATION_ID = new Integer(properties.getProperty("strava_application_id"));
		STRAVA_CLIENT_SECRET = properties.getProperty("client_secret");
//		VALID_TOKEN = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
//		VALID_TOKEN_WITHOUT_WRITE_ACCESS = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD);
		
		INVALID_TOKEN = properties.getProperty("test.activityServicesImplTest.invalidToken");
		ACTIVITY_WITH_EFFORTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithEfforts"));
		ACTIVITY_WITH_PHOTOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithPhotos"));
		ACTIVITY_WITHOUT_PHOTOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutPhotos"));
		ACTIVITY_WITH_COMMENTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithComments"));
		ACTIVITY_WITHOUT_COMMENTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutComments"));
		ACTIVITY_WITH_KUDOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithKudos"));
		ACTIVITY_WITHOUT_KUDOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutKudos"));
		ACTIVITY_WITH_LAPS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithLaps"));
		ACTIVITY_WITHOUT_LAPS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutLaps"));
		ACTIVITY_WITH_ZONES = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithZones"));
		ACTIVITY_WITHOUT_ZONES = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutZones"));
		ACTIVITY_FOR_AUTHENTICATED_USER = new Integer(properties.getProperty("test.activityServicesImplTest.activityBelongingToAuthenticatedUser"));
		ACTIVITY_FOR_UNAUTHENTICATED_USER = new Integer(properties.getProperty("test.activityServicesImplTest.activityBelongingToUnauthenticatedUser"));
		ACTIVITY_INVALID = new Integer(properties.getProperty("test.activityServicesImplTest.activityInvalid"));
		ACTIVITY_DEFAULT_FOR_CREATE = TestUtils.createDefaultActivityForCreation();
	}

	/**
	 * @return
	 */
	private static Activity createDefaultActivityForCreation() {
		Activity activity = new Activity();
		activity.setName("TO BE DELETED");
		activity.setType(ActivityType.RIDE);
		activity.setStartDateLocal(new Date());
		activity.setElapsedTime(1000);
		activity.setDescription("Created by Strava API v3 Java");
		activity.setDistance(1000.1F);
		return activity;
	}

	/**
	 * @param propertiesFile
	 * @return
	 */
	public static Properties loadPropertiesFile(String propertiesFile) throws IOException {
		Properties properties = new Properties();
		URL url = ActivityServicesImplTest.class.getClassLoader().getResource(PROPERTIES_FILE); 
			properties.load(url.openStream());
		return properties;
	}
	
	public static String getValidToken() {
		return HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
	}
	
	public static String getValidTokenWithoutWriteAccess() {
		return HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD);
	}
}
