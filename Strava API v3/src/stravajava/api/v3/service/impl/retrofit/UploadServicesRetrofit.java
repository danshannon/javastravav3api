package stravajava.api.v3.service.impl.retrofit;

import java.io.File;

import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import stravajava.api.v3.model.StravaUploadResponse;
import stravajava.api.v3.model.reference.StravaActivityType;

/**
 * @author Dan Shannon
 *
 */
public interface UploadServicesRetrofit {

	/**
	 * @see stravajava.api.v3.service.UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Multipart
	@POST("/uploads")
	public StravaUploadResponse upload(@Field("activity_type") StravaActivityType activityType, @Field("name") String name, @Field("description") String description, @Field("private") Boolean _private, @Field("trainer") Boolean trainer,
			@Field("data_type") String dataType, @Field("external_id") String externalId, @Part("file") File file);

	/**
	 * @see stravajava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@GET("/uploads/{id}")
	public StravaUploadResponse checkUploadStatus(@Path("id") Integer id);

}
