package test.model;

import javastrava.model.Athlete;
import test.utils.BeanTest;

public class AthleteTest extends BeanTest<Athlete> {

	@Override
	protected Class<Athlete> getClassUnderTest() {
		return Athlete.class;
	}

}
