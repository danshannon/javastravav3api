package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.ActivityZone;

/**
 * @author dshannon
 *
 */
public class ActivityZoneTest {

	@Test
	public void test() {
		new BeanTester().testBean(ActivityZone.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(ActivityZone.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
