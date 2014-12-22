package com.danshannon.strava.api.service.impl.retrofit;

import java.io.File;
import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.UploadResponse;
import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.UploadServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

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
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static UploadServices implementation(final String token) throws UnauthorizedException {
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

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,UploadServices> restServices = new HashMap<String,UploadServices>();
	
	private UploadServicesRetrofit restService;

	/**
	 * @see com.danshannon.strava.api.service.UploadServices#upload(com.danshannon.strava.api.model.reference.ActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public UploadResponse upload(ActivityType activityType, String name, String description, Boolean _private, Boolean trainer,
			String dataType, String externalId, File file) {
		return restService.upload(activityType, name, description, _private, trainer, dataType, externalId, file);
	}

	/**
	 * @see com.danshannon.strava.api.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@Override
	public UploadResponse checkUploadStatus(Integer id) {
		return restService.checkUploadStatus(id);
	}

}
