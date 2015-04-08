package javastrava.api.v3.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.SegmentService;

/**
 * <p>
 * A segment effort represents an athlete's attempt at a segment. It can also be thought of as a portion of a ride that covers a
 * segment. The object is returned in summary or detailed representations. They are currently the same.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaSegmentEffort {
	/**
	 * Strava's unique identifier for this segment effort
	 */
	private Long				id;
	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * Name of the segment
	 */
	private String				name;
	/**
	 * Activity - this instance will contain the id of the activity only
	 */
	private StravaActivity		activity;
	/**
	 * Athlete - this instance will contain the id of the athlete only
	 */
	private StravaAthlete		athlete;
	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer				elapsedTime;
	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer				movingTime;
	/**
	 * Start date and time for this effort
	 */
	private ZonedDateTime				startDate;
	/**
	 * Start date and time for this effort, hacked to local time zone at the start
	 */
	private LocalDateTime				startDateLocal;
	/**
	 *  the length im metres of the effort as described by the activity, this may be different than the length of the segment
	 */
	private Float				distance;
	/**
	 * the activity stream index of the start of this effort
	 */
	private Integer				startIndex;
	/**
	 * the activity stream index of the end of this effort
	 */
	private Integer				endIndex;
	/**
	 * Average cadence in revolutions per minute, if cadence data was provided with the activity upload
	 */
	private Float				averageCadence;
	/**
	 * Average watts (rides only)
	 */
	private Float				averageWatts;
	/**
	 * Average heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Float				averageHeartrate;
	/**
	 * Maximum heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Integer				maxHeartrate;
	/**
	 * {@link StravaResourceState#SUMMARY} representation of the segment
	 */
	private StravaSegment		segment;
	/**
	 * 1-10 rank on segment at time of upload
	 */
	private Integer				komRank;
	/**
	 * 1-3 personal record on segment at time of upload
	 */
	private Integer				prRank;
	/**
	 * indicates a hidden/non-important effort when returned as part of an activity, value may change over time, see retrieve an activity for more details
	 */
	private Boolean				hidden;
	/**
	 * @see SegmentService#listStarredSegments(Integer)
	 */
	private Boolean				isKom;
	
	/**
	 * Summary of achievements on this effort
	 */
	private List<StravaAchievement> achievements;
	/**
	 * Summary of athlete's statistics on this segment
	 */
	private StravaAthleteSegmentStats athleteSegmentStats;
	
	private Boolean deviceWatts;

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
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
	public Integer getMaxHeartrate() {
		return this.maxHeartrate;
	}

	/**
	 * @param maxHeartrate the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}

	/**
	 * @return the segment
	 */
	public StravaSegment getSegment() {
		return this.segment;
	}

	/**
	 * @param segment the segment to set
	 */
	public void setSegment(final StravaSegment segment) {
		this.segment = segment;
	}

	/**
	 * @return the komRank
	 */
	public Integer getKomRank() {
		return this.komRank;
	}

	/**
	 * @param komRank the komRank to set
	 */
	public void setKomRank(final Integer komRank) {
		this.komRank = komRank;
	}

	/**
	 * @return the prRank
	 */
	public Integer getPrRank() {
		return this.prRank;
	}

	/**
	 * @param prRank the prRank to set
	 */
	public void setPrRank(final Integer prRank) {
		this.prRank = prRank;
	}

	/**
	 * @return the hidden
	 */
	public Boolean getHidden() {
		return this.hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(final Boolean hidden) {
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
	public void setIsKom(final Boolean isKom) {
		this.isKom = isKom;
	}

	/**
	 * @return the achievements
	 */
	public List<StravaAchievement> getAchievements() {
		return this.achievements;
	}

	/**
	 * @param achievements the achievements to set
	 */
	public void setAchievements(final List<StravaAchievement> achievements) {
		this.achievements = achievements;
	}

	/**
	 * @return the athleteSegmentStats
	 */
	public StravaAthleteSegmentStats getAthleteSegmentStats() {
		return this.athleteSegmentStats;
	}

	/**
	 * @param athleteSegmentStats the athleteSegmentStats to set
	 */
	public void setAthleteSegmentStats(final StravaAthleteSegmentStats athleteSegmentStats) {
		this.athleteSegmentStats = athleteSegmentStats;
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
}
