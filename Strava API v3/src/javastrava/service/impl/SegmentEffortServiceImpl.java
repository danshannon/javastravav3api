package javastrava.service.impl;

import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.SegmentEffortService;
import javastrava.service.SegmentService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.PrivacyUtils;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffortServiceImpl extends StravaServiceImpl implements SegmentEffortService {
	/**
	 * <p>
	 * Returns an instance of {@link SegmentEffortService segment effort services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
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
	 * Cache of segment efforts
	 */
	private final StravaCache<StravaSegmentEffort, Long> effortCache;

	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is by using {@link #instance(Token)} with a valid access token.
	 * </p>
	 *
	 * @param token
	 *            The access token to be used for authentication to the Strava API
	 */
	private SegmentEffortServiceImpl(final Token token) {
		super(token);
		this.effortCache = new StravaCacheImpl<StravaSegmentEffort, Long>(StravaSegmentEffort.class, token);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		this.effortCache.removeAll();
	}

	/**
	 * @see javastrava.service.SegmentEffortService#getSegmentEffort(Long)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId) {
		// If id is null, return null
		if (segmentEffortId == null) {
			return null;
		}

		// Try to get the effort from cache
		StravaSegmentEffort effort = this.effortCache.get(segmentEffortId);
		if ((effort != null) && (effort.getResourceState() != StravaResourceState.META)) {
			return effort;
		}

		// If it wasn't in cache, get it from the API
		try {
			effort = this.api.getSegmentEffort(segmentEffortId);
		} catch (final NotFoundException e) {
			// Segment effort doesn't exist
			return null;
		} catch (final UnauthorizedException e) {
			effort = PrivacyUtils.privateSegmentEffort(segmentEffortId);
		}

		// TODO This is a workaround for issue javastrava-api #78
		// See https://github.com/danshannon/javastravav3api/issues/78
		if (effort.getResourceState() == StravaResourceState.DETAILED) {
			final StravaSegment segment = this.getToken().getService(SegmentService.class).getSegment(effort.getSegment().getId());
			if (segment.getResourceState() == StravaResourceState.PRIVATE) {
				effort = PrivacyUtils.privateSegmentEffort(segmentEffortId);
			}
		}
		// End of workaround

		// Put the effort into cache and return it
		this.effortCache.put(effort);
		return effort;
	}

	/**
	 * @see javastrava.service.SegmentEffortService#getSegmentEffortAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<StravaSegmentEffort> getSegmentEffortAsync(final Long segmentEffortId) {
		return StravaServiceImpl.future(() -> {
			return getSegmentEffort(segmentEffortId);
		});
	}

}
