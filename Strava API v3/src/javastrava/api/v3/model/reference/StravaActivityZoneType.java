package javastrava.api.v3.model.reference;

import javastrava.api.v3.service.ActivityServices;
import javastrava.config.Messages;

/**
 * <p>
 * Type of activity zone - see {@link ActivityServices#listActivityZones(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaActivityZoneType {
	HEARTRATE(Messages.getString("StravaActivityZoneType.heartrate"), Messages.getString("StravaActivityZoneType.heartrate.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	POWER(Messages.getString("StravaActivityZoneType.power"), Messages.getString("StravaActivityZoneType.power.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaActivityZoneType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
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
