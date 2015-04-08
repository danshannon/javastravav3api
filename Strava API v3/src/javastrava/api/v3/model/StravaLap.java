package javastrava.api.v3.model;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * A lap within an {@link StravaActivity}
 * 
 * @author Dan Shannon
 *
 */
public class StravaLap {
	/**
	 * Strava's uniique identifier for this lap
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Name of the lap
	 */
	private String name;
	/**
	 * Activity that this lap is part of
	 */
	private StravaActivity activity;
	/**
	 * Athlete who did the activity that this lap is part of
	 */
	private StravaAthlete athlete;
	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Start date and time for this activity
	 */
	private ZonedDateTime startDate;
	/**
	 * Start date and time for this activity, hacked to the time zone where the activity started
	 */
	private LocalDateTime startDateLocal;
	/**
	 * Distance travelled in metres
	 */
	private Float distance;
	/**
	 * Index of the entry in the activity streams that represents the start of the lap
	 */
	private Integer startIndex;
	/**
	 * Index of the entry in the activity streams that represents the end of the lap
	 */
	private Integer endIndex;
	/**
	 * Total elevation gain in metres
	 */
	private Float totalElevationGain;
	/**
	 * Average speed in metres per second
	 */
	private Float averageSpeed;
	/**
	 * Maximum speed in metres per second
	 */
	private Float maxSpeed;
	/**
	 * Average cadence in revolutions per minute (returned only if cadence data was uploaded with the activity). Applies only to rides.
	 */
	private Float averageCadence;
	/**
	 * Average power in watts
	 */
	private Float averageWatts;
	/**
	 * Is set to <code>true</code> if the power was measured using a power meter (i.e. power data was included in the upload)
	 */
	private Boolean deviceWatts;
	/**
	 * Average heart rate in beats per minute
	 */
	private Float averageHeartrate;
	/**
	 * Maximum heart rate in beats per minute
	 */
	private Float maxHeartrate;
	/**
	 * Lap number
	 */
	private Integer lapIndex;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @return the activity
	 */
	public StravaActivity getActivity() {
		return this.activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(final StravaActivity activity) {
		this.activity = activity;
	}
	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
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
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return this.startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(final Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return this.endIndex;
	}
	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(final Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}
	/**
	 * @param totalElevationGain the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}
	/**
	 * @return the averageSpeed
	 */
	public Float getAverageSpeed() {
		return this.averageSpeed;
	}
	/**
	 * @param averageSpeed the averageSpeed to set
	 */
	public void setAverageSpeed(final Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	/**
	 * @return the maxSpeed
	 */
	public Float getMaxSpeed() {
		return this.maxSpeed;
	}
	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(final Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	/**
	 * @return the averageCadence
	 */
	public Float getAverageCadence() {
		return this.averageCadence;
	}
	/**
	 * @param averageCadence the averageCadence to set
	 */
	public void setAverageCadence(final Float averageCadence) {
		this.averageCadence = averageCadence;
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
	 * @return the deviceWatts
	 */
	public Boolean getDeviceWatts() {
		return this.deviceWatts;
	}
	/**
	 * @param deviceWatts the deviceWatts to set
	 */
	public void setDeviceWatts(final Boolean deviceWatts) {
		this.deviceWatts = deviceWatts;
	}
	/**
	 * @return the averageHeartrate
	 */
	public Float getAverageHeartrate() {
		return this.averageHeartrate;
	}
	/**
	 * @param averageHeartrate the averageHeartrate to set
	 */
	public void setAverageHeartrate(final Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}
	/**
	 * @return the maxHeartrate
	 */
	public Float getMaxHeartrate() {
		return this.maxHeartrate;
	}
	/**
	 * @param maxHeartrate the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Float maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}
	/**
	 * @return the lapIndex
	 */
	public Integer getLapIndex() {
		return this.lapIndex;
	}
	/**
	 * @param lapIndex the lapIndex to set
	 */
	public void setLapIndex(final Integer lapIndex) {
		this.lapIndex = lapIndex;
	}
}
