package com.danshannon.strava.api.service.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.Stream;
import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.StreamServices;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class StreamServicesImpl implements StreamServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private StreamServicesImpl(StreamServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
	 * <p>Returns an implementation of {@link StreamServices segment effort services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the stream services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static StreamServices implementation(final String token) throws UnauthorizedException {
		StreamServices restService = restServices.get(token);
		if (restService == null) {
			restService = new StreamServicesImpl(new RestAdapter.Builder()
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
				.create(StreamServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,StreamServices> restServices = new HashMap<String,StreamServices>();
	
	private StreamServicesRetrofit restService;

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getActivityStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@Override
	public Stream[] getActivityStreams(String id, StreamType[] types, StreamResolutionType resolution,
			StreamSeriesDownsamplingType seriesType) {
		return restService.getActivityStreams(id, types, resolution, seriesType);
	}

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getEffortStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@Override
	public Stream[] getEffortStreams(String id, StreamType[] types, StreamResolutionType resolution,
			StreamSeriesDownsamplingType seriesType) {
		return restService.getEffortStreams(id, types, resolution, seriesType);
	}

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getSegmentStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@Override
	public Stream[] getSegmentStreams(String id, StreamType[] types, StreamResolutionType resolution,
			StreamSeriesDownsamplingType seriesType) {
		return restService.getSegmentStreams(id, types, resolution, seriesType);
	}

}
