package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.ActivityServices;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.ActivityServicesImpl;

/**
 * <p>Unit tests for {@link ActivityServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class ActivityServicesImplTest {
	private static TestHttpUtils HTTP_UTILS;
	private static String USERNAME;
	private static String PASSWORD;
	private static Integer STRAVA_APPLICATION_ID;
	private static String STRAVA_CLIENT_SECRET;
	
	private static String VALID_TOKEN; 
	private static String INVALID_TOKEN;
	private static String VALID_TOKEN_WITHOUT_WRITE_ACCESS;
	private static Integer ACTIVITY_WITH_EFFORTS;
	private static Integer ACTIVITY_WITH_PHOTOS;
	private static Integer ACTIVITY_WITHOUT_PHOTOS;
	private static Integer ACTIVITY_FOR_AUTHENTICATED_USER;
	private static Integer ACTIVITY_FOR_UNAUTHENTICATED_USER;
	private static Integer ACTIVITY_INVALID;
	private static Integer ACTIVITY_WITH_COMMENTS;
	private static Integer ACTIVITY_WITHOUT_COMMENTS;
	private static Integer ACTIVITY_WITH_KUDOS;
	private static Integer ACTIVITY_WITHOUT_KUDOS;
	private static Integer ACTIVITY_WITH_LAPS;
	private static Integer ACTIVITY_WITHOUT_LAPS;
	private static Integer ACTIVITY_WITH_ZONES;
	private static Integer ACTIVITY_WITHOUT_ZONES;
	private static Activity ACTIVITY_DEFAULT_FOR_CREATE;
	private static final String PROPERTIES_FILE = "test-config.properties";

	/**
	 * <p>Loads the properties from the test configuration file</p>
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// TODO Encapsulate all the security crap inside the TestHttpUtils
		HTTP_UTILS = new TestHttpUtils();
		VALID_TOKEN = HTTP_UTILS.getStravaAccessToken(STRAVA_APPLICATION_ID, STRAVA_CLIENT_SECRET, USERNAME, PASSWORD, AuthorisationScope.VIEW_PRIVATE, AuthorisationScope.WRITE);
		VALID_TOKEN_WITHOUT_WRITE_ACCESS = HTTP_UTILS.getStravaAccessToken(STRAVA_APPLICATION_ID, STRAVA_CLIENT_SECRET, USERNAME, PASSWORD);
		
		Properties properties = TestUtils.loadPropertiesFile(PROPERTIES_FILE);
		VALID_TOKEN = properties.getProperty("test.activityServicesImplTest.validToken");
		INVALID_TOKEN = properties.getProperty("test.activityServicesImplTest.invalidToken");
		VALID_TOKEN_WITHOUT_WRITE_ACCESS = properties.getProperty("test.activityServicesImplTest.validTokenWithoutWriteAccess");
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
	 * <p>Test we get a {@link ActivityServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link ActivityServicesImpl service implementation} if the token isn't valid</p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		ActivityServices service = null;
		try {
			service = ActivityServicesImpl.implementation(INVALID_TOKEN);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
		assertNull("Got a service for an invalid token!",service);
	}

	/**
	 * <p>Test that we don't get a {@link ActivityServicesImpl service implementation} if the token has been revoked by the user</p>
	 */
	@Test
	public void testImplementation_revokedToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test that when we ask for a {@link ActivityServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		ActivityServices service2 = ActivityServicesImpl.implementation(VALID_TOKEN);
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link ActivityServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test retrieval of a known {@link Activity}, complete with all {@link SegmentEffort efforts}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityWithEfforts() throws UnauthorizedException  {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_WITH_EFFORTS, Boolean.TRUE);

		assertNotNull("Returned null Activity for known activity with id " + ACTIVITY_WITH_EFFORTS,activity);
		assertNotNull("Activity " + ACTIVITY_WITH_EFFORTS + " was returned but segmentEfforts is null", activity.getSegmentEfforts());
		assertNotEquals("Activity " + ACTIVITY_WITH_EFFORTS + " was returned but segmentEfforts is empty",0,activity.getSegmentEfforts().size());
	}
	
	/**
	 * <p>Test retrieval of a known {@link Activity} that belongs to the authenticated user; it should be a detailed {@link ResourceState representation}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityBelongsToAuthenticatedUser() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_FOR_AUTHENTICATED_USER, Boolean.FALSE);
		
		assertNotNull("Returned null Activity for known activity with id " + ACTIVITY_FOR_AUTHENTICATED_USER,activity);
		assertEquals("Returned activity is not a detailed representation as expected - " + activity.getResourceState(),ResourceState.DETAILED,activity.getResourceState());
	}

	/**
	 * <p>Test retrieval of a known {@link Activity} that DOES NOT belong to the authenticated user; it should be a summary {@link ResourceState representation}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityBelongsToUnauthenticatedUser() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_FOR_UNAUTHENTICATED_USER, Boolean.FALSE);
		
		assertNotNull("Returned null Activity for known activity with id " + ACTIVITY_FOR_UNAUTHENTICATED_USER,activity);
		assertEquals("Returned activity is not a summary representation as expected - " + activity.getResourceState(), ResourceState.SUMMARY, activity.getResourceState());
	}
	/**
	 * <p>Test retrieval of a known {@link Activity}, without the efforts being returned (i.e. includeAllEfforts = false)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityWithoutEfforts() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_WITH_EFFORTS, Boolean.FALSE);

		assertNotNull("Returned null Activity for known activity with id " + ACTIVITY_WITH_EFFORTS,activity);
		assertNotNull("Returned null segment efforts for known activity, when they were expected", activity.getSegmentEfforts());
		assertEquals("Returned segment efforts despite asking not to for activity with id " + ACTIVITY_WITH_EFFORTS, 0, activity.getSegmentEfforts().size());
	}
	
	/**
	 * <p>Test retrieval of a non-existent {@link Activity}</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_unknownActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_INVALID, Boolean.FALSE);
		
		assertNull("Got an activity for an invalid activity id " + ACTIVITY_INVALID,activity);
	}
	
	/**
	 * <p>Default test to list {@link Activity activities} for the currently authenticated athlete (i.e. the one who corresponds to the current token)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_default() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] activities = service.listAuthenticatedAthleteActivities(null, null, null, null);
		
		assertNotNull("Authenticated athlete's activities returned as null",activities);
		assertNotEquals("No activities returned for the authenticated athlete",0,activities.length);
	}
	
	/**
	 * <p>Test listing of {@link Activity activities} before a given time (i.e. the before parameter, tested in isolation)</p>
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	
	/**
	 * <p>Test listing of {@link Activity activities} after a given time (i.e. the after parameter, tested in isolation)</p>
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_afterActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * <p>Test listing of {@link Activity activities} between two given times (i.e. before and after parameters in combination)</p>
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterCombination() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * <p>Test listing of {@link Activity activities} between two given times (i.e. before and after parameters in combination)
	 * BUT WITH AN INVALID COMBINATION OF BEFORE AND AFTER</p>
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterInvalidCombination() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * <p>Test paging (page size only)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] activities = service.listAuthenticatedAthleteActivities(null, null, null, 1);
		
		assertNotNull("Authenticated athlete's activities returned as null when asking for a page of size 1",activities);
		assertEquals("Wrong number of activities returned when asking for a page of size 1",1,activities.length);
		
		// TODO Test maximum page size (test at max, and at max+1)
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 activities from the service, then ask for the first page only and check that it's the same as the first activity, then ask for the second page and check that it's the same as the second activity</p>
	 *  
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pageNumberAndSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] defaultActivities = service.listAuthenticatedAthleteActivities(null, null, null, 2);

		assertEquals("Default page of activities should be of size 2",2,defaultActivities.length);

		Activity[] firstPageOfActivities = service.listAuthenticatedAthleteActivities(null, null, 1, 1);
		
		assertEquals("First page of activities should be of size 1",1,firstPageOfActivities.length);
		assertEquals("Different first page of activities to expected", defaultActivities[0].getId(),firstPageOfActivities[0].getId());

		Activity[] secondPageOfActivities = service.listAuthenticatedAthleteActivities(null, null, 2, 1);
		
		assertEquals("Second page of activities should be of size 1",1,firstPageOfActivities.length);
		assertEquals("Different second page of activities to expected", defaultActivities[1].getId(),secondPageOfActivities[0].getId());
		
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too high)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		// Ask for the 200,000th activity by the athlete (this is probably safe!)
		Activity[] activities = service.listAuthenticatedAthleteActivities(null, null, 1000, 200);
		
		assertEquals("Unexpected return of activities for paging out of range (high)",0,activities.length);
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too low)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		
		// Ask for the 0th activity by the athlete (this is probably safe!)
		try {
			service.listAuthenticatedAthleteActivities(null, null, 0, 0);
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		
		fail("Unexpected return of activities for paging out of range (low)");
	}
	
	/**
	 * <p>List {@link Photo photos}, with an {@link Activity activity} that has a known non-zero number of photos</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityPhotos_default() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_WITH_PHOTOS);

		assertNotNull("Null list of photos returned for activity",photos);
		assertNotEquals("No photos returned although some were expected",0,photos.length);
	}
	
	/**
	 * <p>Attempt to list {@link Photo photos} for a non-existent {@link Activity activity}</p>
	 * 
	 * <p>Should return <code>null</code> because the {@link Activity} doesn't exist</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityPhotos_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_INVALID);

		assertNull("Photos returned for an invalid activity",photos);
	}
	
	/**
	 * <p>List {@link Photo photos}, for an {@link Activity activity} that has no photos</p>
	 * 
	 * <p>Should return an empty array</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityPhotos_hasNoPhotos() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_WITHOUT_PHOTOS);

		assertNotNull("Photos returned as null for a valid activity without photos",photos);
		assertEquals("Photos were returned for an activity which has no photos",0,photos.length);
	}
	
	/**
	 * <p>Attempt to create a valid manual {@link Activity} for the user associated with the security token</p>
	 * 
	 * <p>Should successfully create the activity, and the activity should be retrievable immediately and identical to the one used to create</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testCreateManualActivity_validActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.createManualActivity(ACTIVITY_DEFAULT_FOR_CREATE);
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	/**
	 * <p>Attempt to create a valid manual {@link Activity} for the user associated with the security token, where the user has NOT granted write access via the OAuth process</p>
	 * 
	 * <p>Should fail to create the activity and throw an {@link UnauthorizedException}, which is trapped in the test because it it expected</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testCreateManualActivity_accessTokenDoesNotHaveWriteAccess() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN_WITHOUT_WRITE_ACCESS);
		Activity activity = null;
		try {
			activity = service.createManualActivity(ACTIVITY_DEFAULT_FOR_CREATE);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour - creation has failed because there's no write access
			return;
		}

		// This is the unexpected behaviour - if we get here, then we've managed to create the activity! So delete it again (if possible)
		try {
			service.deleteActivity(activity.getId());
		} catch (NotFoundException e) {
			// Don't worry, there's not really any more we can do at this point
		}
		fail("Created a manual activity but should have failed and thrown an UnauthorizedException!");
	}

	/**
	 * <p>Attempt to create a duplicate manual {@link Activity} for the user</p>
	 * 
	 * TODO Determine correct behaviour based on what the API actually does in this circumstance
	 */
	@Test
	public void testCreateManualActivity_duplicateActivity() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	/**
	 * <p>Attempt to create an incomplete manual {@link Activity} for the user where not all required attributes are set</p>
	 * 
	 * <p>Should fail to create the activity in each case where a required attribute is missing</p>
	 * 
	 * TODO Determine list of required attributes, create a test for each one</p>
	 */
	@Test
	public void testCreateManualActivity_incompleteActivityDetails() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	/**
	 * <p>Attempt to create an {@link Activity} which cannot be successfully stored because it has attributes set which cannot be saved via the Strava API</p>
	 * 
	 * <p>Should fail to create the activity</p>
	 */
	@Test
	public void testCreateManualActivity_tooManyActivityAttributes() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	/**
	 * <p>Attempt to delete an existing {@link Activity} for the user</p>
	 * 
	 * <p>In order to avoid deleting genuine data, this test creates the activity first, checks that it has been successfully written (i.e. that it can be read back from the API) and then deletes it again</p>
	 * 
	 * <p>Should successfully delete the activity; it should no longer be able to be retrieved via the API</p>
	 */
	@Test
	public void testDeleteActivity_validActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	/**
	 * <p>Attempt to create an {@link Activity} for the user, using a token which has not been granted write access through the OAuth process</p>
	 * 
	 * <p>Should fail to create the activity and throw an {@link UnauthorizedException}</p>
	 */
	@Test
	public void testDeleteActivity_accessTokenDoesNotHaveWriteAccess() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	/**
	 * <p>Attempt to delete an {@link Activity} which does not exist</p>
	 */
	@Test
	public void testDeleteActivity_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	/**
	 * <p>Attempt to delete an {@link Activity} which does not belong to the currently authenticated user</p>
	 * 
	 * <p>Should fail to delete the activity, which should still be able to be read via the API</p>
	 */
	@Test
	public void testDeleteActivity_unauthenticatedAthletesActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	/**
	 * <p>List {@link Comment comments} for a valid activity</p>
	 * 
	 * <p>Expectation is that at least one of the comments contains Markdown; this is tested by checking that at least one comment is different</p>
	 * 
	 * TODO Check handling of Markdown in comments properly!!
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_hasComments() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Comment[] comments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.TRUE, null, null);
		
		assertNotNull("Returned null list of comments (with markdown) when some were expected");
		assertNotEquals("Returned empty list of comments when some were expected", 0, comments.length);
		
		Comment[] commentsWithoutMarkdown = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, null, null);
		
		// Check that the lists are the same length!!
		assertNotNull("Returned null list of comments (without markdown) when some were expected");
		assertEquals("List of comments for activity " + ACTIVITY_WITH_COMMENTS + " is not same length with/without markdown!", comments.length, commentsWithoutMarkdown.length);
		
		// Check that at least one comment is different (i.e. because of the markdown)
		boolean difference = false;
		for (int i = 0 ; i < comments.length; i++) {
			if (!comments[i].equals(commentsWithoutMarkdown[i])) {
				difference = true;
			}
		}
		if (!difference) {
			fail("Comments without markdown are identical to comments with markdown, that's not right!");
		}
	}
	
	/**
	 * <p>List {@link Comment comments} for a valid activity which has no comments</p>
	 * 
	 * <p>Should return an empty array of comments</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_hasNoComments() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Comment[] comments = service.listActivityComments(ACTIVITY_WITHOUT_COMMENTS, Boolean.TRUE, null, null);

		assertNotNull("Returned null list of comments when an empty array was expected",comments);
		assertEquals("Returned a non-empty list of comments when none were expected", 0, comments.length);
	}
	
	@Test
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 comments from the service (using the default page with a page size of 2), then ask for the first page only with size 1 and check that it's the same as the first one in the previous list</p> 
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	public void testListActivityComments_pageNumberAndSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Comment[] defaultComments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, null, 2);
		
		assertEquals("Default page of comments should be of size 2",2,defaultComments.length);

		Comment[] firstPageOfComments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, 1, 1);
		
		assertEquals("First page of comments should be of size 1",1,firstPageOfComments.length);
		assertEquals("Different first page of comments to expected", defaultComments[0].getId(),firstPageOfComments[0].getId());

		Comment[] secondPageOfComments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, 2, 1);

		assertEquals("Second page of activities should be of size 1",1,firstPageOfComments.length);
		assertEquals("Different second page of comments to expected", defaultComments[1].getId(),secondPageOfComments[0].getId());
	}
	
	/**
	 * <p>Test page size parameter handling behaves as expected when listing {@link Comment comments} for an existing {@link Activity}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Comment[] comments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, null, 1);
		
		assertNotNull("Asked for one comment in a page, got null",comments);
		assertEquals("Asked for one comment in a page, got " + comments.length,1,comments.length);
	
		// TODO Test maximum page size (test at max, and at max+1)
		fail("Not yet implemented");

	}
	
	/**
	 * <p>Test pagination of {@link Comment comments} for parameters which are out of range - i.e. too high</p>
	 * 
	 * <p>Should return an empty array of {@link Comment comments}</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		// Attempt to get the 200,000th comment, that's probably out of range!
		Comment[] comments = service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, 1000, 200);
		
		assertNotNull("Comments should be returned as an empty array, got null",comments);
		assertEquals("Asked for out of range comments, expected an empty array, got " + comments.length + " comments unexpectedly", 0, comments.length);
	}
	
	/**
	 * <p>Test pagination of {@link Comment comments} for parameters which are out of range - i.e. too low</p>
	 * 
	 * <p>Should throw an {@link IllegalArgumentException} (which will be trapped and ignored by this test)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		try {
			service.listActivityComments(ACTIVITY_WITH_COMMENTS, Boolean.FALSE, 0, 0);
		} catch (IllegalArgumentException e) {
			// Expected behaviour!
			return;
		}
		
		fail("Paging of comments for out-of-range (low) parameters should have failed, but didn't!");
	}
	
	/**
	 * <p>Attempt to list {@link Comment comments} for a non-existent {@link Activity}</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityComments_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		Comment[] comments = service.listActivityComments(ACTIVITY_WITHOUT_COMMENTS, Boolean.FALSE, null, null);
		
		assertNull("Expected null response when retrieving comments for an invalid activity",comments);
	}

	/**
	 * <p>List {@link Athlete athletes} giving kudos for an {@link Activity} which has >0 kudos</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_hasKudoers() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Athlete[] kudoers = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, null, null);
		
		assertNotNull("Returned null kudos array for activity with kudos",kudoers);
		assertNotEquals("Returned empty kudos array for activity with kudos",0,kudoers.length);
	}
	
	/**
	 * <p>List {@link Athlete athletes} giving kudos for an {@link Activity} which has NO kudos</p>
	 * 
	 * <p>Should return an empty array of {@link Athlete athletes}</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_hasNoKudoers() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Athlete[] kudoers = service.listActivityKudoers(ACTIVITY_WITHOUT_KUDOS, null, null);

		assertNotNull("Returned null kudos array for activity without kudos",kudoers);
		assertEquals("Did not return empty kudos array for activity with no kudos",0,kudoers.length);
	}

	/**
	 * <p>Attempt to list {@link Athlete athletes} giving kudos for an {@link Activity} which does not exist</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Athlete[] kudoers = service.listActivityKudoers(ACTIVITY_INVALID, null, null);

		assertNull("Returned a non-null array of kudoers for an invalid activity",kudoers);
	}

	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 kudos from the service (using the default page with a page size of 2), then ask for the first page only with size 1 and check that it's the same as the first one in the previous list</p> 
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_pageNumberAndSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);

		Athlete[] defaultKudoers = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, null, 2);
		
		assertEquals("Default kudoers should be of length 2",2,defaultKudoers.length);
		
		Athlete[] firstPage = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, 1, 1);
		
		assertEquals("Asking for page of size 1 should return an array of length 1",1,firstPage.length);
		assertEquals("Page 1 of size 1 should contain the same athlete as the first athlete returned",defaultKudoers[0].getId(),firstPage[0].getId());
		
		Athlete[] secondPage = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, 2, 1);
		
		assertEquals("Asking for page of size 1 should return an array of length 1",1,secondPage.length);
		assertEquals("Page 2 of size 1 should contain the same athlete as the second athlete returned",defaultKudoers[1].getId(),secondPage[0].getId());
	}

	/**
	 * <p>Test page size parameter handling behaves as expected when listing {@link Athlete athletes} giving kudos for an existing {@link Activity}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * 
	 */
	@Test
	public void testListActivityKudoers_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Athlete[] kudoers = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, null, 1);
		
		assertNotNull("Asked for one kudoer in a page, got null",kudoers);
		assertEquals("Asked for one comment in a page, got " + kudoers.length,1,kudoers.length);
		
		// TODO Test maximum page size (test at max, and at max+1)
		fail("Not yet implemented");

	}

	/**
	 * <p>Attempt to get a result from a pagination result which is way too high</p>
	 * 
	 * <p>Should return an empty array</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Athlete[] kudoers = service.listActivityKudoers(ACTIVITY_WITH_KUDOS, 1000, 200);
	
		assertNotNull("Kudoers should be returned as an empty array, got null",kudoers);
		assertEquals("Asked for out of range kudos, expected an empty array, got " + kudoers.length + " kudoers unexpectedly", 0, kudoers.length);
}

	/**
	 * <p>Test pagination of {@link Athlete kudoers} for parameters which are out of range - i.e. too low</p>
	 * 
	 * <p>Should throw an {@link IllegalArgumentException} (which will be trapped and ignored by this test)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityKudoers_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		try {
			service.listActivityKudoers(ACTIVITY_WITH_KUDOS, 0, 0);
		} catch (IllegalArgumentException e) {
			// Expected behaviour!
			return;
		}
		
		fail("Paging of kudoers for out-of-range (low) parameters should have failed, but didn't!");
	}

	/**
	 * <p>Attempt to list the {@link Lap laps} in an {@link Activity} which has laps</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityLaps_hasLaps() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Lap[] laps = service.listActivityLaps(ACTIVITY_WITH_LAPS);
		
		assertNotNull("Laps not returned for an activity which should have them",laps);
		assertNotEquals("No laps returned for an activity which should have them",0,laps.length);
	}

	/**
	 * <p>Attempt to list the {@link Lap laps} in an {@link Activity} which has NO laps</p>
	 * 
	 * <p>Should return an empty array of {@link Lap}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityLaps_hasNoLaps() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Lap[] laps = service.listActivityLaps(ACTIVITY_WITHOUT_LAPS);
		
		assertNotNull("Laps not returned for an activity which should have them",laps);
		assertNotEquals("No laps returned for an activity which should have them",0,laps.length);
	}

	/**
	 * <p>Attempt to list the {@link Lap laps} in a non-existent {@link Activity}</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityLaps_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Lap[] laps = service.listActivityLaps(ACTIVITY_INVALID);
		
		assertNull("Laps returned for an invalid activity",laps);
	}

	/**
	 * <p>List {@link ActivityZone activity zones} for an {@link Activity} which has them</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityZones_hasZones() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		ActivityZone[] zones = service.listActivityZones(ACTIVITY_WITH_ZONES);
		
		assertNotNull("Returned null activity zones for an activity with zones",zones);
		assertNotEquals("Returned an empty array of activity zones for an activity with zones",0,zones.length);
	}

	/**
	 * <p>Attempt to list {@link ActivityZone zones} for an {@link Activity} which doesn't have any</p>
	 * 
	 * <p>Should return an empty array</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityZones_hasNoZones() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		ActivityZone[] zones = service.listActivityZones(ACTIVITY_WITHOUT_ZONES);
		
		assertNotNull("Returned null activity zones for an activity without zones (should return an empty array)",zones);
		assertEquals("Returned an non-empty array of activity zones for an activity without zones",0,zones.length);
	}

	/**
	 * <p>Attempt to list {@link ActivityZone zones} for an {@link Activity} which doesn't exist</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityZones_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		ActivityZone[] zones = service.listActivityZones(ACTIVITY_INVALID);
		
		assertNull("Returned non-null activity zones for an activity which doesn't exist",zones);
	}

	/**
	 * <p>List latest {@link Activity activities} for {@link Athlete athletes} the currently authorised user is following</p>
	 * 
	 * <p>Should return a list of rides in descending order of start date</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_hasFriends() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] activities = service.listFriendsActivities(null, null);
		
		assertNotNull("Returned null array for latest friends' activities",activities);
		
		// Check that the activities are returned in descending order of start date
		Date lastStartDate = null;
		for (Activity activity : activities) {
			if (lastStartDate == null) { 
				lastStartDate = activity.getStartDate();
			} else {
				if (activity.getStartDate().after(lastStartDate)) {
					fail("Activities not returned in descending start date order");
				}
			}
		}
	}
	
	@Test
	public void testListFriendsActivities_hasNoFriends() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 activities from the service, then ask for the first page only and check that it's the same as the first activity, then ask for the second page and check that it's the same as the second activity</p>
	 *  
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pageNumberAndSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] defaultActivities = service.listFriendsActivities(null, 2);

		assertEquals("Default page of activities should be of size 2",2,defaultActivities.length);

		Activity[] firstPageOfActivities = service.listFriendsActivities(1, 1);
		
		assertEquals("First page of activities should be of size 1",1,firstPageOfActivities.length);
		assertEquals("Different first page of activities to expected", defaultActivities[0].getId(),firstPageOfActivities[0].getId());

		Activity[] secondPageOfActivities = service.listFriendsActivities(2, 1);
		
		assertEquals("Second page of activities should be of size 1",1,firstPageOfActivities.length);
		assertEquals("Different second page of activities to expected", defaultActivities[1].getId(),secondPageOfActivities[0].getId());
		
	}
	
	/**
	 * <p>Test paging (page size only)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] activities = service.listFriendsActivities(null, 1);
		
		assertNotNull("Authenticated athlete's activities returned as null when asking for a page of size 1",activities);
		assertEquals("Wrong number of activities returned when asking for a page of size 1",1,activities.length);
		
		// TODO Test maximum page size (test at max, and at max+1)
		fail("Not yet implemented");

	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too high)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		
		// Ask for the 2,000,000th activity by the athlete's friends (this is probably safe!)
		Activity[] activities = service.listFriendsActivities(10000, 200);
		
		assertEquals("Unexpected return of activities for paging out of range (high)",0,activities.length);
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too low)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);		
		
		// Ask for the 0th activity by the athlete (this is probably safe!)
		try {
			service.listFriendsActivities(0, 0);
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		
		fail("Unexpected return of activities for paging out of range (low)");
	}
	
	// Test cases: allowed to update the following attributes:
	// 1.
	
	@Test
	public void testUpdateActivity_validUpdate() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateActivity_tooManyActivityAttributes() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateActivity_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateActivity_unauthenticatedAthletesActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateActivity_accessTokenDoesNotHaveWriteAccess() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

}
