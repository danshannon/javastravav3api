package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaFrameType;

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
