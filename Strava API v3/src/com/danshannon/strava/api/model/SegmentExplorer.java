package com.danshannon.strava.api.model;

import java.util.Arrays;

import com.danshannon.strava.api.service.SegmentServices;

/**
 * Returned by the {@link SegmentServices#segmentExplore(MapPoint, MapPoint, com.danshannon.strava.api.service.athlete.SegmentExplorerActivityType, Integer, Integer) segment explorer service}.
 * 
 * Essentially is an array of {@link Segment segments} but sadly is slightly not quite the same
 * 
 * @author Dan Shannon
 *
 */
public class SegmentExplorer {
	private SegmentExploreSegment[] segments;

	/**
	 * @return the segments
	 */
	public SegmentExploreSegment[] getSegments() {
		return this.segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(SegmentExploreSegment[] segments) {
		this.segments = segments;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentExplorer [" + (this.segments != null ? "segments=" + Arrays.toString(this.segments) : "") + "]";
	}
	
}
