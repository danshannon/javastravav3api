package javastrava.api.v3.model.reference;

/**
 * <p>
 * State of a resource returned from Strava.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaResourceState {
	UPDATING(-1, "updating"), META(1, "meta"), SUMMARY(2, "summary"), DETAILED(3, "detailed"), UNKNOWN(-2, "Unknown");

	private Integer	id;
	private String	description;

	// @JsonValue
	public Integer getValue() {
		return this.id;
	}

	private StravaResourceState(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonCreator
	public static StravaResourceState create(final Integer id) {
		StravaResourceState[] states = StravaResourceState.values();
		for (StravaResourceState state : states) {
			if (state.getValue() != null && state.getValue().equals(id)) {
				return state;
			}
		}
		return StravaResourceState.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
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
		return this.id.toString();
	}
}
