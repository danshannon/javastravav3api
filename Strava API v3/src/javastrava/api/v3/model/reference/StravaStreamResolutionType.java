package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaStream;
import javastrava.config.Messages;

/**
 * <p>
 * Resolution type for requested/returned {@link StravaStream streams}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamResolutionType {
	LOW(Messages.getString("StravaStreamResolutionType.low"), Messages.getString("StravaStreamResolutionType.low.description"), 100), //$NON-NLS-1$ //$NON-NLS-2$
	MEDIUM(Messages.getString("StravaStreamResolutionType.medium"), Messages.getString("StravaStreamResolutionType.medium.description"), 1000), //$NON-NLS-1$ //$NON-NLS-2$
	HIGH(Messages.getString("StravaStreamResolutionType.high"), Messages.getString("StravaStreamResolutionType.high.description"), 10000), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description"), 0); //$NON-NLS-1$ //$NON-NLS-2$ 

	private String id;
	private String description;
	private int size;

	private StravaStreamResolutionType(final String id, final String description, final Integer size) {
		this.id = id;
		this.description = description;
		this.size = size;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
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
