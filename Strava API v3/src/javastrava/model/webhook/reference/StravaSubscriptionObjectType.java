package javastrava.model.webhook.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.model.reference.StravaReferenceType;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSubscriptionObjectType implements StravaReferenceType<Integer> {
	/**
	 * Activities
	 */
	ACTIVITY(StravaConfig.integer("StravaSubscriptionObjectType.activity"), Messages.string("StravaSubscriptionObjectType.activity.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown - shouldn't happen but may if the API implementation changes
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param id the id
	 * @return The matching instance, or {@link #UNKNOWN} if there is no match
	 */
	public static StravaSubscriptionObjectType create(final Integer id) {
		for (final StravaSubscriptionObjectType type : StravaSubscriptionObjectType.values()) {
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
	 * @param id Identifier
	 * @param description Description
	 */
	private StravaSubscriptionObjectType(final Integer id, final String description) {
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
	 * @return The integer value associated with this instance
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
