package javastrava.api.v3.model;

import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Record of an individual comment made on an activity
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaComment {
	/**
	 * Strava's unique identifier for the comment
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Identifier of the activity on which this is a comment
	 */
	private Integer activityId;
	/**
	 * Text of the comment. Allegedly Strava supports markdown in comments!
	 */
	private String text;
	/**
	 * Athlete who MADE the comment
	 */
	private StravaAthlete athlete;
	/**
	 * Date and time the comment was posted
	 */
	private ZonedDateTime createdAt;
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
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(final String text) {
		this.text = text;
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
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
