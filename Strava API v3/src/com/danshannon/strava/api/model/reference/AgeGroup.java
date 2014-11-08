package com.danshannon.strava.api.model.reference;

import com.danshannon.strava.api.model.SegmentLeaderboard;

/**
 * Age group ranges used to generate {@link SegmentLeaderboard segment leaderboards}
 * 
 * @author Dan Shannon
 *
 */
public enum AgeGroup {
	AGE0_24("0_24","Up to 24"),
	AGE25_34("25_34","25 to 34"),
	AGE35_44("35_44","35 to 44"),
	AGE45_54("45_54","45 to 54"),
	AGE55_64("55_64","55 to 64"),
	AGE65_PLUS("65_plus","65 and over"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private AgeGroup(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}
	
	// For use as Jackson @JsonCreator
	public static AgeGroup create(String id) {
		AgeGroup[] ageGroups = AgeGroup.values();
		for (AgeGroup ageGroup : ageGroups) {
			if (ageGroup.getId().equals(id)) {
				return ageGroup;
			}
		}
		return AgeGroup.UNKNOWN;
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
