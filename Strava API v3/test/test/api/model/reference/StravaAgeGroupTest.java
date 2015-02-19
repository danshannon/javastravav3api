package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaAgeGroup;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaAgeGroupTest {
	@Test
	public void testGetId() {
		for (StravaAgeGroup type : StravaAgeGroup.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaAgeGroup.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaAgeGroup type : StravaAgeGroup.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
