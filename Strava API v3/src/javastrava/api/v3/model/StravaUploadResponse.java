package javastrava.api.v3.model;

import javastrava.api.v3.service.UploadService;

/**
 * <p>
 * Response to an upload request
 * </p>
 * 
 * @see UploadService#upload(javastrava.api.v3.model.reference.StravaActivityType, String, String, Boolean, Boolean, String, String, java.io.File)
 * @author Dan Shannon
 *
 */
public class StravaUploadResponse {
	/**
	 * Unique identifier of the upload
	 */
	private Integer id;
	/**
	 * Unique identifier for the activity as given by the uploading application
	 */
	private String externalId;
	/**
	 * <p>
	 * if there was an error during processing, this will contain a human a human readable error message that may include escaped HTML
	 * </p>
	 */
	private String error;
	/**
	 * <p>
	 * describes the error, possible values:
	 * </p>
	 * <ul>
	 * <li>Your activity is still being processed.</li>
	 * <li>The created activity has been deleted.</li>
	 * <li>There was an error processing your activity.</li>
	 * <li>Your activity is ready.</li>
	 * </ul>
	 */
	private String status;
	/**
	 * Unique identifier of the activity, if it was created as part of the upload process.
	 */
	private Integer activityId;
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
	 * @return the externalId
	 */
	public String getExternalId() {
		return this.externalId;
	}
	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
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
}
