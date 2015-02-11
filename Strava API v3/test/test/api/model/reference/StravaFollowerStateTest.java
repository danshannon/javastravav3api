package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaFollowerState;

/**
 * @author dshannon
 *
 */
public class StravaFollowerStateTest {
	@Test
	public void testGetId() {
		for (StravaFollowerState type : StravaFollowerState.values()) {
			assertNotNull(type.getId());
			assertEquals(type,StravaFollowerState.create(type.getId()));
		}
	}
	
	@Test
	public void testGetDescription() {
		for (StravaFollowerState type : StravaFollowerState.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
