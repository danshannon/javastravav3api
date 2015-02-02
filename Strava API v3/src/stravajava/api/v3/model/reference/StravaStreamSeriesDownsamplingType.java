package stravajava.api.v3.model.reference;


/**
 * @author dshannon
 *
 */
public enum StravaStreamSeriesDownsamplingType {
	TIME("time","Time"),
	DISTANCE("distance","Distance"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private StravaStreamSeriesDownsamplingType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StravaStreamSeriesDownsamplingType create(String id) {
		StravaStreamSeriesDownsamplingType[] types = StravaStreamSeriesDownsamplingType.values();
		for (StravaStreamSeriesDownsamplingType type : types ) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamSeriesDownsamplingType.UNKNOWN;
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
