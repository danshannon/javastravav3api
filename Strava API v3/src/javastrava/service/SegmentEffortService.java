package javastrava.service;

import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaAthlete;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;

/**
 * <p>
 * A {@link StravaSegmentEffort segment effort} represents an {@link StravaAthlete athlete's} attempt at a {@link StravaSegment segment}.
 * </p>
 *
 * <p>
 * It can also be thought of as a portion of a ride that covers a segment.
 * </p>
 *
 * <p>
 * The object is returned in summary or detailed representations. They are currently the same.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface SegmentEffortService extends StravaService {
	/**
	 * <p>
	 * Retrieve details about a specific segment effort. The effort must be public or it must correspond to the current athlete.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment effort does not exist or is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/efforts/#retrieve">http://strava.github.io/api/v3/efforts/#retrieve</a>
	 *
	 *
	 * @param segmentEffortId
	 *            The id of the segment to be retrieved
	 * @return Returns a detailed segment effort representation
	 */
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId);

	/**
	 * <p>
	 * Retrieve details about a specific segment effort. The effort must be public or it must correspond to the current athlete.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment effort does not exist or is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segment_efforts/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/efforts/#retrieve">http://strava.github.io/api/v3/efforts/#retrieve</a>
	 *
	 *
	 * @param segmentEffortId
	 *            The id of the segment to be retrieved
	 * @return Returns a detailed segment effort representation
	 */
	public CompletableFuture<StravaSegmentEffort> getSegmentEffortAsync(final Long segmentEffortId);
}
