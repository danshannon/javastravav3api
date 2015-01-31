package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum FollowerState {
	PENDING("pending","Pending"),
	ACCEPTED("accepted","Accepted"),
	BLOCKED("blocked","Blocked"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	private String description;
	
	private FollowerState(String id,String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static FollowerState create(String id) {
		FollowerState[] followerStates = FollowerState.values();
		for (FollowerState followerState : followerStates) {
			if (followerState.getId().equals(id)) {
				return followerState;
			}
		}
		return FollowerState.UNKNOWN;
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
