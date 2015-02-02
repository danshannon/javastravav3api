package test.api.model;

import stravajava.api.v3.model.StravaMapPoint;
import test.utils.BeanTest;


/**
 * @author dshannon
 *
 */
public class StravaMapPointTest extends BeanTest<StravaMapPoint>{

	@Override
	protected Class<StravaMapPoint> getClassUnderTest() {
		return StravaMapPoint.class;
	}

}
