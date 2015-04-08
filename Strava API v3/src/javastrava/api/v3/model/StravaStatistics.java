package javastrava.api.v3.model;

import javastrava.api.v3.service.AthleteService;

/**
 * <p>
 * Athlete statistics as returned by {@link AthleteService#statistics(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
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
	/**
	 * @return the biggestRideDistance
	 */
	public Float getBiggestRideDistance() {
		return this.biggestRideDistance;
	}
	/**
	 * @param biggestRideDistance the biggestRideDistance to set
	 */
	public void setBiggestRideDistance(final Float biggestRideDistance) {
		this.biggestRideDistance = biggestRideDistance;
	}
	/**
	 * @return the biggestClimbElevationGain
	 */
	public Float getBiggestClimbElevationGain() {
		return this.biggestClimbElevationGain;
	}
	/**
	 * @param biggestClimbElevationGain the biggestClimbElevationGain to set
	 */
	public void setBiggestClimbElevationGain(final Float biggestClimbElevationGain) {
		this.biggestClimbElevationGain = biggestClimbElevationGain;
	}
	/**
	 * @return the recentRideTotals
	 */
	public StravaStatisticsEntry getRecentRideTotals() {
		return this.recentRideTotals;
	}
	/**
	 * @param recentRideTotals the recentRideTotals to set
	 */
	public void setRecentRideTotals(final StravaStatisticsEntry recentRideTotals) {
		this.recentRideTotals = recentRideTotals;
	}
	/**
	 * @return the recentRunTotals
	 */
	public StravaStatisticsEntry getRecentRunTotals() {
		return this.recentRunTotals;
	}
	/**
	 * @param recentRunTotals the recentRunTotals to set
	 */
	public void setRecentRunTotals(final StravaStatisticsEntry recentRunTotals) {
		this.recentRunTotals = recentRunTotals;
	}
	/**
	 * @return the ytdRideTotals
	 */
	public StravaStatisticsEntry getYtdRideTotals() {
		return this.ytdRideTotals;
	}
	/**
	 * @param ytdRideTotals the ytdRideTotals to set
	 */
	public void setYtdRideTotals(final StravaStatisticsEntry ytdRideTotals) {
		this.ytdRideTotals = ytdRideTotals;
	}
	/**
	 * @return the ytdRunTotals
	 */
	public StravaStatisticsEntry getYtdRunTotals() {
		return this.ytdRunTotals;
	}
	/**
	 * @param ytdRunTotals the ytdRunTotals to set
	 */
	public void setYtdRunTotals(final StravaStatisticsEntry ytdRunTotals) {
		this.ytdRunTotals = ytdRunTotals;
	}
	/**
	 * @return the allRideTotals
	 */
	public StravaStatisticsEntry getAllRideTotals() {
		return this.allRideTotals;
	}
	/**
	 * @param allRideTotals the allRideTotals to set
	 */
	public void setAllRideTotals(final StravaStatisticsEntry allRideTotals) {
		this.allRideTotals = allRideTotals;
	}
	/**
	 * @return the allRunTotals
	 */
	public StravaStatisticsEntry getAllRunTotals() {
		return this.allRunTotals;
	}
	/**
	 * @param allRunTotals the allRunTotals to set
	 */
	public void setAllRunTotals(final StravaStatisticsEntry allRunTotals) {
		this.allRunTotals = allRunTotals;
	}
}
