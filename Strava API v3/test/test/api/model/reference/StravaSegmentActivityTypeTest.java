package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaSegmentActivityType;

/**
 * @author dshannon
 *
 */
public class StravaSegmentActivityTypeTest {
	@Test
	public void testGetId() {
		for (StravaSegmentActivityType type : StravaSegmentActivityType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaSegmentActivityType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaSegmentActivityType type : StravaSegmentActivityType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
