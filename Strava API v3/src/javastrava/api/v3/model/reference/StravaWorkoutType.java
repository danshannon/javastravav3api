package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaActivity;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.WorkoutTypeSerializer;

/**
 * <p>
 * Workout types associated with {@link StravaActivity activities}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaWorkoutType implements StravaReferenceType<Integer> {
	/**
	 * Default
	 */
	DEFAULT(StravaConfig.integer("StravaWorkoutType.default"), Messages.string("StravaWorkoutType.default.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Race
	 */
	RACE(StravaConfig.integer("StravaWorkoutType.race"), Messages.string("StravaWorkoutType.race.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Long run
	 */
	LONG_RUN(StravaConfig.integer("StravaWorkoutType.longRun"), Messages.string("StravaWorkoutType.longRun.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Intervals
	 */
	INTERVALS(StravaConfig.integer("StravaWorkoutType.intervals"), Messages.string("StravaWorkoutType.intervals.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of a {@link StravaWorkoutType} as returned by the Strava API
	 * @return The matching {@link StravaWorkoutType}, or {@link StravaWorkoutType#UNKNOWN} if there is no match
	 * @see WorkoutTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaWorkoutType create(final Integer id) {
		final StravaWorkoutType[] workoutTypes = StravaWorkoutType.values();
		for (final StravaWorkoutType workoutType : workoutTypes) {
			if (workoutType.getId().equals(id)) {
				return workoutType;
			}
		}
		return StravaWorkoutType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private Integer	id;

	/**
	 * Description
	 */
	private String	description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaWorkoutType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The integer representation of this {@link StravaWorkoutType} to be used with the Strava API
	 * @see WorkoutTypeSerializer#serialize(StravaWorkoutType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public Integer getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}

}
