package com.danshannon.strava.api.model;

import java.util.Date;

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
	private Date uploadedAt;
	private Date createdAt;
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
	public Date getUploadedAt() {
		return this.uploadedAt;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
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
	public void setUploadedAt(Date uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(MapPoint location) {
		this.location = location;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Photo [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.activityId != null ? "activityId=" + this.activityId + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.ref != null ? "ref=" + this.ref + ", " : "") + (this.uid != null ? "uid=" + this.uid + ", " : "")
				+ (this.caption != null ? "caption=" + this.caption + ", " : "")
				+ (this.type != null ? "type=" + this.type + ", " : "")
				+ (this.uploadedAt != null ? "uploadedAt=" + this.uploadedAt + ", " : "")
				+ (this.createdAt != null ? "createdAt=" + this.createdAt + ", " : "")
				+ (this.location != null ? "location=" + this.location : "") + "]";
	}
}
