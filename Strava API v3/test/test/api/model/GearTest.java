package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Gear;

/**
 * @author dshannon
 *
 */
public class GearTest {

	@Test
	public void test() {
		new BeanTester().testBean(Gear.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Gear.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
