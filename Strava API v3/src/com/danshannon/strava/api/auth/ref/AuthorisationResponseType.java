package com.danshannon.strava.api.auth.ref;

import com.danshannon.strava.api.auth.AuthorisationServices;

/**
 * <p>Valid authorisation response types for {@link AuthorisationServices#requestAccess(Integer, String, AuthorisationResponseType, com.danshannon.strava.api.auth.AuthorisationApprovalPrompt, com.danshannon.strava.api.auth.AuthorisationScope[], String) access requests}</p>
 * 
 * @author Dan Shannon
 *
 */
public enum AuthorisationResponseType {
	CODE("code"),
	UNKNOWN(null);
	
	private final String id;
	
	private AuthorisationResponseType(String id) {
		this.id = id;
	}

	public String getValue() {
		return this.id;
	}
	
	public static AuthorisationResponseType create(String id) {
		for (AuthorisationResponseType type : AuthorisationResponseType.values()) {
			if (type.getId().equals(id)) {
				return type;
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
