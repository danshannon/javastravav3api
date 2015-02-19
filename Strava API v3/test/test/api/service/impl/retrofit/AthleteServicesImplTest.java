/**
 * 
 */
package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.service.AthleteServices;
import javastrava.api.v3.service.Strava;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.AthleteServicesImpl;
import javastrava.util.Paging;

import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * Unit tests for {@link AthleteServicesImpl}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class AthleteServicesImplTest {
	@Test
	public void testImplementation_validToken() {
		AthleteServices services = AthleteServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull(services);
	}

	@Test
	public void testImplementation_invalidToken() {
		AthleteServices services = AthleteServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		try {
			services.getAuthenticatedAthlete();
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a service object using an invalid token");
	}

	@Test
	public void testImplementation_revokedToken() {
		AthleteServices services = AthleteServicesImpl.implementation(TestUtils.getRevokedToken());
		try {
			services.getAuthenticatedAthlete();
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a service object using a revoked token");

	}

	@Test
	public void testImplementation_implementationIsCached() {
		String token = TestUtils.getValidToken();
		AthleteServices service1 = AthleteServicesImpl.implementation(token);
		AthleteServices service2 = AthleteServicesImpl.implementation(token);
		assertTrue(service1 == service2);
	}

	@Test
	public void testImplementation_differentImplementationIsNotCached() {
		String token1 = TestUtils.getValidToken();
		AthleteServices service1 = AthleteServicesImpl.implementation(token1);

		String token2 = TestUtils.getValidTokenWithoutWriteAccess();
		assertFalse(token1.equals(token2));
		AthleteServices service2 = AthleteServicesImpl.implementation(token2);
		assertFalse(service1 == service2);
	}

	@Test
	public void testGetAuthenticatedAthlete() {
		AthleteServices service = getService();
		StravaAthlete athlete = service.getAuthenticatedAthlete();
		assertEquals(TestUtils.ATHLETE_AUTHENTICATED_ID, athlete.getId());
	}

	@Test
	public void testGetAthlete_validAthlete() {
		AthleteServices service = getService();
		StravaAthlete athlete = service.getAthlete(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(athlete);
		assertEquals(TestUtils.ATHLETE_VALID_ID, athlete.getId());
	}

	@Test
	public void testGetAthlete_invalidAthlete() {
		AthleteServices service = getService();
		StravaAthlete athlete = service.getAthlete(TestUtils.ATHLETE_INVALID_ID);
		assertNull(athlete);

	}

	@Test
	public void testGetAthlete_privateAthlete() {
		AthleteServices service = getService();
		StravaAthlete athlete = service.getAthlete(TestUtils.ATHLETE_PRIVATE_ID);
		assertNotNull(athlete);
		assertEquals(TestUtils.ATHLETE_PRIVATE_ID, athlete.getId());
		// StravaAthlete privateAthlete = new StravaAthlete();
		// privateAthlete.setId(TestUtils.ATHLETE_PRIVATE_ID);
		// assertEquals(privateAthlete,athlete);
	}

	@Test
	public void testUpdateAuthenticatedAthlete() {
		AthleteServices service = getService();
		StravaAthlete athlete = service.getAuthenticatedAthlete();

		String city = athlete.getCity();
		String state = athlete.getState();
		StravaGender sex = athlete.getSex();
		String country = athlete.getCountry();
		athlete.setWeight(92.0f);
		athlete = service.updateAuthenticatedAthlete(null, null, null, null, new Float(92));
		athlete = service.updateAuthenticatedAthlete(city, state, country, sex, null);

	}

	@Test
	public void testUpdateAuthenticatedAthlete_noWriteAccess() {
		AthleteServices service = getServiceWithoutWriteAccess();
		@SuppressWarnings("unused")
		StravaAthlete athlete = service.getAuthenticatedAthlete();

		try {
			athlete = service.updateAuthenticatedAthlete(null, null, null, null, new Float(92));
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Succesfully updated authenticated athlete despite not having write access");

	}

	// Test cases
	// 1. Valid athlete with some KOM's
	@Test
	public void testListAthleteKOMs_withKOM() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertNotNull(koms);
		assertFalse(koms.size() == 0);
	}

	// 2. Valid athlete with no KOM's
	@Test
	public void testListAthleteKOMs_withoutKOM() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_WITHOUT_KOMS);
		assertNotNull(koms);
		assertTrue(koms.size() == 0);
	}

	// 2. Invalid athlete
	@Test
	public void testListAthleteKOMs_invalidAthlete() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = null;
		koms = service.listAthleteKOMs(TestUtils.ATHLETE_INVALID_ID);

		assertNull(koms);
	}

	// 3. Paging - size only
	@Test
	public void testListAthleteKOMs_pageSize() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 1));
		assertEquals(1, koms.size());
	}

	// 4. Paging - size and page
	@Test
	public void testListAthleteKOMs_pageSizeAndNumber() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(2, 1));
		Long effortId = koms.get(0).getId();
		assertEquals(1, koms.size());
		koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2));
		assertEquals(2, koms.size());
		assertEquals(effortId, koms.get(1).getId());
	}

	// 5. Paging - out of range high
	@Test
	public void testListAthleteKOMs_pagingOutOfRangeHigh() {
		AthleteServices service = getService();
		List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1000, 200));
		assertEquals(0, koms.size());
	}

	// 6. Paging - out of range low
	@Test
	public void testListAthleteKOMs_pagingOutOfRangeLow() {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<StravaSegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Illegal paging parameters were accepted");
	}

	@Test
	public void testListAthleteKOMs_pagingIgnoreFirstN() {
		List<StravaSegmentEffort> efforts = getService().listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 1, 0));
		assertNotNull(efforts);
		assertEquals(1, efforts.size());
		List<StravaSegmentEffort> expectedEfforts = getService().listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertEquals(expectedEfforts.get(1), efforts.get(0));
	}

	@Test
	public void testListAthleteKOMs_pagingIgnoreLastN() {
		List<StravaSegmentEffort> efforts = getService().listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 0, 1));
		assertNotNull(efforts);
		assertEquals(1, efforts.size());
		List<StravaSegmentEffort> expectedEfforts = getService().listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertEquals(expectedEfforts.get(0), efforts.get(0));
	}

	@Test
	public void testListAuthenticatedAthleteFriends_friends() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAuthenticatedAthleteFriends();
		assertNotNull(friends);
		assertFalse(friends.isEmpty());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pageSize() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAuthenticatedAthleteFriends(new Paging(1, 1));
		assertNotNull(friends);
		assertEquals(1, friends.size());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pageSizeAndNumber() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAuthenticatedAthleteFriends(new Paging(2, 1));
		assertNotNull(friends);
		assertEquals(1, friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAuthenticatedAthleteFriends(new Paging(1, 2));
		assertNotNull(friends);
		assertEquals(2, friends.size());
		assertEquals(friendId, friends.get(1).getId());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingOutOfRangeHigh() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAuthenticatedAthleteFriends(new Paging(1000, 200));
		assertNotNull(friends);
		assertEquals(0, friends.size());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingOutOfRangelow() {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<StravaAthlete> friends = service.listAuthenticatedAthleteFriends(new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Illegal paging parameters were accepted");
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingIgnoreFirstN() {
		List<StravaAthlete> athletes = getService().listAuthenticatedAthleteFriends(new Paging(1, 2, 1, 0));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingIgnoreLastN() {
		List<StravaAthlete> athletes = getService().listAuthenticatedAthleteFriends(new Paging(1, 2, 0, 1));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	@Test
	public void testListAthleteFriends_validAthlete() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
	}

	// Test cases
	// 2. Invalid athlete
	@Test
	public void testListAthleteFriends_invalidAthlete() {
		AthleteServices service = getService();
		List<StravaAthlete> friends;
		friends = service.listAthleteFriends(TestUtils.ATHLETE_INVALID_ID);

		assertNull("Listed friends despite athlete id being invalid", friends);

	}

	// 4. Paging - size only (including test for max page size)
	@Test
	public void testListAthleteFriends_pageSize() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1, 1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1, friends.size());
	}

	// 5. Paging - size and page
	@Test
	public void testListAthleteFriends_pageNumberAndSize() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(2, 1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1, friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1, 2));
		assertNotNull(friends);
		assertEquals(2, friends.size());
		assertEquals(friendId, friends.get(1).getId());
	}

	// 6. Paging - out of range high
	@Test
	public void testListAthleteFriends_pagingOutOfRangeHigh() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1000, 200));
		assertNotNull(friends);
		assertEquals(0, friends.size());
	}

	// 7. Paging - out of range low
	@Test
	public void testListAthleteFriends_pagingOutOfRangeLow() {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<StravaAthlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Listed friends despite paging instructions being illegal");
	}

	@Test
	public void testListAthleteFriends_pagingIgnoreFirstN() {
		List<StravaAthlete> friends = getService().listAthleteFriends(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 1, 0));
		assertNotNull(friends);
		assertEquals(1, friends.size());
	}

	@Test
	public void testListAthleteFriends_pagingIgnoreLastN() {
		List<StravaAthlete> friends = getService().listAthleteFriends(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, 2, 0, 1));
		assertNotNull(friends);
		assertEquals(1, friends.size());
	}

	@Test
	public void testListAthleteFriends_pagingPageSizeTooLarge() {
		List<StravaAthlete> friends = getService().listAthleteFriends(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1, Strava.MAX_PAGE_SIZE + 1));
		assertNotNull(friends);
	}

	// Test cases
	// 1. Valid athlete - at least 1 common friend
	@Test
	public void testListAthletesBothFollowing_validAthlete() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
	}

	// 2. Invalid other athlete
	@Test
	public void testListAthletesBothFollowing_invalidAthlete() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_INVALID_ID);
		assertNull("Returned list of athletes being followed by invalid athlete", friends);
	}

	// 4. Paging - size only (including test for max page size)
	@Test
	public void testListAthletesBothFollowing_pageSize() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1, 1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1, friends.size());
	}

	// 5. Paging - size and page
	@Test
	public void testListAthletesBothFollowing_pageSizeAndNumber() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(2, 1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1, friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1, 2));
		assertNotNull(friends);
		assertEquals(2, friends.size());
		assertEquals(friendId, friends.get(1).getId());
	}

	// 6. Paging - out of range high
	@Test
	public void testListAthletesBothFollowing_pagingOutOfRangeHigh() {
		AthleteServices service = getService();
		List<StravaAthlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1000, 200));
		assertNotNull(friends);
		assertEquals(0, friends.size());
	}

	// 7. Paging - out of range low
	@Test
	public void testListAthletesBothFollowing_pagingOutOfRangeLow() {
		AthleteServices service = getService();
		@SuppressWarnings("unused")
		List<StravaAthlete> friends;
		try {
			friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(-1, -1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Listed friends despite paging instructions being illegal");
	}

	@Test
	public void testListAthletesBothFollowing_pagingIgnoreFirstN() {
		List<StravaAthlete> athletes = getService().listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1, 2, 1, 0));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	@Test
	public void testListAthletesBothFollowing_pagingIgnoreLastN() {
		List<StravaAthlete> athletes = getService().listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1, 2, 0, 1));
		assertNotNull(athletes);
		assertEquals(1, athletes.size());
	}

	@Test
	public void testStatistics_authenticatedAthlete() {
		StravaStatistics stats = getService().statistics(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertNotNull(stats);
	}

	@Test
	public void testStatistics_otherAthlete() {
		StravaStatistics stats = getService().statistics(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(stats);
	}

	@Test
	public void testStatistics_invalidAthlete() {
		StravaStatistics stats = getService().statistics(TestUtils.ATHLETE_INVALID_ID);
		assertNull(stats);
	}

	/**
	 * @return
	 * @throws UnauthorizedException
	 */
	private AthleteServices getService() {
		return AthleteServicesImpl.implementation(TestUtils.getValidToken());
	}

	private AthleteServices getServiceWithoutWriteAccess() {
		return AthleteServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}

}
