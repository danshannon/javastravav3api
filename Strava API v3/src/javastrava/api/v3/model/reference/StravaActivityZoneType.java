package javastrava.api.v3.model.reference;

import javastrava.api.v3.service.ActivityService;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ActivityZoneTypeSerializer;

/**
 * <p>
 * Type of activity zone - see {@link ActivityService#listActivityZones(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaActivityZoneType {
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
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaActivityZoneType}
	 * @see ActivityZoneTypeSerializer#serialize(StravaActivityZoneType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaActivityZoneType} as returned by the Strava API
	 * @return The matching {@link StravaActivityZoneType}, or {@link StravaActivityZoneType#UNKNOWN} if there is no match
	 */
	public static StravaActivityZoneType create(final String id) {
		StravaActivityZoneType[] types = StravaActivityZoneType.values();
		for (StravaActivityZoneType type : types) {
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
