package com.danshannon.strava.api.model;

import java.util.List;

import lombok.Data;

/**
 * {@link Segment} leaderboard
 * 
 * @author Dan Shannon
 *
 */
@Data
public class SegmentLeaderboard {
	public SegmentLeaderboard() {
		// Required
		super();
	}
	
	private Integer entryCount;
	private List<SegmentLeaderboardEntry> entries;
}
