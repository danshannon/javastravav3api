package test.api.model;

import stravajava.api.v3.model.StravaAthlete;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaAthleteTest extends BeanTest<StravaAthlete> {

	@Override
	protected Class<StravaAthlete> getClassUnderTest() {
		return StravaAthlete.class;
	}
}
