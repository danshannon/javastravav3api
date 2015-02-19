package test.api.model;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentLeaderboardTest extends BeanTest<StravaSegmentLeaderboard> {

	@Override
	protected Class<StravaSegmentLeaderboard> getClassUnderTest() {
		return StravaSegmentLeaderboard.class;
	}
}
