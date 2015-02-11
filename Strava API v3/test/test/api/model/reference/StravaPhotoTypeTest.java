package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaPhotoType;

/**
 * @author dshannon
 *
 */
public class StravaPhotoTypeTest {
	@Test
	public void testGetId() {
		for (StravaPhotoType type : StravaPhotoType.values()) {
			assertNotNull(type.getId());
			assertEquals(type,StravaPhotoType.create(type.getId()));
		}
	}
	
	@Test
	public void testGetDescription() {
		for (StravaPhotoType type : StravaPhotoType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
