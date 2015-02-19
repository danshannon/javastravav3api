package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaSportType;

/**
 * @author dshannon
 *
 */
public class SportTypeSerializerTest extends EnumSerializerTest<StravaSportType> {

	@Override
	protected StravaSportType getUnknownValue() {
		return StravaSportType.UNKNOWN;
	}

	@Override
	public Class<StravaSportType> getClassUnderTest() {
		return StravaSportType.class;
	}
}
