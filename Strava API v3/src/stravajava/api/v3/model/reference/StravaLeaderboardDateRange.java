package stravajava.api.v3.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum StravaLeaderboardDateRange {
	THIS_YEAR("this_year","This year"),
	THIS_MONTH("this_month","This month"),
	THIS_WEEK("this_week","This week"),
	TODAY("today","Today"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private StravaLeaderboardDateRange(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StravaLeaderboardDateRange create(String id) {
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
