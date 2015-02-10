package test.api.model;

import stravajava.api.v3.model.StravaStatisticsEntry;
import test.utils.BeanTest;

public class StravaStatisticsEntryTest extends BeanTest<StravaStatisticsEntry> {

	@Override
	protected Class<StravaStatisticsEntry> getClassUnderTest() {
		return StravaStatisticsEntry.class;
	}

}
