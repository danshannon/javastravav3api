package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.GenderSerializer;

/**
 * <p>
 * Athlete gender
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaGender {
	/**
	 * Male
	 */
	MALE(Messages.getString("StravaGender.male"), Messages.getString("StravaGender.male.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Female
	 */
	FEMALE(Messages.getString("StravaGender.female"), Messages.getString("StravaGender.female.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaGender(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaGender} to be used with the Strava API
	 * @see GenderSerializer#serialize(StravaGender, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaGender} returned by the Strava API
	 * @return The matching {@link StravaGender}, or {@link StravaGender#UNKNOWN} if there is no match
	 */
	public static StravaGender create(final String id) {
		StravaGender[] genders = StravaGender.values();
		for (StravaGender gender : genders) {
			if (gender.getId().equals(id)) {
				return gender;
			}
		}
		return StravaGender.UNKNOWN;
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
