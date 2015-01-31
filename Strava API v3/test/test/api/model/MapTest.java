package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Map;

/**
 * @author dshannon
 *
 */
public class MapTest {

	@Test
	public void test() {
		new BeanTester().testBean(Map.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Map.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
