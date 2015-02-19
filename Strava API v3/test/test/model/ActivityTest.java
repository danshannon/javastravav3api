package test.model;

import javastrava.model.Activity;
import test.utils.BeanTest;

public class ActivityTest extends BeanTest<Activity> {

	@Override
	protected Class<Activity> getClassUnderTest() {
		return Activity.class;
	}

}
