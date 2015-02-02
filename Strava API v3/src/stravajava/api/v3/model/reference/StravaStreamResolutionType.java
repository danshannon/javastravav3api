package stravajava.api.v3.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum StravaStreamResolutionType {
	LOW("low","low"),
	MEDIUM("medium","medium"),
	HIGH("high","high"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private StravaStreamResolutionType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static StravaStreamResolutionType create(String id) {
		StravaStreamResolutionType[] types = StravaStreamResolutionType.values();
		for (StravaStreamResolutionType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamResolutionType.UNKNOWN;
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
