package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaStreamType;

/**
 * @author dshannon
 *
 */
public class StreamTypeSerializerTest extends EnumSerializerTest<StravaStreamType> {

	@Override
	protected StravaStreamType getUnknownValue() {
		return StravaStreamType.UNKNOWN;
	}

	@Override
	public Class<StravaStreamType> getClassUnderTest() {
		return StravaStreamType.class;
	}

}
