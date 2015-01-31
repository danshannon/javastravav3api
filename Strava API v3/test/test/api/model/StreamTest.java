package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Stream;

/**
 * @author dshannon
 *
 */
public class StreamTest {

	@Test
	public void test() {
		new BeanTester().testBean(Stream.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Stream.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
