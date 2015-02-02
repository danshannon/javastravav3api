package test.model;

import stravajava.model.Lap;
import test.utils.BeanTest;

public class LapTest extends BeanTest<Lap> {

	@Override
	protected Class<Lap> getClassUnderTest() {
		return Lap.class;
	}

}
