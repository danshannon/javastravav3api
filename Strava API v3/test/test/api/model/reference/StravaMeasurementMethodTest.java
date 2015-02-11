package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaMeasurementMethod;

/**
 * @author dshannon
 *
 */
public class StravaMeasurementMethodTest {
	@Test
	public void testGetId() {
		for (StravaMeasurementMethod type : StravaMeasurementMethod.values()) {
			assertNotNull(type.getId());
			assertEquals(type,StravaMeasurementMethod.create(type.getId()));
		}
	}
	
	@Test
	public void testGetDescription() {
		for (StravaMeasurementMethod type : StravaMeasurementMethod.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
