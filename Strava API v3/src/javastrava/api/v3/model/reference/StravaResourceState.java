package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ResourceStateSerializer;

/**
 * <p>
 * State of a resource returned from Strava.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaResourceState {
	/**
	 * Resource is currently being updated
	 */
	UPDATING(StravaConfig.integer("StravaResourceState.updating"), Messages.string("StravaResourceState.updating.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a representation of the resource which contains the id ONLY (other than the resource state)
	 */
	META(StravaConfig.integer("StravaResourceState.meta"), Messages.string("StravaResourceState.meta.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a summary representation of the resource
	 */
	SUMMARY(StravaConfig.integer("StravaResourceState.summary"), Messages.string("StravaResourceState.summary.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * This is a detailed representation of the resource
	 */
	DETAILED(StravaConfig.integer("StravaResourceState.detailed"), Messages.string("StravaResourceState.detailed.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private Integer	id;
	private String	description;

	/**
	 * Used by JSON serialisation
	 * @return The integer representation of this {@link StravaResourceState} to be used with the Strava API
	 * @see ResourceStateSerializer#serialize(StravaResourceState, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public Integer getValue() {
		return this.id;
	}

	private StravaResourceState(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of this {@link StravaResourceState} as returned by the Strava API
	 * @return The matching {@link StravaResourceState}, or {@link StravaResourceState#UNKNOWN} if there is no match
	 */
	public static StravaResourceState create(final Integer id) {
		StravaResourceState[] states = StravaResourceState.values();
		for (StravaResourceState state : states) {
			if (state.getValue() != null && state.getValue().equals(id)) {
				return state;
			}
		}
		return StravaResourceState.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}
}
