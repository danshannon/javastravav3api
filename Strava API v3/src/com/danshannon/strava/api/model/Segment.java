package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SegmentActivityType;
import com.google.gson.annotations.SerializedName;

/**
 * <p>{@link Segment Segments} are specific sections of road. {@link Athlete Athletes�} {@link SegmentEffort} efforts are compared on these segments and leaderboards are created.</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class Segment {
	public Segment() {
		// Required
		super();
	}
	
	private String id;
	private ResourceState resourceState;
	private String name;
	private SegmentActivityType activityType;
	private Float distance;
	private Float averageGrade;
	private Float maximumGrade;
	private Float elevationHigh;
	private Float elevationLow;
	private MapPoint startLatlng;
	private MapPoint endLatlng;
	private ClimbCategory climbCategory;
	private String city;
	private String state;
	private String country;
	@SerializedName("private")
	private Boolean privateSegment; // is "private" in JSON API
	private Boolean starred; // true if authenticated athlete has starred segment
	private Date createdAt;
	private Date updatedAt;
	private Float totalElevationGain;
	private Map map;
	private Integer effortCount;
	private Integer athleteCount;
	private Boolean hazardous;
	private Integer starCount;
	private SegmentEffort athletePrEffort; // See {@link SegmentServices#listStarredSegments()}
}
