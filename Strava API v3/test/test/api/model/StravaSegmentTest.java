package test.api.model;

import javastrava.api.v3.model.StravaSegment;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentTest extends BeanTest<StravaSegment> {

	@Override
	protected Class<StravaSegment> getClassUnderTest() {
		return StravaSegment.class;
	}
}
