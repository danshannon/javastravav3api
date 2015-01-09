package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum ResourceState {
	META(1,"meta"),
	SUMMARY(2,"summary"),
	DETAILED(3,"detailed"),
	UNKNOWN(null,"Unknown");
	
	private Integer id;
	private String description;
	
	//@JsonValue
	public Integer getValue() {
		return this.id;
	}
	
	private ResourceState(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonCreator
	public static ResourceState create(Integer id) {
		ResourceState[] states = ResourceState.values();
		for (ResourceState state : states) {
			if (state.getValue() != null && state.getValue().equals(id)) {
				return state;
			}
		}
		return ResourceState.UNKNOWN;
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
