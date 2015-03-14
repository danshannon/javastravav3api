package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Status of an athlete's follower relationship with another athlete
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaFollowerState {
	PENDING(Messages.getString("StravaFollowerState.pending"), Messages.getString("StravaFollowerState.pending.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	ACCEPTED(Messages.getString("StravaFollowerState.accepted"), Messages.getString("StravaFollowerState.accepted.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	BLOCKED(Messages.getString("StravaFollowerState.blocked"), Messages.getString("StravaFollowerState.blocked.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaFollowerState(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaFollowerState create(final String id) {
		StravaFollowerState[] followerStates = StravaFollowerState.values();
		for (StravaFollowerState followerState : followerStates) {
			if (followerState.getId().equals(id)) {
				return followerState;
			}
		}
		return StravaFollowerState.UNKNOWN;
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
}
