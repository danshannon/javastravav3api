package javastrava.api.v3.model;

import javastrava.api.v3.service.ClubService;

/**
 * <p>
 * Container for response from club membership join and leave
 * </p>
 * 
 * <p>
 * See {@link ClubService#joinClub(Integer)} and {@link ClubService#leaveClub(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaClubMembershipResponse {
	/**
	 * TODO ???
	 */
	private Boolean success;
	/**
	 * TODO ???
	 */
	private Boolean active;
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return this.success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(final Boolean success) {
		this.success = success;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}
}
