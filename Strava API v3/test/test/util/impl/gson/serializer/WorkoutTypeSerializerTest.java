package test.util.impl.gson.serializer;

import stravajava.api.v3.model.reference.StravaWorkoutType;

/**
 * @author dshannon
 *
 */
public class WorkoutTypeSerializerTest extends EnumSerializerTest<StravaWorkoutType> {

	@Override
	protected StravaWorkoutType getUnknownValue() {
		return StravaWorkoutType.UNKNOWN;
	}

	@Override
	public Class<StravaWorkoutType> getClassUnderTest() {
		return StravaWorkoutType.class;
	}
}
