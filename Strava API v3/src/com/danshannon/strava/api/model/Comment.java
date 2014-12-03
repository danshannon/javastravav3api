package com.danshannon.strava.api.model;

import java.util.Date;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
public class Comment {
	private Integer id;
	private ResourceState resourceState;
	private Integer activityId;
	private String text;
	private Athlete athlete;
	private Date createdAt;
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
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}
	/**
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return this.createdAt;
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
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.activityId != null ? "activityId=" + this.activityId + ", " : "")
				+ (this.text != null ? "text=" + this.text + ", " : "")
				+ (this.athlete != null ? "athlete=" + this.athlete + ", " : "")
				+ (this.createdAt != null ? "createdAt=" + this.createdAt : "") + "]";
	}
}
