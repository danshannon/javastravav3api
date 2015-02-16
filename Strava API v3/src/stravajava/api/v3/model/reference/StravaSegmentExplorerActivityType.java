package stravajava.api.v3.model.reference;

import stravajava.api.v3.service.SegmentServices;

/**
 * <p>
 * The Strava 
 * {@link SegmentServices#segmentExplore(stravajava.api.v3.model.StravaMapPoint, stravajava.api.v3.model.StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory, StravaClimbCategory)
 * segment explorer} really <strong>should</strong> return the same activity types as held in {@link StravaSegmentActivityType}.
 * But it doesn't. Hey ho!
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSegmentExplorerActivityType {
	RUNNING("running", "Running"), RIDING("riding", "Riding"), UNKNOWN("UNKNOWN", "Unknown");

	private String	id;
	private String	description;

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
