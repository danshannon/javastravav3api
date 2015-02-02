package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaActivityZone;

/**
 * @author dshannon
 *
 */
public class ActivityZoneTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaActivityZone.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaActivityZone.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
