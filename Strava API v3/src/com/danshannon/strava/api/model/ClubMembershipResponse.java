package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.service.ClubServices;

/**
 * <p>Container for response from club membership join and leave</p>
 * 
 * <p>See {@link ClubServices#joinClub(Integer)} and {@link ClubServices#leaveClub(Integer)}
 * 
 * @author Dan Shannon
 *
 */
@Data
public class ClubMembershipResponse {
	public ClubMembershipResponse() {
		// Required
		super();
	}
	
	private Boolean success;
	private Boolean active;
}
