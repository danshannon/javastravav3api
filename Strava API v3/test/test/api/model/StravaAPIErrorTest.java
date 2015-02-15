package test.api.model;

import stravajava.api.v3.model.StravaAPIError;
import test.utils.BeanTest;

public class StravaAPIErrorTest extends BeanTest<StravaAPIError> {

	@Override
	protected Class<StravaAPIError> getClassUnderTest() {
		return StravaAPIError.class;
	}

}
