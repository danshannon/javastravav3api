package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.danshannon.strava.api.service.ClubServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.ClubServicesImpl;

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
		// TODO Not yet implemented
		fail("Not yet implemented");
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
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Valid club
	// 2. Invalid club
	// 3. Private club of which current authenticated athlete is a member
	// 4. Private club of which current authenticated athlete is NOT a member
	@Test
	public void testGetClub(Integer id) {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}
	
	// Test cases
	// 1. Athlete has clubs
	// 2. Athlete has no clubs
	@Test
	public void testListAuthenticatedAthleteClubs() {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}

	// Test cases
	// 1. Valid club
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListClubMembers() {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}
	
	// Test cases
	// 1. Valid club
	// 2. Invalid club
	// 3. Club the current authenticated club is NOT a member of (according to Strava should return 0 results)
	// 4. Club with > 200 activities (according to Strava, should only return a max of 200 results)
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListRecentClubActivities() {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}

}
