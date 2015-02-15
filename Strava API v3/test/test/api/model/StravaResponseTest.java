package test.api.model;

import stravajava.api.v3.model.StravaResponse;
import test.utils.BeanTest;

public class StravaResponseTest extends BeanTest<StravaResponse> {

	@Override
	protected Class<StravaResponse> getClassUnderTest() {
		return StravaResponse.class;
	}

}
