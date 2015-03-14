package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;

/**
 * <p>
 * Date range options used for filtering {@link StravaSegmentLeaderboard leaderboards}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaLeaderboardDateRange {
	THIS_YEAR(Messages.getString("StravaLeaderboardDateRange.thisYear"), Messages.getString("StravaLeaderboardDateRange.thisYear.description")), //$NON-NLS-1$ //$NON-NLS-2$
	THIS_MONTH(Messages.getString("StravaLeaderboardDateRange.thisMonth"), Messages.getString("StravaLeaderboardDateRange.thisMonth.description")), //$NON-NLS-1$ //$NON-NLS-2$
	THIS_WEEK(Messages.getString("StravaLeaderboardDateRange.thisWeek"), Messages.getString("StravaLeaderboardDateRange.thisWeek.description")), //$NON-NLS-1$ //$NON-NLS-2$
	TODAY(Messages.getString("StravaLeaderboardDateRange.today"), Messages.getString("StravaLeaderboardDateRange.today.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

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
