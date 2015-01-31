package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import com.danshannon.strava.api.model.BestEffort;

/**
 * @author dshannon
 *
 */
public class BestEffortTest {

	@Test
	public void test() {
		BeanTester tester = new BeanTester();
		
		// TODO 
		// Ignore Segment because it causes an infinite loop
		Configuration config = new ConfigurationBuilder()
			.ignoreProperty("segment")
			.build();
		tester.testBean(BestEffort.class, config);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(BestEffort.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
