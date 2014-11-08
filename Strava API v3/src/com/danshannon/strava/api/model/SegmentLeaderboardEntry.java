package com.danshannon.strava.api.model;

import java.util.Calendar;

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
	private Calendar startDate;
	private Calendar startDateLocal;
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
	public Calendar getStartDate() {
		return this.startDate;
	}
	/**
	 * @return the startDateLocal
	 */
	public Calendar getStartDateLocal() {
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
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param startDateLocal the startDateLocal to set
	 */
	public void setStartDateLocal(Calendar startDateLocal) {
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
}
