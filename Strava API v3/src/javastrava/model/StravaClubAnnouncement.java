package javastrava.model;

import java.time.ZonedDateTime;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Announcements are posts sent by Club Admins or Owners to the members of a club. Only members of private clubs can access their announcements. The objects are returned in summary representation.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClubAnnouncement implements StravaEntity {
	/**
	 * Unique identifier of the announcement
	 */
	private Integer id;

	/**
	 * State indicating level of detail of this representation of an announcement
	 */
	private StravaResourceState resourceState;

	/**
	 * Club identifier the announcement relates to
	 */
	private Integer clubId;

	/**
	 * Athlete making the announcement
	 */
	private StravaAthlete athlete;

	/**
	 * Date and time the announcement was posted on Strava
	 */
	private ZonedDateTime createdAt;

	/**
	 * Text of the announcement
	 */
	private String message;

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
		if (!(obj instanceof StravaClubAnnouncement)) {
			return false;
		}
		final StravaClubAnnouncement other = (StravaClubAnnouncement) obj;
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.clubId == null) {
			if (other.clubId != null) {
				return false;
			}
		} else if (!this.clubId.equals(other.clubId)) {
			return false;
		}
		if (this.createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!this.createdAt.equals(other.createdAt)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!this.message.equals(other.message)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		return true;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}

	/**
	 * @return the clubId
	 */
	public Integer getClubId() {
		return this.clubId;
	}

	/**
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.clubId == null) ? 0 : this.clubId.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.message == null) ? 0 : this.message.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		return result;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param clubId
	 *            the clubId to set
	 */
	public void setClubId(final Integer clubId) {
		this.clubId = clubId;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClubAnnouncement [id=" + this.id + ", resourceState=" + this.resourceState + ", clubId=" + this.clubId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", athlete=" + this.athlete //$NON-NLS-1$
				+ ", createdAt=" + this.createdAt + ", message=" + this.message + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
