package com.danshannon.strava.api.model.reference;


/**
 * @author dshannon
 *
 */
public enum StreamSeriesDownsamplingType {
	TIME("time","Time"),
	DISTANCE("distance","Distance"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private StreamSeriesDownsamplingType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StreamSeriesDownsamplingType create(String id) {
		StreamSeriesDownsamplingType[] types = StreamSeriesDownsamplingType.values();
		for (StreamSeriesDownsamplingType type : types ) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StreamSeriesDownsamplingType.UNKNOWN;
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
