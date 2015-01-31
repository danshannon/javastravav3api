package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.ActivityZoneDistributionBucket;

/**
 * @author dshannon
 *
 */
public class ActivityZoneDistributionBucketTest {

	@Test
	public void test() {
		new BeanTester().testBean(ActivityZoneDistributionBucket.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(ActivityZoneDistributionBucket.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
