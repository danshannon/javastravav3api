package javastrava.model;

import javastrava.model.reference.StravaResourceState;
import javastrava.service.UploadService;

/**
 * <p>
 * Response to an upload request
 * </p>
 *
 * @see UploadService#upload(javastrava.model.reference.StravaActivityType, String, String, Boolean, Boolean, Boolean, String, String, java.io.File)
 * @author Dan Shannon
 *
 */
public class StravaUploadResponse implements StravaEntity {
	/**
	 * Unique identifier of the upload
	 */
	private Long	id;
	/**
	 * Unique identifier for the activity as given by the uploading application
	 */
	private String	externalId;
	/**
	 * <p>
	 * if there was an error during processing, this will contain a human a human readable error message that may include escaped HTML
	 * </p>
	 */
	private String	error;
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
	private String	status;
	/**
	 * Unique identifier of the activity, if it was created as part of the upload process.
	 */
	private Long	activityId;

	/**
	 * No args constructor
	 */
	public StravaUploadResponse() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaUploadResponse)) {
			return false;
		}
		final StravaUploadResponse other = (StravaUploadResponse) obj;
		if (this.activityId == null) {
			if (other.activityId != null) {
				return false;
			}
		} else if (!this.activityId.equals(other.activityId)) {
			return false;
		}
		if (this.error == null) {
			if (other.error != null) {
				return false;
			}
		} else if (!this.error.equals(other.error)) {
			return false;
		}
		if (this.externalId == null) {
			if (other.externalId != null) {
				return false;
			}
		} else if (!this.externalId.equals(other.externalId)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!this.status.equals(other.status)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return this.activityId;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return this.externalId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activityId == null) ? 0 : this.activityId.hashCode());
		result = (prime * result) + ((this.error == null) ? 0 : this.error.hashCode());
		result = (prime * result) + ((this.externalId == null) ? 0 : this.externalId.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
		return result;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(final Long activityId) {
		this.activityId = activityId;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * @param externalId
	 *            the externalId to set
	 */
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaUploadResponse [id=" + this.id + ", externalId=" + this.externalId + ", error=" + this.error + ", status=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.status + ", activityId=" + this.activityId + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
