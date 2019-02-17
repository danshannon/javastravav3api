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

	READ(StravaConfig.string("AuthorisationScope.read"), Messages.string("AuthorisationScope.read.description")),

	READ_ALL(StravaConfig.string("AuthorisationScope.read_all"), Messages.string("AuthorisationScope.read_all.description")),

	PROFILE_READ_ALL(StravaConfig.string("AuthorisationScope.profile_read_all"), Messages.string("AuthorisationScope.profile_read_all.description")),

	PROFILE_WRITE(StravaConfig.string("AuthorisationScope.profile_write"), Messages.string("AuthorisationScope.profile_write.description")),

	ACTIVITY_READ(StravaConfig.string("AuthorisationScope.activity_read"), Messages.string("AuthorisationScope.activity_read.description")),

	ACTIVITY_READ_ALL(StravaConfig.string("AuthorisationScope.activity_read_all"), Messages.string("AuthorisationScope.activity_read_all.description")),

	ACTIVITY_WRITE(StravaConfig.string("AuthorisationScope.activity_write"), Messages.string("AuthorisationScope.activity_write.description")),

	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description"));

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
