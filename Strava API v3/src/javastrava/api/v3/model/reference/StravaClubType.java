package javastrava.api.v3.model.reference;

/**
 * <p>
 * Strava club type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClubType {
	CASUAL("casual_club", "Casual club"),
	TEAM("racing_team", "Racing team"),
	SHOP("shop", "Shop"),
	COMPANY("company", "Company"),
	OTHER("other", "Other"),
	UNKNOWN("UNKNOWN", "Unknown");

	private String	id;
	private String	description;

	private StravaClubType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaClubType create(final String id) {
		StravaClubType[] clubTypes = StravaClubType.values();
		for (StravaClubType clubType : clubTypes) {
			if (clubType.getId().equals(id)) {
				return clubType;
			}
		}
		return StravaClubType.UNKNOWN;
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
