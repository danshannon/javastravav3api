package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaWeightClass;

/**
 * @author dshannon
 *
 */
public class WeightClassSerializerTest extends EnumSerializerTest<StravaWeightClass> {

	@Override
	protected StravaWeightClass getUnknownValue() {
		return StravaWeightClass.UNKNOWN;
	}

	@Override
	public Class<StravaWeightClass> getClassUnderTest() {
		return StravaWeightClass.class;
	}
}
