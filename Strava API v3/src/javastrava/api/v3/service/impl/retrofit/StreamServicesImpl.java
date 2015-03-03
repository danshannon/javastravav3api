package javastrava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.service.StreamServices;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;

/**
 * <p>
 * Implementation of {@link StreamServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StreamServicesImpl extends StravaServiceImpl implements StreamServices {
	/**
	 * <p>
	 * Private constructor prevents anyone from getting an instance without a valid access token
	 * </p>
	 * 
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	private StreamServicesImpl(final Token token) {
		super(token);
		this.restService = Retrofit.retrofit(StreamServicesRetrofit.class, token);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link StreamServices segment effort services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the stream services
	 */
	public static StreamServices implementation(final Token token) {
		StreamServices restService = restServices.get(token);
		if (restService == null) {
			restService = new StreamServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<Token, StreamServices> restServices = new HashMap<Token, StreamServices>();

	private final StreamServicesRetrofit restService;

	/**
	 * @see javastrava.api.v3.service.StreamServices#getActivityStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Integer id, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		StravaStreamType[] typesToGet = types;
		validateArguments(resolution, seriesType, typesToGet);
		if (typesToGet == null || typesToGet.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		List<StravaStream> streams = null;
		try {
			streams = Arrays.asList(this.restService.getActivityStreams(id, typeString(typesToGet), resolution, seriesType));
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
	 * @param resolution
	 * @param seriesType
	 * @param types
	 */
	private void validateArguments(final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		if (resolution == StravaStreamResolutionType.UNKNOWN) {
			throw new IllegalArgumentException("Invalid stream resolution type " + resolution);
		}
		if (seriesType == StravaStreamSeriesDownsamplingType.UNKNOWN) {
			throw new IllegalArgumentException("Invalid stream series downsampling type " + seriesType);
		}
		if (types != null) {
			for (StravaStreamType type : types) {
				if (type == StravaStreamType.UNKNOWN) {
					throw new IllegalArgumentException("Invalid stream type " + type);
				}
			}
		}
	}

	/**
	 * convert array of types to a comma-separated string, that's what Strava expects
	 * 
	 * @param types
	 * @return
	 */
	private String typeString(final StravaStreamType[] types) {
		if (types.length == 0) {
			return null;
		}
		if (types.length == 1) {
			return types[0].toString();
		}
		String typeString = types[0].toString();
		for (int i = 1; i < types.length; i++) {
			typeString = typeString + "," + types[i].toString();
		}
		return typeString;
	}

	/**
	 * @see javastrava.api.v3.service.StreamServices#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long id, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if (types == null || types.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		try {
			return Arrays.asList(this.restService.getEffortStreams(id, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.StreamServices#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, StravaStreamType...)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer id, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		validateArguments(resolution, seriesType, types);
		StravaStreamType[] typesToGet = types;
		if (seriesType == StravaStreamSeriesDownsamplingType.TIME) {
			throw new IllegalArgumentException("Cannot downsample a Segment by TIME");
		}
		if (types == null || types.length == 0) {
			typesToGet = getAllStreamTypes();
		}
		try {
			return Arrays.asList(this.restService.getSegmentStreams(id, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.StreamServices#getActivityStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Integer id) {
		return getActivityStreams(id, null, null, getAllStreamTypes());
	}

	/**
	 * @return
	 */
	private StravaStreamType[] getAllStreamTypes() {
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
	 * @see javastrava.api.v3.service.StreamServices#getEffortStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long id) {
		return getEffortStreams(id, null, null, getAllStreamTypes());
	}

	/**
	 * @see javastrava.api.v3.service.StreamServices#getSegmentStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer id) {
		return getSegmentStreams(id, null, null, getAllStreamTypes());
	}

}
