package javastrava.api.v3.model.reference;

/**
 * <p>
 * Bicycle frame type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaFrameType {
	MOUNTAIN_BIKE(1, "Mountain Bike"), CROSS(2, "Cross"), ROAD(3, "Road Bike"), TIME_TRIAL(4, "Time Trial"), UNKNOWN(-1, "Unknown");

	private Integer	id;
	private String	description;

	private StravaFrameType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public Integer getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaFrameType create(final Integer id) {
		StravaFrameType[] frameTypes = StravaFrameType.values();
		for (StravaFrameType frameType : frameTypes) {
			if (frameType.getId().equals(id)) {
				return frameType;
			}
		}
		return StravaFrameType.UNKNOWN;
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
