package test.util.impl.gson.serializer;

import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerActivityTypeSerializerTest extends EnumSerializerTest<StravaSegmentExplorerActivityType> {

	@Override
	protected StravaSegmentExplorerActivityType getUnknownValue() {
		return StravaSegmentExplorerActivityType.UNKNOWN;
	}

	@Override
	public Class<StravaSegmentExplorerActivityType> getClassUnderTest() {
		return StravaSegmentExplorerActivityType.class;
	}
}
