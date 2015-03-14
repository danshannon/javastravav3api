package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegment;
import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.SegmentActivityTypeSerializer;

/**
 * <p>
 * Activity type associated with a {@link StravaSegment}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSegmentActivityType {
	/**
	 * Bike ride
	 */
	RIDE(Messages.getString("StravaSegmentActivityType.ride"), Messages.getString("StravaSegmentActivityType.ride.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Run
	 */
	RUN(Messages.getString("StravaSegmentActivityType.run"), Messages.getString("StravaSegmentActivityType.run.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Walk
	 */
	WALK(Messages.getString("StravaSegmentActivityType.walk"),Messages.getString("StravaSegmentActivityType.walk.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String id;
	private String description;

	private StravaSegmentActivityType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of the {@link StravaSegmentActivityType} to be used with the Strava API
	 * @see SegmentActivityTypeSerializer#serialize(StravaSegmentActivityType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaSegmentActivityType} as returned by the Strava API
	 * @return The matching {@link StravaSegmentActivityType}, or {@link StravaSegmentActivityType#UNKNOWN} if there is no match
	 */
	public static StravaSegmentActivityType create(final String id) {
		StravaSegmentActivityType[] activityTypes = StravaSegmentActivityType.values();
		for (StravaSegmentActivityType activityType : activityTypes) {
			if (activityType.getId().equals(id)) {
				return activityType;
			}
		}
		return StravaSegmentActivityType.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

}
