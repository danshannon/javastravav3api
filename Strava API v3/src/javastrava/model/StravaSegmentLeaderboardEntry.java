package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * A single entry in a {@link StravaSegmentLeaderboard}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSegmentLeaderboardEntry implements StravaEntity {
	/**
	 * Name of the athlete
	 */
	private String			athleteName;
	/**
	 * Strava's unique identifier for the athlete
	 */
	private Integer			athleteId;
	/**
	 * Athlete's gender
	 */
	private StravaGender	athleteGender;
	/**
	 * Average heart rate (in beats per minute) for the effort associated with the leaderboard entry, if data was provided with the upload
	 */
	private Float			averageHr;
	/**
	 * Average power (in watts) for the effort associated with this leaderboard entry
	 */
	private Float			averageWatts;
	/**
	 * Total distance of the effort in metres
	 */
	private Float			distance;
	/**
	 * Elapsed time for the effort in seconds (including time spent stopped)
	 */
	private Integer			elapsedTime;
	/**
	 * Moving time for the effort in seconds (excluding time spent stopped)
	 */
	private Integer			movingTime;
	/**
	 * Start date and time for the effort
	 */
	private ZonedDateTime	startDate;
	/**
	 * Start date and time for the effort, hacked into the local timezone
	 */
	private LocalDateTime	startDateLocal;
	/**
	 * Identifier of the {@link StravaActivity activity} associated with this entry
	 */
	private Integer			activityId;
	/**
	 * Identifier of the {@link StravaSegmentEffort effort} associated with this entry
	 */
	private Long			effortId;
	/**
	 * Overall rank of this entry
	 */
	private Integer			rank;
	/**
	 * URL of the athlete's profile picture
	 */
	private String			athleteProfile;
	/**
	 * Indicates which grouping the entry is in (overall leaderboard, or the athlete extract)
	 */
	private Integer			neighborhoodIndex;

	/**
	 * No args constructor
	 */
	public StravaSegmentLeaderboardEntry() {
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
		if (!(obj instanceof StravaSegmentLeaderboardEntry)) {
			return false;
		}
		final StravaSegmentLeaderboardEntry other = (StravaSegmentLeaderboardEntry) obj;
		if (this.activityId == null) {
			if (other.activityId != null) {
				return false;
			}
		} else if (!this.activityId.equals(other.activityId)) {
			return false;
		}
		if (this.athleteGender != other.athleteGender) {
			return false;
		}
		if (this.athleteId == null) {
			if (other.athleteId != null) {
				return false;
			}
		} else if (!this.athleteId.equals(other.athleteId)) {
			return false;
		}
		if (this.athleteName == null) {
			if (other.athleteName != null) {
				return false;
			}
		} else if (!this.athleteName.equals(other.athleteName)) {
			return false;
		}
		if (this.athleteProfile == null) {
			if (other.athleteProfile != null) {
				return false;
			}
		} else if (!this.athleteProfile.equals(other.athleteProfile)) {
			return false;
		}
		if (this.averageHr == null) {
			if (other.averageHr != null) {
				return false;
			}
		} else if (!this.averageHr.equals(other.averageHr)) {
			return false;
		}
		if (this.averageWatts == null) {
			if (other.averageWatts != null) {
				return false;
			}
		} else if (!this.averageWatts.equals(other.averageWatts)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.effortId == null) {
			if (other.effortId != null) {
				return false;
			}
		} else if (!this.effortId.equals(other.effortId)) {
			return false;
		}
		if (this.elapsedTime == null) {
			if (other.elapsedTime != null) {
				return false;
			}
		} else if (!this.elapsedTime.equals(other.elapsedTime)) {
			return false;
		}
		if (this.movingTime == null) {
			if (other.movingTime != null) {
				return false;
			}
		} else if (!this.movingTime.equals(other.movingTime)) {
			return false;
		}
		if (this.neighborhoodIndex == null) {
			if (other.neighborhoodIndex != null) {
				return false;
			}
		} else if (!this.neighborhoodIndex.equals(other.neighborhoodIndex)) {
			return false;
		}
		if (this.rank == null) {
			if (other.rank != null) {
				return false;
			}
		} else if (!this.rank.equals(other.rank)) {
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
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}

	/**
	 * @return the athleteGender
	 */
	public StravaGender getAthleteGender() {
		return this.athleteGender;
	}

	/**
	 * @return the athleteId
	 */
	public Integer getAthleteId() {
		return this.athleteId;
	}

	/**
	 * @return the athleteName
	 */
	public String getAthleteName() {
		return this.athleteName;
	}

	/**
	 * @return the athleteProfile
	 */
	public String getAthleteProfile() {
		return this.athleteProfile;
	}

	/**
	 * @return the averageHr
	 */
	public Float getAverageHr() {
		return this.averageHr;
	}

	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the effortId
	 */
	public Long getEffortId() {
		return this.effortId;
	}

	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}

	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}

	/**
	 * @return the neighborhoodIndex
	 */
	public Integer getNeighborhoodIndex() {
		return this.neighborhoodIndex;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
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
		result = (prime * result) + ((this.activityId == null) ? 0 : this.activityId.hashCode());
		result = (prime * result) + ((this.athleteGender == null) ? 0 : this.athleteGender.hashCode());
		result = (prime * result) + ((this.athleteId == null) ? 0 : this.athleteId.hashCode());
		result = (prime * result) + ((this.athleteName == null) ? 0 : this.athleteName.hashCode());
		result = (prime * result) + ((this.athleteProfile == null) ? 0 : this.athleteProfile.hashCode());
		result = (prime * result) + ((this.averageHr == null) ? 0 : this.averageHr.hashCode());
		result = (prime * result) + ((this.averageWatts == null) ? 0 : this.averageWatts.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.effortId == null) ? 0 : this.effortId.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.neighborhoodIndex == null) ? 0 : this.neighborhoodIndex.hashCode());
		result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		return result;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(final Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * @param athleteGender
	 *            the athleteGender to set
	 */
	public void setAthleteGender(final StravaGender athleteGender) {
		this.athleteGender = athleteGender;
	}

	/**
	 * @param athleteId
	 *            the athleteId to set
	 */
	public void setAthleteId(final Integer athleteId) {
		this.athleteId = athleteId;
	}

	/**
	 * @param athleteName
	 *            the athleteName to set
	 */
	public void setAthleteName(final String athleteName) {
		this.athleteName = athleteName;
	}

	/**
	 * @param athleteProfile
	 *            the athleteProfile to set
	 */
	public void setAthleteProfile(final String athleteProfile) {
		this.athleteProfile = athleteProfile;
	}

	/**
	 * @param averageHr
	 *            the averageHr to set
	 */
	public void setAverageHr(final Float averageHr) {
		this.averageHr = averageHr;
	}

	/**
	 * @param averageWatts
	 *            the averageWatts to set
	 */
	public void setAverageWatts(final Float averageWatts) {
		this.averageWatts = averageWatts;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param effortId
	 *            the effortId to set
	 */
	public void setEffortId(final Long effortId) {
		this.effortId = effortId;
	}

	/**
	 * @param elapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * @param movingTime
	 *            the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}

	/**
	 * @param neighborhoodIndex
	 *            the neighborhoodIndex to set
	 */
	public void setNeighborhoodIndex(final Integer neighborhoodIndex) {
		this.neighborhoodIndex = neighborhoodIndex;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(final Integer rank) {
		this.rank = rank;
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
		return "StravaSegmentLeaderboardEntry [athleteName=" + this.athleteName + ", athleteId=" + this.athleteId //$NON-NLS-1$ //$NON-NLS-2$
				+ ", athleteGender=" + this.athleteGender //$NON-NLS-1$
				+ ", averageHr=" + this.averageHr + ", averageWatts=" + this.averageWatts + ", distance=" + this.distance //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", elapsedTime=" + this.elapsedTime //$NON-NLS-1$
				+ ", movingTime=" + this.movingTime + ", startDate=" + this.startDate + ", startDateLocal=" + this.startDateLocal //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", activityId=" //$NON-NLS-1$
				+ this.activityId + ", effortId=" + this.effortId + ", rank=" + this.rank + ", athleteProfile=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.athleteProfile + ", neighborhoodIndex=" //$NON-NLS-1$
				+ this.neighborhoodIndex + "]"; //$NON-NLS-1$
	}
}
