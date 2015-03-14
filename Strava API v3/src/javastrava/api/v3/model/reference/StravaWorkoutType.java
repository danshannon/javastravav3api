package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaActivity;
import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.WorkoutTypeSerializer;

/**
 * <p>
 * Workout types associated with {@link StravaActivity activities}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaWorkoutType {
	/**
	 * Default
	 */
	DEFAULT(Integer.valueOf(0), Messages.getString("StravaWorkoutType.default.description")),  //$NON-NLS-1$
	/**
	 * Race
	 */
	RACE(Integer.valueOf(1), Messages.getString("StravaWorkoutType.race.description")),  //$NON-NLS-1$
	/**
	 * Long run
	 */
	LONG_RUN(Integer.valueOf(2), Messages.getString("StravaWorkoutType.longRun.description")),  //$NON-NLS-1$
	/**
	 * Intervals
	 */
	INTERVALS(Integer.valueOf(3), Messages.getString("StravaWorkoutType.intervals.description")),  //$NON-NLS-1$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Integer.valueOf(-1), Messages.getString("Common.unknown.description")); //$NON-NLS-1$

	private Integer	id;
	private String	description;

	private StravaWorkoutType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The integer representation of this {@link StravaWorkoutType} to be used with the Strava API
	 * @see WorkoutTypeSerializer#serialize(StravaWorkoutType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public Integer getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of a {@link StravaWorkoutType} as returned by the Strava API
	 * @return The matching {@link StravaWorkoutType}, or {@link StravaWorkoutType#UNKNOWN} if there is no match
	 * @see WorkoutTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
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
