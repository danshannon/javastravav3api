package com.danshannon.strava.api.auth.ref;

import com.danshannon.strava.api.auth.AuthorisationServices;

/**
 * <p>Valid authorisation response types for {@link AuthorisationServices#requestAccess(Integer, String, AuthorisationResponseType, com.danshannon.strava.api.auth.AuthorisationApprovalPrompt, com.danshannon.strava.api.auth.AuthorisationScope[], String) access requests}</p>
 * 
 * <p>As of Strava API v3.0, this value must be set to {@link #CODE}</p>
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
