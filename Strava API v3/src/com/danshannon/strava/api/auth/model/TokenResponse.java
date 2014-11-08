package com.danshannon.strava.api.auth.model;

import com.danshannon.strava.api.model.Athlete;

/**
 * @author Dan Shannon
 *
 */
public class TokenResponse {
	private String accessToken;
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
}
