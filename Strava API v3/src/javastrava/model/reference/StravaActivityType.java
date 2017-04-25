package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ActivityTypeSerializer;

/**
 * <p>
 * These are all the available Activity types:
 * </p>
 * 
 * <ul>
 * <li>Ride</li>
 * <li>Run</li>
 * <li>Swim</li>
 * <li>Hike</li>
 * <li>Walk</li>
 * <li>AlpineSki</li>
 * <li>BackcountrySki</li>
 * <li>Caneoing</li>
 * <li>Crossfit</li>
 * <li>EBikeRide</li>
 * <li>Elliptical</li>
 * <li>IceSkate</li>
 * <li>InlineSkate</li>
 * <li>Kayaking</li>
 * <li>Kitesurf</li>
 * <li>NordicSki</li>
 * <li>RockClimbing</li>
 * <li>RollerSki</li>
 * <li>Rowing</li>
 * <li>Snowboard</li>
 * <li>Snowshoe</li>
 * <li>StairStepper</li>
 * <li>StandUpPaddling</li>
 * <li>Surfing</li>
 * <li>VirtualRide
 * <li>WeightTraining</li>
 * <li>Windsurf</li>
 * <li>Workout</li>
 * <li>Yoga</li>
 * </ul>
 * 
 * <p>
 * Activities that don’t use real GPS should utilize the {@link #VIRTUAL_RIDE}
 * type. Electronically assisted rides should use the {@link #EBIKE_RIDE} type.
 * The @link {@link #WORKOUT} type is recommended for miscellaneous activities.
 * </p>
 * 
 * <p>
 * Type is detected from file upload overrides, uses athlete's default type if
 * not specified
 * </p>
 *
 * <p>
 * NOTE: The crosscountryskiing type has been removed. Please use
 * {@link StravaActivityType#NORDIC_SKI} instead.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaActivityType implements StravaReferenceType<String> {
	/**
	 * Bike ride
	 */
	RIDE(StravaConfig.string("StravaActivityType.ride"), Messages.string("StravaActivityType.ride.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Run
	 */
	RUN(StravaConfig.string("StravaActivityType.run"), Messages.string("StravaActivityType.run.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Swim
	 */
	SWIM(StravaConfig.string("StravaActivityType.swim"), Messages.string("StravaActivityType.swim.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Hike
	 */
	HIKE(StravaConfig.string("StravaActivityType.hike"), Messages.string("StravaActivityType.hike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Walk
	 */
	WALK(StravaConfig.string("StravaActivityType.walk"), Messages.string("StravaActivityType.walk.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Alpine skiing
	 */
	ALPINE_SKI(StravaConfig.string("StravaActivityType.alpineski"), //$NON-NLS-1$
			Messages.string("StravaActivityType.alpineski.description")), //$NON-NLS-1$
	/**
	 * Back-country skiing (off piste)
	 */
	BACKCOUNTRY_SKI(StravaConfig.string("StravaActivityType.backcountryski"), //$NON-NLS-1$
			Messages.string("StravaActivityType.backcountryski.description")), //$NON-NLS-1$
	/**
	 * Canoeing
	 */
	CANOEING(StravaConfig.string("StravaActivityType.canoeing"), //$NON-NLS-1$
			Messages.string("StravaActivityType.canoeing.description")), //$NON-NLS-1$
	/**
	 * Crossfit
	 */
	CROSSFIT(StravaConfig.string("StravaActivityType.crossfit"), //$NON-NLS-1$
			Messages.string("StravaActivityType.crossfit.description")), //$NON-NLS-1$
	/**
	 * E-Bike Ride
	 */
	EBIKE_RIDE(StravaConfig.string("StravaActivityType.ebikeride"), //$NON-NLS-1$
			Messages.string("StravaActivityType.ebikeride.description")), //$NON-NLS-1$
	/**
	 * Elliptical Trainer
	 */
	ELLIPTICAL(StravaConfig.string("StravaActivityType.elliptical"), //$NON-NLS-1$
			Messages.string("StravaActivityType.elliptical.description")), //$NON-NLS-1$
	/**
	 * Ice skating
	 */
	ICE_SKATE(StravaConfig.string("StravaActivityType.iceskate"), //$NON-NLS-1$
			Messages.string("StravaActivityType.iceskate.description")), //$NON-NLS-1$
	/**
	 * Inline skating (rollerblading)
	 */
	INLINE_SKATE(StravaConfig.string("StravaActivityType.inlineskate"), //$NON-NLS-1$
			Messages.string("StravaActivityType.inlineskate.description")), //$NON-NLS-1$
	/**
	 * Kayaking
	 */
	KAYAKING(StravaConfig.string("StravaActivityType.kayaking"), //$NON-NLS-1$
			Messages.string("StravaActivityType.kayaking.description")), //$NON-NLS-1$
	/**
	 * Kite surfing
	 */
	KITESURF(StravaConfig.string("StravaActivityType.kitesurf"), //$NON-NLS-1$
			Messages.string("StravaActivityType.kitesurf.description")), //$NON-NLS-1$
	/**
	 * Nordic skiing (telemark)
	 */
	NORDIC_SKI(StravaConfig.string("StravaActivityType.nordicski"), //$NON-NLS-1$
			Messages.string("StravaActivityType.nordicski.description")), //$NON-NLS-1$
	/**
	 * Rock climbing
	 */
	ROCK_CLIMBING(StravaConfig.string("StravaActivityType.rockclimbing"), //$NON-NLS-1$
			Messages.string("StravaActivityType.rockclimbing.description")), //$NON-NLS-1$
	/**
	 * Rollerskiing
	 */
	ROLLERSKI(StravaConfig.string("StravaActivityType.rollerski"), //$NON-NLS-1$
			Messages.string("StravaActivityType.rollerski.description")), //$NON-NLS-1$
	/**
	 * Rowing
	 */
	ROWING(StravaConfig.string("StravaActivityType.rowing"), Messages.string("StravaActivityType.rowing.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Snowboarding
	 */
	SNOWBOARD(StravaConfig.string("StravaActivityType.snowboard"), //$NON-NLS-1$
			Messages.string("StravaActivityType.snowboard.description")), //$NON-NLS-1$
	/**
	 * Snowshoeing
	 */
	SNOWSHOE(StravaConfig.string("StravaActivityType.snowshoe"), //$NON-NLS-1$
			Messages.string("StravaActivityType.snowshoe.description")), //$NON-NLS-1$
	/**
	 * Stair stepper
	 */
	STAIR_STEPPER(StravaConfig.string("StravaActivityType.stairstepper"), //$NON-NLS-1$
			Messages.string("StravaActivityType.stairstepper.description")), //$NON-NLS-1$
	/**
	 * Stand-up Paddling
	 */
	STAND_UP_PADDLING(StravaConfig.string("StravaActivityType.standuppaddling"), //$NON-NLS-1$
			Messages.string("StravaActivityType.standuppaddling.description")), //$NON-NLS-1$
	/**
	 * Surfing
	 */
	SURFING(StravaConfig.string("StravaActivityType.surfing"), //$NON-NLS-1$
			Messages.string("StravaActivityType.surfing.description")), //$NON-NLS-1$
	/**
	 * Virtual ride
	 */
	VIRTUAL_RIDE(StravaConfig.string("StravaActivityType.virtualride"), //$NON-NLS-1$
			Messages.string("StravaActivityType.virtualride.description")), //$NON-NLS-1$
	/**
	 * Weight training
	 */
	WEIGHT_TRAINING(StravaConfig.string("StravaActivityType.weighttraining"), //$NON-NLS-1$
			Messages.string("StravaActivityType.weighttraining.description")), //$NON-NLS-1$
	/**
	 * Windsurfing
	 */
	WINDSURF(StravaConfig.string("StravaActivityType.windsurf"), //$NON-NLS-1$
			Messages.string("StravaActivityType.windsurf.description")), //$NON-NLS-1$
	/**
	 * Workout
	 */
	WORKOUT(StravaConfig.string("StravaActivityType.workout"), //$NON-NLS-1$
			Messages.string("StravaActivityType.workout.description")), //$NON-NLS-1$
	/**
	 * Yoga
	 */
	YOGA(StravaConfig.string("StravaActivityType.yoga"), Messages.string("StravaActivityType.yoga.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param id
	 *            The string representation of the activity type as returned by
	 *            the Strava API
	 * @return The {@link StravaActivityType} with the matching id, or
	 *         {@link StravaActivityType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement,
	 *      java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaActivityType create(final String id) {
		for (final StravaActivityType type : StravaActivityType.values()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private String id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations above
	 * 
	 * @param id
	 *            Identifier (used in JSON)
	 * @param description
	 *            Description
	 */
	private StravaActivityType(final String id, final String description) {
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
	 * @see ActivityTypeSerializer#serialize(StravaActivityType,
	 *      java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @return The id
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

}
