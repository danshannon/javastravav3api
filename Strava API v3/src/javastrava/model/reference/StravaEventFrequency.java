package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * Club group event frequency code
 *
 * @author Dan Shannon
 *
 */
public enum StravaEventFrequency implements StravaReferenceType<String> {
	/**
	 * Does not repeat
	 */
	NO_REPEAT(StravaConfig.string("StravaEventFrequency.no_repeat"), Messages.string("StravaEventFrequency.no_repeat.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Weekly
	 */
	WEEKLY(StravaConfig.string("StravaEventFrequency.weekly"), Messages.string("StravaEventFrequency.weekly")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Monthly
	 */
	MONTHLY(StravaConfig.string("StravaEventFrequency.monthly"), Messages.string("StravaEventFrequency.monthly")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param id
	 *            The string representation of the frequency
	 * @return The frequency with the matching code, or UNKNOWN if there is no match
	 */
	public static StravaEventFrequency create(final String id) {
		for (final StravaEventFrequency frequency : StravaEventFrequency.values()) {
			if (frequency.getId().equalsIgnoreCase(id)) {
				return frequency;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private final String id;

	/**
	 * Description
	 */
	private final String description;

	private StravaEventFrequency(final String id, final String description) {
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
	 * @return The id
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id;
	}
}
