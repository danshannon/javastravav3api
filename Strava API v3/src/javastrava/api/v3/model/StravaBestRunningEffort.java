package javastrava.api.v3.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaResourceState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * A 'best running effort' - calculated by Strava for runs only. Returned as part of activity details.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaBestRunningEffort {
	/**
	 * Strava's unique identifier for this running effort
	 */
	private Integer id;
	/**
	 * Status of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Name of this effort (Best 1k, etc.)
	 */
	private String name;
	/**
	 * Strava segment associated with this best effort (if there is one)
	 */
	private StravaSegment segment;
	/**
	 * Strava activity associated with this best effort
	 */
	private StravaActivity activity;
	/**
	 * Strava athlete associated with this run
	 */
	private StravaAthlete athlete;
	/**
	 * KOM rank for the effort
	 */
	private Integer komRank;
	/**
	 * Personal record ranking for this effort
	 */
	private Integer prRank;
	/**
	 * elapsed time in seconds (includes time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * moving time in seconds (excludes time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * date and time that the effort started
	 */
	private ZonedDateTime startDate;
	/**
	 * local start date for the effort (i.e. in the timezone that it started, shifted to UTC)
	 */
	private LocalDateTime startDateLocal;
	/**
	 * distance in metres
	 */
	private Float distance;
}
