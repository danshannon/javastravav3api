package com.danshannon.strava.api.model.reference;


/**
 * @author dshannon
 *
 */
public enum MeasurementMethod {
	IMPERIAL("feet","Imperial"),
	METRIC("meters","Metric"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private MeasurementMethod(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static MeasurementMethod create(String id) {
		MeasurementMethod[] methods = MeasurementMethod.values();
		for (MeasurementMethod method : methods) {
			if (method.getId().equals(id)) {
				return method;
			}
		}
		return MeasurementMethod.UNKNOWN;
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
