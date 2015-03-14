package javastrava.api.v3.model.reference;

import javastrava.api.v3.service.SegmentServices;
import javastrava.config.Messages;

/**
 * <p>
 * The Strava
 * {@link SegmentServices#segmentExplore(javastrava.api.v3.model.StravaMapPoint, javastrava.api.v3.model.StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory, StravaClimbCategory)
 * segment explorer} really <strong>should</strong> return the same activity types as held in {@link StravaSegmentActivityType}. But it doesn't. Hey ho!
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSegmentExplorerActivityType {
	RUNNING(Messages.getString("StravaSegmentExplorerActivityType.running"), Messages.getString("StravaSegmentExplorerActivityType.running.description")), //$NON-NLS-1$ //$NON-NLS-2$
	RIDING(Messages.getString("StravaSegmentExplorerActivityType.riding"), Messages.getString("StravaSegmentExplorerActivityType.riding.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String id;
	private String description;

	private StravaSegmentExplorerActivityType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaSegmentExplorerActivityType create(final String id) {
		for (StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
			if (type.getId().equals(id)) {
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
