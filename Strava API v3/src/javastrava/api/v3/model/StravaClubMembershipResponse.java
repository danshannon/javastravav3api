package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaClubMembershipStatus;
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
	 * Membership status
	 */
	private StravaClubMembershipStatus membership;
	/**
	 * No args constructor
	 */
	public StravaClubMembershipResponse() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StravaClubMembershipResponse other = (StravaClubMembershipResponse) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (membership != other.membership)
			return false;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		return true;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return this.success;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((membership == null) ? 0 : membership.hashCode());
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		return result;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(final Boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "StravaClubMembershipResponse [success=" + success + ", active=" + active + ", membership=" + membership
				+ "]";
	}
	public StravaClubMembershipStatus getMembership() {
		return membership;
	}
	public void setMembership(StravaClubMembershipStatus membership) {
		this.membership = membership;
	}
}
