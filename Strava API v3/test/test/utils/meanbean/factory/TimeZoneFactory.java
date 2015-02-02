package test.utils.meanbean.factory;

import java.util.TimeZone;

import org.meanbean.lang.Factory;

public class TimeZoneFactory implements Factory<TimeZone> {

	@Override
	public TimeZone create() {
		return TimeZone.getDefault();
	}

}
