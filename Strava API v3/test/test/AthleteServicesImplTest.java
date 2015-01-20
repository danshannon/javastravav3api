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

import org.junit.Test;

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.AthleteServices;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.AthleteServicesImpl;

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
		@SuppressWarnings("unused")
		AthleteServices services;
		try {
			services = AthleteServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a service object using an invalid token");
	}
	
	@Test
	public void testImplementation_revokedToken() {
		try {
			@SuppressWarnings("unused")
			AthleteServices services = AthleteServicesImpl.implementation(TestUtils.getRevokedToken());
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
		
		String token2 = TestUtils.getValidToken();
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
		fail("Not yet implemented");
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
	// 2. Valid athlete with no KOM's
	// 2. Invalid athlete
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListAthleteKOMs() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Some friends (at least 2)
	// 2. No friends
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListAuthenticatedAthleteFriends() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Valid athlete - at least 2 friends
	// 2. Invalid athlete
	// 3. Valid athlete - no friends 
	// 4. Paging - size only (including test for max page size)
	// 5. Paging - size and page
	// 6. Paging - out of range high
	// 7. Paging - out of range low
	@Test
	public void testListAthleteFriends() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test cases
	// 1. Valid athlete - at least 1 common friend
	// 2. Invalid other athlete
	// 3. Valid athlete - no common friends
	// 4. Paging - size only (including test for max page size)
	// 5. Paging - size and page
	// 6. Paging - out of range high
	// 7. Paging - out of range low
	@Test
	public void testListAthletesBothFollowing() {
		// TODO Not yet implemented
		fail("Not yet implemented");		
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
