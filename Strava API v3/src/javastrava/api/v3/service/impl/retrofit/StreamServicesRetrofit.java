package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.service.StreamServices;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * Retrofit definitions for {@link StreamServices} endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface StreamServicesRetrofit {
	public static RestAdapter.LogLevel	LOG_LEVEL	= RestAdapter.LogLevel.FULL;

	/**
	 * @see javastrava.api.v3.service.StreamServices#getActivityStreams(Integer, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 * 
	 * @param id
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams/{types}")
	public StravaStream[] getActivityStreams(@Path("id") final Integer id, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamServices#getEffortStreams(Long, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 * 
	 * @param id
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public StravaStream[] getEffortStreams(@Path("id") final Long id, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamServices#getSegmentStreams(Integer, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 * 
	 * @param id
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public StravaStream[] getSegmentStreams(@Path("id") final Integer id, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

}
