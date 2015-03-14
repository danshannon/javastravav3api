/**
 * 
 */
package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * @author Dan Shannon
 *
 */
public enum StravaGearType {
	BIKE(Messages.getString("StravaGearType.bike"),Messages.getString("StravaGearType.bike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	SHOES(Messages.getString("StravaGearType.shoes"),Messages.getString("StravaGearType.shoes.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"),Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$
	
	private String	id;
	private String	description;

	private StravaGearType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
	public static StravaGearType create(final String id) {
		for (StravaGearType type : StravaGearType.values()) {
			if (type.getId().toLowerCase().equals(id.toLowerCase())) {
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
