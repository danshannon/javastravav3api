package stravajava.api.v3.auth.ref;

/**
 * <p>
 * view_private and/or write, leave blank for read-only permissions.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum AuthorisationScope {
	VIEW_PRIVATE("view_private", "Allow viewing of private data"), WRITE("write", "Allow creation of data"), UNKNOWN("UNKNOWN", "Unknown");

	public static AuthorisationScope create(final String id) {
		for (final AuthorisationScope scope : AuthorisationScope.values()) {
			if (scope.getId().equals(id)) {
				return scope;
			}
		}
		return AuthorisationScope.UNKNOWN;
	}

	private String id;

	private String description;

	private AuthorisationScope(final String id, final String description) {
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
