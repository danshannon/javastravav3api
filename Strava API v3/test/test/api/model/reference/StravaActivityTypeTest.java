package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaActivityType;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaActivityTypeTest {
	@Test
	public void testGetId() {
		for (StravaActivityType type : StravaActivityType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaActivityType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaActivityType type : StravaActivityType.values()) {
			assertNotNull(type.getDescription());
		}
	}
}
