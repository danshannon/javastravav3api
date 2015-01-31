package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Athlete;

/**
 * @author dshannon
 *
 */
public class AthleteTest {

	@Test
	public void test() {
		new BeanTester().testBean(Athlete.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Athlete.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
