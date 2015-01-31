package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum PhotoType {
	INSTAGRAM("InstagramPhoto","Instagram"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private PhotoType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static PhotoType create(String id) {
		PhotoType[] types = PhotoType.values();
		for (PhotoType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return PhotoType.UNKNOWN;
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
