package javastrava.api.v3.auth.ref;

import javastrava.config.Messages;

/**
 * <p>
 * view_private and/or write, leave blank for read-only permissions.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum AuthorisationScope {
	VIEW_PRIVATE(Messages.getString("AuthorisationScope.view_private"), Messages.getString("AuthorisationScope.view_private.description")), WRITE(Messages.getString("AuthorisationScope.write"), Messages.getString("AuthorisationScope.write.description")), UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

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
