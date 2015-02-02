package test.api.model;

import stravajava.api.v3.model.StravaActivityZone;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaActivityZoneTest extends BeanTest<StravaActivityZone> {

	@Override
	protected Class<StravaActivityZone> getClassUnderTest() {
		return StravaActivityZone.class;
	}
}
