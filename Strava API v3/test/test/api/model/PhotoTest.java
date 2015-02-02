package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaPhoto;

/**
 * @author dshannon
 *
 */
public class PhotoTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaPhoto.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaPhoto.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
