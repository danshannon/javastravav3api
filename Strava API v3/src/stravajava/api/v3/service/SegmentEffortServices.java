package stravajava.api.v3.service;

import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>A {@link StravaSegmentEffort segment effort} represents an {@link StravaAthlete athlete�s} attempt at a {@link StravaSegment segment}.</p>
 * 
 * <p>It can also be thought of as a portion of a ride that covers a segment.</p>
 * 
 * <p> The object is returned in summary or detailed representations. They are currently the same.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface SegmentEffortServices {
	/**
	 * <p>Retrieve details about a specific segment effort. The effort must be public or it must correspond to the current athlete.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segment_efforts/:id</p>
	 * 
	 * @see http://strava.github.io/api/v3/efforts/#retrieve
	 * 
	 * @param id The id of the segment to be retrieved
	 * @return Returns a detailed segment effort representation.
	 * @throws UnauthorizedException
	 */
	public StravaSegmentEffort getSegmentEffort(Long id) throws UnauthorizedException;
}
