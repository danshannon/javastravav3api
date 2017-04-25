package javastrava.auth.ref;

import javastrava.config.StravaConfig;

/**
 * <p>
 * Valid authorisation response types
 * </p>
 *
 * <p>
 * As of Strava API v3.0, this value must be set to {@link #CODE}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum AuthorisationResponseType {
	/**
	 * <p>
	 * Currently Strava requires the use of this value when exchanging authorisation via OAuth. This is the only valid value.
	 * </p>
	 */
	CODE(StravaConfig.string("AuthorisationResponseType.code")), //$NON-NLS-1$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown")); //$NON-NLS-1$

	/**
	 * Required by GSON serialiser
	 *
	 * @param id
	 *            The String value of the id
	 * @return An instance of {@link AuthorisationResponseType} corresponding to the id, or {@link #UNKNOWN} if no such instance is available.
	 */
	public static AuthorisationResponseType create(final String id) {
		for (final AuthorisationResponseType type : AuthorisationResponseType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return AuthorisationResponseType.UNKNOWN;
	}

	/**
	 * <p>
	 * Identifier
	 * </p>
	 */
	private final String id;

	/**
	 * <p>
	 * Private constructor - this is an enum, so we can't be running round having public constructors, can we
	 * </p>
	 * @param id The identifier of the response type
	 */
	private AuthorisationResponseType(final String id) {
		this.id = id;
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

}
