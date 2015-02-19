package test.api.model;

import javastrava.api.v3.model.StravaSegmentExplorerResponseSegment;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentExplorerResponseSegmentTest extends BeanTest<StravaSegmentExplorerResponseSegment> {

	@Override
	protected Class<StravaSegmentExplorerResponseSegment> getClassUnderTest() {
		return StravaSegmentExplorerResponseSegment.class;
	}
}
