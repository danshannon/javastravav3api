package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Data stream types
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamType {
	TIME(Messages.getString("StravaStreamType.time"), Messages.getString("StravaStreamType.time.description")), //$NON-NLS-1$ //$NON-NLS-2$
	MAPPOINT(Messages.getString("StravaStreamType.location"), Messages.getString("StravaStreamType.location.description")), //$NON-NLS-1$ //$NON-NLS-2$
	DISTANCE(Messages.getString("StravaStreamType.distance"), Messages.getString("StravaStreamType.distance.description")), //$NON-NLS-1$ //$NON-NLS-2$
	ALTITUDE(Messages.getString("StravaStreamType.altitude"), Messages.getString("StravaStreamType.altitude.description")), //$NON-NLS-1$ //$NON-NLS-2$
	VELOCITY(Messages.getString("StravaStreamType.velocity"), Messages.getString("StravaStreamType.velocity.description")), //$NON-NLS-1$ //$NON-NLS-2$
	HEARTRATE(Messages.getString("StravaStreamType.heartrate"), Messages.getString("StravaStreamType.heartrate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	CADENCE(Messages.getString("StravaStreamType.cadence"), Messages.getString("StravaStreamType.cadence.description")), //$NON-NLS-1$ //$NON-NLS-2$
	POWER(Messages.getString("StravaStreamType.power"), Messages.getString("StravaStreamType.power.description")), //$NON-NLS-1$ //$NON-NLS-2$
	TEMPERATURE(Messages.getString("StravaStreamType.temperature"), Messages.getString("StravaStreamType.temperature.description")), //$NON-NLS-1$ //$NON-NLS-2$
	MOVING(Messages.getString("StravaStreamType.moving"), Messages.getString("StravaStreamType.moving.description")), //$NON-NLS-1$ //$NON-NLS-2$
	GRADE(Messages.getString("StravaStreamType.grade"), Messages.getString("StravaStreamType.grade.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

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
