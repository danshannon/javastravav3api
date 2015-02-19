package javastrava.api.v3.model.reference;

/**
 * <p>
 * Data stream types
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamType {
	TIME("time", "Time"),
	MAPPOINT("latlng", "Latitude/Longitude"),
	DISTANCE("distance", "Distance"),
	ALTITUDE("altitude", "Altitude"),
	VELOCITY("velocity_smooth", "Velocity (smoothed)"),
	HEARTRATE("heartrate", "Heartrate"),
	CADENCE("cadence", "Cadence"),
	POWER("watts", "Watts"),
	TEMPERATURE("temp", "Temperature"),
	MOVING("moving", "Moving?"),
	GRADE("grade_smooth", "Grade % (smoothed)"),
	UNKNOWN("UNKNOWN", "Unknown");

	private String	id;
	private String	description;

	private StravaStreamType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaStreamType create(final String id) {
		StravaStreamType[] streamTypes = StravaStreamType.values();
		for (StravaStreamType streamType : streamTypes) {
			if (streamType.getId().equals(id)) {
				return streamType;
			}
		}
		return StravaStreamType.UNKNOWN;
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
