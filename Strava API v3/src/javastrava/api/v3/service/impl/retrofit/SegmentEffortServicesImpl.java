package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.SegmentEffortServices;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffortServicesImpl extends StravaServiceImpl<SegmentEffortServicesRetrofit> implements SegmentEffortServices {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is by using {@link #implementation(String)} with a valid access token.
	 * </p>
	 * 
	 * @param token The access token to be used for authentication to the Strava API
	 */
	private SegmentEffortServicesImpl(final Token token) {
		super(SegmentEffortServicesRetrofit.class, token);
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
	public static SegmentEffortServices implementation(final Token token) {
		// Get the service from the token's cache
		SegmentEffortServices service = token.getService(SegmentEffortServices.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new SegmentEffortServicesImpl(token);
			token.addService(SegmentEffortServices.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentEffortServices#getSegmentEffort(Long)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(final Long id) {
		StravaSegmentEffort effort = null;
		try {
			effort = this.restService.getSegmentEffort(id);
		} catch (NotFoundException e) {
			// Segment effort doesn't exist
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				// Private effort
				effort = new StravaSegmentEffort();
				effort.setId(id);
				effort.setResourceState(StravaResourceState.META);
				return effort;
			} else {
				// Token broken
				throw e;
			}
		}
		
		// TODO This is a workaround for issue javastrava-api #26 (https://github.com/danshannon/javastravav3api/issues/26)
		if (effort != null && effort.getActivity() != null && effort.getActivity().getResourceState() == null) {
			effort.getActivity().setResourceState(StravaResourceState.META);
		}
		// End of workaround
		
		// TODO This is a workaround for issue javastrava-api #27 (https://github.com/danshannon/javastravav3api/issues/27)
		if (effort != null && effort.getAthlete() != null && effort.getAthlete().getResourceState() == null) {
			effort.getAthlete().setResourceState(StravaResourceState.META);
		}
		
		return effort;
	}

}
