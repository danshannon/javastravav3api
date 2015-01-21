package com.danshannon.strava.api.auth.model;

import com.danshannon.strava.api.model.Athlete;

/**
 * <p>The TokenResponse is returned by authorisation services; it contains the access token which is then used for authentication purposes for all other Strava API access</p>
 * 
 * @author Dan Shannon
 */
public class TokenResponse {
	private String accessToken;
	private String tokenType;
	private Athlete athlete;
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return this.accessToken;
	}
	/**
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return this.tokenType;
	}
	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenResponse [" + (this.accessToken != null ? "accessToken=" + this.accessToken + ", " : "")
				+ (this.tokenType != null ? "tokenType=" + this.tokenType + ", " : "")
				+ (this.athlete != null ? "athlete=" + this.athlete : "") + "]";
	}
}
