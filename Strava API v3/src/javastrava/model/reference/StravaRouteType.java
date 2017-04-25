package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ActivityTypeSerializer;

/**
 * Type of Strava Route
 *
 * @author Dan Shannon
 *
 */
public enum StravaRouteType implements StravaReferenceType<Integer> {
	/**
	 * Ride
	 */
	RIDE(StravaConfig.integer("StravaRouteType.ride"), Messages.string("StravaRouteType.ride.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Run
	 */
	RUN(StravaConfig.integer("StravaRouteType.run"), Messages.string("StravaRouteType.run.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown type
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param id
	 *            The string representation of the activity type as returned by the Strava API
	 * @return The {@link StravaRouteType} with the matching id, or {@link StravaRouteType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	public static StravaRouteType create(final Integer id) {
		for (final StravaRouteType type : StravaRouteType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	private Integer id;

	private String description;

	/**
	 * Private constructor used by declarations above
	 *
	 * @param id
	 *            Identifier (used in JSON)
	 * @param description
	 *            Description
	 */
	private StravaRouteType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getValue() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id.toString();
	}

}
