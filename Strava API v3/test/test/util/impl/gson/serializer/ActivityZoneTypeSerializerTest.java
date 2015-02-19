package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaActivityZoneType;

/**
 * @author dshannon
 *
 */
public class ActivityZoneTypeSerializerTest extends EnumSerializerTest<StravaActivityZoneType> {
	@Override
	public Class<StravaActivityZoneType> getClassUnderTest() {
		return StravaActivityZoneType.class;
	}

	@Override
	protected StravaActivityZoneType getUnknownValue() {
		return StravaActivityZoneType.UNKNOWN;
	}

}
