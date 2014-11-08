package com.danshannon.strava.api.model;

import java.util.List;

/**
 * {@link Segment} leaderboard
 * 
 * @author Dan Shannon
 *
 */
public class SegmentLeaderboard {
	private Integer entryCount;
	private List<SegmentLeaderboardEntry> entries;
	/**
	 * @return the entryCount
	 */
	public Integer getEntryCount() {
		return this.entryCount;
	}
	/**
	 * @return the entries
	 */
	public List<SegmentLeaderboardEntry> getEntries() {
		return this.entries;
	}
	/**
	 * @param entryCount the entryCount to set
	 */
	public void setEntryCount(Integer entryCount) {
		this.entryCount = entryCount;
	}
	/**
	 * @param entries the entries to set
	 */
	public void setEntries(List<SegmentLeaderboardEntry> entries) {
		this.entries = entries;
	}
	
}
