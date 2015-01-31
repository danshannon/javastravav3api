package test.api.auth.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.auth.model.TokenResponse;

/**
 * @author dshannon
 *
 */
public class TokenResponseTest {

	@Test
	public void testGettersAndSetters() {
		new BeanTester().testBean(TokenResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(TokenResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
