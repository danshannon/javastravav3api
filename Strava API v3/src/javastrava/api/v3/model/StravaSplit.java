package javastrava.api.v3.model;

import javastrava.api.v3.service.ActivityService;

/**
 * <p>
 * Split time data associated with runs. Comes in metric (1km) and imperial (1 mile) versions. See {@link ActivityService#getActivity(Long)}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSplit {
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Elapsed time for the split in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * elevation difference in metres
	 */
	private Float elevationDifference;
	/**
	 * Moving time for the split in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Order of the split within the run
	 */
	private Integer split;
	/**
	 * No args constructor
	 */
	public StravaSplit() {
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
		if (!(obj instanceof StravaSplit)) {
			return false;
		}
		final StravaSplit other = (StravaSplit) obj;
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
	 * @return the split
	 */
	public Integer getSplit() {
		return this.split;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.elevationDifference == null) ? 0 : this.elevationDifference.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.split == null) ? 0 : this.split.hashCode());
		return result;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @param elevationDifference the elevationDifference to set
	 */
	public void setElevationDifference(final Float elevationDifference) {
		this.elevationDifference = elevationDifference;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @param split the split to set
	 */
	public void setSplit(final Integer split) {
		this.split = split;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaSplit [distance=" + this.distance + ", elapsedTime=" + this.elapsedTime + ", elevationDifference=" + this.elevationDifference //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", movingTime=" + this.movingTime + ", split=" + this.split + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
