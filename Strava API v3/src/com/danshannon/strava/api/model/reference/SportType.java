package com.danshannon.strava.api.model.reference;



/**
 * @author Dan Shannon
 *
 */
public enum SportType {
	CYCLING("cycling","Cycling"),
	RUNNING("running","Running"),
	TRIATHLON("triathlon","Triathlon"),
	OTHER("other","Other"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private SportType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static SportType create(String id) {
		SportType[] sportTypes = SportType.values();
		for (SportType sportType : sportTypes) {
			if (sportType.getId().equals(id)) {
				return sportType;
			}
		}
		return SportType.UNKNOWN;
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
