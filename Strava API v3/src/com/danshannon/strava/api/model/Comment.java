package com.danshannon.strava.api.model;

import java.util.Calendar;

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
	private Calendar createdAt;
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
	public Calendar getCreatedAt() {
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
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
}
