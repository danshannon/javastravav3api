package com.danshannon.strava.api.model;

/**
 * @author Dan Shannon
 *
 */
public class ActivityZoneDistributionBucket {
	private Integer max;
	private Integer min;
	private Integer time;
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
	/**
	 * @return the time
	 */
	public Integer getTime() {
		return this.time;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(Integer min) {
		this.min = min;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Integer time) {
		this.time = time;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityZoneDistributionBucket [" + (this.max != null ? "max=" + this.max + ", " : "")
				+ (this.min != null ? "min=" + this.min + ", " : "") + (this.time != null ? "time=" + this.time : "") + "]";
	}
}
