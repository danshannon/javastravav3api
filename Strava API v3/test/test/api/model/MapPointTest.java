package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaMapPoint;

/**
 * @author dshannon
 *
 */
public class MapPointTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaMapPoint.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaMapPoint.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
