package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;
import stravajava.api.v3.model.StravaUploadResponse;
import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.service.exception.BadRequestException;

/**
 * @author Dan Shannon
 *
 */
public interface UploadServicesRetrofit {
	public static final RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.BASIC;


	/**
	 * @see stravajava.api.v3.service.UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Multipart
	@POST("/uploads")
	public StravaUploadResponse upload(@Part("activity_type") StravaActivityType activityType, @Part("name") String name, @Part("description") String description, @Part("private") Boolean _private, @Part("trainer") Boolean trainer,
			@Part("data_type") String dataType, @Part("external_id") String externalId, @Part("file") TypedFile file) throws BadRequestException;

	/**
	 * @see stravajava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@GET("/uploads/{id}")
	public StravaUploadResponse checkUploadStatus(@Path("id") Integer id);

}
