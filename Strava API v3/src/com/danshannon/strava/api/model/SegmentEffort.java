package com.danshannon.strava.api.model;

import java.util.Date;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffort {
	private Long id;
	private ResourceState resourceState;
	private String name;
	private Activity activity;
	private Athlete athlete;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
	private Integer startIndex;
	private Integer endIndex;
	private Float averageCadence;
	private Float averageWatts;
	private Float averageHeartrate;
	private Integer maxHeartrate;
	private Segment segment;
	private Integer komRank;
	private Integer prRank;
	private Boolean hidden;
	private Boolean isKom; // see {@link SegmentServices#starredSegments()}
	/**
	 * @return the id
	 */
	public Long getId() {
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
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return this.startIndex;
	}
	/**
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return this.endIndex;
	}
	/**
	 * @return the averageCadence
	 */
	public Float getAverageCadence() {
		return this.averageCadence;
	}
	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
	}
	/**
	 * @return the averageHeartrate
	 */
	public Float getAverageHeartrate() {
		return this.averageHeartrate;
	}
	/**
	 * @return the maxHeartrate
	 */
	public Integer getMaxHeartrate() {
		return this.maxHeartrate;
	}
	/**
	 * @return the segment
	 */
	public Segment getSegment() {
		return this.segment;
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
	 * @return the hidden
	 */
	public Boolean getHidden() {
		return this.hidden;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**
	 * @param averageCadence the averageCadence to set
	 */
	public void setAverageCadence(Float averageCadence) {
		this.averageCadence = averageCadence;
	}
	/**
	 * @param averageWatts the averageWatts to set
	 */
	public void setAverageWatts(Float averageWatts) {
		this.averageWatts = averageWatts;
	}
	/**
	 * @param averageHeartrate the averageHeartrate to set
	 */
	public void setAverageHeartrate(Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}
	/**
	 * @param maxHeartrate the maxHeartrate to set
	 */
	public void setMaxHeartrate(Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}
	/**
	 * @param segment the segment to set
	 */
	public void setSegment(Segment segment) {
		this.segment = segment;
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
	 * @param hidden the hidden to set
	 */
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	/**
	 * @return the isKom
	 */
	public Boolean getIsKom() {
		return this.isKom;
	}
	/**
	 * @param isKom the isKom to set
	 */
	public void setIsKom(Boolean isKom) {
		this.isKom = isKom;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentEffort [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.activity != null ? "activity=" + this.activity + ", " : "")
				+ (this.athlete != null ? "athlete=" + this.athlete + ", " : "")
				+ (this.elapsedTime != null ? "elapsedTime=" + this.elapsedTime + ", " : "")
				+ (this.movingTime != null ? "movingTime=" + this.movingTime + ", " : "")
				+ (this.startDate != null ? "startDate=" + this.startDate + ", " : "")
				+ (this.startDateLocal != null ? "startDateLocal=" + this.startDateLocal + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance + ", " : "")
				+ (this.startIndex != null ? "startIndex=" + this.startIndex + ", " : "")
				+ (this.endIndex != null ? "endIndex=" + this.endIndex + ", " : "")
				+ (this.averageCadence != null ? "averageCadence=" + this.averageCadence + ", " : "")
				+ (this.averageWatts != null ? "averageWatts=" + this.averageWatts + ", " : "")
				+ (this.averageHeartrate != null ? "averageHeartrate=" + this.averageHeartrate + ", " : "")
				+ (this.maxHeartrate != null ? "maxHeartrate=" + this.maxHeartrate + ", " : "")
				+ (this.segment != null ? "segment=" + this.segment + ", " : "")
				+ (this.komRank != null ? "komRank=" + this.komRank + ", " : "")
				+ (this.prRank != null ? "prRank=" + this.prRank + ", " : "")
				+ (this.hidden != null ? "hidden=" + this.hidden + ", " : "") + (this.isKom != null ? "isKom=" + this.isKom : "")
				+ "]";
	}
}
