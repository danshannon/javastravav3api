package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;

/**
 * {@link StravaSegment} leaderboard
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaSegmentLeaderboard {
	public StravaSegmentLeaderboard() {
		// Required
		super();
	}
	
	private Integer entryCount;
	private List<StravaSegmentLeaderboardEntry> entries;
}
