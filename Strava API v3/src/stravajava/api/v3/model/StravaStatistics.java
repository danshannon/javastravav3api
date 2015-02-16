package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.service.AthleteServices;

/**
 * <p>
 * Athlete statistics as returned by {@link AthleteServices#statistics(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStatistics {
	/**
	 * distance (in metres) of athlete's longest ride
	 */
	private Float biggestRideDistance;
	/**
	 * elevation gain (in metres) of athlete's biggest climb
	 */
	private Float biggestClimbElevationGain;
	/**
	 * Statistics for last 28 days' rides
	 */
	private StravaStatisticsEntry recentRideTotals;
	/**
	 * Statistics for last 28 days' runs
	 */
	private StravaStatisticsEntry recentRunTotals;
	/**
	 * Year to date ride statistics
	 */
	private StravaStatisticsEntry ytdRideTotals;
	/**
	 * Year to date run statistics
	 */
	private StravaStatisticsEntry ytdRunTotals;
	/**
	 * All time ride statistics
	 */
	private StravaStatisticsEntry allRideTotals;
	/**
	 * All time run statistics
	 */
	private StravaStatisticsEntry allRunTotals;
}
