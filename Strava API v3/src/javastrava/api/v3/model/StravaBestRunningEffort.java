package javastrava.api.v3.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * A 'best running effort' - calculated by Strava for runs only. Returned as part of activity details.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaBestRunningEffort {
	/**
	 * Strava's unique identifier for this running effort
	 */
	private Integer id;
	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Name of this effort (Best 1k, etc.)
	 */
	private String name;
	/**
	 * Strava segment associated with this best effort (if there is one)
	 */
	private StravaSegment segment;
	/**
	 * Strava activity associated with this best effort
	 */
	private StravaActivity activity;
	/**
	 * Strava athlete associated with this run
	 */
	private StravaAthlete athlete;
	/**
	 * KOM rank for the effort
	 */
	private Integer komRank;
	/**
	 * Personal record ranking for this effort
	 */
	private Integer prRank;
	/**
	 * elapsed time in seconds (includes time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * moving time in seconds (excludes time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * date and time that the effort started
	 */
	private ZonedDateTime startDate;
	/**
	 * local start date for the effort (i.e. in the timezone that it started, shifted to UTC)
	 */
	private LocalDateTime startDateLocal;
	/**
	 * distance in metres
	 */
	private Float distance;
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
}
