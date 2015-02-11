package stravajava.api.v3.model.reference;

import stravajava.api.v3.model.StravaSegmentLeaderboard;

/**
 * Age group ranges used to generate {@link StravaSegmentLeaderboard segment leaderboards}
 * 
 * @author Dan Shannon
 *
 */
public enum StravaAgeGroup {
	AGE0_24("0_24", "Up to 24"), AGE25_34("25_34", "25 to 34"), AGE35_44("35_44", "35 to 44"), AGE45_54("45_54", "45 to 54"), AGE55_64("55_64", "55 to 64"), AGE65_PLUS(
			"65_plus", "65 and over"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaAgeGroup(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
	public static StravaAgeGroup create(String id) {
		StravaAgeGroup[] ageGroups = StravaAgeGroup.values();
		for (StravaAgeGroup ageGroup : ageGroups) {
			if (ageGroup.getId().equals(id)) {
				return ageGroup;
			}
		}
		return StravaAgeGroup.UNKNOWN;
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
