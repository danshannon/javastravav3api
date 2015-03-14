package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.ActivityTypeSerializer;

/**
 * <p>
 * Possible values: ride, run, swim, workout, hike, walk, nordicski, alpineski, backcountryski, iceskate, inlineskate, kitesurf,
 * rollerski, windsurf, workout, snowboard, snowshoe
 * </p>
 * 
 * <p>
 * Type detected from file upload overrides, uses athlete's default type if not specified
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaActivityType {
	/**
	 * Bike ride
	 */
	RIDE(Messages.getString("StravaActivityType.ride"), Messages.getString("StravaActivityType.ride.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Run
	 */
	RUN(Messages.getString("StravaActivityType.run"), Messages.getString("StravaActivityType.run.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Swim
	 */
	SWIM(Messages.getString("StravaActivityType.swim"), Messages.getString("StravaActivityType.swim.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Workout
	 */
	WORKOUT(Messages.getString("StravaActivityType.workout"), Messages.getString("StravaActivityType.workout.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Hike
	 */
	HIKE(Messages.getString("StravaActivityType.hike"), Messages.getString("StravaActivityType.hike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Walk
	 */
	WALK(Messages.getString("StravaActivityType.walk"), Messages.getString("StravaActivityType.walk.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Nordic skiing (telemark)
	 */
	NORDIC_SKI(Messages.getString("StravaActivityType.nordicski"), Messages.getString("StravaActivityType.nordicski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Alpine skiing
	 */
	ALPINE_SKI(Messages.getString("StravaActivityType.alpineski"), Messages.getString("StravaActivityType.alpineski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Back-country skiing (off piste)
	 */
	BACKCOUNTRY_SKI(Messages.getString("StravaActivityType.backcountryski"), Messages.getString("StravaActivityType.backcountryski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Ice skating
	 */
	ICE_SKATE(Messages.getString("StravaActivityType.iceskate"), Messages.getString("StravaActivityType.iceskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Inline skating (rollerblading)
	 */
	INLINE_SKATE(Messages.getString("StravaActivityType.inlineskate"), Messages.getString("StravaActivityType.inlineskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Kite surfing
	 */
	KITESURF(Messages.getString("StravaActivityType.kitesurf"), Messages.getString("StravaActivityType.kitesurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Rollerskiing
	 */
	ROLLERSKI(Messages.getString("StravaActivityType.rollerski"), Messages.getString("StravaActivityType.rollerski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Windsurfing
	 */
	WINDSURF(Messages.getString("StravaActivityType.windsurf"), Messages.getString("StravaActivityType.windsurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Snowboarding
	 */
	SNOWBOARD(Messages.getString("StravaActivityType.snowboard"), Messages.getString("StravaActivityType.snowboard.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Snowshoeing
	 */
	SNOWSHOE(Messages.getString("StravaActivityType.snowshoe"), Messages.getString("StravaActivityType.snowshoe.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaActivityType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return The id
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * @param id The string representation of the activity type as returned by the Strava API
	 * @return The {@link StravaActivityType} with the matching id, or {@link StravaActivityType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaActivityType create(final String id) {
		for (StravaActivityType type : StravaActivityType.values()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * @return the id
	 * @see ActivityTypeSerializer#serialize(StravaActivityType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
