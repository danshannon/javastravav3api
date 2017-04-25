package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ActivityTypeSerializer;

/**
 * Strava Route sub-types
 *
 * @author Dan Shannon
 *
 */
public enum StravaRouteSubType implements StravaReferenceType<Integer> {
	/**
	 * Road
	 */
	ROAD(StravaConfig.integer("StravaRouteSubType.road"), Messages.string("StravaRouteSubType.road.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Mountain bike
	 */
	MTB(StravaConfig.integer("StravaRouteSubType.mtb"), Messages.string("StravaRouteSubType.mtb.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Cross
	 */
	CX(StravaConfig.integer("StravaRouteSubType.cx"), Messages.string("StravaRouteSubType.cx.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Trail
	 */
	TRAIL(StravaConfig.integer("StravaRouteSubType.trail"), Messages.string("StravaRouteSubType.trail.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Mixed terrain
	 */
	MIXED(StravaConfig.integer("StravaRouteSubType.mixed"), Messages.string("StravaRouteSubType.mixed.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown type
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param id
	 *            The string representation of the activity type as returned by the Strava API
	 * @return The {@link StravaRouteSubType} with the matching id, or {@link StravaRouteSubType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	public static StravaRouteSubType create(final Integer id) {
		for (final StravaRouteSubType type : StravaRouteSubType.values()) {
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
	private StravaRouteSubType(final Integer id, final String description) {
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
