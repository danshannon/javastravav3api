package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import stravajava.api.v3.model.StravaSegmentEffort;

/**
 * @author dshannon
 *
 */
public class SegmentEffortTest {

	@Test
	public void test() {
		BeanTester tester = new BeanTester();
		Configuration customConfiguration = new ConfigurationBuilder()
			.ignoreProperty("segment")
			.build();
		tester.testBean(StravaSegmentEffort.class, customConfiguration);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaSegmentEffort.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
