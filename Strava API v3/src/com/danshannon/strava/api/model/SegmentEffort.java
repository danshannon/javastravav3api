package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
@Data
public class SegmentEffort {
	public SegmentEffort() {
		// Required
		super();
	}
	
	private Long id;
	private ResourceState resourceState;
	private String name;
	private Activity activity;
	private Athlete athlete;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
	private Integer startIndex;
	private Integer endIndex;
	private Float averageCadence;
	private Float averageWatts;
	private Float averageHeartrate;
	private Integer maxHeartrate;
	private Segment segment;
	private Integer komRank;
	private Integer prRank;
	private Boolean hidden;
	private Boolean isKom; // see {@link SegmentServices#starredSegments()}
}
