package stravajava.api.v3.service.impl.retrofit;

import java.io.File;
import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.model.StravaUploadResponse;
import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.UploadServices;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class UploadServicesImpl implements UploadServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private UploadServicesImpl(UploadServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
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
			restService = new UploadServicesImpl(new RestAdapter.Builder()
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(LOG_LEVEL)
				.setEndpoint(Strava.ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(UploadServicesRetrofit.class));

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,UploadServices> restServices = new HashMap<String,UploadServices>();
	
	private UploadServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.UploadServices#upload(stravajava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public StravaUploadResponse upload(StravaActivityType activityType, String name, String description, Boolean _private, Boolean trainer,
			String dataType, String externalId, File file) {
		return restService.upload(activityType, name, description, _private, trainer, dataType, externalId, file);
	}

	/**
	 * @see stravajava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@Override
	public StravaUploadResponse checkUploadStatus(Integer id) {
		return restService.checkUploadStatus(id);
	}

}
