package com.danshannon.strava.api.model;

import java.util.Date;

import com.danshannon.strava.api.model.reference.Gender;

/**
 * A single entry in a {@link SegmentLeaderboard}
 * 
 * @author Dan Shannon
 *
 */
public class SegmentLeaderboardEntry {
	private String athleteName;
	private Integer athleteId;
	private Gender athleteGender;
	private Float averageHr;
	private Float averageWatts;
	private Float distance;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Integer activityId;
	private Integer effortId;
	private Integer rank;
	private String athleteProfile;
	/**
	 * @return the athleteName
	 */
	public String getAthleteName() {
		return this.athleteName;
	}
	/**
	 * @return the athleteId
	 */
	public Integer getAthleteId() {
		return this.athleteId;
	}
	/**
	 * @return the athleteGender
	 */
	public Gender getAthleteGender() {
		return this.athleteGender;
	}
	/**
	 * @return the averageHr
	 */
	public Float getAverageHr() {
		return this.averageHr;
	}
	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
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
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @return the effortId
	 */
	public Integer getEffortId() {
		return this.effortId;
	}
	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}
	/**
	 * @return the athleteProfile
	 */
	public String getAthleteProfile() {
		return this.athleteProfile;
	}
	/**
	 * @param athleteName the athleteName to set
	 */
	public void setAthleteName(String athleteName) {
		this.athleteName = athleteName;
	}
	/**
	 * @param athleteId the athleteId to set
	 */
	public void setAthleteId(Integer athleteId) {
		this.athleteId = athleteId;
	}
	/**
	 * @param athleteGender the athleteGender to set
	 */
	public void setAthleteGender(Gender athleteGender) {
		this.athleteGender = athleteGender;
	}
	/**
	 * @param averageHr the averageHr to set
	 */
	public void setAverageHr(Float averageHr) {
		this.averageHr = averageHr;
	}
	/**
	 * @param averageWatts the averageWatts to set
	 */
	public void setAverageWatts(Float averageWatts) {
		this.averageWatts = averageWatts;
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
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @param effortId the effortId to set
	 */
	public void setEffortId(Integer effortId) {
		this.effortId = effortId;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**
	 * @param athleteProfile the athleteProfile to set
	 */
	public void setAthleteProfile(String athleteProfile) {
		this.athleteProfile = athleteProfile;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentLeaderboardEntry [" + (this.athleteName != null ? "athleteName=" + this.athleteName + ", " : "")
				+ (this.athleteId != null ? "athleteId=" + this.athleteId + ", " : "")
				+ (this.athleteGender != null ? "athleteGender=" + this.athleteGender + ", " : "")
				+ (this.averageHr != null ? "averageHr=" + this.averageHr + ", " : "")
				+ (this.averageWatts != null ? "averageWatts=" + this.averageWatts + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance + ", " : "")
				+ (this.elapsedTime != null ? "elapsedTime=" + this.elapsedTime + ", " : "")
				+ (this.movingTime != null ? "movingTime=" + this.movingTime + ", " : "")
				+ (this.startDate != null ? "startDate=" + this.startDate + ", " : "")
				+ (this.startDateLocal != null ? "startDateLocal=" + this.startDateLocal + ", " : "")
				+ (this.activityId != null ? "activityId=" + this.activityId + ", " : "")
				+ (this.effortId != null ? "effortId=" + this.effortId + ", " : "")
				+ (this.rank != null ? "rank=" + this.rank + ", " : "")
				+ (this.athleteProfile != null ? "athleteProfile=" + this.athleteProfile : "") + "]";
	}
}
