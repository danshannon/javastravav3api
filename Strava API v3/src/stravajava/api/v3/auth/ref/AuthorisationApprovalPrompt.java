package stravajava.api.v3.auth.ref;

/**
 * <p>
 * {@link #FORCE} or {@link #AUTO}
 * </p>
 *
 * <p>
 * Use {@link #FORCE} to always show the authorisation prompt even if the user
 * has already authorised the current application
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
    FORCE("force"), AUTO("auto"), UNKNOWN("UNKNOWN");

    public static AuthorisationApprovalPrompt create(final String id) {
	for (final AuthorisationApprovalPrompt prompt : AuthorisationApprovalPrompt.values()) {
	    if (prompt.getId().equals(id)) {
		return prompt;
	    }
	}
	return AuthorisationApprovalPrompt.UNKNOWN;
    }

    private String id;

    private AuthorisationApprovalPrompt(final String id) {
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
