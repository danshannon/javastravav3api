package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Activity;

/**
 * @author dshannon
 *
 */
public class ActivityTest {

	@Test
	public void testGettersAndSetters() {
		new BeanTester().testBean(Activity.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Activity.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
