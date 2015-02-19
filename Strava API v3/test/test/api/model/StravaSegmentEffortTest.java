package test.api.model;

import javastrava.api.v3.model.StravaSegmentEffort;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentEffortTest extends BeanTest<StravaSegmentEffort> {

	@Override
	protected Class<StravaSegmentEffort> getClassUnderTest() {
		return StravaSegmentEffort.class;
	}
}
