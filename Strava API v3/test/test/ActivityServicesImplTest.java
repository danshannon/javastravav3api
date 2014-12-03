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
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too high)</p>
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
	public void testListActivityPhotos_noPhotos() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(VALID_TOKEN);
		Photo[] photos = service.listActivityPhotos(ACTIVITY_WITHOUT_PHOTOS);

		assertNull("Photos returned for an activity without photos",photos);
	}

	// Test criteria:
	// 1. Valid activity
	// 2. Invalid token - NO LONGER REQUIRED
	// 3. Token is valid but doesn't have write access
	// 4. Duplicate activity??
	// 5. Activity with missing bits??
	// 6. Activity with too many attributes??
	@Test
	public void testCreateManualActivity() {
		// TODO Not yet implemented
		fail("Not yet Implemented");
	}

	// Test criteria:
	// 1. Valid activity
	// 2. Invalid token
	// 3. Token is valid but doesn't have write access
	// 4. Non-existent activity
	// 5. Activity that belongs to another athlete
	// 6. Invalid activity id
	@Test
	public void testDeleteActivity() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:
	// 1. Has comments, with markdown
	// 2. Has comments, without markdown
	// 3. Has no comments
	// 4. Invalid token
	// 5. Page number
	// 6. Page size
	// 7. Page number and page size
	// 8. Paging out of range
	// 9. Invalid activity
	@Test
	public void testListActivityComments() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:
	// 1. Has kudoers
	// 2. Has no kudoers
	// 3. Invalid token
	// 4. Invalid (non-existent) activity
	// 5. Page number
	// 6. Page size
	// 7. Page number and page size
	// 8. Paging out of range
	@Test
	public void testListActivityKudoers() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:
	// 1. Has laps
	// 2. Has no laps
	// 3. Invalid token
	// 4. Invalid (non-existent) activity
	@Test
	public void testListActivityLaps() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:
	// 1. Has zones
	// 2. Has no zones
	// 3. Invalid token
	// 4. Invalid (non-existent) activity
	@Test
	public void testListActivityZones() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test criteria:
	// 1. Has friends
	// 2. Has no friends
	// 3. Invalid token
	// 4. Page number
	// 5. Page size
	// 6. Page number and size
	// 7. Paging out of range
	@Test
	public void testListFriendsActivities() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test criteria:
	// 1. Valid activity, valid update
	// 2. Valid activity, attempt to update invalid elements of the activity
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
