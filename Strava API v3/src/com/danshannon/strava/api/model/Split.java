package com.danshannon.strava.api.model;

/**
 * @author Dan Shannon
 *
 */
public class Split {
	private Float distance;
	private Integer elapsedTime;
	private Float elevationDifference;
	private Integer movingTime;
	private Integer split;
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
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @param elevationDifference the elevationDifference to set
	 */
	public void setElevationDifference(Float elevationDifference) {
		this.elevationDifference = elevationDifference;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @param split the split to set
	 */
	public void setSplit(Integer split) {
		this.split = split;
	}
	
	
}
