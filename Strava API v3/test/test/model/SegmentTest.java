package test.model;

import stravajava.model.Segment;
import test.utils.BeanTest;

public class SegmentTest extends BeanTest<Segment> {

	@Override
	protected Class<Segment> getClassUnderTest() {
		return Segment.class;
	}

}
