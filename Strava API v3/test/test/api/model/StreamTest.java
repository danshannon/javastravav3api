package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaStream;

/**
 * @author dshannon
 *
 */
public class StreamTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaStream.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaStream.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
