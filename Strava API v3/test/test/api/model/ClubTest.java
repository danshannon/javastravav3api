package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Club;

/**
 * @author dshannon
 *
 */
public class ClubTest {

	@Test
	public void test() {
		new BeanTester().testBean(Club.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Club.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
