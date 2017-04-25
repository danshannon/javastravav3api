package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.SegmentService;

/**
 * <p>
 * A segment effort represents an athlete's attempt at a segment. It can also be thought of as a portion of a ride that covers a segment. The object is returned in summary or detailed representations.
 * They are currently the same.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSegmentEffort implements StravaCacheableEntity<Long> {
	/**
	 * Strava's unique identifier for this segment effort
	 */
	private Long id;

	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState		resourceState;
	/**
	 * Name of the segment
	 */
	private String					name;
	/**
	 * Activity - this instance will contain the id of the activity only
	 */
	private StravaActivity			activity;
	/**
	 * Athlete - this instance will contain the id of the athlete only
	 */
	private StravaAthlete			athlete;
	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer					elapsedTime;
	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer					movingTime;
	/**
	 * Start date and time for this effort
	 */
	private ZonedDateTime			startDate;
	/**
	 * Start date and time for this effort, hacked to local time zone at the start
	 */
	private LocalDateTime			startDateLocal;
	/**
	 * the length im metres of the effort as described by the activity, this may be different than the length of the segment
	 */
	private Float					distance;
	/**
	 * the activity stream index of the start of this effort
	 */
	private Integer					startIndex;
	/**
	 * the activity stream index of the end of this effort
	 */
	private Integer					endIndex;
	/**
	 * Average cadence in revolutions per minute, if cadence data was provided with the activity upload
	 */
	private Float					averageCadence;
	/**
	 * Average watts (rides only)
	 */
	private Float					averageWatts;
	/**
	 * Average heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Float					averageHeartrate;
	/**
	 * Maximum heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Integer					maxHeartrate;
	/**
	 * {@link StravaResourceState#SUMMARY} representation of the segment
	 */
	private StravaSegment			segment;
	/**
	 * 1-10 rank on segment at time of upload
	 */
	private Integer					komRank;
	/**
	 * 1-3 personal record on segment at time of upload
	 */
	private Integer					prRank;
	/**
	 * indicates a hidden/non-important effort when returned as part of an activity, value may change over time, see retrieve an activity for more details
	 */
	private Boolean					hidden;
	/**
	 * @see SegmentService#listStarredSegments(Integer)
	 */
	private Boolean					isKom;
	/**
	 * Summary of achievements on this effort
	 */
	private List<StravaAchievement>	achievements;

	/**
	 * Summary of athlete's statistics on this segment
	 */
	private StravaAthleteSegmentStats	athleteSegmentStats;
	/**
	 * Was the power data on this effort from a device (i.e. a power meter of some kind)
	 */
	private Boolean						deviceWatts;

	/**
	 *
	 */
	public StravaSegmentEffort() {
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
		if (!(obj instanceof StravaSegmentEffort)) {
			return false;
		}
		final StravaSegmentEffort other = (StravaSegmentEffort) obj;
		if (this.achievements == null) {
			if (other.achievements != null) {
				return false;
			}
		} else if (!this.achievements.equals(other.achievements)) {
			return false;
		}
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
		if (this.athleteSegmentStats == null) {
			if (other.athleteSegmentStats != null) {
				return false;
			}
		} else if (!this.athleteSegmentStats.equals(other.athleteSegmentStats)) {
			return false;
		}
		if (this.averageCadence == null) {
			if (other.averageCadence != null) {
				return false;
			}
		} else if (!this.averageCadence.equals(other.averageCadence)) {
			return false;
		}
		if (this.averageHeartrate == null) {
			if (other.averageHeartrate != null) {
				return false;
			}
		} else if (!this.averageHeartrate.equals(other.averageHeartrate)) {
			return false;
		}
		if (this.averageWatts == null) {
			if (other.averageWatts != null) {
				return false;
			}
		} else if (!this.averageWatts.equals(other.averageWatts)) {
			return false;
		}
		if (this.deviceWatts == null) {
			if (other.deviceWatts != null) {
				return false;
			}
		} else if (!this.deviceWatts.equals(other.deviceWatts)) {
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
		if (this.endIndex == null) {
			if (other.endIndex != null) {
				return false;
			}
		} else if (!this.endIndex.equals(other.endIndex)) {
			return false;
		}
		if (this.hidden == null) {
			if (other.hidden != null) {
				return false;
			}
		} else if (!this.hidden.equals(other.hidden)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.isKom == null) {
			if (other.isKom != null) {
				return false;
			}
		} else if (!this.isKom.equals(other.isKom)) {
			return false;
		}
		if (this.komRank == null) {
			if (other.komRank != null) {
				return false;
			}
		} else if (!this.komRank.equals(other.komRank)) {
			return false;
		}
		if (this.maxHeartrate == null) {
			if (other.maxHeartrate != null) {
				return false;
			}
		} else if (!this.maxHeartrate.equals(other.maxHeartrate)) {
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
		if (this.startIndex == null) {
			if (other.startIndex != null) {
				return false;
			}
		} else if (!this.startIndex.equals(other.startIndex)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the achievements
	 */
	public List<StravaAchievement> getAchievements() {
		return this.achievements;
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
	 * @return the athleteSegmentStats
	 */
	public StravaAthleteSegmentStats getAthleteSegmentStats() {
		return this.athleteSegmentStats;
	}

	/**
	 * @return the averageCadence
	 */
	public Float getAverageCadence() {
		return this.averageCadence;
	}

	/**
	 * @return the averageHeartrate
	 */
	public Float getAverageHeartrate() {
		return this.averageHeartrate;
	}

	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
	}

	/**
	 * @return the deviceWatts
	 */
	public Boolean getDeviceWatts() {
		return this.deviceWatts;
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
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return this.endIndex;
	}

	/**
	 * @return the hidden
	 */
	public Boolean getHidden() {
		return this.hidden;
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the isKom
	 */
	public Boolean getIsKom() {
		return this.isKom;
	}

	/**
	 * @return the komRank
	 */
	public Integer getKomRank() {
		return this.komRank;
	}

	/**
	 * @return the maxHeartrate
	 */
	public Integer getMaxHeartrate() {
		return this.maxHeartrate;
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
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return this.startIndex;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.achievements == null) ? 0 : this.achievements.hashCode());
		result = (prime * result) + ((this.activity == null) ? 0 : this.activity.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.athleteSegmentStats == null) ? 0 : this.athleteSegmentStats.hashCode());
		result = (prime * result) + ((this.averageCadence == null) ? 0 : this.averageCadence.hashCode());
		result = (prime * result) + ((this.averageHeartrate == null) ? 0 : this.averageHeartrate.hashCode());
		result = (prime * result) + ((this.averageWatts == null) ? 0 : this.averageWatts.hashCode());
		result = (prime * result) + ((this.deviceWatts == null) ? 0 : this.deviceWatts.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.endIndex == null) ? 0 : this.endIndex.hashCode());
		result = (prime * result) + ((this.hidden == null) ? 0 : this.hidden.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.isKom == null) ? 0 : this.isKom.hashCode());
		result = (prime * result) + ((this.komRank == null) ? 0 : this.komRank.hashCode());
		result = (prime * result) + ((this.maxHeartrate == null) ? 0 : this.maxHeartrate.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.prRank == null) ? 0 : this.prRank.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.segment == null) ? 0 : this.segment.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		result = (prime * result) + ((this.startIndex == null) ? 0 : this.startIndex.hashCode());
		return result;
	}

	/**
	 * @param achievements
	 *            the achievements to set
	 */
	public void setAchievements(final List<StravaAchievement> achievements) {
		this.achievements = achievements;
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
	 * @param athleteSegmentStats
	 *            the athleteSegmentStats to set
	 */
	public void setAthleteSegmentStats(final StravaAthleteSegmentStats athleteSegmentStats) {
		this.athleteSegmentStats = athleteSegmentStats;
	}

	/**
	 * @param averageCadence
	 *            the averageCadence to set
	 */
	public void setAverageCadence(final Float averageCadence) {
		this.averageCadence = averageCadence;
	}

	/**
	 * @param averageHeartrate
	 *            the averageHeartrate to set
	 */
	public void setAverageHeartrate(final Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}

	/**
	 * @param averageWatts
	 *            the averageWatts to set
	 */
	public void setAverageWatts(final Float averageWatts) {
		this.averageWatts = averageWatts;
	}

	/**
	 * @param deviceWatts
	 *            the deviceWatts to set
	 */
	public void setDeviceWatts(final Boolean deviceWatts) {
		this.deviceWatts = deviceWatts;
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
	 * @param endIndex
	 *            the endIndex to set
	 */
	public void setEndIndex(final Integer endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(final Boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param isKom
	 *            the isKom to set
	 */
	public void setIsKom(final Boolean isKom) {
		this.isKom = isKom;
	}

	/**
	 * @param komRank
	 *            the komRank to set
	 */
	public void setKomRank(final Integer komRank) {
		this.komRank = komRank;
	}

	/**
	 * @param maxHeartrate
	 *            the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
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
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(final Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaSegmentEffort [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", activity=" + this.activity //$NON-NLS-1$
				+ ", athlete=" + this.athlete + ", elapsedTime=" + this.elapsedTime + ", movingTime=" + this.movingTime //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", startDate=" + this.startDate //$NON-NLS-1$
				+ ", startDateLocal=" + this.startDateLocal + ", distance=" + this.distance + ", startIndex=" + this.startIndex //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", endIndex=" + this.endIndex //$NON-NLS-1$
				+ ", averageCadence=" + this.averageCadence + ", averageWatts=" + this.averageWatts + ", averageHeartrate=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.averageHeartrate + ", maxHeartrate=" + this.maxHeartrate + ", segment=" + this.segment + ", komRank=" + this.komRank + ", prRank=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.prRank + ", hidden=" //$NON-NLS-1$
				+ this.hidden + ", isKom=" + this.isKom + ", achievements=" + this.achievements + ", athleteSegmentStats=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.athleteSegmentStats + ", deviceWatts=" + this.deviceWatts + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
