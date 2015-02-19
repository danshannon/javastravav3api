package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaSegmentActivityType;

import org.junit.Test;

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
