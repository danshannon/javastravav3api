/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.AthleteServices;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.AthleteServicesImpl;
import com.danshannon.strava.util.Paging;

/**
 * <p>Unit tests for {@link AthleteServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class AthleteServicesImplTest {
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
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
	public void testImplementation_revokedToken() throws UnauthorizedException {
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
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		String token = TestUtils.getValidToken();
		AthleteServices service1 = AthleteServicesImpl.implementation(token);
		AthleteServices service2 = AthleteServicesImpl.implementation(token);
		assertTrue(service1 == service2);
	}
	
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		String token1 = TestUtils.getValidToken();
		AthleteServices service1 = AthleteServicesImpl.implementation(token1);
		
		String token2 = TestUtils.getValidTokenWithoutWriteAccess();
		assertFalse(token1.equals(token2));
		AthleteServices service2 = AthleteServicesImpl.implementation(token2);
		assertFalse(service1 == service2);
	}
	
	@Test
	public void testGetAuthenticatedAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		Athlete athlete = service.getAuthenticatedAthlete();
		assertEquals(TestUtils.ATHLETE_AUTHENTICATED_ID,athlete.getId());
	}
	
	@Test
	public void testGetAthlete_validAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		Athlete athlete = service.getAthlete(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(athlete);
		assertEquals(TestUtils.ATHLETE_VALID_ID,athlete.getId());
	}
	
	@Test
	public void testGetAthlete_invalidAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		Athlete athlete = service.getAthlete(TestUtils.ATHLETE_INVALID_ID);
		assertNull(athlete);
		
	}
	
	@Test
	public void testGetAthlete_privateAthlete() {
		// TODO Not yet implemented
	}
	
	@Test
	public void testUpdateAuthenticatedAthlete() throws UnauthorizedException, NotFoundException {
		AthleteServices service = getService();
		Athlete athlete = service.getAuthenticatedAthlete();
		
		String city = athlete.getCity();
		String state = athlete.getState();
		Gender sex = athlete.getSex();
		String country = athlete.getCountry();
		athlete.setWeight(92.0f);
		athlete = service.updateAuthenticatedAthlete(null, null, null, null, new Float(92));
		athlete = service.updateAuthenticatedAthlete(city, state, country, sex, null);
		
	}
	
	@Test
	public void testUpdateAuthenticatedAthlete_noWriteAccess() throws UnauthorizedException, NotFoundException {
		AthleteServices service = getServiceWithoutWriteAccess();
		@SuppressWarnings("unused")
		Athlete athlete = service.getAuthenticatedAthlete();
		
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
	public void testListAthleteKOMs_withKOM() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID);
		assertNotNull(koms);
		assertFalse(koms.size() == 0);
	}

	// 2. Valid athlete with no KOM's
	@Test
	public void testListAthleteKOMs_withoutKOM() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_WITHOUT_KOMS);
		assertNotNull(koms);
		assertTrue(koms.size() == 0);
	}

	// 2. Invalid athlete
	@Test
	public void testListAthleteKOMs_invalidAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = null;
		koms = service.listAthleteKOMs(TestUtils.ATHLETE_INVALID_ID);

		assertNull(koms);
	}

	// 3. Paging - size only 
	@Test
	public void testListAthleteKOMs_pageSize() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1,1));
		assertEquals(1,koms.size());
	}

	// 4. Paging - size and page
	@Test
	public void testListAthleteKOMs_pageSizeAndNumber() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(2,1));
		Long effortId = koms.get(0).getId();
		assertEquals(1,koms.size());
		koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1,2));
		assertEquals(2,koms.size());
		assertEquals(effortId,koms.get(1).getId());
	}

	// 5. Paging - out of range high
	@Test
	public void testListAthleteKOMs_pagingOutOfRangeHigh() throws UnauthorizedException {
		AthleteServices service = getService();
		List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(1000,200));
		assertEquals(0,koms.size());
	}

	// 6. Paging - out of range low
	@Test
	public void testListAthleteKOMs_pagingOutOfRangeLow() throws UnauthorizedException {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<SegmentEffort> koms = service.listAthleteKOMs(TestUtils.ATHLETE_AUTHENTICATED_ID, new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Illegal paging parameters were accepted");
	}

	@Test
	public void testListAuthenticatedAthleteFriends_friends() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAuthenticatedAthleteFriends();
		assertNotNull(friends);
		assertFalse(friends.isEmpty());
	}
	
	@Test
	public void testListAuthenticatedAthleteFriends_pageSize() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAuthenticatedAthleteFriends(new Paging(1,1));
		assertNotNull(friends);
		assertEquals(1,friends.size());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pageSizeAndNumber() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAuthenticatedAthleteFriends(new Paging(2,1));
		assertNotNull(friends);
		assertEquals(1,friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAuthenticatedAthleteFriends(new Paging(1,2));
		assertNotNull(friends);
		assertEquals(2,friends.size());
		assertEquals(friendId,friends.get(1).getId());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingOutOfRangeHigh() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAuthenticatedAthleteFriends(new Paging(1000,200));
		assertNotNull(friends);
		assertEquals(0,friends.size());
	}

	@Test
	public void testListAuthenticatedAthleteFriends_pagingOutOfRangelow() throws UnauthorizedException {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<Athlete> friends = service.listAuthenticatedAthleteFriends(new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Illegal paging parameters were accepted");
	}

	@Test
	public void testListAthleteFriends_validAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(friends);
		assertFalse(friends.size() == 0);		
	}

	// Test cases
	// 2. Invalid athlete
	@Test
	public void testListAthleteFriends_invalidAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends;
		friends = service.listAthleteFriends(TestUtils.ATHLETE_INVALID_ID);
		
		assertNull("Listed friends despite athlete id being invalid",friends);

	}

	// 4. Paging - size only (including test for max page size)
	public void testListAthleteFriends_pageSize() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1,1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1,friends.size());
	}

	// 5. Paging - size and page
	public void testListAthleteFriends_pageNumberAndSize() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(2,1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1,friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1,2));
		assertNotNull(friends);
		assertEquals(2,friends.size());
		assertEquals(friendId, friends.get(1).getId());
	}

	// 6. Paging - out of range high
	public void testListAthleteFriends_pagingOutOfRangeHigh() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(1000,200));
		assertNotNull(friends);
		assertEquals(0,friends.size());
	}

	// 7. Paging - out of range low
	public void testListAthleteFriends_pagingOutOfRangeLow() throws UnauthorizedException {
		AthleteServices service = getService();
		try {
			@SuppressWarnings("unused")
			List<Athlete> friends = service.listAthleteFriends(TestUtils.ATHLETE_VALID_ID, new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Listed friends despite paging instructions being illegal");
	}

	// Test cases
	// 1. Valid athlete - at least 1 common friend
	@Test
	public void testListAthletesBothFollowing_validAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID);
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
	}

	// 2. Invalid other athlete
	@Test
	public void testListAthletesBothFollowing_invalidAthlete() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_INVALID_ID);
		assertNull("Returned list of athletes being followed by invalid athlete",friends);
	}

	// 4. Paging - size only (including test for max page size)
	@Test
	public void testListAthletesBothFollowing_pageSize() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1,1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1,friends.size());
	}

	// 5. Paging - size and page
	@Test
	public void testListAthletesBothFollowing_pageSizeAndNumber() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(2,1));
		assertNotNull(friends);
		assertFalse(friends.size() == 0);
		assertEquals(1,friends.size());
		Integer friendId = friends.get(0).getId();
		friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1,2));
		assertNotNull(friends);
		assertEquals(2,friends.size());
		assertEquals(friendId,friends.get(1).getId());
	}

	// 6. Paging - out of range high
	@Test
	public void testListAthletesBothFollowing_pagingOutOfRangeHigh() throws UnauthorizedException {
		AthleteServices service = getService();
		List<Athlete> friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(1000,200));
		assertNotNull(friends);
		assertEquals(0,friends.size());
	}

	// 7. Paging - out of range low
	@Test
	public void testListAthletesBothFollowing_pagingOutOfRangeLow() throws UnauthorizedException {
		AthleteServices service = getService();
		@SuppressWarnings("unused")
		List<Athlete> friends;
		try {
			friends = service.listAthletesBothFollowing(TestUtils.ATHLETE_VALID_ID, new Paging(-1,-1));
		} catch (IllegalArgumentException e) {
			// Expected behaviour
			return;
		}
		fail("Listed friends despite paging instructions being illegal");		
	}

	/**
	 * @return
	 * @throws UnauthorizedException 
	 */
	private AthleteServices getService() throws UnauthorizedException {
		return AthleteServicesImpl.implementation(TestUtils.getValidToken());
	}
	
	private AthleteServices getServiceWithoutWriteAccess() throws UnauthorizedException {
		return AthleteServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}

}
