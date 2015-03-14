package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegment;
import javastrava.config.Messages;

/**
 * <p>
 * Activity type associated with a {@link StravaSegment}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSegmentActivityType {
	RIDE(Messages.getString("StravaSegmentActivityType.ride"), Messages.getString("StravaSegmentActivityType.ride.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	RUN(Messages.getString("StravaSegmentActivityType.run"), Messages.getString("StravaSegmentActivityType.run.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	WALK(Messages.getString("StravaSegmentActivityType.walk"),Messages.getString("StravaSegmentActivityType.walk.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String id;
	private String description;

	private StravaSegmentActivityType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
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
