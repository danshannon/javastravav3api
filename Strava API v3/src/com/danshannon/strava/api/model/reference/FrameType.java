package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum FrameType {
	MOUNTAIN_BIKE(1,"Mountain Bike"),
	CROSS(2,"Cross"),
	ROAD(3,"Road Bike"),
	TIME_TRIAL(4,"Time Trial"),
	UNKNOWN(-1,"Unknown");
	
	private Integer id;
	private String description;
	
	private FrameType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public Integer getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static FrameType create(Integer id) {
		FrameType[] frameTypes = FrameType.values();
		for (FrameType frameType : frameTypes) {
			if (frameType.getId().equals(id)) {
				return frameType;
			}
		}
		return FrameType.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
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
		return this.id.toString();
	}
	
}
