package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.StreamTypeSerializer;

/**
 * <p>
 * Data stream types
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaStreamType implements StravaReferenceType<String> {
	/**
	 * Time
	 */
	TIME(StravaConfig.string("StravaStreamType.time"), Messages.string("StravaStreamType.time.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Map points (locations)
	 */
	MAPPOINT(StravaConfig.string("StravaStreamType.location"), Messages.string("StravaStreamType.location.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Distance
	 */
	DISTANCE(StravaConfig.string("StravaStreamType.distance"), Messages.string("StravaStreamType.distance.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Altitude
	 */
	ALTITUDE(StravaConfig.string("StravaStreamType.altitude"), Messages.string("StravaStreamType.altitude.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Speed
	 */
	VELOCITY(StravaConfig.string("StravaStreamType.velocity"), Messages.string("StravaStreamType.velocity.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Heart rate
	 */
	HEARTRATE(StravaConfig.string("StravaStreamType.heartrate"), Messages.string("StravaStreamType.heartrate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Cadence
	 */
	CADENCE(StravaConfig.string("StravaStreamType.cadence"), Messages.string("StravaStreamType.cadence.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Power
	 */
	POWER(StravaConfig.string("StravaStreamType.power"), Messages.string("StravaStreamType.power.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Temperature
	 */
	TEMPERATURE(StravaConfig.string("StravaStreamType.temperature"), Messages.string("StravaStreamType.temperature.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Moving (as a series of booleans, indicating whether the athlete was moving or not at each point
	 */
	MOVING(StravaConfig.string("StravaStreamType.moving"), Messages.string("StravaStreamType.moving.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Grade
	 */
	GRADE(StravaConfig.string("StravaStreamType.grade"), Messages.string("StravaStreamType.grade.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamType} as returned by the Strava API
	 * @return The matching {@link StravaStreamType}, or {@link StravaStreamType#UNKNOWN} if there is no match
	 */
	public static StravaStreamType create(final String id) {
		final StravaStreamType[] streamTypes = StravaStreamType.values();
		for (final StravaStreamType streamType : streamTypes) {
			if (streamType.getId().equals(id)) {
				return streamType;
			}
		}
		return StravaStreamType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private String	id;

	/**
	 * Description
	 */
	private String	description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaStreamType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamType} to be used with the Strava API
	 * @see StreamTypeSerializer#serialize(StravaStreamType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}
