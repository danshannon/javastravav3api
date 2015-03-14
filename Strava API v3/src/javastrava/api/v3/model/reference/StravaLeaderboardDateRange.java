package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.impl.gson.serializer.LeaderboardDateRangeSerializer;

/**
 * <p>
 * Date range options used for filtering {@link StravaSegmentLeaderboard leaderboards}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaLeaderboardDateRange {
	/**
	 * This calendar year
	 */
	THIS_YEAR(Strava.stringProperty("StravaLeaderboardDateRange.thisYear"), Messages.getString("StravaLeaderboardDateRange.thisYear.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This calendar month
	 */
	THIS_MONTH(Strava.stringProperty("StravaLeaderboardDateRange.thisMonth"), Messages.getString("StravaLeaderboardDateRange.thisMonth.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This calendar week
	 */
	THIS_WEEK(Strava.stringProperty("StravaLeaderboardDateRange.thisWeek"), Messages.getString("StravaLeaderboardDateRange.thisWeek.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Today
	 */
	TODAY(Strava.stringProperty("StravaLeaderboardDateRange.today"), Messages.getString("StravaLeaderboardDateRange.today.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Strava.stringProperty("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaLeaderboardDateRange(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaLeaderboardDateRange} to be used with the Strava API
	 * @see LeaderboardDateRangeSerializer#serialize(StravaLeaderboardDateRange, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaLeaderboardDateRange} returned by the Strava API
	 * @return The matching {@link StravaLeaderboardDateRange}, or {@link StravaLeaderboardDateRange#UNKNOWN} if there is no match
	 */
	public static StravaLeaderboardDateRange create(final String id) {
		for (StravaLeaderboardDateRange dateRange : StravaLeaderboardDateRange.values()) {
			if (dateRange.getId().equals(id)) {
				return dateRange;
			}
		}
		return UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

}
