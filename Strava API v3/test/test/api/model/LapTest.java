package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaLap;

/**
 * @author dshannon
 *
 */
public class LapTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaLap.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaLap.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
