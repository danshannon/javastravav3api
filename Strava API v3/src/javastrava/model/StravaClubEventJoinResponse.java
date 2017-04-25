package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * Response returned by the club event join/leave endpoints
 *
 * @author Dan Shannon
 *
 */
public class StravaClubEventJoinResponse implements StravaEntity {
	/**
	 * Indicates whether the authenticated athlete has joined the event
	 */
	private Boolean joined;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaClubEventJoinResponse)) {
			return false;
		}
		final StravaClubEventJoinResponse other = (StravaClubEventJoinResponse) obj;
		if (this.joined == null) {
			if (other.joined != null) {
				return false;
			}
		} else if (!this.joined.equals(other.joined)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the joined
	 */
	public Boolean getJoined() {
		return this.joined;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.joined == null) ? 0 : this.joined.hashCode());
		return result;
	}

	/**
	 * @param joined
	 *            the joined to set
	 */
	public void setJoined(Boolean joined) {
		this.joined = joined;
	}

	@Override
	public String toString() {
		return "StravaClubEventJoinResponse [joined=" + this.joined + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
