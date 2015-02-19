package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaMeasurementMethod;

/**
 * @author dshannon
 *
 */
public class MeasurementMethodSerializerTest extends EnumSerializerTest<StravaMeasurementMethod> {

	@Override
	protected StravaMeasurementMethod getUnknownValue() {
		return StravaMeasurementMethod.UNKNOWN;
	}

	@Override
	public Class<StravaMeasurementMethod> getClassUnderTest() {
		return StravaMeasurementMethod.class;
	}
}
