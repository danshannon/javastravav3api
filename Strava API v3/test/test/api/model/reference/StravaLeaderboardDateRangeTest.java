package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.model.reference.StravaLeaderboardDateRange;

/**
 * @author dshannon
 *
 */
public class StravaLeaderboardDateRangeTest {
	@Test
	public void testGetId() {
		for (StravaLeaderboardDateRange type : StravaLeaderboardDateRange.values()) {
			assertNotNull(type.getId());
			assertEquals(type,StravaLeaderboardDateRange.create(type.getId()));
		}
	}
	
	@Test
	public void testGetDescription() {
		for (StravaLeaderboardDateRange type : StravaLeaderboardDateRange.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
