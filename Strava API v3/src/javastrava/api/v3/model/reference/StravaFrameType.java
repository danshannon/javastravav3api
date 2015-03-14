package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.FrameTypeSerializer;

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
	MOUNTAIN_BIKE(Integer.valueOf(1), Messages.getString("StravaFrameType.mountain_bike.description")),  //$NON-NLS-1$
	/**
	 * Cross bike
	 */
	CROSS(Integer.valueOf(2), Messages.getString("StravaFrameType.cross.description")),  //$NON-NLS-1$
	/**
	 * Road bike
	 */
	ROAD(Integer.valueOf(3), Messages.getString("StravaFrameType.road.description")),  //$NON-NLS-1$
	/**
	 * Time trial bike
	 */
	TIME_TRIAL(Integer.valueOf(4), Messages.getString("StravaFrameType.tt.description")),  //$NON-NLS-1$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Integer.valueOf(-1), Messages.getString("Common.unknown.description")); //$NON-NLS-1$

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
