package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.FrameTypeSerializer;

/**
 * <p>
 * Bicycle frame type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaFrameType {
	/**
	 * Mountain bike
	 */
	MOUNTAIN_BIKE(StravaConfig.integer("StravaFrameType.mountain_bike"), Messages.string("StravaFrameType.mountain_bike.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Cross bike
	 */
	CROSS(StravaConfig.integer("StravaFrameType.cross"), Messages.string("StravaFrameType.cross.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Road bike
	 */
	ROAD(StravaConfig.integer("StravaFrameType.road"), Messages.string("StravaFrameType.road.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Time trial bike
	 */
	TIME_TRIAL(StravaConfig.integer("StravaFrameType.tt"), Messages.string("StravaFrameType.tt.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private Integer	id;
	private String	description;

	private StravaFrameType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The integer representation of this {@link StravaFrameType} to be used with the Strava API
	 * @see FrameTypeSerializer#serialize(StravaFrameType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public Integer getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of a {@link StravaFrameType} as returned by the Strava API
	 * @return The matching {@link StravaFrameType}, or {@link StravaFrameType#UNKNOWN} if there is no match
	 */
	public static StravaFrameType create(final Integer id) {
		StravaFrameType[] frameTypes = StravaFrameType.values();
		for (StravaFrameType frameType : frameTypes) {
			if (frameType.getId().equals(id)) {
				return frameType;
			}
		}
		return StravaFrameType.UNKNOWN;
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
