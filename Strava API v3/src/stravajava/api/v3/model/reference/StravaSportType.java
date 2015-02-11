package stravajava.api.v3.model.reference;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSportType {
	CYCLING("cycling", "Cycling"), RUNNING("running", "Running"), TRIATHLON("triathlon", "Triathlon"), OTHER("other", "Other"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaSportType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaSportType create(String id) {
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
