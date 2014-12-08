package com.danshannon.strava.api.service.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.service.SegmentEffortServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffortServicesImpl implements SegmentEffortServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private SegmentEffortServicesImpl(SegmentEffortServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
	 * <p>Returns an implementation of {@link SegmentEffortServices segment effort services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the segment effort services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static SegmentEffortServices implementation(String token) throws UnauthorizedException {
		SegmentEffortServices restService = restServices.get(token);
		if (restService == null) {
			restService = new SegmentEffortServicesImpl(new RestAdapter.Builder()
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
				.create(SegmentEffortServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,SegmentEffortServices> restServices = new HashMap<String,SegmentEffortServices>();
	
	private SegmentEffortServicesRetrofit restService;

	/**
	 * @see com.danshannon.strava.api.service.SegmentEffortServices#getSegmentEffort(java.lang.Integer)
	 */
	@Override
	public SegmentEffort getSegmentEffort(Integer id) {
		try {
			return restService.getSegmentEffort(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

}
