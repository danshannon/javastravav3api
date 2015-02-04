package stravajava.api.v3.service.impl.retrofit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;
import stravajava.api.v3.service.StreamServices;

/**
 * @author dshannon
 *
 */
public class StreamServicesImpl implements StreamServices {
	private StreamServicesImpl(String token) {
		this.restService = Retrofit.retrofit(StreamServicesRetrofit.class, token, StreamServicesRetrofit.LOG_LEVEL);
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
	 */
	public static StreamServices implementation(final String token) {
		StreamServices restService = restServices.get(token);
		if (restService == null) {
			restService = new StreamServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,StreamServices> restServices = new HashMap<String,StreamServices>();
	
	private StreamServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.StreamServices#getActivityStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getActivityStreams(String id, StravaStreamType[] types, StravaStreamResolutionType resolution,
			StravaStreamSeriesDownsamplingType seriesType) {
		return Arrays.asList(restService.getActivityStreams(id, types, resolution, seriesType));
	}

	/**
	 * @see stravajava.api.v3.service.StreamServices#getEffortStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getEffortStreams(String id, StravaStreamType[] types, StravaStreamResolutionType resolution,
			StravaStreamSeriesDownsamplingType seriesType) {
		return Arrays.asList(restService.getEffortStreams(id, types, resolution, seriesType));
	}

	/**
	 * @see stravajava.api.v3.service.StreamServices#getSegmentStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(String id, StravaStreamType[] types, StravaStreamResolutionType resolution,
			StravaStreamSeriesDownsamplingType seriesType) {
		return Arrays.asList(restService.getSegmentStreams(id, types, resolution, seriesType));
	}

}
