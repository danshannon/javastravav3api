package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.GenderSerializer;

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
	MALE(StravaConfig.string("StravaGender.male"), Messages.string("StravaGender.male.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Female
	 */
	FEMALE(StravaConfig.string("StravaGender.female"), Messages.string("StravaGender.female.description")),  //$NON-NLS-1$ //$NON-NLS-2$
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
