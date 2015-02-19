package test.api.model;

import javastrava.api.v3.model.StravaLap;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaLapTest extends BeanTest<StravaLap> {

	@Override
	protected Class<StravaLap> getClassUnderTest() {
		return StravaLap.class;
	}
}
