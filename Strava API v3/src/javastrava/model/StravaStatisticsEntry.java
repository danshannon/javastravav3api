package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Detail of athlete {@link StravaStatistics}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaStatisticsEntry implements StravaEntity {
	/**
	 * Number of activities
	 */
	private Integer	count;
	/**
	 * Total distance in metres
	 */
	private Float	distance;
	/**
	 * Total moving time in seconds (excluding time spent stopped)
	 */
	private Integer	movingTime;
	/**
	 * Total elapsed time in seconds (including time spent stopped)
	 */
	private Integer	elapsedTime;
	/**
	 * Total elevation gain in metres
	 */
	private Float	elevationGain;
	/**
	 * Total number of achievements
	 */
	private Integer	achievementCount;

	/**
	 *
	 */
	public StravaStatisticsEntry() {
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
		if (!(obj instanceof StravaStatisticsEntry)) {
			return false;
		}
		final StravaStatisticsEntry other = (StravaStatisticsEntry) obj;
		if (this.achievementCount == null) {
			if (other.achievementCount != null) {
				return false;
			}
		} else if (!this.achievementCount.equals(other.achievementCount)) {
			return false;
		}
		if (this.count == null) {
			if (other.count != null) {
				return false;
			}
		} else if (!this.count.equals(other.count)) {
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
		if (this.elevationGain == null) {
			if (other.elevationGain != null) {
				return false;
			}
		} else if (!this.elevationGain.equals(other.elevationGain)) {
			return false;
		}
		if (this.movingTime == null) {
			if (other.movingTime != null) {
				return false;
			}
		} else if (!this.movingTime.equals(other.movingTime)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the achievementCount
	 */
	public Integer getAchievementCount() {
		return this.achievementCount;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return this.count;
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
	 * @return the elevationGain
	 */
	public Float getElevationGain() {
		return this.elevationGain;
	}

	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.achievementCount == null) ? 0 : this.achievementCount.hashCode());
		result = (prime * result) + ((this.count == null) ? 0 : this.count.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.elevationGain == null) ? 0 : this.elevationGain.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		return result;
	}

	/**
	 * @param achievementCount
	 *            the achievementCount to set
	 */
	public void setAchievementCount(final Integer achievementCount) {
		this.achievementCount = achievementCount;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(final Integer count) {
		this.count = count;
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
	 * @param elevationGain
	 *            the elevationGain to set
	 */
	public void setElevationGain(final Float elevationGain) {
		this.elevationGain = elevationGain;
	}

	/**
	 * @param movingTime
	 *            the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaStatisticsEntry [count=" + this.count + ", distance=" + this.distance + ", movingTime=" + this.movingTime + ", elapsedTime=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.elapsedTime + ", elevationGain=" + this.elevationGain + ", achievementCount=" + this.achievementCount + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
