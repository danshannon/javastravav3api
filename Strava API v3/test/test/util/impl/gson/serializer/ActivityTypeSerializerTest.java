package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaActivityType;

/**
 * @author dshannon
 *
 */
public class ActivityTypeSerializerTest extends EnumSerializerTest<StravaActivityType> {

	@Override
	public Class<StravaActivityType> getClassUnderTest() {
		return StravaActivityType.class;
	}

	@Override
	protected StravaActivityType getUnknownValue() {
		return StravaActivityType.UNKNOWN;
	}

}
