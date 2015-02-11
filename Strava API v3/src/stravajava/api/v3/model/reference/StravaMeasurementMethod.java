package stravajava.api.v3.model.reference;

/**
 * @author dshannon
 *
 */
public enum StravaMeasurementMethod {
	IMPERIAL("feet", "Imperial"), METRIC("meters", "Metric"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaMeasurementMethod(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaMeasurementMethod create(String id) {
		StravaMeasurementMethod[] methods = StravaMeasurementMethod.values();
		for (StravaMeasurementMethod method : methods) {
			if (method.getId().equals(id)) {
				return method;
			}
		}
		return StravaMeasurementMethod.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

}
