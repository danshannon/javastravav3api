package javastrava.api.v3.service.impl;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.SegmentEffortService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.util.PrivacyUtils;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffortServiceImpl extends StravaServiceImpl implements SegmentEffortService {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is by
	 * using {@link #instance(Token)} with a valid access token.
	 * </p>
	 *
	 * @param token
	 *            The access token to be used for authentication to the Strava
	 *            API
	 */
	private SegmentEffortServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link SegmentEffortService segment effort
	 * services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token,
	 * the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava
	 *            API
	 * @return An instance of the segment effort services
	 */
	public static SegmentEffortService instance(final Token token) {
		// Get the service from the token's cache
		SegmentEffortService service = token.getService(SegmentEffortService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new SegmentEffortServiceImpl(token);
			token.addService(SegmentEffortService.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentEffortService#getSegmentEffort(Long)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(final Long id) {
		StravaSegmentEffort effort = null;
		try {
			effort = api.getSegmentEffort(id);
		} catch (final NotFoundException e) {
			// Segment effort doesn't exist
			return null;
		} catch (final UnauthorizedException e) {
			return PrivacyUtils.privateSegmentEffort(id);
		}

		// TODO This is a workaround for issue javastrava-api #26
		// (https://github.com/danshannon/javastravav3api/issues/26)
		if (effort != null && effort.getActivity() != null && effort.getActivity().getResourceState() == null) {
			effort.getActivity().setResourceState(StravaResourceState.META);
		}
		// End of workaround

		return effort;
	}

}
