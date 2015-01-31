package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.SegmentLeaderboardEntry;

/**
 * @author dshannon
 *
 */
public class SegmentLeaderboardEntryTest {

	@Test
	public void test() {
		new BeanTester().testBean(SegmentLeaderboardEntry.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(SegmentLeaderboardEntry.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
