package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaStream;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.service.StreamService;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.client.Response;
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
	 * @see javastrava.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams/{types}")
	public StravaStream[] getActivityStreams(@Path("id") final Long activityId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams/{types}")
	public void getActivityStreams(@Path("id") final Long activityId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams/{types}")
	public Response getActivityStreamsRaw(@Path("id") final Long activityId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public StravaStream[] getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public void getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public Response getEffortStreamsRaw(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @return Array of Streams. distance, altitude and latlng stream types are always returned.
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public StravaStream[] getRouteStreams(@Path("id") final Integer id) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public void getRouteStreams(@Path("id") final Integer id, final StravaAPICallback<StravaStream[]> callback) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @return Array of Streams. distance, altitude and latlng stream types are always returned.
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public Response getRouteStreamsRaw(@Path("id") final Integer id) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public StravaStream[] getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public void getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public Response getSegmentStreamsRaw(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;
}
