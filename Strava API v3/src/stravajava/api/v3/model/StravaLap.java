package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * A lap within an {@link StravaActivity}
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaLap {
	/**
	 * Strava's uniique identifier for this lap
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Name of the lap
	 */
	private String name;
	/**
	 * Activity that this lap is part of
	 */
	private StravaActivity activity;
	/**
	 * Athlete who did the activity that this lap is part of
	 */
	private StravaAthlete athlete;
	/**
	 * Elapsed time in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * Moving time in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Start date and time for this activity
	 */
	private Date startDate;
	/**
	 * Start date and time for this activity, hacked to the time zone where the activity started
	 */
	private Date startDateLocal;
	/**
	 * Distance travelled in metres
	 */
	private Float distance;
	/**
	 * Index of the entry in the activity streams that represents the start of the lap
	 */
	private Integer startIndex;
	/**
	 * Index of the entry in the activity streams that represents the end of the lap
	 */
	private Integer endIndex;
	/**
	 * Total elevation gain in metres
	 */
	private Float totalElevationGain;
	/**
	 * Average speed in metres per second
	 */
	private Float averageSpeed;
	/**
	 * Maximum speed in metres per second
	 */
	private Float maxSpeed;
	/**
	 * Average cadence in revolutions per minute (returned only if cadence data was uploaded with the activity). Applies only to rides.
	 */
	private Float averageCadence;
	/**
	 * Average power in watts
	 */
	private Float averageWatts;
	/**
	 * Is set to <code>true</code> if the power was measured using a power meter (i.e. power data was included in the upload)
	 */
	private Boolean deviceWatts;
	/**
	 * Average heart rate in beats per minute
	 */
	private Float averageHeartrate;
	/**
	 * Maximum heart rate in beats per minute
	 */
	private Float maxHeartrate;
	/**
	 * Lap number
	 */
	private Integer lapIndex;
}
