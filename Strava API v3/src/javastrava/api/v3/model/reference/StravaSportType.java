package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaClub;
import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.SportTypeSerializer;

/**
 * <p>
 * Strava sport type associated with {@link StravaClub clubs}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSportType {
	/**
	 * Cycling
	 */
	CYCLING(Messages.getString("StravaSportType.cycling"), Messages.getString("StravaSportType.cycling.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Running
	 */
	RUNNING(Messages.getString("StravaSportType.running"), Messages.getString("StravaSportType.running.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Triathlon
	 */
	TRIATHLON(Messages.getString("StravaSportType.triathlon"), Messages.getString("StravaSportType.triathlon.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Other sport types
	 */
	OTHER(Messages.getString("StravaSportType.other"), Messages.getString("StravaSportType.other.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaSportType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaSportType} to be used with the Strava API
	 * @see SportTypeSerializer#serialize(StravaSportType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaSportType} as returned by the Strava API
	 * @return The matching {@link StravaSportType}, or {@link StravaSportType#UNKNOWN} if there is no match
	 * @see SportTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaSportType create(final String id) {
		StravaSportType[] sportTypes = StravaSportType.values();
		for (StravaSportType sportType : sportTypes) {
			if (sportType.getId().equals(id)) {
				return sportType;
			}
		}
		return StravaSportType.UNKNOWN;
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
