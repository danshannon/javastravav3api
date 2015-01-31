package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import com.danshannon.strava.api.model.Segment;

/**
 * @author dshannon
 *
 */
public class SegmentTest {

	@Test
	public void test() {
		BeanTester tester = new BeanTester();
		
		// TODO Fix this
		// Ignore the property that causes an infinite loop
		Configuration customConfiguration = new ConfigurationBuilder()
			.ignoreProperty("athletePrEffort")
			.build();
		tester.testBean(Segment.class,customConfiguration);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Segment.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
