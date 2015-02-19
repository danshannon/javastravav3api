package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;

/**
 * @author dshannon
 *
 */
public class StreamSeriesDownsamplingTypeSerializerTest extends EnumSerializerTest<StravaStreamSeriesDownsamplingType> {

	@Override
	protected StravaStreamSeriesDownsamplingType getUnknownValue() {
		return StravaStreamSeriesDownsamplingType.UNKNOWN;
	}

	@Override
	public Class<StravaStreamSeriesDownsamplingType> getClassUnderTest() {
		return StravaStreamSeriesDownsamplingType.class;
	}
}
