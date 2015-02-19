package test.api.model;

import javastrava.api.v3.model.StravaSegmentExplorerResponse;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSegmentExplorerResponseTest extends BeanTest<StravaSegmentExplorerResponse> {

	@Override
	protected Class<StravaSegmentExplorerResponse> getClassUnderTest() {
		return StravaSegmentExplorerResponse.class;
	}

}
