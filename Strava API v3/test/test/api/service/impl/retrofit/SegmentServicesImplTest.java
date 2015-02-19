package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import javastrava.api.v3.model.StravaMapPoint;
import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaSegmentExplorerResponse;
import javastrava.api.v3.model.StravaSegmentExplorerResponseSegment;
import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.api.v3.model.StravaSegmentLeaderboardEntry;
import javastrava.api.v3.model.reference.StravaAgeGroup;
import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.service.SegmentServices;
import javastrava.api.v3.service.Strava;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.SegmentServicesImpl;
import javastrava.util.Paging;

import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * Unit tests for {@link SegmentServicesImpl}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class SegmentServicesImplTest {
	/**
	 * <p>
	 * Test we get a {@link SegmentServicesImpl service implementation} successfully with a valid token
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		SegmentServices service = SegmentServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}

	/**
	 * <p>
	 * Test that we don't get a {@link SegmentServicesImpl service implementation} if the token isn't valid
	 * </p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		try {
			SegmentServices service = SegmentServicesImpl.implementation(TestUtils.INVALID_TOKEN);
			service.getSegment(TestUtils.SEGMENT_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Got a working service for an invalid token!");
	}

	/**
	 * <p>
	 * Test that we don't get a {@link SegmentServicesImpl service implementation} if the token has been revoked by the user
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_revokedToken() throws UnauthorizedException {
		SegmentServices service = SegmentServicesImpl.implementation(TestUtils.getRevokedToken());
		try {
			service.getSegment(TestUtils.SEGMENT_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Got a working service for a revoked token!");
	}

	/**
	 * <p>
	 * Test that when we ask for a {@link SegmentServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching
	 * strategy is working)
	 * </p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		SegmentServices service = SegmentServicesImpl.implementation(TestUtils.getValidToken());
		SegmentServices service2 = SegmentServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one", service, service2);
	}

	/**
	 * <p>
	 * Test that when we ask for a {@link SegmentServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		SegmentServices service = SegmentServicesImpl.implementation(TestUtils.getValidToken());
		SegmentServices service2 = SegmentServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}

	// Test cases:
	// 1. Valid segment
	@Test
	public void testGetSegment_validSegment() throws UnauthorizedException {
		SegmentServices service = getService();
		StravaSegment segment = service.getSegment(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(segment);
	}

	// 2. Invalid segment
	@Test
	public void testGetSegment_invalidSegment() throws UnauthorizedException {
		SegmentServices service = getService();
		StravaSegment segment = service.getSegment(TestUtils.SEGMENT_INVALID_ID);
		assertNull(segment);
	}

	// 3. Private segment belonging to another user
	@Test
	public void testGetSegment_otherUserPrivateSegment() throws UnauthorizedException {
		SegmentServices service = getService();
		try {
			service.getSegment(TestUtils.SEGMENT_OTHER_USER_PRIVATE_ID);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		fail("Returned segment details for a private segment that belongs to another user");
	}

	// 4. Private segment belonging to the authenticated user
	@Test
	public void testGetSegment_private() throws UnauthorizedException {
		SegmentServices service = getService();
		StravaSegment segment = service.getSegment(TestUtils.SEGMENT_PRIVATE_ID);
		assertNotNull(segment);
		assertEquals(TestUtils.SEGMENT_PRIVATE_ID, segment.getId());
	}

	// Test cases:
	// 1. No paging
	@Test
	public void testListAuthenticatedAthleteStarredSegments_noPaging() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listAuthenticatedAthleteStarredSegments();
		assertNotNull(segments);
		assertFalse(segments.size() == 0);
	}

	// 2. Paging size only
	@Test
	public void testListAuthenticatedAthleteStarredSegments_pageSize() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listAuthenticatedAthleteStarredSegments(new Paging(1, 1));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}

	// 3. Paging size and number
	@Test
	public void testListAuthenticatedAthleteStarredSegments_pageSizeAndNumber() {
		SegmentServices service = getService();
		List<StravaSegment> defaultPage = service.listAuthenticatedAthleteStarredSegments(new Paging(1, 2));
		assertEquals(2, defaultPage.size());

		List<StravaSegment> firstPage = service.listAuthenticatedAthleteStarredSegments(new Paging(1, 1));
		assertEquals(1, firstPage.size());

		List<StravaSegment> secondPage = service.listAuthenticatedAthleteStarredSegments(new Paging(2, 1));
		assertEquals(1, secondPage.size());

		assertEquals(firstPage.get(0).getId(), defaultPage.get(0).getId());
		assertEquals(secondPage.get(0).getId(), defaultPage.get(1).getId());
	}

	// 4. Paging out of range low
	@Test
	public void testListAuthenticatedAthleteStarredSegments_pagingOutOfRangeLow() {
		SegmentServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<StravaSegment> segments = service.listAuthenticatedAthleteStarredSegments(new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected result
			return;
		}
		fail("Asked for -1th page, still got something!");
	}

	// 5. Paging out of range high
	@Test
	public void testListAuthenticatedAthleteStarredSegments_pagingOutOfRangeHigh() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listAuthenticatedAthleteStarredSegments(new Paging(1000, 200));
		assertNotNull(segments);
		assertEquals(0, segments.size());
	}

	// 6. Paging with over-sized page size
	@Test
	public void testListAuthenticatedAthleteStarredSegments_pageSizeTooLarge() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listAuthenticatedAthleteStarredSegments(new Paging(1, 201));
		assertNotNull(segments);
		assertFalse(0 == segments.size());
	}

	@Test
	public void testListAuthenticatedAthleteStarredSegments_pagingIgnoreFirstN() {
		List<StravaSegment> segments = getService().listAuthenticatedAthleteStarredSegments(new Paging(1, 2, 1, 0));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}

	@Test
	public void testListAuthenticatedAthleteStarredSegments_pagingIgnoreLastN() {
		List<StravaSegment> segments = getService().listAuthenticatedAthleteStarredSegments(new Paging(1, 2, 0, 1));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}

	// Test cases
	// 1. No filtering, valid segment
	@Test
	public void testListSegmentEfforts_validSegment() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(efforts);
		assertFalse(efforts.size() == 0);
	}

	// 2. No filtering, invalid segment
	@Test
	public void testListSegmentEfforts_invalidSegment() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_INVALID_ID);
		assertNull(efforts);
	}

	// 3. Filter by valid athlete, valid segment
	@Test
	public void testListSegmentEfforts_filterByValidAthlete() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, TestUtils.ATHLETE_AUTHENTICATED_ID, null, null);
		assertNotNull(efforts);
		assertFalse(0 == efforts.size());
		for (StravaSegmentEffort effort : efforts) {
			assertEquals(TestUtils.ATHLETE_AUTHENTICATED_ID, effort.getAthlete().getId());
		}
	}

	// 4. Filter by invalid athlete, valid segment
	@Test
	public void testListSegmentEfforts_filterByInvalidAthlete() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, TestUtils.ATHLETE_INVALID_ID, null, null);
		assertNull(efforts);
	}

	// 5. Filter by start date, valid segment
	@Test
	public void testListSegmentEfforts_filterByStartDate() {
		SegmentServices service = getService();
		Calendar startDate = Calendar.getInstance();
		startDate.set(2014, Calendar.JANUARY, 1, 0, 0, 0);

		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, startDate, null);
		assertNotNull(efforts);
		assertFalse(0 == efforts.size());
		for (StravaSegmentEffort effort : efforts) {
			assertNotNull(effort.getStartDateLocal());
			assertTrue(effort.getStartDateLocal().after(startDate.getTime()));
		}

	}

	// 6. Filter by end date, valid segment
	@Test
	public void testListSegmentEfforts_filterByEndDate() {
		SegmentServices service = getService();
		Calendar endDate = Calendar.getInstance();
		endDate.set(2013, Calendar.DECEMBER, 31, 23, 59, 59);

		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, null, endDate);
		assertNotNull(efforts);
		assertFalse(0 == efforts.size());
		for (StravaSegmentEffort effort : efforts) {
			assertNotNull(effort.getStartDateLocal());
			assertTrue(effort.getStartDateLocal().before(endDate.getTime()));
		}
	}

	// 7. Filter by date range, valid segment
	@Test
	public void testListSegmentEfforts_filterByDateRange() {
		SegmentServices service = getService();
		Calendar startDate = Calendar.getInstance();
		startDate.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2014, Calendar.JANUARY, 31, 23, 59, 59);

		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, startDate, endDate);
		assertNotNull(efforts);
		assertFalse(0 == efforts.size());
		for (StravaSegmentEffort effort : efforts) {
			assertNotNull(effort.getStartDateLocal());
			assertTrue(effort.getStartDateLocal().after(startDate.getTime()));
			assertTrue(effort.getStartDateLocal().before(endDate.getTime()));
		}
	}

	// 8. Paging size only
	@Test
	public void testListSegmentEfforts_pageSizeOnly() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 1));
		assertNotNull(efforts);
		assertFalse(efforts.size() == 0);
		assertEquals(1, efforts.size());
	}

	// 9. Paging size and number
	@Test
	public void testListSegmentEfforts_pageSizeAndNumber() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2));
		assertNotNull(efforts);
		assertEquals(2, efforts.size());

		List<StravaSegmentEffort> page1 = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 1));
		List<StravaSegmentEffort> page2 = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(2, 1));

		assertNotNull(page1);
		assertNotNull(page2);
		assertEquals(1, page1.size());
		assertEquals(1, page2.size());
		assertEquals(efforts.get(0).getId(), page1.get(0).getId());
		assertEquals(efforts.get(1).getId(), page2.get(0).getId());
	}

	// 10. Paging out of range high
	@Test
	public void testListSegmentEfforts_pagingOutOfRangeHigh() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1000, 200));

		assertNotNull(efforts);
		assertEquals(0, efforts.size());
	}

	// 11. Paging out of range low
	@Test
	public void testListSegmentEfforts_pagingOutOfRangeLow() {
		SegmentServices service = getService();

		try {
			@SuppressWarnings("unused")
			List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Returned something from the service when asking for page -1!");
	}

	// 12. Paging size too large (for Strava)
	@Test
	public void testListSegmentEfforts_pageSizeTooLarge() {
		SegmentServices service = getService();
		List<StravaSegmentEffort> efforts = service.listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 201));

		assertNotNull(efforts);
		assertEquals(201, efforts.size());
	}

	@Test
	public void testListSegmentEfforts_pagingIgnoreFirstN() {
		List<StravaSegmentEffort> efforts = getService().listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2, 1, 0));
		assertNotNull(efforts);
		assertEquals(1, efforts.size());
	}

	@Test
	public void testListSegmentEfforts_pagingIgnoreLastN() {
		List<StravaSegmentEffort> efforts = getService().listSegmentEfforts(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2, 0, 1));
		assertNotNull(efforts);
		assertEquals(1, efforts.size());
	}

	// Test cases
	// 1. Valid segment, no filtering
	@Test
	public void testGetSegmentLeaderboard_validSegment() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(leaderboard);
		for (StravaSegmentLeaderboardEntry entry : leaderboard.getEntries()) {
			assertNotNull(entry.getEffortId());
		}
	}

	// 2. Invalid segment
	@Test
	public void testGetSegmentLeaderboard_invalidSegment() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_INVALID_ID);
		assertNull(leaderboard);
	}

	// 3. Filter by gender
	@Test
	public void testGetSegmentLeaderboard_filterByGender() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, StravaGender.FEMALE, null, null, null, null, null,
				null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
		for (StravaSegmentLeaderboardEntry entry : leaderboard.getEntries()) {
			assertEquals(StravaGender.FEMALE, entry.getAthleteGender());
		}
	}

	// 4. Filter by age group
	@Test
	public void testGetSegmentLeaderboard_filterByAgeGroup() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, StravaAgeGroup.AGE35_44, null, null, null, null,
				null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 5. Filter by weight class
	@Test
	public void testGetSegmentLeaderboard_filterByWeightClass() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, null, StravaWeightClass.KG75_84, null, null,
				null, null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 6. Filter by athletes the authenticated user is following
	@Test
	public void testGetSegmentLeaderboard_filterByFollowing() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, null, null, Boolean.TRUE, null, null, null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 7. Filter by valid club
	@Test
	public void testGetSegmentLeaderboard_filterByClub() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, null, null, null, TestUtils.CLUB_VALID_ID, null,
				null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 8. Filter by invalid club
	@Test
	public void testGetSegmentLeaderboard_filterByInvalidClub() {
		SegmentServices service = getService();
		@SuppressWarnings("unused")
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, null, null, null, TestUtils.CLUB_INVALID_ID,
				null, null);
		// TODO There's a Strava bug here - returns the full leaderboard
		// assertNull(leaderboard);
	}

	// 9. Filter by leaderboard date range
	@Test
	public void testGetSegmentLeaderboard_filterByLeaderboardDateRange() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, null, null, null, null, null,
				StravaLeaderboardDateRange.THIS_YEAR, null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 10. Filter by ALL options combined
	@Test
	public void testGetSegmentLeaderboard_filterByAllOptions() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, StravaGender.MALE, StravaAgeGroup.AGE45_54,
				StravaWeightClass.KG85_94, Boolean.FALSE, TestUtils.CLUB_VALID_ID, StravaLeaderboardDateRange.THIS_YEAR, null);
		assertNotNull(leaderboard);
		assertFalse(leaderboard.getEntries().isEmpty());
	}

	// 11. Paging size only
	@Test
	public void testGetSegmentLeaderboard_pagingSize() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 1));
		assertNotNull(leaderboard);
		assertTrue(1 == leaderboard.getEntries().size());
	}

	// 12. Paging size and number
	@Test
	public void testGetSegmentLeaderboard_pagingSizeAndNumber() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2));
		StravaSegmentLeaderboard page1 = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 1));
		StravaSegmentLeaderboard page2 = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(2, 1));

		assertEquals(leaderboard.getEntries().get(0).getEffortId(), page1.getEntries().get(0).getEffortId());
		assertEquals(leaderboard.getEntries().get(1).getEffortId(), page2.getEntries().get(0).getEffortId());

	}

	// 13. Paging out of range high
	@Test
	public void testGetSegmentLeaderboard_pagingOutOfRangeHigh() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1000, 200));
		assertNotNull(leaderboard);
		assertEquals(0, leaderboard.getEntries().size());
	}

	// 14. Paging out of range low
	@Test
	public void testGetSegmentLeaderboard_pagingOutOfRangeLow() {
		SegmentServices service = getService();
		try {
			@SuppressWarnings("unused")
			StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Asked for page -1, didn't fall over!");
	}

	// 15. Paging size too large (for Strava)
	@Test
	public void testGetSegmentLeaderboard_pagingSizeTooLarge() {
		SegmentServices service = getService();
		StravaSegmentLeaderboard leaderboard = service.getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 201));
		assertNotNull(leaderboard);
	}

	@Test
	public void testGetSegmentLeaderboard_pagingIgnoreFirstN() {
		StravaSegmentLeaderboard leaderBoard = getService().getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2, 1, 0));
		assertNotNull(leaderBoard);
		assertEquals(1, leaderBoard.getEntries().size());
	}

	@Test
	public void testGetSegmentLeaderboard_pagingIgnoreLastN() {
		StravaSegmentLeaderboard leaderBoard = getService().getSegmentLeaderboard(TestUtils.SEGMENT_VALID_ID, new Paging(1, 2, 0, 1));
		assertNotNull(leaderBoard);
		assertEquals(1, leaderBoard.getEntries().size());
	}

	// Test cases
	// 1. Normal
	@Test
	public void testSegmentExplore_normal() {
		SegmentServices service = getService();
		StravaSegmentExplorerResponse response = service.segmentExplore(new StravaMapPoint(-39.4f, 136f), new StravaMapPoint(-25f, 154f), null, null, null);
		assertNotNull(response);
	}

	// 2. Filter by activity type
	@Test
	public void testSegmentExplore_filterByActivityType() {
		SegmentServices service = getService();
		StravaSegmentExplorerResponse response = service.segmentExplore(new StravaMapPoint(-39.4f, 136f), new StravaMapPoint(-25f, 154f),
				StravaSegmentExplorerActivityType.RUNNING, null, null);
		assertNotNull(response);
	}

	// 3. Filter by minimum category
	@Test
	public void testSegmentExplore_filterByMinimumCategory() {
		SegmentServices service = getService();
		StravaSegmentExplorerResponse response = service.segmentExplore(new StravaMapPoint(-39.4f, 136f), new StravaMapPoint(-25f, 154f), null,
				StravaClimbCategory.HORS_CATEGORIE, null);
		assertNotNull(response);
		for (StravaSegmentExplorerResponseSegment segment : response.getSegments()) {
			assertTrue(segment.getClimbCategory().getValue() >= StravaClimbCategory.HORS_CATEGORIE.getValue());
		}
	}

	// 4. Filter by maximum category
	@Test
	public void testSegmentExplore_filterByMaximumCategory() {
		SegmentServices service = getService();
		StravaSegmentExplorerResponse response = service.segmentExplore(new StravaMapPoint(-39.4f, 136f), new StravaMapPoint(-25f, 154f), null, null,
				StravaClimbCategory.CATEGORY1);
		assertNotNull(response);
		for (StravaSegmentExplorerResponseSegment segment : response.getSegments()) {
			assertTrue(segment.getClimbCategory().getValue() <= StravaClimbCategory.CATEGORY1.getValue());
			;
		}
	}

	// 5. Filter by both minimum and maximum category
	@Test
	public void testSegmentExplore_filterMaxAndMinCategory() {
		SegmentServices service = getService();
		StravaSegmentExplorerResponse response = service.segmentExplore(new StravaMapPoint(-39.4f, 136f), new StravaMapPoint(-25f, 154f), null, null, null);
		assertNotNull(response);
	}

	private SegmentServices getService() {
		return SegmentServicesImpl.implementation(TestUtils.getValidToken());
	}

	@Test
	public void testListStarredSegments_authenticatedUser() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertNotNull(segments);
	}

	@Test
	public void testListStarredSegments_otherUser() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listStarredSegments(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(segments);
	}

	@Test
	public void testListStarredSegments_invalidAthlete() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listStarredSegments(TestUtils.ATHLETE_INVALID_ID);
		assertNull(segments);
	}

	@Test
	public void testListStarredSegments_privateAthlete() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listStarredSegments(TestUtils.ATHLETE_PRIVATE_ID);
		assertNotNull(segments);
		assertEquals(0, segments.size());
	}

	@Test
	public void testListStarredSegments_pagingOutOfRangeHigh() {
		SegmentServices service = getService();
		List<StravaSegment> segments = service.listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1000, 200));
		assertNotNull(segments);
		assertEquals(0, segments.size());
	}

	@Test
	public void testListStarredSegments_pagingOutOfRangeLow() {
		SegmentServices service = getService();
		try {
			service.listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Got starred segments for page -1, size -1");
	}

	@Test
	public void testListStarredSegments_pagingSize() {
		List<StravaSegment> segments = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 1));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}

	@Test
	public void testListStarredSegments_pagingSizeAndNumber() {
		List<StravaSegment> pageOf2 = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2));
		List<StravaSegment> page1Of1 = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 1));
		List<StravaSegment> page2Of1 = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(2, 1));
		assertNotNull(pageOf2);
		assertEquals(2, pageOf2.size());
		assertEquals(1, page1Of1.size());
		assertEquals(1, page2Of1.size());
		assertEquals(pageOf2.get(0), page1Of1.get(0));
		assertEquals(pageOf2.get(1), page2Of1.get(0));
	}

	@Test
	public void testListStarredSegments_pagingSizeTooLarge() {
		List<StravaSegment> segments = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, Strava.MAX_PAGE_SIZE + 1));
		assertNotNull(segments);
		// Don't know a priori how many we'll get so no further testing...
	}

	@Test
	public void testListStarredSegments_pagingIgnoreFirstN() {
		List<StravaSegment> segments = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 1, 0));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}

	@Test
	public void testListStarredSegments_pagingIgnoreLastN() {
		List<StravaSegment> segments = getService().listStarredSegments(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 0, 1));
		assertNotNull(segments);
		assertEquals(1, segments.size());
	}
}
