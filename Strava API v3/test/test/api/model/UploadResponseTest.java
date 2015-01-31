package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.UploadResponse;

/**
 * @author dshannon
 *
 */
public class UploadResponseTest {

	@Test
	public void test() {
		new BeanTester().testBean(UploadResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(UploadResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
