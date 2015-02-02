package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaSegmentLeaderboardEntry;

/**
 * @author dshannon
 *
 */
public class SegmentLeaderboardEntryTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaSegmentLeaderboardEntry.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaSegmentLeaderboardEntry.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}
