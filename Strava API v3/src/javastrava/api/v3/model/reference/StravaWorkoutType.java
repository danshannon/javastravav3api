package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaActivity;
import javastrava.config.Messages;

/**
 * <p>
 * Workout types associated with {@link StravaActivity activities}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaWorkoutType {
	DEFAULT(0, Messages.getString("StravaWorkoutType.default.description")),  //$NON-NLS-1$
	RACE(1, Messages.getString("StravaWorkoutType.race.description")),  //$NON-NLS-1$
	LONG_RUN(2, Messages.getString("StravaWorkoutType.longRun.description")),  //$NON-NLS-1$
	INTERVALS(3, Messages.getString("StravaWorkoutType.intervals.description")),  //$NON-NLS-1$
	UNKNOWN(-1, Messages.getString("Common.unknown.description")); //$NON-NLS-1$

	private Integer	id;
	private String	description;

	private StravaWorkoutType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public Integer getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaWorkoutType create(final Integer id) {
		StravaWorkoutType[] workoutTypes = StravaWorkoutType.values();
		for (StravaWorkoutType workoutType : workoutTypes) {
			if (workoutType.getId().equals(id)) {
				return workoutType;
			}
		}
		return StravaWorkoutType.UNKNOWN;
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
