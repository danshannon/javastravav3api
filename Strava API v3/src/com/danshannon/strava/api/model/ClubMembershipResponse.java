package com.danshannon.strava.api.model;

import com.danshannon.strava.api.service.ClubServices;

/**
 * <p>Container for response from club membership join and leave</p>
 * 
 * <p>See {@link ClubServices#joinClub(Integer)} and {@link ClubServices#leaveClub(Integer)}
 * 
 * @author Dan Shannon
 *
 */
public class ClubMembershipResponse {
	private Boolean success;
	private Boolean active;
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return this.success;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
}
