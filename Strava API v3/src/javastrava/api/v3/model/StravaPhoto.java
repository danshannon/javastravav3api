package javastrava.api.v3.model;

import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaPhotoType;
import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaPhoto {
	/**
	 * Strava's unique identifier for the photo
	 */
	private Integer				id;
	/**
	 * Identifier of the activity to which the photo is attached
	 */
	private Integer				activityId;
	/**
	 * Current state of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * External link to the image
	 */
	private String				ref;
	/**
	 * Unique identifier specified by the source
	 */
	private String				uid;
	/**
	 * Caption
	 */
	private String				caption;
	/**
	 * Photo's source, currently only Instagram
	 */
	private StravaPhotoType		type;
	/**
	 * Date and time the photo was uploaded
	 */
	private ZonedDateTime				uploadedAt;
	/**
	 * Date and time the photo was linked with Strava
	 */
	private ZonedDateTime				createdAt;
	/**
	 * Location of the photo
	 */
	private StravaMapPoint		location;
	
	private Integer source;
	private StravaPhotoUrls urls;
	private String uniqueId;
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
	 * @return the ref
	 */
	public String getRef() {
		return this.ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(final String ref) {
		this.ref = ref;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(final String uid) {
		this.uid = uid;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return this.caption;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(final String caption) {
		this.caption = caption;
	}
	/**
	 * @return the type
	 */
	public StravaPhotoType getType() {
		return this.type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final StravaPhotoType type) {
		this.type = type;
	}
	/**
	 * @return the uploadedAt
	 */
	public ZonedDateTime getUploadedAt() {
		return this.uploadedAt;
	}
	/**
	 * @param uploadedAt the uploadedAt to set
	 */
	public void setUploadedAt(final ZonedDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
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
	/**
	 * @return the location
	 */
	public StravaMapPoint getLocation() {
		return this.location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(final StravaMapPoint location) {
		this.location = location;
	}
	/**
	 * @return the source
	 */
	public Integer getSource() {
		return this.source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(final Integer source) {
		this.source = source;
	}
	/**
	 * @return the urls
	 */
	public StravaPhotoUrls getUrls() {
		return this.urls;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(final StravaPhotoUrls urls) {
		this.urls = urls;
	}
	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return this.uniqueId;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(final String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
