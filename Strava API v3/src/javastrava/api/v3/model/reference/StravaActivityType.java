package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.Strava;
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
	RIDE(Strava.stringProperty("StravaActivityType.ride"), Messages.getString("StravaActivityType.ride.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Run
	 */
	RUN(Strava.stringProperty("StravaActivityType.run"), Messages.getString("StravaActivityType.run.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Swim
	 */
	SWIM(Strava.stringProperty("StravaActivityType.swim"), Messages.getString("StravaActivityType.swim.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Workout
	 */
	WORKOUT(Strava.stringProperty("StravaActivityType.workout"), Messages.getString("StravaActivityType.workout.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Hike
	 */
	HIKE(Strava.stringProperty("StravaActivityType.hike"), Messages.getString("StravaActivityType.hike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Walk
	 */
	WALK(Strava.stringProperty("StravaActivityType.walk"), Messages.getString("StravaActivityType.walk.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Nordic skiing (telemark)
	 */
	NORDIC_SKI(Strava.stringProperty("StravaActivityType.nordicski"), Messages.getString("StravaActivityType.nordicski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Alpine skiing
	 */
	ALPINE_SKI(Strava.stringProperty("StravaActivityType.alpineski"), Messages.getString("StravaActivityType.alpineski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Back-country skiing (off piste)
	 */
	BACKCOUNTRY_SKI(Strava.stringProperty("StravaActivityType.backcountryski"), Messages.getString("StravaActivityType.backcountryski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Ice skating
	 */
	ICE_SKATE(Strava.stringProperty("StravaActivityType.iceskate"), Messages.getString("StravaActivityType.iceskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Inline skating (rollerblading)
	 */
	INLINE_SKATE(Strava.stringProperty("StravaActivityType.inlineskate"), Messages.getString("StravaActivityType.inlineskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Kite surfing
	 */
	KITESURF(Strava.stringProperty("StravaActivityType.kitesurf"), Messages.getString("StravaActivityType.kitesurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Rollerskiing
	 */
	ROLLERSKI(Strava.stringProperty("StravaActivityType.rollerski"), Messages.getString("StravaActivityType.rollerski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Windsurfing
	 */
	WINDSURF(Strava.stringProperty("StravaActivityType.windsurf"), Messages.getString("StravaActivityType.windsurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Snowboarding
	 */
	SNOWBOARD(Strava.stringProperty("StravaActivityType.snowboard"), Messages.getString("StravaActivityType.snowboard.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Snowshoeing
	 */
	SNOWSHOE(Strava.stringProperty("StravaActivityType.snowshoe"), Messages.getString("StravaActivityType.snowshoe.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Strava.stringProperty("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

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
