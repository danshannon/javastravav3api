package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum LeaderboardDateRange {
	THIS_YEAR("this_year","This year"),
	THIS_MONTH("this_month","This month"),
	THIS_WEEK("this_week","This week"),
	TODAY("today","Today"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private LeaderboardDateRange(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static LeaderboardDateRange create(String id) {
		for (LeaderboardDateRange dateRange : LeaderboardDateRange.values()) {
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
