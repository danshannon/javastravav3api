package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum StreamResolutionType {
	LOW("low","low"),
	MEDIUM("medium","medium"),
	HIGH("high","high"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private StreamResolutionType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StreamResolutionType create(String id) {
		StreamResolutionType[] types = StreamResolutionType.values();
		for (StreamResolutionType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StreamResolutionType.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
}
