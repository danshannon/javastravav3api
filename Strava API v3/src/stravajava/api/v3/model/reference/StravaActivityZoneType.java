package stravajava.api.v3.model.reference;

/**
 * @author Dan Shannon
 *
 */
public enum StravaActivityZoneType {
	HEARTRATE("heartrate", "Heartrate"), POWER("power", "Power"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaActivityZoneType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
	public static StravaActivityZoneType create(String id) {
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
