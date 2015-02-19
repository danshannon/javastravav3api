package javastrava.api.v3.model;

import javastrava.api.v3.service.UploadServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Response to an upload request
 * </p>
 * 
 * @see UploadServices#upload(javastrava.api.v3.model.reference.StravaActivityType, String, String, Boolean, Boolean, String, String, java.io.File)
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
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
}
