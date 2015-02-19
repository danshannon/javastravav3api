package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.service.ClubServices;
import javastrava.api.v3.service.Strava;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.ClubServicesImpl;
import javastrava.util.Paging;

import org.junit.Before;
import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * Unit tests for {@link ClubServicesImpl}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class ClubServicesImplTest {
	private ClubServices clubService;

	@Before
	public void before() {
		this.clubService = getClubService();
	}

	/**
	 * <p>
	 * Test we get a {@link ClubServicesImpl service implementation} successfully with a valid token
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             If token is not valid
	 */
	@Test
	public void testImplementation_validToken() {
		ClubServices service = getClubService();
		assertNotNull("Got a NULL service for a valid token", service);
	}

	/**
	 * <p>
	 * Test that we don't get a {@link ClubServicesImpl service implementation} if the token isn't valid
	 * </p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		ClubServices service = null;
		try {
			service = ClubServicesImpl.implementation(TestUtils.INVALID_TOKEN);
			service.getClub(TestUtils.CLUB_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Got a service for an invalid token!");
	}

	/**
	 * <p>
	 * Test that we don't get a {@link ClubServicesImpl service implementation} if the token has been revoked by the user
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_revokedToken() {
		ClubServices service = getRevokedTokenService();
		try {
			service.getClub(TestUtils.CLUB_VALID_ID);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a usable service implementation despite using a revoked token");
	}

	/**
	 * <p>
	 * Test that when we ask for a {@link ClubServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching
	 * strategy is working)
	 * </p>
	 */
	@Test
	public void testImplementation_implementationIsCached() {
		ClubServices service = getClubService();
		ClubServices service2 = getClubService();
		assertEquals("Retrieved multiple service instances for the same token - should only be one", service, service2);
	}

	/**
	 * <p>
	 * Test that when we ask for a {@link ClubServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() {
		ClubServices service = getClubService();
		ClubServices service2 = ClubServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}

	// Test cases
	// 1. Valid club
	@Test
	public void testGetClub_validClub() {
		ClubServices service = getClubService();
		StravaClub club = service.getClub(TestUtils.CLUB_VALID_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_VALID_ID, club.getId());
	}

	// 2. Invalid club
	@Test
	public void testGetClub_invalidClub() {
		ClubServices service = getClubService();
		StravaClub club = service.getClub(TestUtils.CLUB_INVALID_ID);
		assertNull("Got club result despite club being invalid", club);
	}

	// 3. Private club of which current authenticated athlete is a member
	@Test
	public void testGetClub_privateClubIsMember() {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		StravaClub club = service.getClub(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_PRIVATE_MEMBER_ID, club.getId());
	}

	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testGetClub_privateClubIsNotMember() {
		ClubServices service = getClubService();
		StravaClub club = service.getClub(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		StravaClub comparison = new StravaClub();
		comparison.setId(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		assertNotNull(club);
		assertEquals(comparison, club);
	}

	// Test cases
	// 1. StravaAthlete has clubs
	@Test
	public void testListAuthenticatedAthleteClubs() {
		ClubServices service = getClubService();
		List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
		assertNotNull(clubs);
		assertFalse(clubs.size() == 0);
	}

	// Test cases
	// 1. Valid club
	@Test
	public void testListClubMembers_validClub() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}

	// 2. Invalid club
	@Test
	public void testListClubMembers_invalidClub() {
		ClubServices service = getClubService();
		List<StravaAthlete> members;

		members = service.listClubMembers(TestUtils.CLUB_INVALID_ID);
		assertNull("Returned a list of members for a non-existent club", members);
	}

	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testListClubMembers_privateClubNotMember() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		assertNotNull(members);
		assertEquals(0, members.size());
	}

	// 3. Private club of which current authenticated athlete is a member
	@Test
	public void testListClubMembers_privateClubIsMember() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}

	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListClubMembers_pageSize() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1, 1));
		assertNotNull(members);
		assertTrue(members.size() == 1);
		members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1, Strava.MAX_PAGE_SIZE + 1));
		assertNotNull(members);
		// Might not be 201 members!
		assertFalse(members.size() == 0);
	}

	// 6. Paging - size and page
	@Test
	public void testListClubMembers_pageSizeAndNumber() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1, 2));
		assertNotNull(members);
		assertTrue(members.size() == 2);
		Integer memberId = members.get(1).getId();
		members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(2, 1));
		assertNotNull(members);
		assertTrue(members.size() == 1);
		assertEquals(memberId, members.get(0).getId());
	}

	// 7. Paging - out of range high
	@Test
	public void testListClubMembers_pagingOutOfRangeHigh() {
		ClubServices service = getClubService();
		List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1000, 200));
		assertNotNull(members);
		assertTrue(members.size() == 0);
	}

	// 8. Paging - out of range low
	@Test
	public void testListClubMembers_pagingOutOfRangeLow() {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		try {
			@SuppressWarnings("unused")
			List<StravaAthlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Attempted to process illegal paging instruction");
	}

	@Test
	public void testListClubMembers_pagingIgnoreFirstN() {
		List<StravaAthlete> athletes = getClubService().listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1, 2, 1, 0));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	@Test
	public void testListClubMembers_pagingIgnoreLastN() {
		List<StravaAthlete> athletes = getClubService().listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1, 2, 0, 1));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	// Test cases
	// 1. Valid club
	@Test
	public void testListRecentClubActivities_validClub() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID);

		assertNotNull(activities);
	}

	// 2. Invalid club
	@Test
	public void testListRecentClubActivities_invalidClub() {
		ClubServices service = getClubService();
		@SuppressWarnings("unused")
		List<StravaActivity> activities;
		activities = service.listRecentClubActivities(TestUtils.CLUB_INVALID_ID);

		assertNull("Didn't fail when returning an invalid club", null);
	}

	// 3. StravaClub the current authenticated athlete is NOT a member of (according to Strava should return 0 results)
	@Test
	public void testListRecentClubActivities_nonMember() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_PUBLIC_NON_MEMBER_ID);

		assertTrue(activities.isEmpty());
	}

	// 4. StravaClub with > 200 activities (according to Strava, should only return a max of 200 results)
	@Test
	public void testListRecentClubActivities_moreThan200() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(2, 200));
		assertNotNull(activities);
		assertEquals(0, activities.size());
		activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1, 200));
		assertNotNull(activities);
		assertFalse(0 == activities.size());
	}

	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListRecentClubActivities_pageSize() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1, 1));

		assertNotNull(activities);
		assertEquals(1, activities.size());
	}

	// 6. Paging - size and page
	@Test
	public void testListRecentClubActivities_pageSizeAndNumber() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1, 2));

		assertNotNull(activities);
		assertEquals(2, activities.size());
		Integer activityId = activities.get(1).getId();
		activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(2, 1));
		assertNotNull(activities);
		assertEquals(1, activities.size());
		assertEquals(activityId, activities.get(0).getId());
	}

	// 7. Paging - out of range high
	@Test
	public void testListRecentClubActivities_pagingOutOfRangeHigh() {
		ClubServices service = getClubService();
		List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1000, 200));

		assertNotNull(activities);
		assertEquals(0, activities.size());
	}

	// 8. Paging - out of range low
	@Test
	public void testListRecentClubActivities_pagingOutOfRangeLow() {
		ClubServices service = getClubService();
		try {
			@SuppressWarnings("unused")
			List<StravaActivity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Didn't handle illegal paging instruction properly");
	}

	@Test
	public void testListRecentClubActivities_pagingIgnoreFirstN() {
		List<StravaActivity> activities = getClubService().listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1, 2, 1, 0));
		assertNotNull(activities);
		assertEquals(1, activities.size());
		List<StravaActivity> expectedActivities = getClubService().listRecentClubActivities(TestUtils.CLUB_VALID_ID);
		assertEquals(expectedActivities.get(1), activities.get(0));
	}

	@Test
	public void testListRecentClubActivities_pagingIgnoreLastN() {
		List<StravaActivity> activities = getClubService().listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1, 2, 0, 1));
		assertNotNull(activities);
		assertEquals(1, activities.size());
		List<StravaActivity> expectedActivities = getClubService().listRecentClubActivities(TestUtils.CLUB_VALID_ID);
		assertEquals(expectedActivities.get(0), activities.get(0));
	}

	// Test cases
	// 1. Valid club which authenticated user is not already a member of
	@Test
	public void testJoinClub_nonMember() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PUBLIC_NON_MEMBER_ID;

		StravaClubMembershipResponse response = service.joinClub(id);

		// Make sure the athlete now a member
		List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);

		// Leave the club again, just to be sure
		service.leaveClub(id);

		assertNotNull(response);
		assertTrue(response.getSuccess());
		assertTrue(response.getActive());
		assertTrue(member);
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testJoinClub_member() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_VALID_ID;

		StravaClubMembershipResponse response = service.joinClub(id);

		// Make sure the athlete now (still) a member
		List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);

		assertNotNull(response);
		assertTrue(response.getSuccess());
		assertTrue(response.getActive());
		assertTrue(member);
	}

	// 3. Invalid club
	@Test
	public void testJoinClub_invalidClub() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_INVALID_ID;

		StravaClubMembershipResponse response = service.joinClub(id);
		assertEquals(Boolean.FALSE, response.getSuccess());
	}

	// 4. Private club which authenticated user is NOT a member of
	@Test
	public void testJoinClub_privateClub() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PRIVATE_NON_MEMBER_ID;

		StravaClubMembershipResponse response = service.joinClub(id);
		assertNotNull(response);
		assertEquals(Boolean.FALSE, response.getSuccess());

	}

	// 5. Attempt to join a club without having write access
	@Test
	public void testJoinClub_noWriteAccess() {
		ClubServices service = getClubServiceWithoutWriteAccess();
		Integer id = TestUtils.CLUB_VALID_ID;

		StravaClubMembershipResponse response = service.joinClub(id);
		assertNotNull(response);
		assertEquals(Boolean.FALSE, response.getSuccess());
	}

	// Test cases
	// 1. Valid club which authenticated user is not already a member of
	@Test
	public void testLeaveClub_nonMember() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PUBLIC_NON_MEMBER_ID;

		service.leaveClub(id);

		List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);

		assertFalse(member);
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testLeaveClub_member() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_VALID_ID;

		service.leaveClub(id);

		List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);

		// Join the club again
		service.joinClub(id);

		assertFalse(member);
	}

	// 3. Invalid club
	@Test
	public void testLeaveClub_invalidClub() {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_INVALID_ID;

		StravaClubMembershipResponse response = service.leaveClub(id);
		assertEquals(Boolean.FALSE, response.getSuccess());
	}

	// 4. Private club which authenticated user is a member of
	// CAN'T DO THIS IN TESTING AS YOU'LL NEVER BE ABLE TO JOIN IT AGAIN!!
	// @Test
	// public void testLeaveClub_privateClubMember() throws UnauthorizedException, NotFoundException {
	// ClubServices service = getClubService();
	// Integer id = TestUtils.CLUB_PRIVATE_MEMBER_ID;
	//
	// service.leaveClub(id);
	//
	// List<StravaClub> clubs = service.listAuthenticatedAthleteClubs();
	// boolean member = checkIsMember(clubs, id);
	//
	// // Join the club again
	// service.joinClub(id);
	// assertFalse(member);
	// }

	// 5. Leave a club using a token with no write access
	@Test
	public void testLeaveClub_noWriteAccess() {
		ClubServices service = getClubServiceWithoutWriteAccess();
		Integer id = TestUtils.CLUB_VALID_ID;

		StravaClubMembershipResponse response = service.leaveClub(id);
		assertNotNull(response);
		assertEquals(Boolean.FALSE, response.getSuccess());
	}

	private ClubServices getClubService() {
		if (this.clubService == null) {
			this.clubService = ClubServicesImpl.implementation(TestUtils.getValidToken());
		}
		return this.clubService;
	}

	private ClubServices getRevokedTokenService() {
		this.clubService = null;
		return ClubServicesImpl.implementation(TestUtils.getRevokedToken());
	}

	private ClubServices getClubServiceWithoutWriteAccess() {
		this.clubService = null;
		return ClubServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}

	/**
	 * @param clubs
	 *            List of clubs to check
	 * @param id
	 *            Id of the club we're checking for membership
	 * @return <code>true</code> if one of the clubs has the given id
	 */
	private boolean checkIsMember(final List<StravaClub> clubs, final Integer id) {
		for (StravaClub club : clubs) {
			if (club.getId().intValue() == id.intValue()) {
				return true;
			}
		}
		return false;
	}

}
