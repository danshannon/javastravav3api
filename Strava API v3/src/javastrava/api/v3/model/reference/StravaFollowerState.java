package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.FollowerStateSerializer;

/**
 * <p>
 * Status of an athlete's follower relationship with another athlete
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaFollowerState {
	/**
	 * Follower has been requested but neither accepted nor blocked at this time
	 */
	PENDING(StravaConfig.string("StravaFollowerState.pending"), Messages.string("StravaFollowerState.pending.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Follower has been accepted
	 */
	ACCEPTED(StravaConfig.string("StravaFollowerState.accepted"), Messages.string("StravaFollowerState.accepted.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Follower has been blocked
	 */
	BLOCKED(StravaConfig.string("StravaFollowerState.blocked"), Messages.string("StravaFollowerState.blocked.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Identifier
	 */
	private String	id;
	/**
	 * Description
	 */
	private String	description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaFollowerState(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaFollowerState} to be used with the Strava API
	 * @see FollowerStateSerializer#serialize(StravaFollowerState, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaFollowerState} as returned by the Strava API
	 * @return The matching {@link StravaFollowerState}, or {@link StravaFollowerState#UNKNOWN} if there is no match
	 */
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
