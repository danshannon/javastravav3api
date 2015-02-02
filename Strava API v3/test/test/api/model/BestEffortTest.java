package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import stravajava.api.v3.model.StravaBestRunningEffort;

/**
 * @author dshannon
 *
 */
public class BestEffortTest {

	@Test
	public void test() {
		BeanTester tester = new BeanTester();
		
		// TODO 
		// Ignore StravaSegment because it causes an infinite loop
		Configuration config = new ConfigurationBuilder()
			.ignoreProperty("segment")
			.build();
		tester.testBean(StravaBestRunningEffort.class, config);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaBestRunningEffort.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
