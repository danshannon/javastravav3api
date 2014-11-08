package com.danshannon.strava.api.model.reference;


/**
 * @author dshannon
 *
 */
public enum SegmentActivityType {
	RIDE("Ride","Ride"),
	RUN("Run","Run"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private SegmentActivityType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static SegmentActivityType create(String id) {
		SegmentActivityType[] activityTypes = SegmentActivityType.values();
		for (SegmentActivityType activityType : activityTypes) {
			if (activityType.getId().equals(id)) {
				return activityType;
			}
		}
		return SegmentActivityType.UNKNOWN;
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
