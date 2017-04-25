package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ResourceStateSerializer;

/**
 * <p>
 * State of a resource returned from Strava.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaResourceState implements StravaReferenceType<Integer> {
	/**
	 * Resource is currently being updated
	 */
	UPDATING(StravaConfig.integer("StravaResourceState.updating"), Messages.string("StravaResourceState.updating.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a representation of the resource which contains the id ONLY (other than the resource state)
	 */
	META(StravaConfig.integer("StravaResourceState.meta"), Messages.string("StravaResourceState.meta.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a summary representation of the resource
	 */
	SUMMARY(StravaConfig.integer("StravaResourceState.summary"), Messages.string("StravaResourceState.summary.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a detailed representation of the resource
	 */
	DETAILED(StravaConfig.integer("StravaResourceState.detailed"), Messages.string("StravaResourceState.detailed.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * <p>
	 * Indicates that the resource is flagged as PRIVATE and as a result is not accessible.
	 * </p>
	 * <p>
	 * Will be returned as an empty object with only the id and resourceState set.
	 * </p>
	 */
	PRIVATE(StravaConfig.integer("StravaResourceState.private"), Messages.string("StravaResourceState.private.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 *
	 * @param id
	 *            The integer representation of this {@link StravaResourceState} as returned by the Strava API
	 * @return The matching {@link StravaResourceState}, or {@link StravaResourceState#UNKNOWN} if there is no match
	 */
	public static StravaResourceState create(final Integer id) {
		final StravaResourceState[] states = StravaResourceState.values();
		for (final StravaResourceState state : states) {
			if ((state.getValue() != null) && state.getValue().equals(id)) {
				return state;
			}
		}
		return StravaResourceState.UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private Integer id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations
	 *
	 * @param id
	 *            Identifier - also used when serialising/deserialising to JSON
	 * @param description
	 *            Description
	 */
	private StravaResourceState(final Integer id, final String description) {
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
	 *
	 * @return The integer representation of this {@link StravaResourceState} to be used with the Strava API
	 * @see ResourceStateSerializer#serialize(StravaResourceState, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
