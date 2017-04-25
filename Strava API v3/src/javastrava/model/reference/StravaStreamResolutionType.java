package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.StreamResolutionTypeSerializer;
import javastrava.model.StravaStream;

/**
 * <p>
 * Resolution type for requested/returned {@link StravaStream streams}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaStreamResolutionType implements StravaReferenceType<String> {
	/**
	 * Low resolution (100 points)
	 */
	LOW(StravaConfig.string("StravaStreamResolutionType.low"), Messages.string("StravaStreamResolutionType.low.description"), 100), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Medium resolution (1000 points)
	 */
	MEDIUM(StravaConfig.string("StravaStreamResolutionType.medium"), Messages.string("StravaStreamResolutionType.medium.description"), 1000), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * High resolution (10000 points)
	 */
	HIGH(StravaConfig.string("StravaStreamResolutionType.high"), Messages.string("StravaStreamResolutionType.high.description"), 10000), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description"), 0); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamResolutionType} returned by the Strava API
	 * @return The matching {@link StravaStreamResolutionType}, or {@link StravaStreamResolutionType#UNKNOWN} if there is no match
	 */
	public static StravaStreamResolutionType create(final String id) {
		final StravaStreamResolutionType[] types = StravaStreamResolutionType.values();
		for (final StravaStreamResolutionType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamResolutionType.UNKNOWN;
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
	 * Maximum size (number of entries) in a stream with this resolution
	 */
	private int size;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 * @param size Maximum size in a stream with this resolution
	 */
	private StravaStreamResolutionType(final String id, final String description, final int size) {
		this.id = id;
		this.description = description;
		this.size = size;
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
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamResolutionType} to be used with the Strava API
	 * @see StreamResolutionTypeSerializer#serialize(StravaStreamResolutionType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
