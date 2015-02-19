package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaFrameType;

/**
 * @author dshannon
 *
 */
public class FrameTypeSerializerTest extends EnumSerializerTest<StravaFrameType> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected StravaFrameType getUnknownValue() {
		return StravaFrameType.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<StravaFrameType> getClassUnderTest() {
		return StravaFrameType.class;
	}
}
