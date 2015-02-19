package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaWorkoutType;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaWorkoutTypeTest {
	@Test
	public void testGetId() {
		for (StravaWorkoutType type : StravaWorkoutType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaWorkoutType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaWorkoutType type : StravaWorkoutType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
