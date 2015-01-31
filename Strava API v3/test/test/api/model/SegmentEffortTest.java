package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import com.danshannon.strava.api.model.SegmentEffort;

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
		tester.testBean(SegmentEffort.class, customConfiguration);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(SegmentEffort.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
