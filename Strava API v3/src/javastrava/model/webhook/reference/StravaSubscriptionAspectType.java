package javastrava.model.webhook.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.model.reference.StravaReferenceType;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSubscriptionAspectType implements StravaReferenceType<Integer> {
	/**
	 * Creation of an object (e.g.for activities, event is triggered when an activity is uploaded or manually created
	 */
	CREATE(StravaConfig.integer("StravaSubscriptionAspectType.create"), Messages.string("StravaSubscriptionAspectType.create.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown - should never occur but may do if Strava API implementation has changed
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON serialisation
	 * @param id Identifier
	 * @return Matching instance, or {@link #UNKNOWN} if there is no match
	 */
	public static StravaSubscriptionAspectType create(final Integer id) {
		for (final StravaSubscriptionAspectType type : StravaSubscriptionAspectType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private final Integer id;

	/**
	 * Description
	 */
	private final String description;

	/**
	 * @param id Unique id
	 * @param description Description
	 */
	private StravaSubscriptionAspectType(final Integer id, final String description) {
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
	public Integer getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The identifier
	 */
	@Override
	public Integer getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}
}
