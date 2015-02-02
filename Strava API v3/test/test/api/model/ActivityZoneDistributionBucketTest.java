package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaActivityZoneDistributionBucket;

/**
 * @author dshannon
 *
 */
public class ActivityZoneDistributionBucketTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaActivityZoneDistributionBucket.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaActivityZoneDistributionBucket.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
