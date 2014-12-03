package com.danshannon.strava.api.model;

import com.danshannon.strava.api.service.UploadServices;

/**
 * <p>Response to an {@link UploadServices#upload(com.danshannon.strava.api.model.reference.ActivityType, String, String, Boolean, Boolean, com.danshannon.strava.api.service.athlete.UploadDataType, String, java.io.File) upload request}</p>
 * 
 * @author Dan Shannon
 *
 */
public class UploadResponse {
	/**
	 * 
	 */
	private Integer id;
	private String externalId;
	/**
	 * <p>if there was an error during processing, this will contain a human a human readable error message that may include escaped HTML</p>
	 */
	private String error;
	/**
	 * <p>describes the error, possible values:</p>
	 * <ul>
	 * <li>‘Your activity is still being processed.’,</li>
	 * <li>‘The created activity has been deleted.’,</li>
	 * <li>‘There was an error processing your activity.’,</li>
	 * <li>‘Your activity is ready.’</li>
	 * </ul>
	 */
	private String status;
	private Integer activityId;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return this.externalId;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UploadResponse [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.externalId != null ? "externalId=" + this.externalId + ", " : "")
				+ (this.error != null ? "error=" + this.error + ", " : "")
				+ (this.status != null ? "status=" + this.status + ", " : "")
				+ (this.activityId != null ? "activityId=" + this.activityId : "") + "]";
	}
}
