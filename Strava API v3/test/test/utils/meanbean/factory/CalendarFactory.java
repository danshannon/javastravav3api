package test.utils.meanbean.factory;

import java.util.Calendar;

import org.meanbean.lang.Factory;

public class CalendarFactory implements Factory<Calendar> {

	@Override
	public Calendar create() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 0, 1, 0, 0, 0);
		return calendar;
	}

}
