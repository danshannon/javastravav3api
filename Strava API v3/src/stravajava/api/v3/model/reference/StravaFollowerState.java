package stravajava.api.v3.model.reference;

/**
 * @author Dan Shannon
 *
 */
public enum StravaFollowerState {
	PENDING("pending", "Pending"), ACCEPTED("accepted", "Accepted"), BLOCKED("blocked", "Blocked"), UNKNOWN("UNKNOWN", "Unknown");

	private String id;
	private String description;

	private StravaFollowerState(String id, String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaFollowerState create(String id) {
		StravaFollowerState[] followerStates = StravaFollowerState.values();
		for (StravaFollowerState followerState : followerStates) {
			if (followerState.getId().equals(id)) {
				return followerState;
			}
		}
		return StravaFollowerState.UNKNOWN;
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
