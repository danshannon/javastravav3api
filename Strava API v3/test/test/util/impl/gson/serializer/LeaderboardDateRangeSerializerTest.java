package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;

/**
 * @author dshannon
 *
 */
public class LeaderboardDateRangeSerializerTest extends EnumSerializerTest<StravaLeaderboardDateRange> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected StravaLeaderboardDateRange getUnknownValue() {
		return StravaLeaderboardDateRange.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<StravaLeaderboardDateRange> getClassUnderTest() {
		return StravaLeaderboardDateRange.class;
	}
}
