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
import stravajava.api.v3.service.UploadServices;
import stravajava.api.v3.service.exception.BadRequestException;

/**
 * <p>
 * Retrofit definitions for {@link UploadServices} endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface UploadServicesRetrofit {
	public static final RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see stravajava.api.v3.service.UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String,
	 *      java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Multipart
	@POST("/uploads")
	public StravaUploadResponse upload(@Part("activity_type") final StravaActivityType activityType, @Part("name") final String name,
			@Part("description") final String description, @Part("private") final Boolean _private, @Part("trainer") final Boolean trainer, @Part("data_type") final String dataType,
			@Part("external_id") final String externalId, @Part("file") final TypedFile file) throws BadRequestException;

	/**
	 * @see stravajava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@GET("/uploads/{id}")
	public StravaUploadResponse checkUploadStatus(@Path("id") final Integer id);

}
