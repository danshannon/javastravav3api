package stravajava.api.v3.service.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.service.SegmentEffortServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.impl.gson.JsonUtilImpl;

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
	 */
	public static SegmentEffortServices implementation(final String token) {
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

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,SegmentEffortServices> restServices = new HashMap<String,SegmentEffortServices>();
	
	private SegmentEffortServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.SegmentEffortServices#getSegmentEffort(java.lang.Integer)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(Long id) throws UnauthorizedException {
		try {
			return restService.getSegmentEffort(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

}
