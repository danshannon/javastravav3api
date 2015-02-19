package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegmentLeaderboard;

/**
 * <p>
 * Date range options used for filtering {@link StravaSegmentLeaderboard leaderboards}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaLeaderboardDateRange {
	THIS_YEAR("this_year", "This year"),
	THIS_MONTH("this_month", "This month"),
	THIS_WEEK("this_week", "This week"),
	TODAY("today", "Today"),
	UNKNOWN("UNKNOWN", "Unknown");

	private String	id;
	private String	description;

	private StravaLeaderboardDateRange(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
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
