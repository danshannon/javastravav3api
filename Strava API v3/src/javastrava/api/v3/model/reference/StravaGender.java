package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Athlete gender
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaGender {
	MALE(Messages.getString("StravaGender.male"), Messages.getString("StravaGender.male.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	FEMALE(Messages.getString("StravaGender.female"), Messages.getString("StravaGender.female.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaGender(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
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
