package javastrava.api.v3.model;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaGender;

/**
 * <p>
 * A single entry in a {@link StravaSegmentLeaderboard}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaSegmentLeaderboardEntry {
	/**
	 * Name of the athlete
	 */
	private String athleteName;
	/**
	 * Strava's unique identifier for the athlete
	 */
	private Integer athleteId;
	/**
	 * Athlete's gender
	 */
	private StravaGender athleteGender;
	/**
	 * Average heart rate (in beats per minute) for the effort associated with the leaderboard entry, if data was provided with the upload
	 */
	private Float averageHr;
	/**
	 * Average power (in watts) for the effort associated with this leaderboard entry
	 */
	private Float averageWatts;
	/**
	 * Total distance of the effort in metres
	 */
	private Float distance;
	/**
	 * Elapsed time for the effort in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * Moving time for the effort in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Start date and time for the effort
	 */
	private ZonedDateTime startDate;
	/**
	 * Start date and time for the effort, hacked into the local timezone
	 */
	private LocalDateTime startDateLocal;
	/**
	 * Identifier of the {@link StravaActivity activity} associated with this entry
	 */
	private Integer activityId;
	/**
	 * Identifier of the {@link StravaSegmentEffort effort} associated with this entry
	 */
	private Long effortId;
	/**
	 * Overall rank of this entry
	 */
	private Integer rank;
	/**
	 * URL of the athlete's profile picture
	 */
	private String athleteProfile;
	/**
	 * Indicates which grouping the entry is in (overall leaderboard, or the athlete extract)
	 */
	private Integer neighborhoodIndex;
	/**
	 * @return the athleteName
	 */
	public String getAthleteName() {
		return this.athleteName;
	}
	/**
	 * @param athleteName the athleteName to set
	 */
	public void setAthleteName(final String athleteName) {
		this.athleteName = athleteName;
	}
	/**
	 * @return the athleteId
	 */
	public Integer getAthleteId() {
		return this.athleteId;
	}
	/**
	 * @param athleteId the athleteId to set
	 */
	public void setAthleteId(final Integer athleteId) {
		this.athleteId = athleteId;
	}
	/**
	 * @return the athleteGender
	 */
	public StravaGender getAthleteGender() {
		return this.athleteGender;
	}
	/**
	 * @param athleteGender the athleteGender to set
	 */
	public void setAthleteGender(final StravaGender athleteGender) {
		this.athleteGender = athleteGender;
	}
	/**
	 * @return the averageHr
	 */
	public Float getAverageHr() {
		return this.averageHr;
	}
	/**
	 * @param averageHr the averageHr to set
	 */
	public void setAverageHr(final Float averageHr) {
		this.averageHr = averageHr;
	}
	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
	}
	/**
	 * @param averageWatts the averageWatts to set
	 */
	public void setAverageWatts(final Float averageWatts) {
		this.averageWatts = averageWatts;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}
	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @return the startDate
	 */
	public ZonedDateTime getStartDate() {
		return this.startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final ZonedDateTime startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the startDateLocal
	 */
	public LocalDateTime getStartDateLocal() {
		return this.startDateLocal;
	}
	/**
	 * @param startDateLocal the startDateLocal to set
	 */
	public void setStartDateLocal(final LocalDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}
	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(final Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return the effortId
	 */
	public Long getEffortId() {
		return this.effortId;
	}
	/**
	 * @param effortId the effortId to set
	 */
	public void setEffortId(final Long effortId) {
		this.effortId = effortId;
	}
	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(final Integer rank) {
		this.rank = rank;
	}
	/**
	 * @return the athleteProfile
	 */
	public String getAthleteProfile() {
		return this.athleteProfile;
	}
	/**
	 * @param athleteProfile the athleteProfile to set
	 */
	public void setAthleteProfile(final String athleteProfile) {
		this.athleteProfile = athleteProfile;
	}
	/**
	 * @return the neighborhoodIndex
	 */
	public Integer getNeighborhoodIndex() {
		return this.neighborhoodIndex;
	}
	/**
	 * @param neighborhoodIndex the neighborhoodIndex to set
	 */
	public void setNeighborhoodIndex(final Integer neighborhoodIndex) {
		this.neighborhoodIndex = neighborhoodIndex;
	}
}
