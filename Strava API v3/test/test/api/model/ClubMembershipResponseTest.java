package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaClubMembershipResponse;

/**
 * @author dshannon
 *
 */
public class ClubMembershipResponseTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaClubMembershipResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaClubMembershipResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
