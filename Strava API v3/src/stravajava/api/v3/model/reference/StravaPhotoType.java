package stravajava.api.v3.model.reference;

/**
 * @author Dan Shannon
 *
 */
public enum StravaPhotoType {
	INSTAGRAM("InstagramPhoto", "Instagram"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaPhotoType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaPhotoType create(String id) {
		StravaPhotoType[] types = StravaPhotoType.values();
		for (StravaPhotoType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaPhotoType.UNKNOWN;
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
