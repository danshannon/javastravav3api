package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Detail of athlete {@link StravaStatistics}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStatisticsEntry {
	/**
	 * Number of activities
	 */
	private Integer count;
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Total moving time in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Total elapsed time in seconds (including time spent stopped) 
	 */
	private Integer elapsedTime;
	/**
	 * Total elevation gain in metres
	 */
	private Float elevationGain;
	/**
	 * Total number of achievements
	 */
	private Integer achievementCount;
}
