package test.api.model;

import javastrava.api.v3.model.StravaSegmentLeaderboardEntry;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentLeaderboardEntryTest extends BeanTest<StravaSegmentLeaderboardEntry> {

	@Override
	protected Class<StravaSegmentLeaderboardEntry> getClassUnderTest() {
		return StravaSegmentLeaderboardEntry.class;
	}
}
