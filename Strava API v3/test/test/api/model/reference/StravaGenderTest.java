package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaGender;

/**
 * @author dshannon
 *
 */
public class StravaGenderTest {
	@Test
	public void testGetId() {
		for (StravaGender type : StravaGender.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaGender.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaGender type : StravaGender.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
