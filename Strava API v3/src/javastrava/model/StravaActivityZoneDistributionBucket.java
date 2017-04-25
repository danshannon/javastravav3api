package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * @author Dan Shannon
 *
 */
public class StravaActivityZoneDistributionBucket implements StravaEntity {
	/**
	 * Maximum value of heart rate or power for this zone. Note that this returns as -1 if the maximum is in fact infinity
	 */
	private Integer	max;

	/**
	 * Minimum value of heart rate or power for this zone
	 */
	private Integer	min;

	/**
	 * Total time in seconds spent in this zone
	 */
	private Integer	time;

	/**
	 *
	 */
	public StravaActivityZoneDistributionBucket() {
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
		if (!(obj instanceof StravaActivityZoneDistributionBucket)) {
			return false;
		}
		final StravaActivityZoneDistributionBucket other = (StravaActivityZoneDistributionBucket) obj;
		if (this.max == null) {
			if (other.max != null) {
				return false;
			}
		} else if (!this.max.equals(other.max)) {
			return false;
		}
		if (this.min == null) {
			if (other.min != null) {
				return false;
			}
		} else if (!this.min.equals(other.min)) {
			return false;
		}
		if (this.time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!this.time.equals(other.time)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return this.max;
	}

	/**
	 * @return the min
	 */
	public Integer getMin() {
		return this.min;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}
	/**
	 * @return the time
	 */
	public Integer getTime() {
		return this.time;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.max == null) ? 0 : this.max.hashCode());
		result = (prime * result) + ((this.min == null) ? 0 : this.min.hashCode());
		result = (prime * result) + ((this.time == null) ? 0 : this.time.hashCode());
		return result;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(final Integer max) {
		this.max = max;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(final Integer min) {
		this.min = min;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(final Integer time) {
		this.time = time;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivityZoneDistributionBucket [max=" + this.max + ", min=" + this.min + ", time=" + this.time + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
