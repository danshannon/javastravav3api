package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.model.StravaGear;

/**
 * Type of {@link StravaGear}
 * @author Dan Shannon
 *
 */
public enum StravaGearType implements StravaReferenceType<String> {
	/**
	 * Bike
	 */
	BIKE(StravaConfig.string("StravaGearType.bike"),Messages.string("StravaGearType.bike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Running shoes
	 */
	SHOES(StravaConfig.string("StravaGearType.shoes"),Messages.string("StravaGearType.shoes.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"),Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaGearType} returned by the Strava API
	 * @return The matching {@link StravaGearType}, or {@link StravaGearType#UNKNOWN} if there is no match
	 */
	public static StravaGearType create(final String id) {
		for (final StravaGearType type : StravaGearType.values()) {
			if (type.getId().toLowerCase().equals(id.toLowerCase())) {
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
	private StravaGearType(final String id, final String description) {
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
	 * @return The string representation of this {@link StravaGearType} to be used with the Strava API
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
