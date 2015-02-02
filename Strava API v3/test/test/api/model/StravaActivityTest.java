package test.api.model;

import stravajava.api.v3.model.StravaActivity;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaActivityTest extends BeanTest<StravaActivity> {

	@Override
	protected Class<StravaActivity> getClassUnderTest() {
		return StravaActivity.class;
	}

}
