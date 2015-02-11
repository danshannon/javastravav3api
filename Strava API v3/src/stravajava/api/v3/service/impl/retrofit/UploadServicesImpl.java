package stravajava.api.v3.service.impl.retrofit;

import java.io.File;
import java.util.HashMap;

import retrofit.mime.TypedFile;
import stravajava.api.v3.model.StravaUploadResponse;
import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.service.UploadServices;
import stravajava.api.v3.service.exception.BadRequestException;

/**
 * @author Dan Shannon
 *
 */
public class UploadServicesImpl extends StravaServiceImpl implements UploadServices {
	
	private UploadServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(UploadServicesRetrofit.class,token,UploadServicesRetrofit.LOG_LEVEL);
	}
	
	/**
	 * <p>Returns an implementation of {@link UploadServices segment effort services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the upload services
	 */
	public static UploadServices implementation(final String token) {
		UploadServices restService = restServices.get(token);
		if (restService == null) {
			restService = new UploadServicesImpl(token);
			restServices.put(token, restService);
		}
		return restService;
	}
	
	private static HashMap<String,UploadServices> restServices = new HashMap<String,UploadServices>();
	
	private final UploadServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer,
			final String dataType, final String externalId, final File file) {
		if (file == null) { 
			throw new IllegalArgumentException("Cannot upload a <null> file!");
		}
		try {
			return this.restService.upload(activityType, name, description, _private, trainer, dataType, externalId, new TypedFile("text/xml",file));
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see stravajava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@Override
	public StravaUploadResponse checkUploadStatus(final Integer id) {
		return this.restService.checkUploadStatus(id);
	}

}
