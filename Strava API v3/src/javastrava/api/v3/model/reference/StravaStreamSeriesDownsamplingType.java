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
	/**
	 * Downsampling by time
	 */
	TIME(Messages.getString("StravaStreamSeriesDownsamplingType.time"), Messages.getString("StravaStreamSeriesDownsamplingType.time.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Downsampling by distance
	 */
	DISTANCE(Messages.getString("StravaStreamSeriesDownsamplingType.distance"), Messages.getString("StravaStreamSeriesDownsamplingType.distance.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String id;
	private String description;

	private StravaStreamSeriesDownsamplingType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamSeriesDownsamplingType} to be used with the Strava API
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamSeriesDownsamplingType} as returned by the Strava API
	 * @return The matching {@link StravaStreamSeriesDownsamplingType}, or {@link StravaStreamSeriesDownsamplingType#UNKNOWN} if there is no match
	 */
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
