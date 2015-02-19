package test.model;

import javastrava.model.Segment;
import test.utils.BeanTest;

public class SegmentTest extends BeanTest<Segment> {

	@Override
	protected Class<Segment> getClassUnderTest() {
		return Segment.class;
	}

}
