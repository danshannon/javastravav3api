package stravajava.api.v3.model.reference;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSegmentExplorerActivityType {
	RUNNING("running", "Running"), RIDING("riding", "Riding"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaSegmentExplorerActivityType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaSegmentExplorerActivityType create(String id) {
		for (StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
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
