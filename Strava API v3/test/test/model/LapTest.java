package test.model;

import javastrava.model.Lap;
import test.utils.BeanTest;

public class LapTest extends BeanTest<Lap> {

	@Override
	protected Class<Lap> getClassUnderTest() {
		return Lap.class;
	}

}
