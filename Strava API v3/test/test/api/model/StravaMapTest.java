package test.api.model;

import javastrava.api.v3.model.StravaMap;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaMapTest extends BeanTest<StravaMap> {

	@Override
	protected Class<StravaMap> getClassUnderTest() {
		return StravaMap.class;
	}
}
