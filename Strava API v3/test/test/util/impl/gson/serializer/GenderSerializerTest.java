package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaGender;

/**
 * @author dshannon
 *
 */
public class GenderSerializerTest extends EnumSerializerTest<StravaGender> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected StravaGender getUnknownValue() {
		return StravaGender.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<StravaGender> getClassUnderTest() {
		return StravaGender.class;
	}
}
