package javastrava.api.v3.model.reference;

/**
 * <p>
 * Preferred measurement system for an athlete. Those of you living in the 19th century will prefer {@link #IMPERIAL}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaMeasurementMethod {
	IMPERIAL("feet", "Imperial"), METRIC("meters", "Metric"), UNKNOWN("UNKNOWN", "Unknown");

	private String	id;
	private String	description;

	private StravaMeasurementMethod(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaMeasurementMethod create(final String id) {
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
