package javastrava.auth.ref;

import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.AuthorisationApprovalPromptSerializer;

/**
 * <p>
 * {@link #FORCE} or {@link #AUTO}
 * </p>
 *
 * <p>
 * Use {@link #FORCE} to always show the authorisation prompt even if the user has already authorised the current application
 * </p>
 *
 * <p>
 * Default is {@link #AUTO}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum AuthorisationApprovalPrompt {
	/**
	 * <p>
	 * Forces Strava's authorisation process to show the authorisation prompt page, even if the user has already authorised the application with the requested authorisation scope(s)
	 * </p>
	 */
	FORCE(StravaConfig.string("AuthorisationApprovalPrompt.force")),  //$NON-NLS-1$
	/**
	 * <p>
	 * Tells Strava's authorisation process not to show the authorisation prompt page if the user hqs already authorised the application with the requested authorisation scope(s)
	 * </p>
	 */
	AUTO(StravaConfig.string("AuthorisationApprovalPrompt.auto")),  //$NON-NLS-1$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown")); //$NON-NLS-1$
	/**
	 * <p>Used by {@link AuthorisationApprovalPromptSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)} when deserialising JSON returned by the Strava API</p>
	 * @param id The text representation returned by Strava
	 * @return The instance of {@link AuthorisationApprovalPrompt} with the correct id
	 */
	public static AuthorisationApprovalPrompt create(final String id) {
		for (final AuthorisationApprovalPrompt prompt : AuthorisationApprovalPrompt.values()) {
			if (prompt.getId().equals(id)) {
				return prompt;
			}
		}
		return AuthorisationApprovalPrompt.UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private String id;

	/**
	 * <p>
	 * Private constructor - this is an enum
	 * </p>
	 * @param id The identifier of the approval prompt
	 */
	private AuthorisationApprovalPrompt(final String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Note that this is also used by {@link AuthorisationApprovalPromptSerializer#serialize(AuthorisationApprovalPrompt, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)} when serialising to JSON
	 * </p>
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
