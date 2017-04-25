package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * UNDOCUMENTED
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSimilarActivities implements StravaEntity {

	/**
	 * UNDOCUMENTED
	 */
	private Integer effortCount;

	/**
	 * UNDOCUMENTED
	 */
	private Float averageSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Float minAverageSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Float midAverageSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Float maxAverageSpeed;

	/**
	 * UNDOCUMENTED
	 */
	private Integer prRank;

	/**
	 * UNDOCUMENTED
	 */
	private String frequencyMilestone;

	/**
	 * UNDOCUMENTED
	 */
	private StravaSimilarActivitiesTrend trend;

	/**
	 * UNDOCUMENTED
	 */
	private StravaResourceState resourceState;

	/**
	 * No-args constructor
	 */
	public StravaSimilarActivities() {
		// Empty
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
		if (!(obj instanceof StravaSimilarActivities)) {
			return false;
		}
		final StravaSimilarActivities other = (StravaSimilarActivities) obj;
		if (this.averageSpeed == null) {
			if (other.averageSpeed != null) {
				return false;
			}
		} else if (!this.averageSpeed.equals(other.averageSpeed)) {
			return false;
		}
		if (this.effortCount == null) {
			if (other.effortCount != null) {
				return false;
			}
		} else if (!this.effortCount.equals(other.effortCount)) {
			return false;
		}
		if (this.frequencyMilestone == null) {
			if (other.frequencyMilestone != null) {
				return false;
			}
		} else if (!this.frequencyMilestone.equals(other.frequencyMilestone)) {
			return false;
		}
		if (this.maxAverageSpeed == null) {
			if (other.maxAverageSpeed != null) {
				return false;
			}
		} else if (!this.maxAverageSpeed.equals(other.maxAverageSpeed)) {
			return false;
		}
		if (this.midAverageSpeed == null) {
			if (other.midAverageSpeed != null) {
				return false;
			}
		} else if (!this.midAverageSpeed.equals(other.midAverageSpeed)) {
			return false;
		}
		if (this.minAverageSpeed == null) {
			if (other.minAverageSpeed != null) {
				return false;
			}
		} else if (!this.minAverageSpeed.equals(other.minAverageSpeed)) {
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
		if (this.trend == null) {
			if (other.trend != null) {
				return false;
			}
		} else if (!this.trend.equals(other.trend)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the averageSpeed
	 */
	public Float getAverageSpeed() {
		return this.averageSpeed;
	}

	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}

	/**
	 * @return the frequencyMilestone
	 */
	public String getFrequencyMilestone() {
		return this.frequencyMilestone;
	}

	/**
	 * @return the maxAverageSpeed
	 */
	public Float getMaxAverageSpeed() {
		return this.maxAverageSpeed;
	}

	/**
	 * @return the midAverageSpeed
	 */
	public Float getMidAverageSpeed() {
		return this.midAverageSpeed;
	}

	/**
	 * @return the minAverageSpeed
	 */
	public Float getMinAverageSpeed() {
		return this.minAverageSpeed;
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
	 * @return the trend
	 */
	public StravaSimilarActivitiesTrend getTrend() {
		return this.trend;
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
		result = (prime * result) + ((this.averageSpeed == null) ? 0 : this.averageSpeed.hashCode());
		result = (prime * result) + ((this.effortCount == null) ? 0 : this.effortCount.hashCode());
		result = (prime * result) + ((this.frequencyMilestone == null) ? 0 : this.frequencyMilestone.hashCode());
		result = (prime * result) + ((this.maxAverageSpeed == null) ? 0 : this.maxAverageSpeed.hashCode());
		result = (prime * result) + ((this.midAverageSpeed == null) ? 0 : this.midAverageSpeed.hashCode());
		result = (prime * result) + ((this.minAverageSpeed == null) ? 0 : this.minAverageSpeed.hashCode());
		result = (prime * result) + ((this.prRank == null) ? 0 : this.prRank.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.trend == null) ? 0 : this.trend.hashCode());
		return result;
	}

	/**
	 * @param averageSpeed
	 *            the averageSpeed to set
	 */
	public void setAverageSpeed(Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	/**
	 * @param effortCount
	 *            the effortCount to set
	 */
	public void setEffortCount(Integer effortCount) {
		this.effortCount = effortCount;
	}

	/**
	 * @param frequencyMilestone
	 *            the frequencyMilestone to set
	 */
	public void setFrequencyMilestone(String frequencyMilestone) {
		this.frequencyMilestone = frequencyMilestone;
	}

	/**
	 * @param maxAverageSpeed
	 *            the maxAverageSpeed to set
	 */
	public void setMaxAverageSpeed(Float maxAverageSpeed) {
		this.maxAverageSpeed = maxAverageSpeed;
	}

	/**
	 * @param midAverageSpeed
	 *            the midAverageSpeed to set
	 */
	public void setMidAverageSpeed(Float midAverageSpeed) {
		this.midAverageSpeed = midAverageSpeed;
	}

	/**
	 * @param minAverageSpeed
	 *            the minAverageSpeed to set
	 */
	public void setMinAverageSpeed(Float minAverageSpeed) {
		this.minAverageSpeed = minAverageSpeed;
	}

	/**
	 * @param prRank
	 *            the prRank to set
	 */
	public void setPrRank(Integer prRank) {
		this.prRank = prRank;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param trend
	 *            the trend to set
	 */
	public void setTrend(StravaSimilarActivitiesTrend trend) {
		this.trend = trend;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaSimilarActivities [effortCount=" + this.effortCount + ", averageSpeed=" + this.averageSpeed + ", minAverageSpeed=" + this.minAverageSpeed + ", midAverageSpeed=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.midAverageSpeed + ", maxAverageSpeed=" + this.maxAverageSpeed + ", prRank=" + this.prRank + ", frequencyMilestone=" + this.frequencyMilestone + ", trend=" + this.trend //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", resourceState=" //$NON-NLS-1$
				+ this.resourceState + "]"; //$NON-NLS-1$
	}

}
