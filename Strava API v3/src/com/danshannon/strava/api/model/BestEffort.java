package com.danshannon.strava.api.model;

import java.util.Date;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
public class BestEffort {
	private Integer id;
	private ResourceState resourceState;
	private String name;
	private Segment segment;
	private Activity activity;
	private Athlete athlete;
	private Integer komRank;
	private Integer prRank;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the segment
	 */
	public Segment getSegment() {
		return this.segment;
	}
	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return this.activity;
	}
	/**
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @return the komRank
	 */
	public Integer getKomRank() {
		return this.komRank;
	}
	/**
	 * @return the prRank
	 */
	public Integer getPrRank() {
		return this.prRank;
	}
	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}
	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	/**
	 * @return the startDateLocal
	 */
	public Date getStartDateLocal() {
		return this.startDateLocal;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param segment the segment to set
	 */
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	/**
	 * @param komRank the komRank to set
	 */
	public void setKomRank(Integer komRank) {
		this.komRank = komRank;
	}
	/**
	 * @param prRank the prRank to set
	 */
	public void setPrRank(Integer prRank) {
		this.prRank = prRank;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param startDateLocal the startDateLocal to set
	 */
	public void setStartDateLocal(Date startDateLocal) {
		this.startDateLocal = startDateLocal;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BestEffort [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.segment != null ? "segment=" + this.segment + ", " : "")
				+ (this.activity != null ? "activity=" + this.activity + ", " : "")
				+ (this.athlete != null ? "athlete=" + this.athlete + ", " : "")
				+ (this.komRank != null ? "komRank=" + this.komRank + ", " : "")
				+ (this.prRank != null ? "prRank=" + this.prRank + ", " : "")
				+ (this.elapsedTime != null ? "elapsedTime=" + this.elapsedTime + ", " : "")
				+ (this.movingTime != null ? "movingTime=" + this.movingTime + ", " : "")
				+ (this.startDate != null ? "startDate=" + this.startDate + ", " : "")
				+ (this.startDateLocal != null ? "startDateLocal=" + this.startDateLocal + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance : "") + "]";
	}
	
}
