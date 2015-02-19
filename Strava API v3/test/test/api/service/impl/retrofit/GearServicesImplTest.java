package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.service.GearServices;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.GearServicesImpl;

import org.junit.Before;
import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * Unit tests for {@link GearServicesImpl}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class GearServicesImplTest {
	private GearServices gearService;

	@Before
	public void setUp() {
		this.gearService = getGearService();
	}

	/**
	 * <p>
	 * Test we get a {@link GearServicesImpl service implementation} successfully with a valid token
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             If token is not valid
	 */
	@Test
	public void testImplementation_validToken() {
		GearServices service = GearServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Got a NULL service for a valid token", service);
	}

	/**
	 * <p>
	 * Test that we don't get a {@link GearServicesImpl service implementation} if the token isn't valid
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_invalidToken() {
		GearServices service = null;
		try {
			service = GearServicesImpl.implementation(TestUtils.INVALID_TOKEN);
			service.getGear(TestUtils.GEAR_VALID_ID);
		} catch (UnauthorizedException e) {
			// This is the expected behaviour
			return;
		}
		fail("Have access despite having an invalid token!");
	}

	/**
	 * <p>
	 * Test that we don't get a {@link GearServicesImpl service implementation} if the token has been revoked by the user
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_revokedToken() {
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
	 * <p>
	 * Test that when we ask for a {@link GearServicesImpl service implementation} for a second time, we get the SAME ONE as the first time (i.e. the caching
	 * strategy is working)
	 * </p>
	 */
	@Test
	public void testImplementation_implementationIsCached() {
		GearServices service = GearServicesImpl.implementation(TestUtils.getValidToken());
		GearServices service2 = GearServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one", service, service2);
	}

	/**
	 * <p>
	 * Test that when we ask for a {@link GearServicesImpl service implementation} for a second, valid, different token, we get a DIFFERENT implementation
	 * </p>
	 * 
	 * @throws UnauthorizedException
	 *             Thrown when security token is invalid
	 */
	@Test
	public void testImplementation_differentImplementationIsNotCached() {
		GearServices service = getGearService();
		GearServices service2 = getGearServiceWithoutWriteAccess();
		assertFalse(service == service2);
	}

	// Test cases
	// 1. Valid gear
	// 2. Invalid gear
	// 3. StravaGear which doesn't belong to the current athlete
	@Test
	public void testGetGear_validGear() {
		GearServices service = getGearService();
		StravaGear gear = service.getGear(TestUtils.GEAR_VALID_ID);

		assertNotNull(gear);
		assertEquals("Retrieved the wrong gear id", TestUtils.GEAR_VALID_ID, gear.getId());
	}

	@Test
	public void testGetGear_invalidGear() {
		GearServices service = getGearService();
		StravaGear gear = service.getGear(TestUtils.GEAR_INVALID_ID);

		assertNull(gear);
	}

	@Test
	public void testGetGear_otherAthlete() {
		GearServices service = getGearService();
		StravaGear gear = service.getGear(TestUtils.GEAR_OTHER_ATHLETE_ID);

		assertNull(gear);
	}

	@Test
	public void testGetGear_privateAthlete() {
		StravaGear gear = getGearService().getGear(TestUtils.GEAR_OTHER_ATHLETE_ID);
		assertNull(gear);
	}

	private GearServices getGearService() {
		if (this.gearService == null) {
			this.gearService = GearServicesImpl.implementation(TestUtils.getValidToken());
		}
		return this.gearService;
	}

	private String getRevokedToken() {
		this.gearService = null;
		return TestUtils.getRevokedToken();
	}

	private GearServices getGearServiceWithoutWriteAccess() {
		this.gearService = null;
		return GearServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}
}
