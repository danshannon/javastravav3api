package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.MapPoint;

/**
 * @author dshannon
 *
 */
public class MapPointTest {

	@Test
	public void test() {
		new BeanTester().testBean(MapPoint.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(MapPoint.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
