package javastrava.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Dan Shannon
 *
 */
public class StravaDateUtils {

	/**
	 * @param date
	 *            Date for which seconds since the epoch date is to be calculated
	 * @return Number of seconds after the unix epoch date equivalent to the given date
	 */
	public static Integer secondsSinceUnixEpoch(final LocalDateTime date) {
		if (date == null) {
			return null;
		}
		final Long timeInSeconds = Long.valueOf(date.toEpochSecond(ZoneOffset.UTC));
		return Integer.valueOf(timeInSeconds.intValue());
	}

}
