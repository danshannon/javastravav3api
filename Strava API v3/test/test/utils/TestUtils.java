package test.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.TokenServices;
import javastrava.api.v3.auth.impl.retrofit.TokenServicesImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import test.api.service.impl.retrofit.ActivityServicesImplTest;

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

	// public static String VALID_TOKEN;
	public static String INVALID_TOKEN;
	// public static String VALID_TOKEN_WITHOUT_WRITE_ACCESS;
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
	public static Integer ACTIVITY_PRIVATE_OTHER_USER;

	public static Integer ATHLETE_AUTHENTICATED_ID;
	public static Integer ATHLETE_VALID_ID;
	public static Integer ATHLETE_INVALID_ID;
	public static Integer ATHLETE_WITHOUT_KOMS;
	public static Integer ATHLETE_WITHOUT_FRIENDS;
	public static Integer ATHLETE_PRIVATE_ID;

	public static Integer CLUB_VALID_ID;
	public static Integer CLUB_INVALID_ID;
	public static Integer CLUB_PUBLIC_NON_MEMBER_ID;
	public static Integer CLUB_PRIVATE_MEMBER_ID;
	public static Integer CLUB_PRIVATE_NON_MEMBER_ID;

	public static String GEAR_VALID_ID;
	public static String GEAR_INVALID_ID;
	public static String GEAR_OTHER_ATHLETE_ID;

	public static Long SEGMENT_EFFORT_VALID_ID;
	public static Long SEGMENT_EFFORT_INVALID_ID;
	public static Long SEGMENT_EFFORT_PRIVATE_ID;
	public static Long SEGMENT_EFFORT_OTHER_USER_PRIVATE_ID;

	public static Integer SEGMENT_VALID_ID;
	public static Integer SEGMENT_INVALID_ID;
	public static Integer SEGMENT_PRIVATE_ID;
	public static Integer SEGMENT_OTHER_USER_PRIVATE_ID;

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
		// VALID_TOKEN = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
		// VALID_TOKEN_WITHOUT_WRITE_ACCESS = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD);

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
		ACTIVITY_PRIVATE_OTHER_USER = integerProperty("test.activityServicesImplTest.activityPrivateOtherUser");

		ATHLETE_AUTHENTICATED_ID = integerProperty("test.athleteServicesImplTest.authenticatedAthleteId");
		ATHLETE_VALID_ID = integerProperty("test.athleteServicesImplTest.athleteId");
		ATHLETE_INVALID_ID = integerProperty("test.athleteServicesImplTest.athleteInvalidId");
		ATHLETE_WITHOUT_KOMS = integerProperty("test.athleteServicesImplTest.athleteWithoutKOMs");
		ATHLETE_WITHOUT_FRIENDS = integerProperty("test.athleteServicesImplTest.athleteWithoutFriends");
		ATHLETE_PRIVATE_ID = integerProperty("test.athleteServicesImplTest.athletePrivate");

		CLUB_VALID_ID = integerProperty("test.clubServicesImplTest.clubId");
		CLUB_INVALID_ID = integerProperty("test.clubServicesImplTest.clubInvalidId");
		CLUB_PRIVATE_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubPrivateMemberId");
		CLUB_PRIVATE_NON_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubPrivateNonMemberId");
		CLUB_PUBLIC_NON_MEMBER_ID = integerProperty("test.clubServicesImplTest.clubNonMemberId");

		GEAR_VALID_ID = properties.getProperty("test.gearServicesImplTest.gearId");
		GEAR_INVALID_ID = properties.getProperty("test.gearServicesImplTest.gearInvalidId");
		GEAR_OTHER_ATHLETE_ID = properties.getProperty("test.gearServicesImplTest.gearOtherAthleteId");

		SEGMENT_EFFORT_INVALID_ID = longProperty("test.segmentEffortServicesImplTest.segmentEffortInvalidId");
		SEGMENT_EFFORT_VALID_ID = longProperty("test.segmentEffortServicesImplTest.segmentEffortId");
		SEGMENT_EFFORT_PRIVATE_ID = longProperty("test.segmentEffortServicesImplTest.segmentEffortPrivateId");
		SEGMENT_EFFORT_OTHER_USER_PRIVATE_ID = longProperty("test.segmentEffortServicesImplTest.segmentEffortOtherUserPrivateId");

		SEGMENT_VALID_ID = integerProperty("test.segmentServicesImplTest.segmentId");
		SEGMENT_INVALID_ID = integerProperty("test.segmentServicesImplTest.segmentInvalidId");
		SEGMENT_PRIVATE_ID = integerProperty("test.segmentServicesImplTest.segmentPrivateId");
		SEGMENT_OTHER_USER_PRIVATE_ID = integerProperty("test.segmentServicesImplTest.segmentOtherUserPrivateId");
	}

	/**
	 * @return
	 */
	public static StravaActivity createDefaultActivity() {
		StravaActivity activity = new StravaActivity();
		activity.setName("TO BE DELETED");
		activity.setType(StravaActivityType.RIDE);
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

	private static Long longProperty(String key) {
		return new Long(properties.getProperty(key));
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

	public static Token getValidTokenAsToken() {
		Token token = TokenManager.implementation().retrieveTokenWithScope(USERNAME, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
		if (token == null) {
			try {
				token = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
				TokenManager.implementation().storeToken(token);
			} catch (BadRequestException | UnauthorizedException e) {
				return null;
			}
		}
		return token;
	}

	public static String getValidToken() {
		return getValidTokenAsToken().getToken();
	}

	public static Token getValidTokenWithoutWriteAccessAsToken() {
		Token token = TokenManager.implementation().retrieveTokenWithExactScope(USERNAME);
		if (token == null) {
			try {
				token = HTTP_UTILS.getStravaAccessToken(USERNAME, PASSWORD);
				TokenManager.implementation().storeToken(token);
			} catch (BadRequestException | UnauthorizedException e) {
				return null;
			}
		}
		return token;
	}

	public static String getValidTokenWithoutWriteAccess() {
		return getValidTokenWithoutWriteAccessAsToken().getToken();
	}

	public static String getRevokedToken() throws UnauthorizedException {
		Token token = getValidTokenAsToken();
		TokenServices service = TokenServicesImpl.implementation(token.getToken());
		service.deauthorise(token);
		return token.getToken();
	}

}
