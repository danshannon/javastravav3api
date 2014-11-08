package com.danshannon.strava.api.service;

import com.danshannon.strava.api.model.SegmentEffort;

/**
 * <p>A {@link SegmentEffort segment effort} represents an {@link Athlete athlete’s} attempt at a {@link Segment segment}.</p>
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
	 */
	public SegmentEffort getSegmentEffort(Integer id);
}
