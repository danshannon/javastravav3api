package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Bicycle frame type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaFrameType {
	MOUNTAIN_BIKE(1, Messages.getString("StravaFrameType.mountain_bike.description")),  //$NON-NLS-1$
	CROSS(2, Messages.getString("StravaFrameType.cross.description")),  //$NON-NLS-1$
	ROAD(3, Messages.getString("StravaFrameType.road.description")),  //$NON-NLS-1$
	TIME_TRIAL(4, Messages.getString("StravaFrameType.tt.description")),  //$NON-NLS-1$
	UNKNOWN(-1, Messages.getString("Common.unknown.description")); //$NON-NLS-1$

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
