package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.FollowerStateSerializer;

/**
 * <p>
 * Status of an athlete's follower relationship with another athlete
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaFollowerState implements StravaReferenceType<String> {
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
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaFollowerState} as returned by the Strava API
	 * @return The matching {@link StravaFollowerState}, or {@link StravaFollowerState#UNKNOWN} if there is no match
	 */
	public static StravaFollowerState create(final String id) {
		final StravaFollowerState[] followerStates = StravaFollowerState.values();
		for (final StravaFollowerState followerState : followerStates) {
			if (followerState.getId().equals(id)) {
				return followerState;
			}
		}
		return StravaFollowerState.UNKNOWN;
	}
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
	 * @return The string representation of this {@link StravaFollowerState} to be used with the Strava API
	 * @see FollowerStateSerializer#serialize(StravaFollowerState, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
