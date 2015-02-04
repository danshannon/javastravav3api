package test.model;

import stravajava.model.Gear;
import test.utils.BeanTest;

public class GearTest extends BeanTest<Gear> {

	@Override
	protected Class<Gear> getClassUnderTest() {
		return Gear.class;
	}

}
