package test.api.model;

import javastrava.api.v3.model.StravaResponse;
import test.utils.BeanTest;

public class StravaResponseTest extends BeanTest<StravaResponse> {

	@Override
	protected Class<StravaResponse> getClassUnderTest() {
		return StravaResponse.class;
	}

}
