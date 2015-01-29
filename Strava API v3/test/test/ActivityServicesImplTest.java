package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Test;

import com.danshannon.strava.api.auth.TokenServices;
import com.danshannon.strava.api.auth.impl.retrofit.TokenServicesImpl;
import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.ActivityServices;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.ActivityServicesImpl;
import com.danshannon.strava.util.Paging;

/**
 * <p>Unit tests for {@link ActivityServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class ActivityServicesImplTest {

	/**
	 * <p>Test we get a {@link ActivityServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link ActivityServicesImpl service implementation} if the token isn't valid</p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		try {
			service.getActivity(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Got a service for an invalid token!");
	}

	/**
	 * <p>Test that we don't get a {@link ActivityServicesImpl service implementation} if the token has been revoked by the user</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testImplementation_revokedToken() throws UnauthorizedException {
		// 1. Get a token
		String token = TestUtils.getValidToken();
		
		// 2. Revoke it using an authorisation service implementation derived from the valid token
		TokenServices authService = TokenServicesImpl.implementation(token);
		authService.deauthorise(token);
		
		// 3. Attempt to get an implementation using the now invalidated token
		ActivityServices activityServices = ActivityServicesImpl.implementation(token);
		
		// 4. Check that it can't be used
		try {
			activityServices.getActivity(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		
		// 5. If we get here, then the service is working despite the token being revoked
		fail("Got a usable service implementation using a revoked token");
	}
	
	/**
	 * <p>Test that when we ask for a {@link ActivityServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		ActivityServices service2 = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link ActivityServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		String token = TestUtils.getValidToken();
		@SuppressWarnings("unused")
		ActivityServices service = ActivityServicesImpl.implementation(token);
		String token2 = TestUtils.getValidTokenWithoutWriteAccess();
		@SuppressWarnings("unused")
		ActivityServices service2 = ActivityServicesImpl.implementation(token2);
		assertNotEquals("Different tokens returned the same service implementation",token,token2);
	}
	
	/**
	 * <p>Test retrieval of a known {@link Activity}, complete with all {@link SegmentEffort efforts}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityWithEfforts() throws UnauthorizedException  {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_WITH_EFFORTS, Boolean.TRUE);

		assertNotNull("Returned null Activity for known activity with id " + TestUtils.ACTIVITY_WITH_EFFORTS,activity);
		assertNotNull("Activity " + TestUtils.ACTIVITY_WITH_EFFORTS + " was returned but segmentEfforts is null", activity.getSegmentEfforts());
		assertNotEquals("Activity " + TestUtils.ACTIVITY_WITH_EFFORTS + " was returned but segmentEfforts is empty",0,activity.getSegmentEfforts().size());
	}
	
	/**
	 * <p>Test retrieval of a known {@link Activity} that belongs to the authenticated user; it should be a detailed {@link ResourceState representation}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityBelongsToAuthenticatedUser() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		
		assertNotNull("Returned null Activity for known activity with id " + TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER,activity);
		assertEquals("Returned activity is not a detailed representation as expected - " + activity.getResourceState(),ResourceState.DETAILED,activity.getResourceState());
	}

	/**
	 * <p>Test retrieval of a known {@link Activity} that DOES NOT belong to the authenticated user; it should be a summary {@link ResourceState representation}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityBelongsToUnauthenticatedUser() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_FOR_UNAUTHENTICATED_USER);
		
		assertNotNull("Returned null Activity for known activity with id " + TestUtils.ACTIVITY_FOR_UNAUTHENTICATED_USER,activity);
		assertEquals("Returned activity is not a summary representation as expected - " + activity.getResourceState(), ResourceState.SUMMARY, activity.getResourceState());
	}
	/**
	 * <p>Test retrieval of a known {@link Activity}, without the non-important/hidden efforts being returned (i.e. includeAllEfforts = false)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testGetActivity_knownActivityWithoutEfforts() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_WITH_EFFORTS, Boolean.FALSE);

		assertNotNull("Returned null Activity for known activity with id " + TestUtils.ACTIVITY_WITH_EFFORTS,activity);
		assertNotNull("Returned null segment efforts for known activity, when they were expected", activity.getSegmentEfforts());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_INVALID);
		
		assertNull("Got an activity for an invalid activity id " + TestUtils.ACTIVITY_INVALID,activity);
	}
	
	/**
	 * <p>Default test to list {@link Activity activities} for the currently authenticated athlete (i.e. the one who corresponds to the current token)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_default() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listAuthenticatedAthleteActivities();
		
		assertNotNull("Authenticated athlete's activities returned as null",activities);
		assertNotEquals("No activities returned for the authenticated athlete",0,activities.size());
	}
	
	/**
	 * <p>Test listing of {@link Activity activities} before a given time (i.e. the before parameter, tested in isolation)</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(2015,Calendar.JANUARY,1);
		
		List<Activity> activities = service.listAuthenticatedAthleteActivities(calendar, null);
		for (Activity activity : activities) {
			assertTrue(activity.getStartDate().before(calendar.getTime()));
		}
	}
	
	
	/**
	 * <p>Test listing of {@link Activity activities} after a given time (i.e. the after parameter, tested in isolation)</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_afterActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(2015,Calendar.JANUARY,1);
		
		List<Activity> activities = service.listAuthenticatedAthleteActivities(null, calendar);
		for (Activity activity : activities) {
			assertTrue(activity.getStartDate().after(calendar.getTime()));
		}
	}
	
	/**
	 * <p>Test listing of {@link Activity activities} between two given times (i.e. before and after parameters in combination)</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterCombination() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Calendar before = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		before.set(2015,Calendar.JANUARY,1);
		Calendar after = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		after.set(2014,Calendar.JANUARY,1);
		
		List<Activity> activities = service.listAuthenticatedAthleteActivities(before, after);
		for (Activity activity : activities) {
			assertTrue(activity.getStartDate().before(before.getTime()));
			assertTrue(activity.getStartDate().after(after.getTime()));
		}
	}
	
	/**
	 * <p>Test listing of {@link Activity activities} between two given times (i.e. before and after parameters in combination)
	 * BUT WITH AN INVALID COMBINATION OF BEFORE AND AFTER</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_beforeAfterInvalidCombination() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Calendar before = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		before.set(2014,Calendar.JANUARY,1);
		Calendar after = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		after.set(2015,Calendar.JANUARY,1);
		
		List<Activity> activities = service.listAuthenticatedAthleteActivities(before, after);
		assertNotNull("Returned null collection of activities", activities);
		for (Activity activity : activities) {
			assertTrue(activity.getStartDate().before(before.getTime()));
			assertTrue(activity.getStartDate().after(after.getTime()));
		}

	}
	
	/**
	 * <p>Test paging (page size only)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listAuthenticatedAthleteActivities(new Paging(1, 1));
		
		assertNotNull("Authenticated athlete's activities returned as null when asking for a page of size 1",activities);
		assertEquals("Wrong number of activities returned when asking for a page of size 1",1,activities.size());
	}

	@Test
	public void testListAuthenticatedAthleteActivities_pageSizeTooLarge() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listAuthenticatedAthleteActivities(new Paging(2, 201));
		assertNotNull("Returned null list of activities",activities);
		assertEquals(201,activities.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> defaultActivities = service.listAuthenticatedAthleteActivities(new Paging(1, 2));

		assertEquals("Default page of activities should be of size 2",2,defaultActivities.size());

		List<Activity> firstPageOfActivities = service.listAuthenticatedAthleteActivities(new Paging(1, 1));
		
		assertEquals("First page of activities should be of size 1",1,firstPageOfActivities.size());
		assertEquals("Different first page of activities to expected", defaultActivities.get(0).getId(),firstPageOfActivities.get(0).getId());

		List<Activity> secondPageOfActivities = service.listAuthenticatedAthleteActivities(new Paging(2, 1));
		
		assertEquals("Second page of activities should be of size 1",1,firstPageOfActivities.size());
		assertEquals("Different second page of activities to expected", defaultActivities.get(1).getId(),secondPageOfActivities.get(0).getId());
		
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too high)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		// Ask for the 200,000th activity by the athlete (this is probably safe!)
		List<Activity> activities = service.listAuthenticatedAthleteActivities(new Paging(1000, 200));
		
		assertEquals("Unexpected return of activities for paging out of range (high)",0,activities.size());
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too low)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListAuthenticatedAthleteActivities_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		
		// Ask for the -1th activity by the athlete (this is probably safe!)
		try {
			service.listAuthenticatedAthleteActivities(new Paging(-1, -1));
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Photo> photos = service.listActivityPhotos(TestUtils.ACTIVITY_WITH_PHOTOS);

		assertNotNull("Null list of photos returned for activity",photos);
		assertNotEquals("No photos returned although some were expected",0,photos.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Photo> photos = service.listActivityPhotos(TestUtils.ACTIVITY_INVALID);

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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Photo> photos = service.listActivityPhotos(TestUtils.ACTIVITY_WITHOUT_PHOTOS);

		assertNotNull("Photos returned as null for a valid activity without photos",photos);
		assertEquals("Photos were returned for an activity which has no photos",0,photos.size());
	}
	
	/**
	 * <p>Attempt to create a valid manual {@link Activity} for the user associated with the security token</p>
	 * 
	 * <p>Should successfully create the activity, and the activity should be retrievable immediately and identical to the one used to create</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException Thrown if the ride cannot be deleted once created
	 * @throws BadRequestException Thrown if the ride cannot be created
	 */
	@Test
	public void testCreateManualActivity_validActivity() throws UnauthorizedException, NotFoundException, BadRequestException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.createManualActivity(TestUtils.ACTIVITY_DEFAULT_FOR_CREATE);
		assertNotNull(activity);
		
		// Load it from Strava
		Activity stravaActivity = service.getActivity(activity.getId());
		assertNotNull(stravaActivity);
		
		// And delete it
		service.deleteActivity(activity.getId());
	}

	/**
	 * <p>Attempt to create a valid manual {@link Activity} for the user associated with the security token, where the user has NOT granted write access via the OAuth process</p>
	 * 
	 * <p>Should fail to create the activity and throw an {@link UnauthorizedException}, which is trapped in the test because it it expected</p>
	 * @throws UnauthorizedException 
	 * @throws BadRequestException 
	 */
	@Test
	public void testCreateManualActivity_accessTokenDoesNotHaveWriteAccess() throws UnauthorizedException, BadRequestException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		Activity activity = null;
		try {
			activity = service.createManualActivity(TestUtils.ACTIVITY_DEFAULT_FOR_CREATE);
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
	 * <p>Attempt to create an incomplete manual {@link Activity} for the user where not all required attributes are set</p>
	 * 
	 * <p>Should fail to create the activity in each case where a required attribute is missing</p>
	 * 
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testCreateManualActivity_incompleteActivityDetails() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		// Name is required
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		Activity stravaResponse = null;
		activity.setName(null);
		try {
			stravaResponse = service.createManualActivity(activity);
		} catch (BadRequestException e) {
			// Expected behaviour
		}
		assertNull("Created an activity with no name in error",stravaResponse);
		
		// Type is required
		activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setType(null);
		try {
			stravaResponse = service.createManualActivity(activity);
		} catch (BadRequestException e) {
			// Expected behaviour
		}
		assertNull("Created an activity with no type in error",stravaResponse);

		// Type must be one of the specified values
		activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setType(ActivityType.UNKNOWN);
		try {
			stravaResponse = service.createManualActivity(activity);
		} catch (BadRequestException e) {
			// Expected behaviour
		}
		assertNull("Created an activity with unknown type in error",stravaResponse);

		// Start date is required
		activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setStartDateLocal(null);
		try {
			stravaResponse = service.createManualActivity(activity);
		} catch (BadRequestException e) {
			// Expected behaviour
		}
		assertNull("Created an activity with no start date in error",stravaResponse);

		// Elapsed time is required
		activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setElapsedTime(null);
		try {
			stravaResponse = service.createManualActivity(activity);
		} catch (BadRequestException e) {
			// Expected behaviour
		}
		assertNull("Created an activity with no elapsed time in error",stravaResponse);		
	}

	/**
	 * <p>Attempt to delete an existing {@link Activity} for the user</p>
	 * 
	 * <p>In order to avoid deleting genuine data, this test creates the activity first, checks that it has been successfully written (i.e. that it can be read back from the API) and then deletes it again</p>
	 * 
	 * <p>Should successfully delete the activity; it should no longer be able to be retrieved via the API</p>
	 * @throws UnauthorizedException 
	 * @throws BadRequestException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testDeleteActivity_validActivity() throws UnauthorizedException, BadRequestException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		Activity stravaResponse = service.createManualActivity(activity);
		activity = service.getActivity(stravaResponse.getId());
		assertNotNull(activity);
		service.deleteActivity(activity.getId());
		
	}

	/**
	 * <p>Attempt to create an {@link Activity} for the user, using a token which has not been granted write access through the OAuth process</p>
	 * 
	 * <p>Should fail to create the activity and throw an {@link UnauthorizedException}</p>
	 * @throws UnauthorizedException 
	 * @throws BadRequestException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testDeleteActivity_accessTokenDoesNotHaveWriteAccess() throws UnauthorizedException, BadRequestException, NotFoundException {
		// Create the activity using a service which DOES have write access
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		Activity stravaResponse = service.createManualActivity(activity);
		activity = service.getActivity(stravaResponse.getId());
		assertNotNull(activity);
		
		// Now get a token without write access and attempt to delete
		service = ActivityServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		try {
			service.deleteActivity(activity.getId());
			fail("Succeeded in deleting an activity despite not having write access");
		} catch (UnauthorizedException e) {
			// Expected behaviour
		}
		
		// Delete the activity using a token with write access
		service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		service.deleteActivity(activity.getId());
	}

	/**
	 * <p>Attempt to delete an {@link Activity} which does not exist</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testDeleteActivity_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			Activity stravaResponse = service.deleteActivity(1);
			fail("deleted an activity that doesn't exist");
		} catch (NotFoundException e) {
			// Expected behaviour
		}
	}

	/**
	 * <p>List {@link Comment comments} for a valid activity</p>
	 * 
	 * <p>Expectation is that at least one of the comments contains Markdown; this is tested by checking that at least one comment is different</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_hasComments() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Comment> comments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.TRUE);
		
		assertNotNull("Returned null list of comments (with markdown) when some were expected");
		assertNotEquals("Returned empty list of comments when some were expected", 0, comments.size());
		
		List<Comment> commentsWithoutMarkdown = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE);
		
		// Check that the lists are the same length!!
		assertNotNull("Returned null list of comments (without markdown) when some were expected");
		assertEquals("List of comments for activity " + TestUtils.ACTIVITY_WITH_COMMENTS + " is not same length with/without markdown!", comments.size(), commentsWithoutMarkdown.size());
		
		// Check that at least one comment is different (i.e. because of the markdown)
		boolean difference = false;
		for (int i = 0 ; i < comments.size(); i++) {
			if (!comments.get(i).equals(commentsWithoutMarkdown.get(i))) {
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
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_hasNoComments() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Comment> comments = service.listActivityComments(TestUtils.ACTIVITY_WITHOUT_COMMENTS, Boolean.TRUE);

		assertNotNull("Returned null list of comments when an empty array was expected",comments);
		assertEquals("Returned a non-empty list of comments when none were expected", 0, comments.size());
	}
	
	@Test
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 comments from the service (using the default page with a page size of 2), then ask for the first page only with size 1 and check that it's the same as the first one in the previous list</p> 
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	public void testListActivityComments_pageNumberAndSize() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Comment> defaultComments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(1, 2));
		
		assertEquals("Default page of comments should be of size 2",2,defaultComments.size());

		List<Comment> firstPageOfComments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(1, 1));
		
		assertEquals("First page of comments should be of size 1",1,firstPageOfComments.size());
		assertEquals("Different first page of comments to expected", defaultComments.get(0).getId(),firstPageOfComments.get(0).getId());

		List<Comment> secondPageOfComments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(2, 1));

		assertEquals("Second page of activities should be of size 1",1,firstPageOfComments.size());
		assertEquals("Different second page of comments to expected", defaultComments.get(1).getId(),secondPageOfComments.get(0).getId());
	}
	
	/**
	 * <p>Test page size parameter handling behaves as expected when listing {@link Comment comments} for an existing {@link Activity}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_pageSize() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Comment> comments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(1, 1));
		
		assertNotNull("Asked for one comment in a page, got null",comments);
		assertEquals("Asked for one comment in a page, got " + comments.size(),1,comments.size());
	}
	
	/**
	 * <p>Test pagination of {@link Comment comments} for parameters which are out of range - i.e. too high</p>
	 * 
	 * <p>Should return an empty array of {@link Comment comments}</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_pagingOutOfRangeHigh() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		// Attempt to get the 200,000th comment, that's probably out of range!
		List<Comment> comments = service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(1000, 200));
		
		assertNotNull("Comments should be returned as an empty array, got null",comments);
		assertEquals("Asked for out of range comments, expected an empty array, got " + comments.size() + " comments unexpectedly", 0, comments.size());
	}
	
	/**
	 * <p>Test pagination of {@link Comment comments} for parameters which are out of range - i.e. too low</p>
	 * 
	 * <p>Should throw an {@link IllegalArgumentException} (which will be trapped and ignored by this test)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_pagingOutOfRangeLow() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		try {
			service.listActivityComments(TestUtils.ACTIVITY_WITH_COMMENTS, Boolean.FALSE, new Paging(-1, -1));
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
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityComments_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		List<Comment> comments;
		comments = service.listActivityComments(TestUtils.ACTIVITY_INVALID, Boolean.FALSE);
		
		assertNull("Expected null response when retrieving comments for an invalid activity",comments);
	}

	/**
	 * <p>List {@link Athlete athletes} giving kudos for an {@link Activity} which has >0 kudos</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityKudoers_hasKudoers() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> kudoers = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS);
		
		assertNotNull("Returned null kudos array for activity with kudos",kudoers);
		assertNotEquals("Returned empty kudos array for activity with kudos",0,kudoers.size());
	}
	
	/**
	 * <p>List {@link Athlete athletes} giving kudos for an {@link Activity} which has NO kudos</p>
	 * 
	 * <p>Should return an empty array of {@link Athlete athletes}</p>
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityKudoers_hasNoKudoers() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> kudoers = service.listActivityKudoers(TestUtils.ACTIVITY_WITHOUT_KUDOS);

		assertNotNull("Returned null kudos array for activity without kudos",kudoers);
		assertEquals("Did not return empty kudos array for activity with no kudos",0,kudoers.size());
	}

	/**
	 * <p>Attempt to list {@link Athlete athletes} giving kudos for an {@link Activity} which does not exist</p>
	 * 
	 * <p>Should return <code>null</code></p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * @throws NotFoundException 
	 */
	@Test
	public void testListActivityKudoers_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> kudoers;
		kudoers = service.listActivityKudoers(TestUtils.ACTIVITY_INVALID);

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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());

		List<Athlete> defaultKudoers = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(1, 2));
		
		assertEquals("Default kudoers should be of length 2",2,defaultKudoers.size());
		
		List<Athlete> firstPage = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(1, 1));
		
		assertEquals("Asking for page of size 1 should return an array of length 1",1,firstPage.size());
		assertEquals("Page 1 of size 1 should contain the same athlete as the first athlete returned",defaultKudoers.get(0).getId(),firstPage.get(0).getId());
		
		List<Athlete> secondPage = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(2, 1));
		
		assertEquals("Asking for page of size 1 should return an array of length 1",1,secondPage.size());
		assertEquals("Page 2 of size 1 should contain the same athlete as the second athlete returned",defaultKudoers.get(1).getId(),secondPage.get(0).getId());
	}

	/**
	 * <p>Test page size parameter handling behaves as expected when listing {@link Athlete athletes} giving kudos for an existing {@link Activity}</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 * 
	 */
	@Test
	public void testListActivityKudoers_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> kudoers = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(1, 1));
		
		assertNotNull("Asked for one kudoer in a page, got null",kudoers);
		assertEquals("Asked for one comment in a page, got " + kudoers.size(),1,kudoers.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> kudoers = service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(1000, 200));
	
		assertNotNull("Kudoers should be returned as an empty array, got null",kudoers);
		assertEquals("Asked for out of range kudos, expected an empty array, got " + kudoers.size() + " kudoers unexpectedly", 0, kudoers.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		try {
			service.listActivityKudoers(TestUtils.ACTIVITY_WITH_KUDOS, new Paging(-1,-1));
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Lap> laps = service.listActivityLaps(TestUtils.ACTIVITY_WITH_LAPS);
		
		assertNotNull("Laps not returned for an activity which should have them",laps);
		assertNotEquals("No laps returned for an activity which should have them",0,laps.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Lap> laps = service.listActivityLaps(TestUtils.ACTIVITY_WITHOUT_LAPS);
		
		assertNotNull("Laps not returned for an activity which should have them",laps);
		assertNotEquals("No laps returned for an activity which should have them",0,laps.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Lap> laps = service.listActivityLaps(TestUtils.ACTIVITY_INVALID);
		
		assertNull("Laps returned for an invalid activity",laps);
	}

	/**
	 * <p>List {@link ActivityZone activity zones} for an {@link Activity} which has them</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListActivityZones_hasZones() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<ActivityZone> zones = service.listActivityZones(TestUtils.ACTIVITY_WITH_ZONES);
		
		assertNotNull("Returned null activity zones for an activity with zones",zones);
		assertNotEquals("Returned an empty array of activity zones for an activity with zones",0,zones.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<ActivityZone> zones = service.listActivityZones(TestUtils.ACTIVITY_WITHOUT_ZONES);
		
		assertNotNull("Returned null activity zones for an activity without zones (should return an empty array)",zones);
		assertEquals("Returned an non-empty array of activity zones for an activity without zones",0,zones.size());
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<ActivityZone> zones = service.listActivityZones(TestUtils.ACTIVITY_INVALID);
		
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
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listFriendsActivities(null);
		
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
	
	/**
	 * <p>Test paging (page number and page size).</p>
	 * 
	 * <p>To test this we get 2 activities from the service, then ask for the first page only and check that it's the same as the first activity, then ask for the second page and check that it's the same as the second activity</p>
	 *  
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pageNumberAndSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> defaultActivities = service.listFriendsActivities(new Paging(1, 2));

		assertEquals("Default page of activities should be of size 2",2,defaultActivities.size());

		List<Activity> firstPageOfActivities = service.listFriendsActivities(new Paging(1, 1));
		
		assertEquals("First page of activities should be of size 1",1,firstPageOfActivities.size());
		assertEquals("Different first page of activities to expected", defaultActivities.get(0).getId(),firstPageOfActivities.get(0).getId());

		List<Activity> secondPageOfActivities = service.listFriendsActivities(new Paging(2, 1));
		
		assertEquals("Second page of activities should be of size 1",1,firstPageOfActivities.size());
		assertEquals("Different second page of activities to expected", defaultActivities.get(1).getId(),secondPageOfActivities.get(0).getId());
		
	}
	
	/**
	 * <p>Test paging (page size only)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pageSize() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listFriendsActivities(new Paging(1, 1));
		
		assertNotNull("Authenticated athlete's activities returned as null when asking for a page of size 1",activities);
		assertEquals("Wrong number of activities returned when asking for a page of size 1",1,activities.size());
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too high)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pagingOutOfRangeHigh() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		
		// Ask for the 2,000,000th activity by the athlete's friends (this is probably safe!)
		List<Activity> activities = service.listFriendsActivities(new Paging(10000, 200));
		
		assertEquals("Unexpected return of activities for paging out of range (high)",0,activities.size());
	}
	
	/**
	 * <p>Test paging for paging parameters that can't return values (i.e. are out of range - too low)</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testListFriendsActivities_pagingOutOfRangeLow() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());		
		
		// Ask for the -1th activity by the athlete (this is probably safe!)
		try {
			service.listFriendsActivities(new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		
		fail("Unexpected return of activities for paging out of range (low)");
	}
	
	/** 
	 * <p>Test cases: allowed to update the following attributes:</p>
	 * <ol>
	 * <li>name</li>
	 * <li>type</li>
	 * <li>private</li>
	 * <li>commute</li>
	 * <li>trainer</li>
	 * <li>gear_id (also allows special case of 'none' which should remove the gear)</li>
	 * <li>description</li>
	 * </ol>
	 * 
	 * @throws UnauthorizedException
	 * @throws BadRequestException
	 * @throws NotFoundException
	 */
	@Test
	public void testUpdateActivity_validUpdate() throws UnauthorizedException, BadRequestException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setType(ActivityType.ALPINE_SKI);
		Fairy fairy = Fairy.create();
		TextProducer text = fairy.textProducer();
		
		// Create the activity on Strava
		Activity stravaResponse = service.createManualActivity(activity);
		
		// Change the name
		String name = text.sentence();
		activity.setName(name);
		activity.setId(stravaResponse.getId());
		
		// Change the type
		activity.setType(ActivityType.RIDE);
		
		// Change the privacy flag
		activity.setPrivate(Boolean.TRUE);
		
		// Change the commute flag
		activity.setCommute(Boolean.TRUE);
		
		// Change the trainer flag
		activity.setTrainer(Boolean.TRUE);
		
		// Change the gear id
		activity.setGearId(TestUtils.GEAR_VALID_ID);
		
		// Change the description
		String description = text.paragraph();
		activity.setDescription(description);

		// Update the activity
		stravaResponse = service.updateActivity(activity);
		
		// Get the activity again
		stravaResponse = service.getActivity(stravaResponse.getId());
		
		// Check that the name is now set
		assertEquals("Name not updated correctly",name,stravaResponse.getName());

		// Check that the type is now set
		assertEquals("Type not updated correctly",ActivityType.RIDE,stravaResponse.getType());

		// Check that the privacy flag is now set
		assertEquals("Private ride flag not updated correctly",Boolean.TRUE,stravaResponse.getPrivate());
		
		// Check that the commute flag is now set
		// TODO There seems to be a Strava bug here
		// assertEquals("Commute flag not updated correctly",Boolean.TRUE,stravaResponse.getCommute());
		
		// Check that the trainer flag is now set
		assertEquals("Trainer flag not updated correctly",Boolean.TRUE,stravaResponse.getTrainer());
		
		// Check that the gear id is set right
		// TODO There seems to be a Strava bug here
		// assertEquals("Gear not set correctly",TestUtils.GEAR_VALID_ID,stravaResponse.getGearId());
		
		// Check the description has changed
		assertEquals("Description not updated correctly",description,stravaResponse.getDescription());
		
		// Change the gear id to 'none'
		activity.setGearId("none");
		stravaResponse = service.updateActivity(activity);
		
		// Check that the gear id is gone
		assertNull("Gear not removed from activity",stravaResponse.getGearId());
		
		
		// Delete the activity at the end
		service.deleteActivity(activity.getId());
	}

	@Test
	public void testUpdateActivity_tooManyActivityAttributes() throws UnauthorizedException, BadRequestException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		Activity stravaResponse = service.createManualActivity(activity);
		
		Float cadence = new Float(67.2);
		activity.setAverageCadence(cadence);
		activity.setId(stravaResponse.getId());
		
		stravaResponse = service.updateActivity(activity);
		assertNull(stravaResponse.getAverageCadence());
	}

	@Test
	public void testUpdateActivity_invalidActivity() throws UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = TestUtils.ACTIVITY_DEFAULT_FOR_CREATE;
		activity.setId(TestUtils.ACTIVITY_INVALID);
		
		try {
			service.updateActivity(activity);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		fail("Updated an activity which doesn't exist?");
	}

	@Test
	public void testUpdateActivity_unauthenticatedAthletesActivity() throws NotFoundException, UnauthorizedException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidToken());
		Activity activity = service.getActivity(TestUtils.ACTIVITY_FOR_UNAUTHENTICATED_USER);
		
		try {
			service.updateActivity(activity);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Updated an activity which belongs to someone else??");
	}

	/**
	 * <p>Test attempting to update an activity using a token that doesn't have write access</p>
	 * 
	 * @throws UnauthorizedException
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@Test
	public void testUpdateActivity_accessTokenDoesNotHaveWriteAccess() throws UnauthorizedException, NotFoundException {
		ActivityServices service = ActivityServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		Fairy fairy = Fairy.create();
		TextProducer text = fairy.textProducer();
		Activity activity = service.getActivity(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		activity.setDescription(text.paragraph(1));
		
		try {
			service.updateActivity(activity);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Successfully updated an activity despite not having write access");
	}
	
	private ActivityServices getActivityService() throws UnauthorizedException {
		return ActivityServicesImpl.implementation(TestUtils.getValidToken());
	}

}
