package javastrava.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javastrava.auth.model.Token;
import javastrava.config.Messages;
import javastrava.model.StravaActivity;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaStream;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaStreamType;
import javastrava.service.ActivityService;
import javastrava.service.SegmentEffortService;
import javastrava.service.SegmentService;
import javastrava.service.StreamService;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Implementation of {@link StreamService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StreamServiceImpl extends StravaServiceImpl implements StreamService {
	/**
	 * @return List of all valid stream types that can be requested
	 */
	private static StravaStreamType[] getAllStreamTypes() {
		final List<StravaStreamType> types = Arrays.asList(StravaStreamType.values());
		final List<StravaStreamType> returnList = types.stream().filter(type -> type != StravaStreamType.UNKNOWN).collect(Collectors.toList());
		return returnList.toArray(new StravaStreamType[returnList.size()]);
	}

	/**
	 * <p>
	 * Returns an instance of {@link StreamService segment effort services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the stream services
	 */
	public static StreamService instance(final Token token) {
		// Get the service from the token's cache
		StreamService service = token.getService(StreamService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new StreamServiceImpl(token);
			token.addService(StreamService.class, service);
		}
		return service;
	}

	/**
	 * convert array of types to a comma-separated string, that's what Strava expects
	 *
	 * @param types
	 *            Array of stream types to be requested
	 * @return Types as a comma-separated string
	 */
	private static String typeString(final StravaStreamType[] types) {
		if (types.length == 0) {
			return null;
		}
		if (types.length == 1) {
			return types[0].toString();
		}
		String typeString = types[0].toString();
		for (int i = 1; i < types.length; i++) {
			typeString = typeString + "," + types[i].toString(); //$NON-NLS-1$
		}
		return typeString;
	}

	/**
	 * Validate that what's being asked for from the service makes any sense
	 *
	 * @param resolution
	 *            Resolution requested
	 * @param seriesType
	 *            Downsampling type requested
	 * @param types
	 *            Stream types requested
	 */
	private static void validateArguments(final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		if (resolution == StravaStreamResolutionType.UNKNOWN) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamResolutionType") + resolution); //$NON-NLS-1$
		}
		if (seriesType == StravaStreamSeriesDownsamplingType.UNKNOWN) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamSeriesDownsamplingType") + seriesType); //$NON-NLS-1$
		}
		if (types != null) {
			for (final StravaStreamType type : types) {
				if (type == StravaStreamType.UNKNOWN) {
					throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamType") + type); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * <p>
	 * Private constructor prevents anyone from getting an instance without a valid access token
	 * </p>
	 *
	 * @param token
	 *            The access token to be used to authenticate to the Strava API
	 */
	private StreamServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		// Nothing to do - there is no cache
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Long activityId) {
		return getActivityStreams(activityId, null, null, getAllStreamTypes());
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		StravaStreamType[] typesToGet = types;
		validateArguments(resolution, seriesType, typesToGet);
		if ((typesToGet == null) || (typesToGet.length == 0)) {
			typesToGet = getAllStreamTypes();
		}

		// Check the activity
		final StravaActivity activity = this.getToken().getService(ActivityService.class).getActivity(activityId);

		// If it's null, it doesn't exist, so return null
		if (activity == null) {
			return null;
		}

		// If it's private, then don't return the streams, just an empty list
		if (activity.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<>();
		}

		List<StravaStream> streams;
		try {
			streams = Arrays.asList(this.api.getActivityStreams(activityId, typeString(typesToGet), resolution, seriesType));
		} catch (final NotFoundException e) {
			return null;
		} catch (final BadRequestException e) {
			throw new IllegalArgumentException(e);
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaStream>();
		}

		// TODO This is a workaround for issue javastrava-api #21
		// (https://github.com/danshannon/javastravav3api/issues/21)
		if (resolution == null) {
			for (final StravaStream stream : streams) {
				stream.setResolution(null);
			}
		}
		// End of workaround

		return streams;
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreamsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId) {
		return StravaServiceImpl.future(() -> getActivityStreams(activityId));
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreamsAsync(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return StravaServiceImpl.future(() -> getActivityStreams(activityId, resolution, seriesType, types));
	}

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long effortId) {
		return getEffortStreams(effortId, null, null, getAllStreamTypes());
	}

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if ((types == null) || (types.length == 0)) {
			typesToGet = getAllStreamTypes();
		}

		// Check that the effort exists and is not private
		final StravaSegmentEffort effort = this.getToken().getService(SegmentEffortService.class).getSegmentEffort(effortId);

		// If it's null it doesn't exist, so return null
		if (effort == null) {
			return null;
		}

		// If its resource state is PRIVATE, then it's private duh
		if (effort.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<>();
		}

		try {
			return Arrays.asList(this.api.getEffortStreams(effortId, typeString(typesToGet), resolution, seriesType));
		} catch (final NotFoundException e) {
			return null;
		} catch (final BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.service.StreamService#getEffortStreamsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId) {
		return StravaServiceImpl.future(() -> getEffortStreams(effortId));
	}

	/**
	 * @see javastrava.service.StreamService#getEffortStreamsAsync(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return StravaServiceImpl.future(() -> getEffortStreams(effortId, resolution, seriesType, types));
	}

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer segmentId) {
		return getSegmentStreams(segmentId, null, null, getAllStreamTypes());
	}

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if (seriesType == StravaStreamSeriesDownsamplingType.TIME) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.cannotDownsampleSegmentByTime")); //$NON-NLS-1$
		}
		if ((types == null) || (types.length == 0)) {
			typesToGet = getAllStreamTypes();
		}

		// Check the segment
		final StravaSegment segment = this.getToken().getService(SegmentService.class).getSegment(segmentId);

		// If the segment is null, it doesn't exist
		if (segment == null) {
			return null;
		}

		// If the segment is PRIVATE, then return an empty list
		if (segment.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<>();
		}

		try {
			return Arrays.asList(this.api.getSegmentStreams(segmentId, typeString(typesToGet), resolution, seriesType));
		} catch (final NotFoundException e) {
			return null;
		} catch (final BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.service.StreamService#getSegmentStreamsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> getSegmentStreams(segmentId));
	}

	/**
	 * @see javastrava.service.StreamService#getSegmentStreamsAsync(java.lang.Integer, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return StravaServiceImpl.future(() -> getSegmentStreams(segmentId, resolution, seriesType, types));
	}

}
