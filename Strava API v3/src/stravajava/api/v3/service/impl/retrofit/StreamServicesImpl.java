package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;
import stravajava.api.v3.service.StreamServices;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;

/**
 * @author dshannon
 *
 */
public class StreamServicesImpl extends StravaServiceImpl implements StreamServices {
	private StreamServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(StreamServicesRetrofit.class, token, StreamServicesRetrofit.LOG_LEVEL);
	}
	
	/**
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
	
	private final StreamServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.StreamServices#getActivityStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Integer id, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
	    StravaStreamType[] typesToGet = types;
		validateArguments(resolution,seriesType,typesToGet);
		if (typesToGet == null || typesToGet.length == 0) { typesToGet = getAllStreamTypes(); }
		try {
			return Arrays.asList(this.restService.getActivityStreams(id, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
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
	 * @see stravajava.api.v3.service.StreamServices#getEffortStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long id, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		validateArguments(resolution,seriesType,types);
		StravaStreamType[] typesToGet = types;
		if (types == null || types.length == 0) { typesToGet = getAllStreamTypes(); }
		try {
			return Arrays.asList(this.restService.getEffortStreams(id, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see stravajava.api.v3.service.StreamServices#getSegmentStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer id, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType, final StravaStreamType... types) {
		validateArguments(resolution,seriesType,types);
		StravaStreamType[] typesToGet = types;
		if (seriesType == StravaStreamSeriesDownsamplingType.TIME) {
			throw new IllegalArgumentException("Cannot downsample a Segment by TIME");
		}
		if (types == null || types.length == 0) { typesToGet = getAllStreamTypes(); }
		try {
			return Arrays.asList(this.restService.getSegmentStreams(id, typeString(typesToGet), resolution, seriesType));
		} catch (NotFoundException e) {
			return null;
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see stravajava.api.v3.service.StreamServices#getActivityStreams(java.lang.Integer)
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

	@Override
	public List<StravaStream> getEffortStreams(final Long id) {
		return getEffortStreams(id, null, null, getAllStreamTypes());
	}

	@Override
	public List<StravaStream> getSegmentStreams(final Integer id) {
		return getSegmentStreams(id, null, null, getAllStreamTypes());
	}

}
