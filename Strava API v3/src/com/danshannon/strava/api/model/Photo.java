package com.danshannon.strava.api.model;

import java.util.Calendar;

import com.danshannon.strava.api.model.reference.PhotoType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
public class Photo {
	private Integer id;
	private Integer activityId;
	private ResourceState resourceState;
	private String ref;
	private String uid;
	private String caption;
	private PhotoType type;
	private Calendar uploadedAt;
	private Calendar createdAt;
	private MapPoint location;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return this.ref;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return this.caption;
	}
	/**
	 * @return the type
	 */
	public PhotoType getType() {
		return this.type;
	}
	/**
	 * @return the uploadedAt
	 */
	public Calendar getUploadedAt() {
		return this.uploadedAt;
	}
	/**
	 * @return the createdAt
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}
	/**
	 * @return the location
	 */
	public MapPoint getLocation() {
		return this.location;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(PhotoType type) {
		this.type = type;
	}
	/**
	 * @param uploadedAt the uploadedAt to set
	 */
	public void setUploadedAt(Calendar uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(MapPoint location) {
		this.location = location;
	}
}
