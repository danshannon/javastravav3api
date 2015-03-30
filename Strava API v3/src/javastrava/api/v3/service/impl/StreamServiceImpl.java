package javastrava.api.v3.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.service.ActivityService;
import javastrava.api.v3.service.SegmentEffortService;
import javastrava.api.v3.service.SegmentService;
import javastrava.api.v3.service.StreamService;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.config.Messages;

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
	 * <p>
	 * Private constructor prevents anyone from getting an instance without a valid access token
	 * </p>
	 * 
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	private StreamServiceImpl(final Token token) {
		super(token);
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
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Integer activityId, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		StravaStreamType[] typesToGet = types;
		validateArguments(resolution, seriesType, typesToGet);
		if (typesToGet == null || typesToGet.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		
		// Check the activity
		StravaActivity activity = this.getToken().getService(ActivityService.class).getActivity(activityId);
		
		// If it's null, it doesn't exist, so return null
		if (activity == null) {
			return null;
		}
		
		// If it's private, then don't return the streams, just an empty list
		if (activity.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<StravaStream>();
		}
		
		List<StravaStream> streams = null;
		try {
			streams = Arrays.asList(this.api.getActivityStreams(activityId, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
		
		// TODO This is a workaround for issue javastrava-api #21 (https://github.com/danshannon/javastravav3api/issues/21)
		if (streams != null && resolution == null) {
			for (StravaStream stream : streams) {
				stream.setResolution(null);
			}
		}
		// End of workaround
		
		return streams;
	}

	/**
	 * Validate that what's being asked for from the service makes any sense
	 * 
	 * @param resolution Resolution requested
	 * @param seriesType Downsampling type requested
	 * @param types Stream types requested
	 */
	private static void validateArguments(final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		if (resolution == StravaStreamResolutionType.UNKNOWN) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamResolutionType") + resolution); //$NON-NLS-1$
		}
		if (seriesType == StravaStreamSeriesDownsamplingType.UNKNOWN) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamSeriesDownsamplingType") + seriesType); //$NON-NLS-1$
		}
		if (types != null) {
			for (StravaStreamType type : types) {
				if (type == StravaStreamType.UNKNOWN) {
					throw new IllegalArgumentException(Messages.string("StreamServiceImpl.invalidStreamType") + type); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * convert array of types to a comma-separated string, that's what Strava expects
	 * 
	 * @param types Array of stream types to be requested
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
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if (types == null || types.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		
		// Check that the effort exists and is not private
		StravaSegmentEffort effort = this.getToken().getService(SegmentEffortService.class).getSegmentEffort(effortId);
		
		// If it's null it doesn't exist, so return null
		if (effort == null) {
			return null;
		}
		
		// If its resource state is PRIVATE, then it's private duh
		if (effort.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<StravaStream>();
		}
		
		try {
			return Arrays.asList(this.api.getEffortStreams(effortId, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer segmentId, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if (seriesType == StravaStreamSeriesDownsamplingType.TIME) {
			throw new IllegalArgumentException(Messages.string("StreamServiceImpl.cannotDownsampleSegmentByTime")); //$NON-NLS-1$
		}
		if (types == null || types.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		
		// Check the segment 
		StravaSegment segment = this.getToken().getService(SegmentService.class).getSegment(segmentId);
		
		// If the segment is null, it doesn't exist
		if (segment == null) {
			return null;
		}
		
		// If the segment is PRIVATE, then return an empty list
		if (segment.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<StravaStream>();
		}
		
		try {
			return Arrays.asList(this.api.getSegmentStreams(segmentId, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Integer id) {
		return getActivityStreams(id, null, null, getAllStreamTypes());
	}

	/**
	 * @return List of all valid stream types that can be requested
	 */
	private static StravaStreamType[] getAllStreamTypes() {
		List<StravaStreamType> types = Arrays.asList(StravaStreamType.values());
		List<StravaStreamType> returnList = new ArrayList<StravaStreamType>();
		for (StravaStreamType type : types) {
			if (type != StravaStreamType.UNKNOWN) {
				returnList.add(type);
			}
		}
		return returnList.toArray(new StravaStreamType[returnList.size()]);
	}

	/**
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long id) {
		return getEffortStreams(id, null, null, getAllStreamTypes());
	}

	/**
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer id) {
		return getSegmentStreams(id, null, null, getAllStreamTypes());
	}

}
