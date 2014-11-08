package com.danshannon.strava.api.auth.ref;


/**
 * <p>‘force’ or ‘auto’, use ‘force’ to always show the authorization prompt even if the user has already authorized the current application, default is ‘auto’</p>
 * 
 * @author Dan Shannon
 *
 */
public enum AuthorisationApprovalPrompt {
	FORCE("force"),
	AUTO("auto"),
	UNKNOWN(null);
	
	private String id;
	
	private AuthorisationApprovalPrompt(String id) {
		this.id = id;
	}
	
	public String getValue() {
		return this.id;
	}
	
	public static AuthorisationApprovalPrompt create(String id) {
		for (AuthorisationApprovalPrompt prompt : AuthorisationApprovalPrompt.values()) {
			if (prompt.getId().equals(id)) {
				return prompt;
			}
		}
		return UNKNOWN;
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
