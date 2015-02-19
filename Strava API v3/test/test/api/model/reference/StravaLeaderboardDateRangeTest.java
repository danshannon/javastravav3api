package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaLeaderboardDateRangeTest {
	@Test
	public void testGetId() {
		for (StravaLeaderboardDateRange type : StravaLeaderboardDateRange.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaLeaderboardDateRange.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaLeaderboardDateRange type : StravaLeaderboardDateRange.values()) {
			assertNotNull(type.getDescription());
		}
	}

}
