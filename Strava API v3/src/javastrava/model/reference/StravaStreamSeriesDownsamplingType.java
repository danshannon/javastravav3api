package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * <p>
 * Method for downsampling a strava stream - either by time or by distance
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaStreamSeriesDownsamplingType implements StravaReferenceType<String> {
	/**
	 * Downsampling by time
	 */
	TIME(StravaConfig.string("StravaStreamSeriesDownsamplingType.time"), Messages.string("StravaStreamSeriesDownsamplingType.time.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Downsampling by distance
	 */
	DISTANCE(StravaConfig.string("StravaStreamSeriesDownsamplingType.distance"), Messages.string("StravaStreamSeriesDownsamplingType.distance.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamSeriesDownsamplingType} as returned by the Strava API
	 * @return The matching {@link StravaStreamSeriesDownsamplingType}, or {@link StravaStreamSeriesDownsamplingType#UNKNOWN} if there is no match
	 */
	public static StravaStreamSeriesDownsamplingType create(final String id) {
		final StravaStreamSeriesDownsamplingType[] types = StravaStreamSeriesDownsamplingType.values();
		for (final StravaStreamSeriesDownsamplingType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamSeriesDownsamplingType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private String id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaStreamSeriesDownsamplingType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamSeriesDownsamplingType} to be used with the Strava API
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}
