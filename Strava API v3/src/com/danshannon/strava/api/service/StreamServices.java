package com.danshannon.strava.api.service;

import java.util.List;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Segment;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.Stream;
import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;

/**
 * <p>Streams is the Strava term for the raw data associated with an activity. All streams for a given activity or segment effort will be the same length and the values at a given index correspond to the same time.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface StreamServices {
	/**
	 * <p>Streams represent the raw data of the uploaded file. External applications may only access this information for activities owned by the authenticated athlete.</p>
	 * 
	 * <p>While there are a large number of {@link StreamType stream types}, they may not exist for all activities. If a stream type does not exist for the activity, it will be ignored.</p>
	 * 
	 * <p>All streams for a given activity will be the same length and the values at a given index correspond to the same time. For example, the time from the time stream can be correlated to the lat/lng or watts streams.</p>
	 * 
	 * <p>Privacy Zones</p>
	 * 
	 * <p>Stream requests made using a public access_token will be cropped with the user�s privacy zones, regardless if the requesting athlete owns the requested activity. To fetch the complete stream data use an access_token with view_private permissions.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/streams/:types</p>
	 * 
	 * @see http://strava.github.io/api/v3/streams/#activity
	 * 
	 * @param id The id of the activity for which streams are to be retrieved
	 * @param types List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType (Optional) relevant only if using resolution. Either �time� or �distance�, default is �distance�, used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public List<Stream> getActivityStreams(String id, StreamType[] types, StreamResolutionType resolution, StreamSeriesDownsamplingType seriesType);
	
	/**
	 * <p>A {@link SegmentEffort segment effort} represents an attempt on a {@link Segment segment}. This resource returns a subset of the {@link Activity activity} streams that correspond to that effort.</p>
	 * 
	 * <p>All streams for a given segment effort will be the same length and the values at a given index correspond to the same time.</p>
	 * 
	 * <p>This resource is available for all public efforts.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segment_efforts/:id/streams/:types</p>
	 * 
	 * @see http://strava.github.io/api/v3/streams/#effort
	 * 
	 * @param id The id of the segment effort for which streams are to be retrieved
	 * @param types List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType (Optional) relevant only if using resolution. Either �time� or �distance�, default is �distance�, used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public List<Stream> getEffortStreams(String id, StreamType[] types, StreamResolutionType resolution, StreamSeriesDownsamplingType seriesType);
	
	/**
	 * <p>Retrieve detailed geographical information streams about a specific {@link Segment}.</p>
	 * 
	 * <p>Only distance, altitude and latlng stream types are available.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/streams/:types</p>
	 * 
	 * @see http://strava.github.io/api/v3/streams/#segment
	 * 
	 * @param id The id of the segment for which streams are to be retrieved
	 * @param types List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType (Optional) relevant only if using resolution. Either �time� or �distance�, default is �distance�, used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 */
	public List<Stream> getSegmentStreams(String id, StreamType[] types, StreamResolutionType resolution, StreamSeriesDownsamplingType seriesType);
	
	
}
