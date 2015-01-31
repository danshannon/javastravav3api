package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.SegmentLeaderboard;

/**
 * @author dshannon
 *
 */
public class SegmentLeaderboardTest {

	@Test
	public void test() {
		new BeanTester().testBean(SegmentLeaderboard.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(SegmentLeaderboard.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
