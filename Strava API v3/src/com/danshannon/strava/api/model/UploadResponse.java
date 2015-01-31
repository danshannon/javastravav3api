package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.service.UploadServices;

/**
 * <p>Response to an {@link UploadServices#upload(com.danshannon.strava.api.model.reference.ActivityType, String, String, Boolean, Boolean, com.danshannon.strava.api.service.athlete.UploadDataType, String, java.io.File) upload request}</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class UploadResponse {
	public UploadResponse() {
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
