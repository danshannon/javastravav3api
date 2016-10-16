package javastrava.api.v3.rest;

import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.service.StreamService;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definitions for {@link StreamService} endpoints
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface StreamAPI {
	/**
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param activityId
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
	public StravaStream[] getActivityStreams(@Path("id") final Long activityId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @param callback The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams/{types}")
	public void getActivityStreams(@Path("id") final Long activityId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
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
	public StravaStream[] getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @param callback The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public void getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
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
	public StravaStream[] getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException,
			NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType,
	 *      StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @param callback The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public void getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types,
			@Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback) throws UnauthorizedException,
			NotFoundException, BadRequestException;

}
