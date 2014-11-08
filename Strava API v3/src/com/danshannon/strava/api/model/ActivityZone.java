package com.danshannon.strava.api.model;

import com.danshannon.strava.api.model.reference.ActivityZoneType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
public class ActivityZone {
	private Integer score;
	private ActivityZoneDistributionBucket[] distributionBuckets;
	private ActivityZoneType type;
	private ResourceState resourceState;
	private Boolean sensorBased;
	private Integer points;
	private Boolean customZones;
	private Integer max;
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return this.score;
	}
	/**
	 * @return the distributionBuckets
	 */
	public ActivityZoneDistributionBucket[] getDistributionBuckets() {
		return this.distributionBuckets;
	}
	/**
	 * @return the type
	 */
	public ActivityZoneType getType() {
		return this.type;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the sensorBased
	 */
	public Boolean getSensorBased() {
		return this.sensorBased;
	}
	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return this.points;
	}
	/**
	 * @return the customZones
	 */
	public Boolean getCustomZones() {
		return this.customZones;
	}
	/**
	 * @return the max
	 */
	public Integer getMax() {
		return this.max;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * @param distributionBuckets the distributionBuckets to set
	 */
	public void setDistributionBuckets(ActivityZoneDistributionBucket[] distributionBuckets) {
		this.distributionBuckets = distributionBuckets;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ActivityZoneType type) {
		this.type = type;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param sensorBased the sensorBased to set
	 */
	public void setSensorBased(Boolean sensorBased) {
		this.sensorBased = sensorBased;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
	 * @param customZones the customZones to set
	 */
	public void setCustomZones(Boolean customZones) {
		this.customZones = customZones;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}
	
	
}
