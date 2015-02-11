package test.api.model;

import stravajava.api.v3.model.StravaStream;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaStreamTest extends BeanTest<StravaStream> {

	@Override
	protected Class<StravaStream> getClassUnderTest() {
		return StravaStream.class;
	}

}
