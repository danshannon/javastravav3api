package test;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.danshannon.strava.api.auth.TokenServices;
import com.danshannon.strava.api.auth.impl.retrofit.TokenServicesImpl;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

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
	
	public static Integer ATHLETE_AUTHENTICATED_ID;
	public static Integer ATHLETE_VALID_ID;
	public static Integer ATHLETE_INVALID_ID;
	public static Integer ATHLETE_WITHOUT_KOMS;
	
	public static Integer CLUB_VALID_ID;
	public static Integer CLUB_INVALID_ID;
	public static Integer CLUB_PUBLIC_NON_MEMBER_ID;
	public static Integer CLUB_PRIVATE_MEMBER_ID;
	public static Integer CLUB_PRIVATE_NON_MEMBER_ID;
	
	private static final String PROPERTIES_FILE = "test-config.properties";
	private static Properties properties;
	
	static {
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
		ACTIVITY_DEFAULT_FOR_CREATE = createDefaultActivityForCreation();
		
		ATHLETE_AUTHENTICATED_ID = integerProperty("test.athleteServicesImplTest.authenticatedAthleteId");
		ATHLETE_VALID_ID = integerProperty("test.athleteServicesImplTest.athleteId");
		ATHLETE_INVALID_ID = integerProperty("test.athleteServicesImplTest.athleteInvalidId");
		ATHLETE_WITHOUT_KOMS = integerProperty("test.athleteServicesImplTest.athleteWithoutKOMs");
		
		CLUB_VALID_ID = integerProperty("test.clubServicesImplTest.clubId");
		CLUB_INVALID_ID = integerProperty("test.clubServicesImplTest.clubInvalidId");
		CLUB_PRIVATE_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubPrivateMemberId");
		CLUB_PRIVATE_NON_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubPrivateNonMemberId");
		CLUB_PUBLIC_NON_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubNonMemberId");
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
	 * @param key
	 * @return
	 */
	private static Integer integerProperty(String key) {
		return new Integer(properties.getProperty(key));
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
		try {
			return HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
		} catch (BadRequestException | UnauthorizedException e) {
			return null;
		}
	}
	
	public static String getValidTokenWithoutWriteAccess() {
		try {
			return HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD);
		} catch (BadRequestException | UnauthorizedException e) {
			return null;
		}
	}
	
	public static String getRevokedToken() throws UnauthorizedException {
		String token = getValidToken();
		TokenServices service = TokenServicesImpl.implementation(token);
		service.deauthorise(token);
		return token;
	}
}
