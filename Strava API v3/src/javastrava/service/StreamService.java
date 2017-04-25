package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaActivity;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaStream;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaStreamType;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Streams is the Strava term for the raw data associated with an activity. All streams for a given activity or segment effort will be the same length and the
 * values at a given index correspond to the same time.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface StreamService extends StravaService {
	/**
	 * <p>
	 * Streams represent the raw data of the uploaded file. External applications may only access this information for activities owned by the authenticated
	 * athlete.
	 * </p>
	 *
	 * <p>
	 * While there are a large number of {@link StravaStreamType stream types}, they may not exist for all activities. If a stream type does not exist for the
	 * activity, it will be ignored.
	 * </p>
	 *
	 * <p>
	 * All streams for a given activity will be the same length and the values at a given index correspond to the same time. For example, the time from the time
	 * stream can be correlated to the lat/lng or watts streams.
	 * </p>
	 *
	 * <p>
	 * Privacy Zones
	 * </p>
	 *
	 * <p>
	 * StravaStream requests made using a public access_token will be cropped with the user's privacy zones, regardless if the requesting athlete owns the
	 * requested activity. To fetch the complete stream data use an access_token with view_private permissions.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#activity">http://strava.github.io/api/v3/streams/#activity</a>
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 */
	public List<StravaStream> getActivityStreams(final Long activityId);

	/**
	 * <p>
	 * Streams represent the raw data of the uploaded file. External applications may only access this information for activities owned by the authenticated
	 * athlete.
	 * </p>
	 *
	 * <p>
	 * While there are a large number of {@link StravaStreamType stream types}, they may not exist for all activities. If a stream type does not exist for the
	 * activity, it will be ignored.
	 * </p>
	 *
	 * <p>
	 * All streams for a given activity will be the same length and the values at a given index correspond to the same time. For example, the time from the time
	 * stream can be correlated to the lat/lng or watts streams.
	 * </p>
	 *
	 * <p>
	 * Privacy Zones
	 * </p>
	 *
	 * <p>
	 * StravaStream requests made using a public access_token will be cropped with the user's privacy zones, regardless if the requesting athlete owns the
	 * requested activity. To fetch the complete stream data use an access_token with view_private permissions.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#activity">http://strava.github.io/api/v3/streams/#activity</a>
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 */
	public List<StravaStream> getActivityStreams(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

	/**
	 * <p>
	 * Streams represent the raw data of the uploaded file. External applications may only access this information for activities owned by the authenticated
	 * athlete.
	 * </p>
	 *
	 * <p>
	 * While there are a large number of {@link StravaStreamType stream types}, they may not exist for all activities. If a stream type does not exist for the
	 * activity, it will be ignored.
	 * </p>
	 *
	 * <p>
	 * All streams for a given activity will be the same length and the values at a given index correspond to the same time. For example, the time from the time
	 * stream can be correlated to the lat/lng or watts streams.
	 * </p>
	 *
	 * <p>
	 * Privacy Zones
	 * </p>
	 *
	 * <p>
	 * StravaStream requests made using a public access_token will be cropped with the user's privacy zones, regardless if the requesting athlete owns the
	 * requested activity. To fetch the complete stream data use an access_token with view_private permissions.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#activity">http://strava.github.io/api/v3/streams/#activity</a>
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 */
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId);

	/**
	 * <p>
	 * Streams represent the raw data of the uploaded file. External applications may only access this information for activities owned by the authenticated
	 * athlete.
	 * </p>
	 *
	 * <p>
	 * While there are a large number of {@link StravaStreamType stream types}, they may not exist for all activities. If a stream type does not exist for the
	 * activity, it will be ignored.
	 * </p>
	 *
	 * <p>
	 * All streams for a given activity will be the same length and the values at a given index correspond to the same time. For example, the time from the time
	 * stream can be correlated to the lat/lng or watts streams.
	 * </p>
	 *
	 * <p>
	 * Privacy Zones
	 * </p>
	 *
	 * <p>
	 * StravaStream requests made using a public access_token will be cropped with the user's privacy zones, regardless if the requesting athlete owns the
	 * requested activity. To fetch the complete stream data use an access_token with view_private permissions.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#activity">http://strava.github.io/api/v3/streams/#activity</a>
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 */
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

	/**
	 * <p>
	 * A {@link StravaSegmentEffort segment effort} represents an attempt on a {@link StravaSegment segment}. This resource returns a subset of the
	 * {@link StravaActivity activity} streams that correspond to that effort.
	 * </p>
	 *
	 * <p>
	 * All streams for a given segment effort will be the same length and the values at a given index correspond to the same time.
	 * </p>
	 *
	 * <p>
	 * This resource is available for all public efforts.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#effort">http://strava.github.io/api/v3/streams/#effort</a>
	 *
	 * @param effortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects.
	 */
	public List<StravaStream> getEffortStreams(final Long effortId);

	/**
	 * <p>
	 * A {@link StravaSegmentEffort segment effort} represents an attempt on a {@link StravaSegment segment}. This resource returns a subset of the
	 * {@link StravaActivity activity} streams that correspond to that effort.
	 * </p>
	 *
	 * <p>
	 * All streams for a given segment effort will be the same length and the values at a given index correspond to the same time.
	 * </p>
	 *
	 * <p>
	 * This resource is available for all public efforts.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#effort">http://strava.github.io/api/v3/streams/#effort</a>
	 *
	 * @param effortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public List<StravaStream> getEffortStreams(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

	/**
	 * <p>
	 * A {@link StravaSegmentEffort segment effort} represents an attempt on a {@link StravaSegment segment}. This resource returns a subset of the
	 * {@link StravaActivity activity} streams that correspond to that effort.
	 * </p>
	 *
	 * <p>
	 * All streams for a given segment effort will be the same length and the values at a given index correspond to the same time.
	 * </p>
	 *
	 * <p>
	 * This resource is available for all public efforts.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#effort">http://strava.github.io/api/v3/streams/#effort</a>
	 *
	 * @param effortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects.
	 */
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId);

	/**
	 * <p>
	 * A {@link StravaSegmentEffort segment effort} represents an attempt on a {@link StravaSegment segment}. This resource returns a subset of the
	 * {@link StravaActivity activity} streams that correspond to that effort.
	 * </p>
	 *
	 * <p>
	 * All streams for a given segment effort will be the same length and the values at a given index correspond to the same time.
	 * </p>
	 *
	 * <p>
	 * This resource is available for all public efforts.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#effort">http://strava.github.io/api/v3/streams/#effort</a>
	 *
	 * @param effortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

	/**
	 * <p>
	 * Retrieve detailed geographical information streams about a specific {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Only distance, altitude and latlng stream types are available.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#segment">http://strava.github.io/api/v3/streams/#segment</a>
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects.
	 */
	public List<StravaStream> getSegmentStreams(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve detailed geographical information streams about a specific {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Only distance, altitude and latlng stream types are available.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#segment">http://strava.github.io/api/v3/streams/#segment</a>
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public List<StravaStream> getSegmentStreams(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

	/**
	 * <p>
	 * Retrieve detailed geographical information streams about a specific {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Only distance, altitude and latlng stream types are available.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#segment">http://strava.github.io/api/v3/streams/#segment</a>
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @return Returns an array of unordered stream objects.
	 */
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve detailed geographical information streams about a specific {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Only distance, altitude and latlng stream types are available.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/streams/:types
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/streams/#segment">http://strava.github.io/api/v3/streams/#segment</a>
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types);

}
