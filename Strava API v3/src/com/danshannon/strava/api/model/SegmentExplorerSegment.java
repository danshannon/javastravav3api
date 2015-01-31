package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ClimbCategory;

/**
 * Summary of segment returned by the segment explorer
 * 
 * @author Dan Shannon
 *
 */
@Data
public class SegmentExplorerSegment {
	public SegmentExplorerSegment() {
		// Required
		super();
	}
	
	private Integer id;
	private String name;
	private ClimbCategory climbCategory;
	private String climbCategoryDesc;
	private Float avgGrade;
	private MapPoint startLatlng;
	private MapPoint endLatlng;
	private Float elevDifference;
	private Float distance;
	private String points;
}
