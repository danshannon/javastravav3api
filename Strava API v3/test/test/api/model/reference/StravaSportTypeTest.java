package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaSportType;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaSportTypeTest {
	@Test
	public void testGetId() {
		for (StravaSportType type : StravaSportType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaSportType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaSportType type : StravaSportType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
