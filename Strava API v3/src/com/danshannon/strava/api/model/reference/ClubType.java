package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum ClubType {
	CASUAL("casual_club","Casual club"),
	TEAM("racing_team","Racing team"),
	SHOP("shop","Shop"),
	COMPANY("company","Company"),
	OTHER("other","Other"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private ClubType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static ClubType create(String id) {
		ClubType[] clubTypes = ClubType.values();
		for (ClubType clubType : clubTypes) {
			if (clubType.getId().equals(id)) {
				return clubType;
			}
		}
		return ClubType.UNKNOWN;
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
