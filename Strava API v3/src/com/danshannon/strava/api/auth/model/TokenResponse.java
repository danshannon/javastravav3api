package com.danshannon.strava.api.auth.model;

import lombok.Data;

import com.danshannon.strava.api.model.Athlete;

/**
 * <p>The TokenResponse is returned by authorisation services; it contains the access token which is then used for authentication purposes for all other Strava API access</p>
 * 
 * @author Dan Shannon
 */
@Data 
public class TokenResponse {
	private String accessToken;
	private String tokenType;
	private Athlete athlete;
}
