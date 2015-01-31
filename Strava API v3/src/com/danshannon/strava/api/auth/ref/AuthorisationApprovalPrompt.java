package com.danshannon.strava.api.auth.ref;


/**
 * <p>{@link #FORCE} or {@link #AUTO}</p>
 * 
 * <p>Use {@link #FORCE} to always show the authorisation prompt even if the user has already authorised the current application</p>
 * 
 * <p>Default is {@link #AUTO}</p>
 * 
 * @author Dan Shannon
 *
 */
public enum AuthorisationApprovalPrompt {
	FORCE("force"),
	AUTO("auto"),
	UNKNOWN("UNKNOWN");
	
	private String id;
	
	private AuthorisationApprovalPrompt(String id) {
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
	
	public static AuthorisationApprovalPrompt create(String id) {
		for (AuthorisationApprovalPrompt prompt : AuthorisationApprovalPrompt.values()) {
			if (prompt.getId().equals(id)) {
				return prompt;
			}
		}
		return AuthorisationApprovalPrompt.UNKNOWN;
	}
	
}
