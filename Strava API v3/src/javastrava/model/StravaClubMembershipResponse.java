package javastrava.model;

import javastrava.model.reference.StravaClubMembershipStatus;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ClubService;

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
public class StravaClubMembershipResponse implements StravaEntity {
	/**
	 * true if the call to set membership status succeeded
	 */
	private Boolean						success;
	/**
	 * true if the authenticated athlete is now a member of the club
	 */
	private Boolean						active;
	/**
	 * Membership status
	 */
	private StravaClubMembershipStatus	membership;

	/**
	 * No args constructor
	 */
	public StravaClubMembershipResponse() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
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
		if (this.membership != other.membership) {
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
	 * @return membership status
	 */
	public StravaClubMembershipStatus getMembership() {
		return this.membership;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return this.success;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.active == null) ? 0 : this.active.hashCode());
		result = (prime * result) + ((this.membership == null) ? 0 : this.membership.hashCode());
		result = (prime * result) + ((this.success == null) ? 0 : this.success.hashCode());
		return result;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * @param membership
	 *            membership status
	 */
	public void setMembership(final StravaClubMembershipStatus membership) {
		this.membership = membership;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(final Boolean success) {
		this.success = success;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClubMembershipResponse [success=" + this.success + ", active=" + this.active + ", membership=" + this.membership //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ "]"; //$NON-NLS-1$
	}
}
