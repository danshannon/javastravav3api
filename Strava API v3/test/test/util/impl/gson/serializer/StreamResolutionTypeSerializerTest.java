package test.util.impl.gson.serializer;

import stravajava.api.v3.model.reference.StravaStreamResolutionType;

/**
 * @author dshannon
 *
 */
public class StreamResolutionTypeSerializerTest extends EnumSerializerTest<StravaStreamResolutionType> {

	@Override
	protected StravaStreamResolutionType getUnknownValue() {
		return StravaStreamResolutionType.UNKNOWN;
	}

	@Override
	public Class<StravaStreamResolutionType> getClassUnderTest() {
		return StravaStreamResolutionType.class;
	}
}
