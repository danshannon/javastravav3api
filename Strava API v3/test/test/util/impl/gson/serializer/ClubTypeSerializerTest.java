package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaClubType;

/**
 * @author dshannon
 *
 */
public class ClubTypeSerializerTest extends EnumSerializerTest<StravaClubType> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected StravaClubType getUnknownValue() {
		return StravaClubType.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<StravaClubType> getClassUnderTest() {
		return StravaClubType.class;
	}

}
