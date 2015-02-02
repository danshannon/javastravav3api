package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaActivity;

/**
 * @author dshannon
 *
 */
public class ActivityTest {

	@Test
	public void testGettersAndSetters() {
		new BeanTester().testBean(StravaActivity.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaActivity.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
