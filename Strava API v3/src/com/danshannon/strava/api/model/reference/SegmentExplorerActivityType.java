package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum SegmentExplorerActivityType {
	RUNNING("running","Running"),
	RIDING("riding","Riding"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private SegmentExplorerActivityType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static SegmentExplorerActivityType create(String id) {
		for (SegmentExplorerActivityType type : SegmentExplorerActivityType.values()) {
			if (type.getId().equals(id)) {
				return type;
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
