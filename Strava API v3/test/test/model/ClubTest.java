package test.model;

import stravajava.model.Club;
import test.utils.BeanTest;

public class ClubTest extends BeanTest<Club> {

	@Override
	protected Class<Club> getClassUnderTest() {
		return Club.class;
	}

}
