package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaStreamType;

/**
 * @author dshannon
 *
 */
public class StravaStreamTypeTest {
	@Test
	public void testGetId() {
		for (StravaStreamType type : StravaStreamType.values()) {
			assertNotNull(type.getId());
			assertEquals(type,StravaStreamType.create(type.getId()));
		}
	}
	
	@Test
	public void testGetDescription() {
		for (StravaStreamType type : StravaStreamType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
