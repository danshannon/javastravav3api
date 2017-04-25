package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.LeaderboardDateRangeSerializer;
import javastrava.model.StravaSegmentLeaderboard;

/**
 * <p>
 * Date range options used for filtering {@link StravaSegmentLeaderboard leaderboards}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaLeaderboardDateRange implements StravaReferenceType<String> {
	/**
	 * This calendar year
	 */
	THIS_YEAR(StravaConfig.string("StravaLeaderboardDateRange.thisYear"), Messages.string("StravaLeaderboardDateRange.thisYear.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This calendar month
	 */
	THIS_MONTH(StravaConfig.string("StravaLeaderboardDateRange.thisMonth"), Messages.string("StravaLeaderboardDateRange.thisMonth.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This calendar week
	 */
	THIS_WEEK(StravaConfig.string("StravaLeaderboardDateRange.thisWeek"), Messages.string("StravaLeaderboardDateRange.thisWeek.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Today
	 */
	TODAY(StravaConfig.string("StravaLeaderboardDateRange.today"), Messages.string("StravaLeaderboardDateRange.today.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaLeaderboardDateRange} returned by the Strava API
	 * @return The matching {@link StravaLeaderboardDateRange}, or {@link StravaLeaderboardDateRange#UNKNOWN} if there is no match
	 */
	public static StravaLeaderboardDateRange create(final String id) {
		for (final StravaLeaderboardDateRange dateRange : StravaLeaderboardDateRange.values()) {
			if (dateRange.getId().equals(id)) {
				return dateRange;
			}
		}
		return UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private String	id;

	/**
	 * Human-readable description
	 */
	private String	description;

	/**
	 * Private constructor for the enum declarations
	 * @param id Identifier
	 * @param description Text description
	 */
	private StravaLeaderboardDateRange(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaLeaderboardDateRange} to be used with the Strava API
	 * @see LeaderboardDateRangeSerializer#serialize(StravaLeaderboardDateRange, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

}
