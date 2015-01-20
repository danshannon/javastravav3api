package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.danshannon.strava.api.service.SegmentEffortServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.SegmentEffortServicesImpl;

/**
 * <p>Unit tests for {@link SegmentEffortServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class SegmentEffortServicesImplTest {
	/**
	 * <p>Test we get a {@link SegmentEffortServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		SegmentEffortServices service = SegmentEffortServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link SegmentEffortServicesImpl service implementation} if the token isn't valid</p>
	 */
	@Test
	public void testImplementation_invalidToken() {
		SegmentEffortServices service = null;
		try {
			service = SegmentEffortServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
		}
		assertNull("Got a service for an invalid token!",service);
	}

	/**
	 * <p>Test that we don't get a {@link SegmentEffortServicesImpl service implementation} if the token has been revoked by the user</p>
	 */
	@Test
	public void testImplementation_revokedToken() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	/**
	 * <p>Test that when we ask for a {@link SegmentEffortServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		SegmentEffortServices service = SegmentEffortServicesImpl.implementation(TestUtils.getValidToken());
		SegmentEffortServices service2 = SegmentEffortServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link SegmentEffortServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Valid effort 
	// 2. Invalid effort
	// 3. Private effort which does belong to the current athlete (is returned)
	// 4. Private effort which doesn't belong to the current athlete (is not returned)
	@Test
	public void testGetSegmentEffort() {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}
}
