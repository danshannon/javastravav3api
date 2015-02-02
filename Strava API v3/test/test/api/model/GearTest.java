package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaGear;

/**
 * @author dshannon
 *
 */
public class GearTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaGear.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaGear.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
