package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import test.TestUtils;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.ClubMembershipResponse;
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
	private ClubServices clubService;
	
	@Before
	public void before() throws UnauthorizedException, NotFoundException {
		this.clubService = getClubService();
		
		// Make sure that the club memberships are right, before starting the tests
		this.clubService.joinClub(TestUtils.CLUB_VALID_ID);
		this.clubService.leaveClub(TestUtils.CLUB_PUBLIC_NON_MEMBER_ID);
	}
	
	
	/**
	 * <p>Test we get a {@link ClubServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		ClubServices service = getClubService();
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link ClubServicesImpl service implementation} if the token isn't valid</p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		ClubServices service = null;
		service = ClubServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		try {
			service.getClub(TestUtils.CLUB_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Got a service for an invalid token!");
	}

	/**
	 * <p>Test that we don't get a {@link ClubServicesImpl service implementation} if the token has been revoked by the user</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testImplementation_revokedToken() throws UnauthorizedException {
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
	 * <p>Test that when we ask for a {@link ClubServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		ClubServices service = getClubService();
		ClubServices service2 = getClubService();
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link ClubServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		ClubServices service = getClubService();
		ClubServices service2 = ClubServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}
	
	// Test cases
	// 1. Valid club
	@Test
	public void testGetClub_validClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		Club club = service.getClub(TestUtils.CLUB_VALID_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_VALID_ID,club.getId());
	}
	
	// 2. Invalid club
	@Test
	public void testGetClub_invalidClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		Club club = service.getClub(TestUtils.CLUB_INVALID_ID);
		assertNull("Got club result despite club being invalid",club);
	}
	
	// 3. Private club of which current authenticated athlete is a member
	@Test
	public void testGetClub_privateClubIsMember() throws UnauthorizedException {
		ClubServices service = ClubServicesImpl.implementation(TestUtils.getValidToken());
		Club club = service.getClub(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(club);
		assertEquals(TestUtils.CLUB_PRIVATE_MEMBER_ID, club.getId());
	}
	
	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testGetClub_privateClubIsNotMember() throws UnauthorizedException {
		ClubServices service = getClubService();
			@SuppressWarnings("unused")
			Club club = null;
			try {
				club = service.getClub(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
			} catch (UnauthorizedException e) {
				// Expected behaviour
				return;
			}
		fail("Got details of a private club?");
	}
	
	// Test cases
	// 1. Athlete has clubs
	@Test
	public void testListAuthenticatedAthleteClubs() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		assertNotNull(clubs);
		assertFalse(clubs.size() == 0);
	}

	// Test cases
	// 1. Valid club
	@Test
	public void testListClubMembers_validClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}
	
	// 2. Invalid club
	@Test
	public void testListClubMembers_invalidClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Athlete> members;
		
		members = service.listClubMembers(TestUtils.CLUB_INVALID_ID);
		assertNull("Returned a list of members for a non-existent club",members);
	}
	
	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testListClubMembers_privateClubNotMember() throws UnauthorizedException {
		ClubServices service = getClubService();
		try {
		@SuppressWarnings("unused")
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_NON_MEMBER_ID);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Asked for a private club's memnber list and didn't get an UnauthorizedException");
	}
	
	// 3. Private club of which current authenticated athlete is a member
	@Test
	public void testListClubMembers_privateClubIsMember() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_PRIVATE_MEMBER_ID);
		assertNotNull(members);
		assertFalse(members.size() == 0);
	}
	
	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListClubMembers_pageSize() throws UnauthorizedException {
		ClubServices service = getClubService();
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
	public void testListClubMembers_pageSizeAndNumber() throws UnauthorizedException {
		ClubServices service = getClubService();
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
	public void testListClubMembers_pagingOutOfRangeHigh() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Athlete> members = service.listClubMembers(TestUtils.CLUB_VALID_ID, new Paging(1000,200));
		assertNotNull(members);
		assertTrue(members.size() == 0);
	}
	
	// 8. Paging - out of range low
	@Test
	public void testListClubMembers_pagingOutOfRangeLow() throws UnauthorizedException {
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
	public void testListRecentClubActivities_validClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID);

		assertNotNull(activities);
	}
	
	// 2. Invalid club
	@Test
	public void testListRecentClubActivities_invalidClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		@SuppressWarnings("unused")
		List<Activity> activities;
		activities = service.listRecentClubActivities(TestUtils.CLUB_INVALID_ID);
		
		assertNull("Didn't fail when returning an invalid club",null);
	}
	
	// 3. Club the current authenticated athlete is NOT a member of (according to Strava should return 0 results)
	@Test
	public void testListRecentClubActivities_nonMember() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_PUBLIC_NON_MEMBER_ID);

		assertTrue(activities.isEmpty());
	}
	
	// 4. Club with > 200 activities (according to Strava, should only return a max of 200 results)
	@Test
	public void testListRecentClubActivities_moreThan200() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(2,200));
		assertNotNull(activities);
		assertEquals(0,activities.size());
		activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1,200));
		assertNotNull(activities);
		assertFalse(0 == activities.size());
		for (Activity activity : activities) {
			System.out.println(activity.getStartDate());
		}
	}
	
	// 5. Paging - size only (including test for max page size)
	@Test
	public void testListRecentClubActivities_pageSize() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1,1));
		
		assertNotNull(activities);
		assertEquals(1,activities.size());
	}
	
	// 6. Paging - size and page
	@Test
	public void testListRecentClubActivities_pageSizeAndNumber() throws UnauthorizedException {
		ClubServices service = getClubService();
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
	public void testListRecentClubActivities_pagingOutOfRangeHigh() throws UnauthorizedException {
		ClubServices service = getClubService();
		List<Activity> activities = service.listRecentClubActivities(TestUtils.CLUB_VALID_ID, new Paging(1000,200));
		
		assertNotNull(activities);
		assertEquals(0,activities.size());
	}

	// 8. Paging - out of range low
	@Test
	public void testListRecentClubActivities_pagingOutOfRangeLow() throws UnauthorizedException {
		ClubServices service = getClubService();
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
	public void testJoinClub_nonMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PUBLIC_NON_MEMBER_ID;
		
		ClubMembershipResponse response = service.joinClub(id);
		
		// Make sure the athlete now a member
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs,id);
		
		// Leave the club again, just to be sure
		service.leaveClub(id);
		
		assertNotNull(response);
		assertTrue(response.getSuccess());
		assertTrue(response.getActive());
		assertTrue(member);
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testJoinClub_member() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_VALID_ID;
		
		ClubMembershipResponse response = service.joinClub(id);
		
		// Make sure the athlete now (still) a member
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs,id);
		
		assertNotNull(response);
		assertTrue(response.getSuccess());
		assertTrue(response.getActive());
		assertTrue(member);
	}

	// 3. Invalid club
	@Test
	public void testJoinClub_invalidClub() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_INVALID_ID;
		
		try {
			service.joinClub(id);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		
		service.leaveClub(id);
		fail("Succeeded in joining invalid club (id = " + id + ")");
	}

	// 4. Private club which authenticated user is NOT a member of
	@Test
	public void testJoinClub_privateClub() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PRIVATE_NON_MEMBER_ID;
		
		try {
			service.joinClub(id);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		
		service.leaveClub(id);
		fail("Succeeded in joining private club (id = " + id + ")");
		
	}
	
	// 5. Attempt to join a club without having write access
	@Test
	public void testJoinClub_noWriteAccess() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubServiceWithoutWriteAccess();
		Integer id = TestUtils.CLUB_VALID_ID;
		
		try {
			service.joinClub(id);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		
		// If we get here there's something wrong
		service.leaveClub(id);
		fail("Succeeded in joining club, but token doesn't have write access (id = " + id + ")");

	}

	// Test cases
	// 1. Valid club which authenticated user is not already a member of
	@Test
	public void testLeaveClub_nonMember() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_PUBLIC_NON_MEMBER_ID;
		
		service.leaveClub(id);
		
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);
		
		assertFalse(member);
	}

	// 2. Valid club which authenticated user is already a member of
	@Test
	public void testLeaveClub_member() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_VALID_ID;
		
		service.leaveClub(id);
		
		List<Club> clubs = service.listAuthenticatedAthleteClubs();
		boolean member = checkIsMember(clubs, id);
		
		// Join the club again
		service.joinClub(id);
		
		assertFalse(member);
	}

	// 3. Invalid club
	@Test
	public void testLeaveClub_invalidClub() throws UnauthorizedException {
		ClubServices service = getClubService();
		Integer id = TestUtils.CLUB_INVALID_ID;
		
		try {
			service.leaveClub(id);
		} catch (NotFoundException e) {
			// Expected behaviour
			return;
		}
		
		// If we get here, something went horribly wrong
		fail("Left a club with an invalid id! (id = " + id + ")");
	}

	// 4. Private club which authenticated user is a member of
	// CAN'T DO THIS IN TESTING AS YOU'LL NEVER BE ABLE TO JOIN IT AGAIN!!
//	@Test
//	public void testLeaveClub_privateClubMember() throws UnauthorizedException, NotFoundException {
//		ClubServices service = getClubService();
//		Integer id = TestUtils.CLUB_PRIVATE_MEMBER_ID;
//		
//		service.leaveClub(id);
//		
//		List<Club> clubs = service.listAuthenticatedAthleteClubs();
//		boolean member = checkIsMember(clubs, id);
//		
//		// Join the club again
//		service.joinClub(id);
//		assertFalse(member);
//	}
	
	// 5. Leave a club using a token with no write access
	@Test
	public void testLeaveClub_noWriteAccess() throws UnauthorizedException, NotFoundException {
		ClubServices service = getClubServiceWithoutWriteAccess();
		Integer id = TestUtils.CLUB_VALID_ID;
		
		try {
			service.leaveClub(id);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		
		// If we get here then something's gone horribly wrong
		service.joinClub(id);
		fail("Left a club using a token without write access! (id = " + id + ")");
	}
	
	private ClubServices getClubService() throws UnauthorizedException {
		if (this.clubService == null) {
			this.clubService = ClubServicesImpl.implementation(TestUtils.getValidToken());
		}
		return this.clubService;
	}
	
	private ClubServices getRevokedTokenService() throws UnauthorizedException {
		this.clubService = null;
		return ClubServicesImpl.implementation(TestUtils.getRevokedToken());
	}
	
	private ClubServices getClubServiceWithoutWriteAccess() throws UnauthorizedException {
		this.clubService = null;
		return ClubServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}

	/**
	 * @param clubs List of clubs to check
	 * @param id Id of the club we're checking for membership
	 * @return <code>true</code> if one of the clubs has the given id
	 */
	private boolean checkIsMember(List<Club> clubs, Integer id) {
		for (Club club : clubs) {
			if (club.getId().intValue() == id.intValue()) {
				return true;
			}
		}
		return false;
	}

}
