package stravajava.api.v3.auth.ref;


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
	CODE("code"), UNKNOWN("UNKNOWN");

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

	private final String id;

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
