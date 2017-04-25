package javastrava.model;

import java.util.List;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * UNDOCUMENTED
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSimilarActivitiesTrend implements StravaEntity {
	/**
	 * UNDOCUMENTED
	 */
	private List<Float> speeds;

	/**
	 * UNDOCUMENTED
	 */
	private Integer currentActivityIndex;

	/**
	 * UNDOCUMENTED
	 */
	private Float minSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Float midSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Float maxSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Integer direction;

	/**
	 * No-args constructor
	 */
	public StravaSimilarActivitiesTrend() {
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
		if (!(obj instanceof StravaSimilarActivitiesTrend)) {
			return false;
		}
		final StravaSimilarActivitiesTrend other = (StravaSimilarActivitiesTrend) obj;
		if (this.currentActivityIndex == null) {
			if (other.currentActivityIndex != null) {
				return false;
			}
		} else if (!this.currentActivityIndex.equals(other.currentActivityIndex)) {
			return false;
		}
		if (this.direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!this.direction.equals(other.direction)) {
			return false;
		}
		if (this.maxSpeed == null) {
			if (other.maxSpeed != null) {
				return false;
			}
		} else if (!this.maxSpeed.equals(other.maxSpeed)) {
			return false;
		}
		if (this.midSpeed == null) {
			if (other.midSpeed != null) {
				return false;
			}
		} else if (!this.midSpeed.equals(other.midSpeed)) {
			return false;
		}
		if (this.minSpeed == null) {
			if (other.minSpeed != null) {
				return false;
			}
		} else if (!this.minSpeed.equals(other.minSpeed)) {
			return false;
		}
		if (this.speeds == null) {
			if (other.speeds != null) {
				return false;
			}
		} else if (!this.speeds.equals(other.speeds)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the currentActivityIndex
	 */
	public Integer getCurrentActivityIndex() {
		return this.currentActivityIndex;
	}

	/**
	 * @return the direction
	 */
	public Integer getDirection() {
		return this.direction;
	}

	/**
	 * @return the maxSpeed
	 */
	public Float getMaxSpeed() {
		return this.maxSpeed;
	}

	/**
	 * @return the midSpeed
	 */
	public Float getMidSpeed() {
		return this.midSpeed;
	}

	/**
	 * @return the minSpeed
	 */
	public Float getMinSpeed() {
		return this.minSpeed;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the speeds
	 */
	public List<Float> getSpeeds() {
		return this.speeds;
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
		result = (prime * result) + ((this.currentActivityIndex == null) ? 0 : this.currentActivityIndex.hashCode());
		result = (prime * result) + ((this.direction == null) ? 0 : this.direction.hashCode());
		result = (prime * result) + ((this.maxSpeed == null) ? 0 : this.maxSpeed.hashCode());
		result = (prime * result) + ((this.midSpeed == null) ? 0 : this.midSpeed.hashCode());
		result = (prime * result) + ((this.minSpeed == null) ? 0 : this.minSpeed.hashCode());
		result = (prime * result) + ((this.speeds == null) ? 0 : this.speeds.hashCode());
		return result;
	}

	/**
	 * @param currentActivityIndex
	 *            the currentActivityIndex to set
	 */
	public void setCurrentActivityIndex(Integer currentActivityIndex) {
		this.currentActivityIndex = currentActivityIndex;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @param midSpeed
	 *            the midSpeed to set
	 */
	public void setMidSpeed(Float midSpeed) {
		this.midSpeed = midSpeed;
	}

	/**
	 * @param minSpeed
	 *            the minSpeed to set
	 */
	public void setMinSpeed(Float minSpeed) {
		this.minSpeed = minSpeed;
	}

	/**
	 * @param speeds
	 *            the speeds to set
	 */
	public void setSpeeds(List<Float> speeds) {
		this.speeds = speeds;
	}

	@Override
	public String toString() {
		return "StravaSimilarActivitiesTrend [speeds=" + this.speeds + ", currentActivityIndex=" + this.currentActivityIndex + ", minSpeed=" + this.minSpeed + ", midSpeed=" + this.midSpeed //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", maxSpeed=" + this.maxSpeed //$NON-NLS-1$
				+ ", direction=" + this.direction + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
