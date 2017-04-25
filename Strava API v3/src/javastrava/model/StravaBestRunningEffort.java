package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * A 'best running effort' - calculated by Strava for runs only. Returned as part of activity details.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaBestRunningEffort implements StravaEntity {
	/**
	 * Strava's unique identifier for this running effort
	 */
	private Integer				id;
	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * Name of this effort (Best 1k, etc.)
	 */
	private String				name;
	/**
	 * Strava segment associated with this best effort (if there is one)
	 */
	private StravaSegment		segment;
	/**
	 * Strava activity associated with this best effort
	 */
	private StravaActivity		activity;
	/**
	 * Strava athlete associated with this run
	 */
	private StravaAthlete		athlete;
	/**
	 * KOM rank for the effort
	 */
	private Integer				komRank;
	/**
	 * Personal record ranking for this effort
	 */
	private Integer				prRank;
	/**
	 * elapsed time in seconds (includes time spent stopped)
	 */
	private Integer				elapsedTime;
	/**
	 * moving time in seconds (excludes time spent stopped)
	 */
	private Integer				movingTime;
	/**
	 * date and time that the effort started
	 */
	private ZonedDateTime		startDate;
	/**
	 * local start date for the effort (i.e. in the timezone that it started, shifted to UTC)
	 */
	private LocalDateTime		startDateLocal;
	/**
	 * distance in metres
	 */
	private Float				distance;

	/**
	 * No args constructor
	 */
	public StravaBestRunningEffort() {
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
		if (!(obj instanceof StravaBestRunningEffort)) {
			return false;
		}
		final StravaBestRunningEffort other = (StravaBestRunningEffort) obj;
		if (this.activity == null) {
			if (other.activity != null) {
				return false;
			}
		} else if (!this.activity.equals(other.activity)) {
			return false;
		}
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.elapsedTime == null) {
			if (other.elapsedTime != null) {
				return false;
			}
		} else if (!this.elapsedTime.equals(other.elapsedTime)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.komRank == null) {
			if (other.komRank != null) {
				return false;
			}
		} else if (!this.komRank.equals(other.komRank)) {
			return false;
		}
		if (this.movingTime == null) {
			if (other.movingTime != null) {
				return false;
			}
		} else if (!this.movingTime.equals(other.movingTime)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.prRank == null) {
			if (other.prRank != null) {
				return false;
			}
		} else if (!this.prRank.equals(other.prRank)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.segment == null) {
			if (other.segment != null) {
				return false;
			}
		} else if (!this.segment.equals(other.segment)) {
			return false;
		}
		if (this.startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!this.startDate.equals(other.startDate)) {
			return false;
		}
		if (this.startDateLocal == null) {
			if (other.startDateLocal != null) {
				return false;
			}
		} else if (!this.startDateLocal.equals(other.startDateLocal)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activity
	 */
	public StravaActivity getActivity() {
		return this.activity;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the komRank
	 */
	public Integer getKomRank() {
		return this.komRank;
	}

	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the prRank
	 */
	public Integer getPrRank() {
		return this.prRank;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the segment
	 */
	public StravaSegment getSegment() {
		return this.segment;
	}

	/**
	 * @return the startDate
	 */
	public ZonedDateTime getStartDate() {
		return this.startDate;
	}

	/**
	 * @return the startDateLocal
	 */
	public LocalDateTime getStartDateLocal() {
		return this.startDateLocal;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activity == null) ? 0 : this.activity.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.komRank == null) ? 0 : this.komRank.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.prRank == null) ? 0 : this.prRank.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.segment == null) ? 0 : this.segment.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		return result;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(final StravaActivity activity) {
		this.activity = activity;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param elapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param komRank
	 *            the komRank to set
	 */
	public void setKomRank(final Integer komRank) {
		this.komRank = komRank;
	}

	/**
	 * @param movingTime
	 *            the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param prRank
	 *            the prRank to set
	 */
	public void setPrRank(final Integer prRank) {
		this.prRank = prRank;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param segment
	 *            the segment to set
	 */
	public void setSegment(final StravaSegment segment) {
		this.segment = segment;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final ZonedDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param startDateLocal
	 *            the startDateLocal to set
	 */
	public void setStartDateLocal(final LocalDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaBestRunningEffort [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", segment=" + this.segment //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", activity=" + this.activity + ", athlete=" + this.athlete + ", komRank=" + this.komRank + ", prRank=" + this.prRank + ", elapsedTime=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.elapsedTime + ", movingTime=" + this.movingTime + ", startDate=" + this.startDate + ", startDateLocal=" + this.startDateLocal //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", distance=" + this.distance + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
