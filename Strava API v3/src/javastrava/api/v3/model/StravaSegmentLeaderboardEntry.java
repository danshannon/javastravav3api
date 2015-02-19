package javastrava.api.v3.model;

import java.util.Date;

import javastrava.api.v3.model.reference.StravaGender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * A single entry in a {@link StravaSegmentLeaderboard}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentLeaderboardEntry {
	/**
	 * Name of the athlete
	 */
	private String athleteName;
	/**
	 * Strava's unique identifier for the athlete
	 */
	private Integer athleteId;
	/**
	 * Athlete's gender
	 */
	private StravaGender athleteGender;
	/**
	 * Average heart rate (in beats per minute) for the effort associated with the leaderboard entry, if data was provided with the upload
	 */
	private Float averageHr;
	/**
	 * Average power (in watts) for the effort associated with this leaderboard entry
	 */
	private Float averageWatts;
	/**
	 * Total distance of the effort in metres
	 */
	private Float distance;
	/**
	 * Elapsed time for the effort in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * Moving time for the effort in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Start date and time for the effort
	 */
	private Date startDate;
	/**
	 * Start date and time for the effort, hacked into the local timezone
	 */
	private Date startDateLocal;
	/**
	 * Identifier of the {@link StravaActivity activity} associated with this entry
	 */
	private Integer activityId;
	/**
	 * Identifier of the {@link StravaSegmentEffort effort} associated with this entry
	 */
	private Long effortId;
	/**
	 * Overall rank of this entry
	 */
	private Integer rank;
	/**
	 * URL of the athlete's profile picture
	 */
	private String athleteProfile;
}
