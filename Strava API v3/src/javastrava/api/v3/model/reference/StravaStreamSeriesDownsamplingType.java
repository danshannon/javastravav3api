package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Method for downsampling a strava stream - either by time or by distance
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamSeriesDownsamplingType {
	TIME(Messages.getString("StravaStreamSeriesDownsamplingType.time"), Messages.getString("StravaStreamSeriesDownsamplingType.time.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	DISTANCE(Messages.getString("StravaStreamSeriesDownsamplingType.distance"), Messages.getString("StravaStreamSeriesDownsamplingType.distance.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String id;
	private String description;

	private StravaStreamSeriesDownsamplingType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaStreamSeriesDownsamplingType create(final String id) {
		StravaStreamSeriesDownsamplingType[] types = StravaStreamSeriesDownsamplingType.values();
		for (StravaStreamSeriesDownsamplingType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamSeriesDownsamplingType.UNKNOWN;
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
