package javastrava.api.v3.model;

import java.util.Date;
import java.util.List;

import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.SegmentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * A segment effort represents an athlete's attempt at a segment. It can also be thought of as a portion of a ride that covers a
 * segment. The object is returned in summary or detailed representations. They are currently the same.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentEffort {
	/**
	 * Strava's unique identifier for this segment effort
	 */
	private Long				id;
	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * Name of the segment
	 */
	private String				name;
	/**
	 * Activity - this instance will contain the id of the activity only
	 */
	private StravaActivity		activity;
	/**
	 * Athlete - this instance will contain the id of the athlete only
	 */
	private StravaAthlete		athlete;
	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer				elapsedTime;
	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer				movingTime;
	/**
	 * Start date and time for this effort
	 */
	private Date				startDate;
	/**
	 * Start date and time for this effort, hacked to local time zone at the start
	 */
	private Date				startDateLocal;
	/**
	 *  the length im metres of the effort as described by the activity, this may be different than the length of the segment
	 */
	private Float				distance;
	/**
	 * the activity stream index of the start of this effort
	 */
	private Integer				startIndex;
	/**
	 * the activity stream index of the end of this effort
	 */
	private Integer				endIndex;
	/**
	 * Average cadence in revolutions per minute, if cadence data was provided with the activity upload
	 */
	private Float				averageCadence;
	/**
	 * Average watts (rides only)
	 */
	private Float				averageWatts;
	/**
	 * Average heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Float				averageHeartrate;
	/**
	 * Maximum heart rate in beats per minute, if heart rate data was provided with the activity upload
	 */
	private Integer				maxHeartrate;
	/**
	 * {@link StravaResourceState#SUMMARY} representation of the segment
	 */
	private StravaSegment		segment;
	/**
	 * 1-10 rank on segment at time of upload
	 */
	private Integer				komRank;
	/**
	 * 1-3 personal record on segment at time of upload
	 */
	private Integer				prRank;
	/**
	 * indicates a hidden/non-important effort when returned as part of an activity, value may change over time, see retrieve an activity for more details
	 */
	private Boolean				hidden;
	/**
	 * @see SegmentService#listStarredSegments(Integer)
	 */
	private Boolean				isKom;
	
	/**
	 * Summary of achievements on this effort
	 */
	private List<StravaAchievement> achievements;
	/**
	 * Summary of athlete's statistics on this segment
	 */
	@SuppressWarnings("deprecation")
	private StravaAthleteSegmentStats athleteSegmentStats;
}
