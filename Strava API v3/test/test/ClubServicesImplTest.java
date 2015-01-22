package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.service.ClubServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.ClubServicesImpl;
import com.danshannon.strava.util.Paging;

/**
 * <p>Unit tests for {@link ClubServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class ClubServicesImplTest {
	/**
	 * <p>Test we get a {@link ClubServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link ClubServicesImpl service implementation} if the token isn't valid</p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		ClubServices service = null;
		try {
			service = ClubServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
		assertNull("Got a service for an invalid token!",service);
	}

	/**
	 * <p>Test that we don't get a {@link ClubServicesImpl service implementation} if the token has been revoked by the user</p>
	 */
	@Test
	public void testImplementation_revokedToken() {
		try {
			@SuppressWarnings("unused")
			ClubServices service = ClubServicesImpl.implementation(TestUtils.getRevokedToken());
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a service implementation despite using a revoked token");
	}
	
	/**
	 * <p>Test that when we ask for a {@link ClubServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		ClubServices service2 = ClubServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link ClubServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		ClubServices service2 = ClubServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}
	
	// Test cases
	// 1. Valid club
	@Test
	public void testGetClub_validClub() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		Club club = service.getClub(TestUtils.CLUB_VALID_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_VALID_ID,club.getId());
	}
	
	// 2. Invalid club
	public void testGetClub_invalidClub() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			Club club = service.getClub(TestUtils.CLUB_INVALID_ID);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		fail("Got club result despite club being invalid");
	}
	
	// 3. Private club of which current authenticated athlete is a member
	public void testGetClub_privateClubIsMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		Club club = service.getClub(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_PRIVATE_MEMBER_ID, club.getId());
	}
	
	// 4. Private club of which current authenticated athlete is NOT a member
	public void testGetClub_privateClubIsNotMember() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			Club club = service.getClub(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		} catch (NotFoundException e) {
			// Expected behaviour????
			return;
		}
		fail("Got details of a private club???? IS THIS RIGHT???");
	}
	
	// Test cases
	// 1. Athlete has clubs
	@Test
	public void testListAuthenticatedAthleteClubs() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		assertNotNull(clubs);
		assertFalse(clubs.size() == 0);
	}

	// Test cases
	// 1. Valid club
	@Test
	public void testListClubMembers_validClub() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}
	
	// 2. Invalid club
	@Test
	public void testListClubMembers_invalidClub() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		@SuppressWarnings("unused")
		List<Athlete> members;
		try {
			members = service.listClubMembers(TestUtils.CLUB_INVALID_ID);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		fail("Club doesn't exist but didn't throw an exception!");
	}
	
	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testListClubMembers_privateClubNotMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
		@SuppressWarnings("unused")
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		fail("Asked for a private club's memnber list and didn't get a NotFoundException");
	}
	
	// 3. Private club of which current authenticated athlete is a member
	@Test
	public void testListClubMembers_privateClubIsMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}
	
	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListClubMembers_pageSize() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1,1));
		assertNotNull(members);
		assertTrue(members.size() == 1);
		members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1,Strava.MAX_PAGE_SIZE + 1));
		assertNotNull(members);
		// Might not be 201 members!
		assertFalse(members.size() == 0);
	}
	
	// 6. Paging - size and page
	@Test
	public void testListClubMembers_pageSizeAndNumber() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1,2));
		assertNotNull(members);
		assertTrue(members.size() == 2);
		Integer memberId = members.get(1).getId();
		members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(2,1));
		assertNotNull(members);
		assertTrue(members.size() == 1);
		assertEquals(memberId,members.get(0).getId());
	}
	
	// 7. Paging - out of range high
	@Test
	public void testListClubMembers_pagingOutOfRangeHigh() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1000,200));
		assertNotNull(members);
		assertTrue(members.size() == 0);
	}
	
	// 8. Paging - out of range low
	@Test
	public void testListClubMembers_pagingOutOfRangeLow() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Attempted to process illegal paging instruction");
	}
	
	// Test cases
	// 1. Valid club
	@Test
	public void testListRecentClubActivities_validClub() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID);

		assertNotNull(activities);
	}
	
	// 2. Invalid club
	@Test
	public void testListRecentClubActivities_invalidClub() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		@SuppressWarnings("unused")
		List<Activity> activities;
		try {
			activities = service.listRecentClubActivities(TestUtils.CLUB_INVALID_ID);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}

		fail("Didn't fail when returning an invalid club");
	}
	
	// 3. Club the current authenticated athlete is NOT a member of (according to Strava should return 0 results)
	@Test
	public void testListRecentClubActivities_nonMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_PUBLIC_NON_MEMBER_ID);

		assertTrue(activities.isEmpty());
	}
	
	// 4. Club with > 200 activities (according to Strava, should only return a max of 200 results)
	@Test
	public void testListRecentClubActivities_moreThan200() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(2,200));
		assertNotNull(activities);
		assertEquals(0,activities.size());
		activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1,200));
		assertNotNull(activities);
		assertEquals(200,activities.size());
	}
	
	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListRecentClubActivities_pageSize() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1,1));
		
		assertNotNull(activities);
		assertEquals(1,activities.size());
	}
	
	// 6. Paging - size and page
	@Test
	public void testListRecentClubActivities_pageSizeAndNumber() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1,2));
		
		assertNotNull(activities);
		assertEquals(2,activities.size());
		Integer activityId = activities.get(1).getId();
		activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(2,1));
		assertNotNull(activities);
		assertEquals(1,activities.size());
		assertEquals(activityId, activities.get(0).getId());
	}

	// 7. Paging - out of range high
	@Test
	public void testListRecentClubActivities_pagingOutOfRangeHigh() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1000,200));
		
		assertNotNull(activities);
		assertEquals(0,activities.size());
	}

	// 8. Paging - out of range low
	@Test
	public void testListRecentClubActivities_pagingOutOfRangeLow() throws UnauthorizedException, NotFoundException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Didn't handle illegal paging instruction properly");
	}

	// Test cases
	// 1. Valid club which authenticated user is not already a member of
	@Test
	public void testJoinClub_member() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testJoinClub_nonMember() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 3. Invalid club
	@Test
	public void testJoinClub_invalidClub() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 4. Private club which authenticated user is NOT a member of
	@Test
	public void testJoinClub_privateClub() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test cases
	// 1. Valid club which authenticated user is not already a member of
	@Test
	public void testLeaveClub_nonMember() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testLeaveClub_member() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 3. Invalid club
	@Test
	public void testLeaveClub_invalidClub() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// 4. Private club which authenticated user is a member of
	@Test
	public void testLeaveClub_privateClubMember() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

}
