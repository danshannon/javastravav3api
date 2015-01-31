package com.danshannon.strava.api.model;

import java.util.List;

import lombok.Data;

import com.danshannon.strava.api.service.SegmentServices;

/**
 * Returned by the {@link SegmentServices#segmentExplore(MapPoint, MapPoint, com.danshannon.strava.api.service.athlete.SegmentExplorerActivityType, Integer, Integer) segment explorer service}.
 * 
 * Essentially is an array of {@link Segment segments} but sadly is slightly not quite the same
 * 
 * @author Dan Shannon
 *
 */
@Data
public class SegmentExplorer {
	public SegmentExplorer() {
		// Required
		super();
	}
	
	private List<SegmentExplorerSegment> segments;
}
