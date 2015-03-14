package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

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
	RIDE(Messages.getString("StravaActivityType.ride"), Messages.getString("StravaActivityType.ride.description")), //$NON-NLS-1$ //$NON-NLS-2$
	RUN(Messages.getString("StravaActivityType.run"), Messages.getString("StravaActivityType.run.description")), //$NON-NLS-1$ //$NON-NLS-2$
	SWIM(Messages.getString("StravaActivityType.swim"), Messages.getString("StravaActivityType.swim.description")), //$NON-NLS-1$ //$NON-NLS-2$
	WORKOUT(Messages.getString("StravaActivityType.workout"), Messages.getString("StravaActivityType.workout.description")), //$NON-NLS-1$ //$NON-NLS-2$
	HIKE(Messages.getString("StravaActivityType.hike"), Messages.getString("StravaActivityType.hike.description")), //$NON-NLS-1$ //$NON-NLS-2$
	WALK(Messages.getString("StravaActivityType.walk"), Messages.getString("StravaActivityType.walk.description")), //$NON-NLS-1$ //$NON-NLS-2$
	NORDIC_SKI(Messages.getString("StravaActivityType.nordicski"), Messages.getString("StravaActivityType.nordicski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	ALPINE_SKI(Messages.getString("StravaActivityType.alpineski"), Messages.getString("StravaActivityType.alpineski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	BACKCOUNTRY_SKI(Messages.getString("StravaActivityType.backcountryski"), Messages.getString("StravaActivityType.backcountryski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	ICE_SKATE(Messages.getString("StravaActivityType.iceskate"), Messages.getString("StravaActivityType.iceskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	INLINE_SKATE(Messages.getString("StravaActivityType.inlineskate"), Messages.getString("StravaActivityType.inlineskate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	KITESURF(Messages.getString("StravaActivityType.kitesurf"), Messages.getString("StravaActivityType.kitesurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	ROLLERSKI(Messages.getString("StravaActivityType.rollerski"), Messages.getString("StravaActivityType.rollerski.description")), //$NON-NLS-1$ //$NON-NLS-2$
	WINDSURF(Messages.getString("StravaActivityType.windsurf"), Messages.getString("StravaActivityType.windsurf.description")), //$NON-NLS-1$ //$NON-NLS-2$
	SNOWBOARD(Messages.getString("StravaActivityType.snowboard"), Messages.getString("StravaActivityType.snowboard.description")), //$NON-NLS-1$ //$NON-NLS-2$
	SNOWSHOE(Messages.getString("StravaActivityType.snowshoe"), Messages.getString("StravaActivityType.snowshoe.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaActivityType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
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
