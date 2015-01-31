package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Lap;

/**
 * @author dshannon
 *
 */
public class LapTest {

	@Test
	public void test() {
		new BeanTester().testBean(Lap.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Lap.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
