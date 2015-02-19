package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaWeightClass;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaWeightClassTest {
	@Test
	public void testListByMeasurementMethod() {
		for (StravaMeasurementMethod method : StravaMeasurementMethod.values()) {
			List<StravaWeightClass> classes = StravaWeightClass.listByMeasurementMethod(method);
			for (StravaWeightClass weightClass : classes) {
				assertEquals(method, weightClass.getMeasurementMethod());
			}
		}
	}

	@Test
	public void testGetId() {
		for (StravaWeightClass weightClass : StravaWeightClass.values()) {
			assertNotNull(weightClass.getId());
			assertEquals(weightClass, StravaWeightClass.create(weightClass.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaWeightClass weightClass : StravaWeightClass.values()) {
			assertNotNull(weightClass.getDescription());
		}
	}

	@Test
	public void testGetMeasurementMethod() {
		for (StravaWeightClass weightClass : StravaWeightClass.values()) {
			assertNotNull(weightClass.getMeasurementMethod());
		}
	}
}
