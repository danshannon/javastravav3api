package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Preferred measurement system for an athlete. Those of you living in the 19th century will prefer {@link #IMPERIAL}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaMeasurementMethod {
	IMPERIAL(Messages.getString("StravaMeasurementMethod.imperial"), Messages.getString("StravaMeasurementMethod.imperial.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	METRIC(Messages.getString("StravaMeasurementMethod.metric"), Messages.getString("StravaMeasurementMethod.metric.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

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
