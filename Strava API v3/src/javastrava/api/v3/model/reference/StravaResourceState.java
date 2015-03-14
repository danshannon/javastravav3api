package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * State of a resource returned from Strava.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaResourceState {
	UPDATING(-1, Messages.getString("StravaResourceState.updating.description")),  //$NON-NLS-1$
	META(1, Messages.getString("StravaResourceState.meta.description")),  //$NON-NLS-1$
	SUMMARY(2, Messages.getString("StravaResourceState.summary.description")),  //$NON-NLS-1$
	DETAILED(3, Messages.getString("StravaResourceState.detailed.description")),  //$NON-NLS-1$
	UNKNOWN(-2, Messages.getString("Common.unknown.description")); //$NON-NLS-1$

	private Integer	id;
	private String	description;

	// @JsonValue
	public Integer getValue() {
		return this.id;
	}

	private StravaResourceState(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonCreator
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
