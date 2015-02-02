package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaUploadResponse;

/**
 * @author dshannon
 *
 */
public class UploadResponseTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaUploadResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaUploadResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
