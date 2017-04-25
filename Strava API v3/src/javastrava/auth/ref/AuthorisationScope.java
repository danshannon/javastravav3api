package javastrava.auth.ref;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.AuthorisationScopeSerializer;

/**
 * <p>
 * view_private and/or write, leave blank for read-only permissions.
 * </p>
 *
 * @author Dan Shannon
 */
public enum AuthorisationScope {
	/**
	 * <p>
	 * This authorisation scope allows the Strava API to return data from within the authenticated user's privacy zones
	 * </p>
	 */
	VIEW_PRIVATE(StravaConfig.string("AuthorisationScope.view_private"), Messages.string("AuthorisationScope.view_private.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * This authorisation scope allows the Strava API to write data - that is to update athlete details, activity details, and to make comments and give kudos to other riders' activities
	 * </p>
	 */
	WRITE(StravaConfig.string("AuthorisationScope.write"), Messages.string("AuthorisationScope.write.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if the Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Used when deserialising JSON returned by the Strava API
	 * </p>
	 * @see AuthorisationScopeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 * @param id String value returned by Strava
	 * @return Returns the matching instance of {@link AuthorisationScope}, or {@link AuthorisationScope#UNKNOWN} if there is no match
	 */
	public static AuthorisationScope create(final String id) {
		for (final AuthorisationScope scope : AuthorisationScope.values()) {
			if (scope.getId().equals(id)) {
				return scope;
			}
		}
		return AuthorisationScope.UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private String id;

	/**
	 * Description of the auth scope
	 */
	private String description;

	/**
	 * <p>
	 * Private constructor because this is, after all, an enum
	 * </p>
	 *
	 * @param id Identifier
	 * @param description Description
	 */
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
	 * @see AuthorisationScopeSerializer#serialize(AuthorisationScope, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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
