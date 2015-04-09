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
	 * No args constructor
	 */
	public StravaClubMembershipResponse() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaClubMembershipResponse)) {
			return false;
		}
		final StravaClubMembershipResponse other = (StravaClubMembershipResponse) obj;
		if (this.active == null) {
			if (other.active != null) {
				return false;
			}
		} else if (!this.active.equals(other.active)) {
			return false;
		}
		if (this.success == null) {
			if (other.success != null) {
				return false;
			}
		} else if (!this.success.equals(other.success)) {
			return false;
		}
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
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.active == null) ? 0 : this.active.hashCode());
		result = (prime * result) + ((this.success == null) ? 0 : this.success.hashCode());
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
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClubMembershipResponse [success=" + this.success + ", active=" + this.active + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
