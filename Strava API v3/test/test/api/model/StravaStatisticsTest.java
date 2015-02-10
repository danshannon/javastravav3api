package test.api.model;

import stravajava.api.v3.model.StravaStatistics;
import test.utils.BeanTest;

public class StravaStatisticsTest extends BeanTest<StravaStatistics> {

	@Override
	protected Class<StravaStatistics> getClassUnderTest() {
		return StravaStatistics.class;
	}

}
