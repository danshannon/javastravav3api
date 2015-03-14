package javastrava.api.v3.model.reference;

import javastrava.api.v3.service.ActivityServices;
import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.ActivityZoneTypeSerializer;

/**
 * <p>
 * Type of activity zone - see {@link ActivityServices#listActivityZones(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaActivityZoneType {
	/**
	 * Heart rate
	 */
	HEARTRATE(Messages.getString("StravaActivityZoneType.heartrate"), Messages.getString("StravaActivityZoneType.heartrate.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Power
	 */
	POWER(Messages.getString("StravaActivityZoneType.power"), Messages.getString("StravaActivityZoneType.power.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

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
