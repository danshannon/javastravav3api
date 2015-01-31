package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.Gender;

/**
 * A single entry in a {@link SegmentLeaderboard}
 * 
 * @author Dan Shannon
 *
 */
@Data
public class SegmentLeaderboardEntry {
	public SegmentLeaderboardEntry() {
		// Required
		super();
	}
	
	private String athleteName;
	private Integer athleteId;
	private Gender athleteGender;
	private Float averageHr;
	private Float averageWatts;
	private Float distance;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Integer activityId;
	private Integer effortId;
	private Integer rank;
	private String athleteProfile;
}
