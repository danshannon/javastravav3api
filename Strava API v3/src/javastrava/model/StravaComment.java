package javastrava.model;

import java.time.ZonedDateTime;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Record of an individual comment made on an activity
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaComment implements StravaCacheableEntity<Integer> {
	/**
	 * Strava's unique identifier for the comment
	 */
	private Integer				id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * Identifier of the activity on which this is a comment
	 */
	private Long				activityId;
	/**
	 * Text of the comment. Allegedly Strava supports markdown in comments!
	 */
	private String				text;
	/**
	 * Athlete who MADE the comment
	 */
	private StravaAthlete		athlete;
	/**
	 * Date and time the comment was posted
	 */
	private ZonedDateTime		createdAt;

	/**
	 * No args constructor
	 */
	public StravaComment() {
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
		if (!(obj instanceof StravaComment)) {
			return false;
		}
		final StravaComment other = (StravaComment) obj;
		if (this.activityId == null) {
			if (other.activityId != null) {
				return false;
			}
		} else if (!this.activityId.equals(other.activityId)) {
			return false;
		}
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
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
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!this.text.equals(other.text)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return this.activityId;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
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
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activityId == null) ? 0 : this.activityId.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.text == null) ? 0 : this.text.hashCode());
		return result;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(final Long activityId) {
		this.activityId = activityId;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
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
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaComment [id=" + this.id + ", resourceState=" + this.resourceState + ", activityId=" + this.activityId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", text=" + this.text //$NON-NLS-1$
				+ ", athlete=" + this.athlete + ", createdAt=" + this.createdAt + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
