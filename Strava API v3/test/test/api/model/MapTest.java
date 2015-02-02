package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaMap;

/**
 * @author dshannon
 *
 */
public class MapTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaMap.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaMap.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
