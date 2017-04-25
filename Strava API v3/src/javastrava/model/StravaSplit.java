package javastrava.model;

import javastrava.model.reference.StravaResourceState;
import javastrava.service.ActivityService;

/**
 * <p>
 * Split time data associated with runs. Comes in metric (1km) and imperial (1 mile) versions. See {@link ActivityService#getActivity(Long)}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSplit implements StravaEntity {
	/**
	 * Total distance in metres
	 */
	private Float	distance;
	/**
	 * Elapsed time for the split in seconds (including time spent stopped)
	 */
	private Integer	elapsedTime;
	/**
	 * elevation difference in metres
	 */
	private Float	elevationDifference;
	/**
	 * Moving time for the split in seconds (excluding time spent stopped)
	 */
	private Integer	movingTime;
	/**
	 * Order of the split within the run
	 */
	private Integer	split;

	/**
	 * Average heartrate
	 */
	private Float averageHeartrate;

	/**
	 * Average speed
	 */
	private Float averageSpeed;

	/**
	 * Pace zone (runs only)
	 */
	private Integer paceZone;

	/**
	 * No args constructor
	 */
	public StravaSplit() {
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
		if (!(obj instanceof StravaSplit)) {
			return false;
		}
		final StravaSplit other = (StravaSplit) obj;
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
		if (this.elevationDifference == null) {
			if (other.elevationDifference != null) {
				return false;
			}
		} else if (!this.elevationDifference.equals(other.elevationDifference)) {
			return false;
		}
		if (this.movingTime == null) {
			if (other.movingTime != null) {
				return false;
			}
		} else if (!this.movingTime.equals(other.movingTime)) {
			return false;
		}
		if (this.paceZone == null) {
			if (other.paceZone != null) {
				return false;
			}
		} else if (!this.paceZone.equals(other.paceZone)) {
			return false;
		}
		if (this.split == null) {
			if (other.split != null) {
				return false;
			}
		} else if (!this.split.equals(other.split)) {
			return false;
		}
		return true;
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
	 * @return the elevationDifference
	 */
	public Float getElevationDifference() {
		return this.elevationDifference;
	}

	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}

	/**
	 * @return the paceZone
	 */
	public Integer getPaceZone() {
		return this.paceZone;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the split
	 */
	public Integer getSplit() {
		return this.split;
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
		result = (prime * result) + ((this.averageHeartrate == null) ? 0 : this.averageHeartrate.hashCode());
		result = (prime * result) + ((this.averageSpeed == null) ? 0 : this.averageSpeed.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.elevationDifference == null) ? 0 : this.elevationDifference.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.paceZone == null) ? 0 : this.paceZone.hashCode());
		result = (prime * result) + ((this.split == null) ? 0 : this.split.hashCode());
		return result;
	}

	/**
	 * @param averageHeartrate
	 *            the averageHeartrate to set
	 */
	public void setAverageHeartrate(Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}

	/**
	 * @param averageSpeed
	 *            the averageSpeed to set
	 */
	public void setAverageSpeed(Float averageSpeed) {
		this.averageSpeed = averageSpeed;
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
	 * @param elevationDifference
	 *            the elevationDifference to set
	 */
	public void setElevationDifference(final Float elevationDifference) {
		this.elevationDifference = elevationDifference;
	}

	/**
	 * @param movingTime
	 *            the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}

	/**
	 * @param paceZone
	 *            the paceZone to set
	 */
	public void setPaceZone(Integer paceZone) {
		this.paceZone = paceZone;
	}

	/**
	 * @param split
	 *            the split to set
	 */
	public void setSplit(final Integer split) {
		this.split = split;
	}

	@Override
	public String toString() {
		return "StravaSplit [distance=" + this.distance + ", elapsedTime=" + this.elapsedTime + ", elevationDifference=" + this.elevationDifference + ", movingTime=" + this.movingTime + ", split=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.split + ", averageHeartrate=" + this.averageHeartrate + ", averageSpeed=" + this.averageSpeed + ", paceZone=" + this.paceZone + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
