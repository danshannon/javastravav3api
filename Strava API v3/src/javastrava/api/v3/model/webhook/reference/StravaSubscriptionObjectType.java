/**
 *
 */
package javastrava.api.v3.model.webhook.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSubscriptionObjectType {
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
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return The integer value associated with this instance
	 */
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
