package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaActivityZoneType;

/**
 * @author dshannon
 *
 */
public class StravaActivityZoneTypeTest {

	@Test
	public void testGetId() {
		for (StravaActivityZoneType type : StravaActivityZoneType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaActivityZoneType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaActivityZoneType type : StravaActivityZoneType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
