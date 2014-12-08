package com.danshannon.strava.api.service.impl.retrofit;

import java.io.File;

import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;

import com.danshannon.strava.api.model.UploadResponse;
import com.danshannon.strava.api.model.reference.ActivityType;

/**
 * @author Dan Shannon
 *
 */
public interface UploadServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.UploadServices#upload(com.danshannon.strava.api.model.reference.ActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Multipart
	@POST("/uploads")
	public UploadResponse upload(@Field("activity_type") ActivityType activityType, @Field("name") String name, @Field("description") String description, @Field("private") Boolean _private, @Field("trainer") Boolean trainer,
			@Field("data_type") String dataType, @Field("external_id") String externalId, @Part("file") File file);

	/**
	 * @see com.danshannon.strava.api.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@GET("/uploads/{id}")
	public UploadResponse checkUploadStatus(@Path("id") Integer id);

}
