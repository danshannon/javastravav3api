package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaActivityZoneType;

import org.junit.Test;

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
