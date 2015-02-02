package stravajava.api.v3.model;

import stravajava.api.v3.service.UploadServices;
import lombok.Data;

/**
 * <p>Response to an {@link UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, String, String, Boolean, Boolean, stravajava.api.v3.service.athlete.UploadDataType, String, java.io.File) upload request}</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaUploadResponse {
	public StravaUploadResponse() {
		// Required
		super();
	}
	
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
	 * <li>Your activity is still being processed.</li>
	 * <li>The created activity has been deleted.</li>
	 * <li>There was an error processing your activity.</li>
	 * <li>Your activity is ready.</li>
	 * </ul>
	 */
	private String status;
	private Integer activityId;
}
