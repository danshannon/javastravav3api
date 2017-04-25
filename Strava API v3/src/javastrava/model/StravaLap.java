package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * A lap within an {@link StravaActivity}; this is a user-specified section of a ride somewhat like a segment effort, but specifically bound to either being a lap of a track or a section of the
 * activity of a particular length (distance)
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaLap implements StravaCacheableEntity<Long> {
	/**
	 * Strava's unique identifier for this lap
	 */
	private Long id;

	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;

	/**
	 * Name of the lap
	 */
	private String name;

	/**
	 * Activity that this lap is part of
	 */
	private StravaActivity activity;

	/**
	 * Athlete who did the activity that this lap is part of
	 */
	private StravaAthlete athlete;

	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;

	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;

	/**
	 * Start date and time for this activity
	 */
	private ZonedDateTime startDate;

	/**
	 * Start date and time for this activity, hacked to the time zone where the activity started
	 */
	private LocalDateTime startDateLocal;

	/**
	 * Distance travelled in metres
	 */
	private Float distance;

	/**
	 * Index of the entry in the activity streams that represents the start of the lap
	 */
	private Integer startIndex;

	/**
	 * Index of the entry in the activity streams that represents the end of the lap
	 */
	private Integer endIndex;

	/**
	 * Total elevation gain in metres
	 */
	private Float totalElevationGain;

	/**
	 * Average speed in metres per second
	 */
	private Float averageSpeed;

	/**
	 * Maximum speed in metres per second
	 */
	private Float maxSpeed;

	/**
	 * Average cadence in revolutions per minute (returned only if cadence data was uploaded with the activity). Applies only to rides.
	 */
	private Float averageCadence;

	/**
	 * Average power in watts
	 */
	private Float averageWatts;

	/**
	 * Is set to <code>true</code> if the power was measured using a power meter (i.e. power data was included in the upload)
	 */
	private Boolean deviceWatts;

	/**
	 * Average heart rate in beats per minute
	 */
	private Float averageHeartrate;

	/**
	 * Maximum heart rate in beats per minute
	 */
	private Float maxHeartrate;

	/**
	 * Lap number
	 */
	private Integer lapIndex;

	/**
	 * Pace zone (runs only)
	 */
	private Integer paceZone;

	/**
	 * UNDOCUMENTED
	 */
	private String split;

	/**
	 * No args constructor
	 */
	public StravaLap() {
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
		if (!(obj instanceof StravaLap)) {
			return false;
		}
		final StravaLap other = (StravaLap) obj;
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
		if (this.averageSpeed == null) {
			if (other.averageSpeed != null) {
				return false;
			}
		} else if (!this.averageSpeed.equals(other.averageSpeed)) {
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
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.lapIndex == null) {
			if (other.lapIndex != null) {
				return false;
			}
		} else if (!this.lapIndex.equals(other.lapIndex)) {
			return false;
		}
		if (this.maxHeartrate == null) {
			if (other.maxHeartrate != null) {
				return false;
			}
		} else if (!this.maxHeartrate.equals(other.maxHeartrate)) {
			return false;
		}
		if (this.maxSpeed == null) {
			if (other.maxSpeed != null) {
				return false;
			}
		} else if (!this.maxSpeed.equals(other.maxSpeed)) {
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
		if (this.paceZone == null) {
			if (other.paceZone != null) {
				return false;
			}
		} else if (!this.paceZone.equals(other.paceZone)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.split == null) {
			if (other.split != null) {
				return false;
			}
		} else if (!this.split.equals(other.split)) {
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
		if (this.totalElevationGain == null) {
			if (other.totalElevationGain != null) {
				return false;
			}
		} else if (!this.totalElevationGain.equals(other.totalElevationGain)) {
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
	 * @return the averageSpeed
	 */
	public Float getAverageSpeed() {
		return this.averageSpeed;
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
	 * @return The identifier
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the lapIndex
	 */
	public Integer getLapIndex() {
		return this.lapIndex;
	}

	/**
	 * @return the maxHeartrate
	 */
	public Float getMaxHeartrate() {
		return this.maxHeartrate;
	}

	/**
	 * @return the maxSpeed
	 */
	public Float getMaxSpeed() {
		return this.maxSpeed;
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
	 * @return the paceZone
	 */
	public Integer getPaceZone() {
		return this.paceZone;
	}

	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the split
	 */
	public String getSplit() {
		return this.split;
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
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
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
		result = (prime * result) + ((this.activity == null) ? 0 : this.activity.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.averageCadence == null) ? 0 : this.averageCadence.hashCode());
		result = (prime * result) + ((this.averageHeartrate == null) ? 0 : this.averageHeartrate.hashCode());
		result = (prime * result) + ((this.averageSpeed == null) ? 0 : this.averageSpeed.hashCode());
		result = (prime * result) + ((this.averageWatts == null) ? 0 : this.averageWatts.hashCode());
		result = (prime * result) + ((this.deviceWatts == null) ? 0 : this.deviceWatts.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.endIndex == null) ? 0 : this.endIndex.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.lapIndex == null) ? 0 : this.lapIndex.hashCode());
		result = (prime * result) + ((this.maxHeartrate == null) ? 0 : this.maxHeartrate.hashCode());
		result = (prime * result) + ((this.maxSpeed == null) ? 0 : this.maxSpeed.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.paceZone == null) ? 0 : this.paceZone.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.split == null) ? 0 : this.split.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		result = (prime * result) + ((this.startIndex == null) ? 0 : this.startIndex.hashCode());
		result = (prime * result) + ((this.totalElevationGain == null) ? 0 : this.totalElevationGain.hashCode());
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
	 * @param averageSpeed
	 *            the averageSpeed to set
	 */
	public void setAverageSpeed(final Float averageSpeed) {
		this.averageSpeed = averageSpeed;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param lapIndex
	 *            the lapIndex to set
	 */
	public void setLapIndex(final Integer lapIndex) {
		this.lapIndex = lapIndex;
	}

	/**
	 * @param maxHeartrate
	 *            the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Float maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(final Float maxSpeed) {
		this.maxSpeed = maxSpeed;
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
	 * @param paceZone
	 *            the paceZone to set
	 */
	public void setPaceZone(Integer paceZone) {
		this.paceZone = paceZone;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param split
	 *            the split to set
	 */
	public void setSplit(String split) {
		this.split = split;
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
	 * @param totalElevationGain
	 *            the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaLap [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", activity=" + this.activity + ", athlete=" + this.athlete + ", elapsedTime=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.elapsedTime + ", movingTime=" //$NON-NLS-1$
				+ this.movingTime + ", startDate=" + this.startDate + ", startDateLocal=" + this.startDateLocal + ", distance=" + this.distance + ", startIndex=" + this.startIndex + ", endIndex=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.endIndex + ", totalElevationGain=" + this.totalElevationGain + ", averageSpeed=" + this.averageSpeed + ", maxSpeed=" + this.maxSpeed + ", averageCadence=" + this.averageCadence //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", averageWatts=" + this.averageWatts //$NON-NLS-1$
				+ ", deviceWatts=" + this.deviceWatts + ", averageHeartrate=" + this.averageHeartrate + ", maxHeartrate=" + this.maxHeartrate + ", lapIndex=" + this.lapIndex + ", paceZone=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.paceZone + ", split=" //$NON-NLS-1$
				+ this.split + "]"; //$NON-NLS-1$
	}
}
