package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaStreamResolutionType;

/**
 * @author dshannon
 *
 */
public class StravaStreamResolutionTypeTest {
	@Test
	public void testGetId() {
		for (StravaStreamResolutionType type : StravaStreamResolutionType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaStreamResolutionType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaStreamResolutionType type : StravaStreamResolutionType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
