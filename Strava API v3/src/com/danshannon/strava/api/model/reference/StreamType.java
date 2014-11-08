package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum StreamType {
	TIME("time","Time"),
	MAPPOINT("latlng","Latitude/Longitude"),
	DISTANCE("distance","Distance"),
	ALTITUDE("altitude","Altitude"),
	VELOCITY("velocity_smooth","Velocity (smoothed)"),
	HEARTRATE("heartrate","Heartrate"),
	CADENCE("cadence","Cadence"),
	POWER("watts","Watts"),
	TEMPERATURE("temp","Temperature"),
	MOVING("moving","Moving?"),
	GRADE("grade_smooth","Grade % (smoothed)"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private StreamType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StreamType create(String id) {
		StreamType[] streamTypes = StreamType.values();
		for (StreamType streamType : streamTypes) {
			if (streamType.getId().equals(id)) {
				return streamType;
			}
		}
		return StreamType.UNKNOWN;
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
