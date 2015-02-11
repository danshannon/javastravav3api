package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaGender;

/**
 * A single entry in a {@link StravaSegmentLeaderboard}
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentLeaderboardEntry {
	private String athleteName;
	private Integer athleteId;
	private StravaGender athleteGender;
	private Float averageHr;
	private Float averageWatts;
	private Float distance;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Integer activityId;
	private Long effortId;
	private Integer rank;
	private String athleteProfile;
}
