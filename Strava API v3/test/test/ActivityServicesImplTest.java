package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.ActivityServices;
import com.danshannon.strava.api.service.impl.retrofit.ActivityServicesImpl;
import com.danshannon.strava.api.service.impl.retrofit.UnauthorizedException;

/**
 * Unit tests for {@link ActivityServicesImpl}
 * 
 * @author Dan Shannon
 *
 */
public class ActivityServicesImplTest {
	private static String VALID_TOKEN; 
	private static String INVALID_TOKEN;
	private static Integer ACTIVITY_WITH_EFFORTS;
	private static Integer ACTIVITY_WITH_PHOTOS;
	private static Integer ACTIVITY_WITHOUT_PHOTOS;
	private static Integer ACTIVITY_FOR_AUTHENTICATED_USER;
	private static Integer ACTIVITY_FOR_UNAUTHENTICATED_USER;
	private static Integer ACTIVITY_INVALID;
	private static Integer ACTIVITY_WITH_COMMENTS;
	private static Integer ACTIVITY_WITHOUT_COMMENTS;
	private static final String PROPERTIES_FILE = "test-config.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = TestUtils.loadPropertiesFile(PROPERTIES_FILE);
		VALID_TOKEN = properties.getProperty("test.activityServicesImplTest.validToken");
		INVALID_TOKEN = properties.getProperty("test.activityServicesImplTest.invalidToken");
		ACTIVITY_WITH_EFFORTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithEfforts"));
		ACTIVITY_WITH_PHOTOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithPhotos"));
		ACTIVITY_WITHOUT_PHOTOS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutPhotos"));
		ACTIVITY_WITH_COMMENTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithComments"));
		ACTIVITY_WITHOUT_COMMENTS = new Integer(properties.getProperty("test.activityServicesImplTest.activityWithoutComments"));
		ACTIVITY_FOR_AUTHENTICATED_USER = new Integer(properties.getProperty("test.activityServicesImplTest.activityBelongingToAuthenticatedUser"));
		ACTIVITY_FOR_UNAUTHENTICATED_USER = new Integer(properties.getProperty("test.activityServicesImplTest.activityBelongingToUnauthenticatedUser"));
		ACTIVITY_INVALID = new Integer(properties.getProperty("test.activityServicesImplTest.activityInvalid"));
	}
	
	/**
	 * Test we get a service implementation successfully with a valid token
	 */
	@Test
	public void testImplementation_validToken() {
		ActivityServices service = null;
		try {
			service = ActivityServicesImpl.implementation(VALID_TOKEN);
		} catch (UnauthorizedException e) {
			fail("Failed to get a service for a valid token");
		}
		assertNotNull("Got a NULL service for a valid token", service);
		
	}
	
	/**
	 * Test that we don't get a service implementation if the token isn't valid
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
	 * Test that we don't get a service implementation if the token has been revoked by the user
	 */
	@Test
	public void testImplementation_revokedToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * Test that when we ask for a service implementation for a second time, we get the SAME ONE as the first time
	 */
	@Test
	public void testImplementation_secondRequestSameAsFirst() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		ActivityServices service2 = ActivityServicesImpl.implementation(VALID_TOKEN);
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * Test retrieval of a known activity, complete with all efforts
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
	 * Test retrieval of a known activity that belongs to the authenticated user; it should be a detailed representation
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
	 * Test retrieval of a known activity that DOES NOT belong to the authenticated user; it should be a summary representation
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
	 * Test retrieval of a known activity, without the efforts being returned (i.e. includeAllEfforts = false)
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityWithoutEfforts() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_WITH_EFFORTS, Boolean.FALSE);

		assertNotNull("Returned null Activity for known activity with id " + ACTIVITY_WITH_EFFORTS,activity);
		if (activity.getSegmentEfforts() != null) {
			assertEquals("Returned segment efforts despite asking not to for activity with id " + ACTIVITY_WITH_EFFORTS, 0, activity.getSegmentEfforts().size());
		}
	}
	
	/**
	 * Test retrieval of a non-existent activity
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_unknownActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity activity = service.getActivity(ACTIVITY_INVALID, Boolean.FALSE);
		
		assertNull("Got an activity for an invalid activity id " + ACTIVITY_INVALID,activity);
	}
	
	/**
	 * Default test to list activities for the currently authenticated athlete (i.e. the one who corresponds to the current token)
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
	 * Test listing of activities before a given time (i.e. the before parameter, tested in isolation)
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	
	/**
	 * Test listing of activities after a given time (i.e. the after parameter, tested in isolation)
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_afterActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * Test listing of activities between one and another (i.e. before and after parameters in combination)
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterCombination() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * Test listing of activities between one and another (i.e. before and after parameters in combination)
	 * BUT WITH AN INVALID COMBINATION OF BEFORE AND AFTER
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterInvalidCombination() {
		// TODO Not yet implemented
		fail("Not yet implemented");

	}
	
	/**
	 * Test paging (page size only)
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Activity[] activities = service.listAuthenticatedAthleteActivities(null, null, null, 1);
		
		assertNotNull("Authenticated athlete's activities returned as null when asking for a page of size 1",activities);
		assertEquals("Wrong number of activities returned when asking for a page of size 1",1,activities.length);
		
	}
	
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 activities from the service, then ask for the first page only and check that it's the same</p> 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pageNumber() throws UnauthorizedException {
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
		Activity[] activities = null;
		try {
			activities = service.listAuthenticatedAthleteActivities(null, null, 0, 0);
		} catch (IllegalArgumentException e) {
			// Expected behaviour
		}
		
		assertNull("Unexpected return of activities for paging out of range (low)",activities);
	}
	
	/**
	 * List photos, with an activity that has a known non-zero number of photos
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
	 * Attempt to list photos for a non-existent activity 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityPhotos_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_INVALID);

		assertNull("Photos returned for an invalid activity",photos);
	}
	
	/**
	 * List photos, with an activity that has no photos
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityPhotos_hasNoPhotos() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_WITHOUT_PHOTOS);

		assertNotNull("Photos returned as null for a valid activity without photos",photos);
		assertEquals("Photos were returned for an activity which has no photos",0,photos.length);
	}
	
	@Test
	public void testCreateManualActivity_validActivity() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	@Test
	public void testCreateManualActivity_accessTokenDoesNotHaveWriteAccess() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	@Test
	public void testCreateManualActivity_duplicateActivity() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	@Test
	public void testCreateManualActivity_incompleteActivityDetails() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	@Test
	public void testCreateManualActivity_tooManyActivityAttributes() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	@Test
	public void testDeleteActivity_validActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteActivity_accessTokenDoesNotHaveWriteAccess() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteActivity_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteActivity_unauthenticatedAthletesActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

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
	
	@Test
	public void testListActivityComments_hasNoComments() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Comment[] comments = service.listActivityComments(ACTIVITY_WITHOUT_COMMENTS, Boolean.TRUE, null, null);

		assertNotNull("Returned null list of comments when an empty array was expected",comments);
		assertEquals("Returned a non-empty list of comments when none were expected", 0, comments);
	}
	
	@Test
	public void testListActivityComments_pageNumber() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListActivityComments_pageSize() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListActivityComments_pagingOutOfRangeHigh() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListActivityComments_pagingOutOfRangeLow() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListActivityComments_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityKudoers_hasKudoers() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListActivityKudoers_hasNoKudoers() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityKudoers_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityKudoers_pageNumber() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityKudoers_pageSize() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:	@Test
	public void testListActivityKudoers_pageOutOfRangeHigh() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityKudoers_pageOutOfRangeLow() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityLaps_hasLaps() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityLaps_hasNoLaps() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityLaps_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityZones_hasZones() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityZones_hasNoZones() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListActivityZones_invalidActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	@Test
	public void testListFriendsActivities_hasFriends() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListFriendsActivities_hasNoFriends() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListFriendsActivities_pageNumber() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListFriendsActivities_pageSize() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListFriendsActivities_pageOutOfRangeHigh() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testListFriendsActivities_pageOutOfRangeLow() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test criteria:
	// 1. Valid activity, valid update
	// 2. Valid activity, attempt to update invalid elements of the activity not catered for by the Strava API
	// 3. Invalid token
	// 4. Invalid (non-existent) activity
	// 5. Invalid (belongs to another athlete) activity
	// 6. Valid activity, but token does not grant write access
	@Test
	public void testUpdateActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

}
