package stravajava.api.v3.service.impl.retrofit;

import java.util.HashMap;

import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.service.SegmentEffortServices;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffortServicesImpl extends StravaServiceImpl implements SegmentEffortServices {
	private SegmentEffortServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(SegmentEffortServicesRetrofit.class, token, SegmentEffortServicesRetrofit.LOG_LEVEL);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link SegmentEffortServices segment effort services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the segment effort services
	 */
	public static SegmentEffortServices implementation(final String token) {
		SegmentEffortServices restService = restServices.get(token);
		if (restService == null) {
			restService = new SegmentEffortServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<String, SegmentEffortServices> restServices = new HashMap<String, SegmentEffortServices>();

	private final SegmentEffortServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.SegmentEffortServices#getSegmentEffort(java.lang.Integer)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(final Long id) {
		try {
			return this.restService.getSegmentEffort(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				// Private effort
				StravaSegmentEffort effort = new StravaSegmentEffort();
				effort.setId(id);
				return effort;
			} else {
				throw e;
			}
		}
	}

}
