package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaClub;

/**
 * @author dshannon
 *
 */
public class ClubTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaClub.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaClub.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
