package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaClimbCategory;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaClimbCategoryTest {
	@Test
	public void testGetId() {
		for (StravaClimbCategory type : StravaClimbCategory.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaClimbCategory.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaClimbCategory type : StravaClimbCategory.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
