package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.ClubMembershipResponse;

/**
 * @author dshannon
 *
 */
public class ClubMembershipResponseTest {

	@Test
	public void test() {
		new BeanTester().testBean(ClubMembershipResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(ClubMembershipResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
