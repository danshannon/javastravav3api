package com.danshannon.strava.api.model.reference;


/**
 * @author dshannon
 *
 */
public enum Gender {
	MALE("M","Male"),
	FEMALE("F","Female"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private Gender(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static Gender create(String id) {
		Gender[] genders = Gender.values();
		for (Gender gender : genders) {
			if (gender.getId().equals(id)) {
				return gender;
			}
		}
		return Gender.UNKNOWN;
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
