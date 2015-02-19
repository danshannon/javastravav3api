package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaFrameType;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaFrameTypeTest {
	@Test
	public void testGetId() {
		for (StravaFrameType type : StravaFrameType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaFrameType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaFrameType type : StravaFrameType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
