package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaStream;
import javastrava.config.Messages;
import javastrava.util.impl.gson.serializer.StreamResolutionTypeSerializer;

/**
 * <p>
 * Resolution type for requested/returned {@link StravaStream streams}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamResolutionType {
	/**
	 * Low resolution (100 points)
	 */
	LOW(Messages.getString("StravaStreamResolutionType.low"), Messages.getString("StravaStreamResolutionType.low.description"), 100), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Medium resolution (1000 points)
	 */
	MEDIUM(Messages.getString("StravaStreamResolutionType.medium"), Messages.getString("StravaStreamResolutionType.medium.description"), 1000), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * High resolution (10000 points)
	 */
	HIGH(Messages.getString("StravaStreamResolutionType.high"), Messages.getString("StravaStreamResolutionType.high.description"), 10000), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description"), 0); //$NON-NLS-1$ //$NON-NLS-2$ 

	private String id;
	private String description;
	private int size;

	private StravaStreamResolutionType(final String id, final String description, final int size) {
		this.id = id;
		this.description = description;
		this.size = size;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamResolutionType} to be used with the Strava API
	 * @see StreamResolutionTypeSerializer#serialize(StravaStreamResolutionType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamResolutionType} returned by the Strava API
	 * @return The matching {@link StravaStreamResolutionType}, or {@link StravaStreamResolutionType#UNKNOWN} if there is no match
	 */
	public static StravaStreamResolutionType create(final String id) {
		StravaStreamResolutionType[] types = StravaStreamResolutionType.values();
		for (StravaStreamResolutionType type : types) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return StravaStreamResolutionType.UNKNOWN;
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

	/**
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}
}
