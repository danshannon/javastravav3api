package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ActivityZoneTypeSerializer;
import javastrava.service.ActivityService;

/**
 * <p>
 * Type of activity zone - see {@link ActivityService#listActivityZones(Long)}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaActivityZoneType implements StravaReferenceType<String> {
	/**
	 * Heart rate
	 */
	HEARTRATE(StravaConfig.string("StravaActivityZoneType.heartrate"), Messages.string("StravaActivityZoneType.heartrate.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Power
	 */
	POWER(StravaConfig.string("StravaActivityZoneType.power"), Messages.string("StravaActivityZoneType.power.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaActivityZoneType} as returned by the Strava API
	 * @return The matching {@link StravaActivityZoneType}, or {@link StravaActivityZoneType#UNKNOWN} if there is no match
	 */
	public static StravaActivityZoneType create(final String id) {
		final StravaActivityZoneType[] types = StravaActivityZoneType.values();
		for (final StravaActivityZoneType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
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
	private StravaActivityZoneType(final String id, final String description) {
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
	 * @return The string representation of this {@link StravaActivityZoneType}
	 * @see ActivityZoneTypeSerializer#serialize(StravaActivityZoneType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
