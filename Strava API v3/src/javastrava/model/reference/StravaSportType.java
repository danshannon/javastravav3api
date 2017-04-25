package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.SportTypeSerializer;
import javastrava.model.StravaClub;

/**
 * <p>
 * Strava sport type associated with {@link StravaClub clubs}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaSportType implements StravaReferenceType<String> {
	/**
	 * Cycling
	 */
	CYCLING(StravaConfig.string("StravaSportType.cycling"), Messages.string("StravaSportType.cycling.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Running
	 */
	RUNNING(StravaConfig.string("StravaSportType.running"), Messages.string("StravaSportType.running.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Triathlon
	 */
	TRIATHLON(StravaConfig.string("StravaSportType.triathlon"), Messages.string("StravaSportType.triathlon.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Other sport types
	 */
	OTHER(StravaConfig.string("StravaSportType.other"), Messages.string("StravaSportType.other.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaSportType} as returned by the Strava API
	 * @return The matching {@link StravaSportType}, or {@link StravaSportType#UNKNOWN} if there is no match
	 * @see SportTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaSportType create(final String id) {
		final StravaSportType[] sportTypes = StravaSportType.values();
		for (final StravaSportType sportType : sportTypes) {
			if (sportType.getId().equals(id)) {
				return sportType;
			}
		}
		return StravaSportType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private String	id;

	/**
	 * description
	 */
	private String	description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaSportType(final String id, final String description) {
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
	 * @return The string representation of this {@link StravaSportType} to be used with the Strava API
	 * @see SportTypeSerializer#serialize(StravaSportType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
