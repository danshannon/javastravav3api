package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum WorkoutType {
	DEFAULT(0,"Default"),
	RACE(1,"Race"),
	LONG_RUN(2,"Long run"),
	INTERVALS(3,"Intervals"),
	UNKNOWN(-1,"Unknown");
	
	private Integer id;
	private String description;
	
	private WorkoutType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	//@JsonValue
	public Integer getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static WorkoutType create(Integer id) {
		WorkoutType[] workoutTypes = WorkoutType.values();
		for (WorkoutType workoutType : workoutTypes) {
			if (workoutType.getId().equals(id)) {
				return workoutType;
			}
		}
		return WorkoutType.UNKNOWN;
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
