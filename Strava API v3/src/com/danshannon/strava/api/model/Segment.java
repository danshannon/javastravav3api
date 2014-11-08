package com.danshannon.strava.api.model;

import java.util.Calendar;

import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SegmentActivityType;

/**
 * <p>{@link Segment Segments} are specific sections of road. {@link Athlete Athletes’} {@link SegmentEffort} efforts are compared on these segments and leaderboards are created.</p>
 * 
 * @author Dan Shannon
 *
 */
public class Segment {
	private String id;
	private ResourceState resourceState;
	private String name;
	private SegmentActivityType activityType;
	private Float distance;
	private Float averageGrade;
	private Float maximumGrade;
	private Float elevationHigh;
	private Float elevationLow;
	private MapPoint startLatlng;
	private MapPoint endLatlng;
	private ClimbCategory climbCategory;
	private String city;
	private String state;
	private String country;
	private Boolean privateSegment; // is "private" in JSON API
	private Boolean starred; // true if authenticated athlete has starred segment
	private Calendar createdAt;
	private Calendar updatedAt;
	private Float totalElevationGain;
	private Map map;
	private Integer effortCount;
	private Integer athleteCount;
	private Boolean hazardous;
	private Integer starCount;
	private SegmentEffort athletePrEffort; // See {@link SegmentServices#listStarredSegments()}
	/**
	 * @return the id
	 */
	public String getId() {
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
	 * @return the activityType
	 */
	public SegmentActivityType getActivityType() {
		return this.activityType;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @return the averageGrade
	 */
	public Float getAverageGrade() {
		return this.averageGrade;
	}
	/**
	 * @return the maximumGrade
	 */
	public Float getMaximumGrade() {
		return this.maximumGrade;
	}
	/**
	 * @return the elevationHigh
	 */
	public Float getElevationHigh() {
		return this.elevationHigh;
	}
	/**
	 * @return the elevationLow
	 */
	public Float getElevationLow() {
		return this.elevationLow;
	}
	/**
	 * @return the startLatlng
	 */
	public MapPoint getStartLatlng() {
		return this.startLatlng;
	}
	/**
	 * @return the endLatlng
	 */
	public MapPoint getEndLatlng() {
		return this.endLatlng;
	}
	/**
	 * @return the climbCategory
	 */
	public ClimbCategory getClimbCategory() {
		return this.climbCategory;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @param activityType the activityType to set
	 */
	public void setActivityType(SegmentActivityType activityType) {
		this.activityType = activityType;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @param averageGrade the averageGrade to set
	 */
	public void setAverageGrade(Float averageGrade) {
		this.averageGrade = averageGrade;
	}
	/**
	 * @param maximumGrade the maximumGrade to set
	 */
	public void setMaximumGrade(Float maximumGrade) {
		this.maximumGrade = maximumGrade;
	}
	/**
	 * @param elevationHigh the elevationHigh to set
	 */
	public void setElevationHigh(Float elevationHigh) {
		this.elevationHigh = elevationHigh;
	}
	/**
	 * @param elevationLow the elevationLow to set
	 */
	public void setElevationLow(Float elevationLow) {
		this.elevationLow = elevationLow;
	}
	/**
	 * @param startLatlng the startLatlng to set
	 */
	public void setStartLatlng(MapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}
	/**
	 * @param endLatlng the endLatlng to set
	 */
	public void setEndLatlng(MapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}
	/**
	 * @param climbCategory the climbCategory to set
	 */
	public void setClimbCategory(ClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	/**
	 * @return the privateSegment
	 */
	public Boolean getPrivateSegment() {
		return this.privateSegment;
	}
	/**
	 * @return the starred
	 */
	public Boolean getStarred() {
		return this.starred;
	}
	/**
	 * @return the createdAt
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}
	/**
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}
	/**
	 * @return the map
	 */
	public Map getMap() {
		return this.map;
	}
	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}
	/**
	 * @return the athleteCount
	 */
	public Integer getAthleteCount() {
		return this.athleteCount;
	}
	/**
	 * @return the hazardous
	 */
	public Boolean getHazardous() {
		return this.hazardous;
	}
	/**
	 * @return the starCount
	 */
	public Integer getStarCount() {
		return this.starCount;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @param privateSegment the privateSegment to set
	 */
	public void setPrivateSegment(Boolean privateSegment) {
		this.privateSegment = privateSegment;
	}
	/**
	 * @param starred the starred to set
	 */
	public void setStarred(Boolean starred) {
		this.starred = starred;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @param totalElevationGain the totalElevationGain to set
	 */
	public void setTotalElevationGain(Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * @param effortCount the effortCount to set
	 */
	public void setEffortCount(Integer effortCount) {
		this.effortCount = effortCount;
	}
	/**
	 * @param athleteCount the athleteCount to set
	 */
	public void setAthleteCount(Integer athleteCount) {
		this.athleteCount = athleteCount;
	}
	/**
	 * @param hazardous the hazardous to set
	 */
	public void setHazardous(Boolean hazardous) {
		this.hazardous = hazardous;
	}
	/**
	 * @param starCount the starCount to set
	 */
	public void setStarCount(Integer starCount) {
		this.starCount = starCount;
	}
	/**
	 * @return the athletePrEffort
	 */
	public SegmentEffort getAthletePrEffort() {
		return this.athletePrEffort;
	}
	/**
	 * @param athletePrEffort the athletePrEffort to set
	 */
	public void setAthletePrEffort(SegmentEffort athletePrEffort) {
		this.athletePrEffort = athletePrEffort;
	}
	
}
