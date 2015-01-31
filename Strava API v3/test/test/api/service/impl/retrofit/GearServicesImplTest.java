package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import test.TestUtils;

import com.danshannon.strava.api.model.Gear;
import com.danshannon.strava.api.service.GearServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.api.service.impl.retrofit.GearServicesImpl;

/**
 * <p>Unit tests for {@link GearServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class GearServicesImplTest {
	private GearServices gearService;
	
	@Before
	public void setUp() throws UnauthorizedException {
		this.gearService = getGearService();
	}
	
	/**
	 * <p>Test we get a {@link GearServicesImpl service implementation} successfully with a valid token</p>
	 * 
	 * @throws UnauthorizedException If token is not valid
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		GearServices service = GearServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}
	
	/**
	 * <p>Test that we don't get a {@link GearServicesImpl service implementation} if the token isn't valid</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testImplementation_invalidToken() throws UnauthorizedException {
		GearServices service = null;
		service = GearServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		try {
			service.getGear(TestUtils.GEAR_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Have access despite having an invalid token!");
	}

	/**
	 * <p>Test that we don't get a {@link GearServicesImpl service implementation} if the token has been revoked by the user</p>
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testImplementation_revokedToken() throws UnauthorizedException {
		String token = getRevokedToken();
		GearServices service = GearServicesImpl.implementation(token);
		
		try {
			service.getGear(TestUtils.GEAR_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Have access despite having an invalid token!");
	}
	
	/**
	 * <p>Test that when we ask for a {@link GearServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching strategy is working)</p>
	 */
	@Test
	public void testImplementation_implementationIsCached() throws UnauthorizedException {
		GearServices service = GearServicesImpl.implementation(TestUtils.getValidToken());
		GearServices service2 = GearServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one",service,service2);
	}
	
	/**
	 * <p>Test that when we ask for a {@link GearServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation</p>
	 * 
	 * @throws UnauthorizedException Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() throws UnauthorizedException {
		GearServices service = getGearService();
		GearServices service2 = getGearServiceWithoutWriteAccess();
		assertFalse(service == service2);
	}
	
	// Test cases
	// 1. Valid gear
	// 2. Invalid gear
	// 3. Gear which doesn't belong to the current athlete
	@Test
	public void testGetGear_validGear() throws UnauthorizedException {
		GearServices service = getGearService();
		Gear gear = service.getGear(TestUtils.GEAR_VALID_ID);
		
		assertNotNull(gear);
		assertEquals("Retrieved the wrong gear id",TestUtils.GEAR_VALID_ID,gear.getId());
	}
	
	@Test
	public void testGetGear_invalidGear() throws UnauthorizedException {
		GearServices service = getGearService();
		Gear gear = service.getGear(TestUtils.GEAR_INVALID_ID);
		
		assertNull(gear);
	}
	
	@Test
	public void testGetGear_otherAthlete() throws UnauthorizedException {
		GearServices service = getGearService();
		Gear gear = service.getGear(TestUtils.GEAR_OTHER_ATHLETE_ID);
		
		assertNull(gear);
	}
	
	private GearServices getGearService() throws UnauthorizedException {
		if (this.gearService == null) {
			this.gearService = GearServicesImpl.implementation(TestUtils.getValidToken());
		}
		return this.gearService;
	}
	
	private String getRevokedToken() throws UnauthorizedException {
		this.gearService = null;
		return TestUtils.getRevokedToken();
	}
	
	private GearServices getGearServiceWithoutWriteAccess() throws UnauthorizedException {
		this.gearService = null;
		return GearServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}
}
