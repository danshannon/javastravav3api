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
	 * No args constructor
	 */
	public StravaStatistics() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaStatistics)) {
			return false;
		}
		final StravaStatistics other = (StravaStatistics) obj;
		if (this.allRideTotals == null) {
			if (other.allRideTotals != null) {
				return false;
			}
		} else if (!this.allRideTotals.equals(other.allRideTotals)) {
			return false;
		}
		if (this.allRunTotals == null) {
			if (other.allRunTotals != null) {
				return false;
			}
		} else if (!this.allRunTotals.equals(other.allRunTotals)) {
			return false;
		}
		if (this.biggestClimbElevationGain == null) {
			if (other.biggestClimbElevationGain != null) {
				return false;
			}
		} else if (!this.biggestClimbElevationGain.equals(other.biggestClimbElevationGain)) {
			return false;
		}
		if (this.biggestRideDistance == null) {
			if (other.biggestRideDistance != null) {
				return false;
			}
		} else if (!this.biggestRideDistance.equals(other.biggestRideDistance)) {
			return false;
		}
		if (this.recentRideTotals == null) {
			if (other.recentRideTotals != null) {
				return false;
			}
		} else if (!this.recentRideTotals.equals(other.recentRideTotals)) {
			return false;
		}
		if (this.recentRunTotals == null) {
			if (other.recentRunTotals != null) {
				return false;
			}
		} else if (!this.recentRunTotals.equals(other.recentRunTotals)) {
			return false;
		}
		if (this.ytdRideTotals == null) {
			if (other.ytdRideTotals != null) {
				return false;
			}
		} else if (!this.ytdRideTotals.equals(other.ytdRideTotals)) {
			return false;
		}
		if (this.ytdRunTotals == null) {
			if (other.ytdRunTotals != null) {
				return false;
			}
		} else if (!this.ytdRunTotals.equals(other.ytdRunTotals)) {
			return false;
		}
		return true;
	}
	/**
	 * @return the allRideTotals
	 */
	public StravaStatisticsEntry getAllRideTotals() {
		return this.allRideTotals;
	}
	/**
	 * @return the allRunTotals
	 */
	public StravaStatisticsEntry getAllRunTotals() {
		return this.allRunTotals;
	}
	/**
	 * @return the biggestClimbElevationGain
	 */
	public Float getBiggestClimbElevationGain() {
		return this.biggestClimbElevationGain;
	}
	/**
	 * @return the biggestRideDistance
	 */
	public Float getBiggestRideDistance() {
		return this.biggestRideDistance;
	}
	/**
	 * @return the recentRideTotals
	 */
	public StravaStatisticsEntry getRecentRideTotals() {
		return this.recentRideTotals;
	}
	/**
	 * @return the recentRunTotals
	 */
	public StravaStatisticsEntry getRecentRunTotals() {
		return this.recentRunTotals;
	}
	/**
	 * @return the ytdRideTotals
	 */
	public StravaStatisticsEntry getYtdRideTotals() {
		return this.ytdRideTotals;
	}
	/**
	 * @return the ytdRunTotals
	 */
	public StravaStatisticsEntry getYtdRunTotals() {
		return this.ytdRunTotals;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.allRideTotals == null) ? 0 : this.allRideTotals.hashCode());
		result = (prime * result) + ((this.allRunTotals == null) ? 0 : this.allRunTotals.hashCode());
		result = (prime * result) + ((this.biggestClimbElevationGain == null) ? 0 : this.biggestClimbElevationGain.hashCode());
		result = (prime * result) + ((this.biggestRideDistance == null) ? 0 : this.biggestRideDistance.hashCode());
		result = (prime * result) + ((this.recentRideTotals == null) ? 0 : this.recentRideTotals.hashCode());
		result = (prime * result) + ((this.recentRunTotals == null) ? 0 : this.recentRunTotals.hashCode());
		result = (prime * result) + ((this.ytdRideTotals == null) ? 0 : this.ytdRideTotals.hashCode());
		result = (prime * result) + ((this.ytdRunTotals == null) ? 0 : this.ytdRunTotals.hashCode());
		return result;
	}
	/**
	 * @param allRideTotals the allRideTotals to set
	 */
	public void setAllRideTotals(final StravaStatisticsEntry allRideTotals) {
		this.allRideTotals = allRideTotals;
	}
	/**
	 * @param allRunTotals the allRunTotals to set
	 */
	public void setAllRunTotals(final StravaStatisticsEntry allRunTotals) {
		this.allRunTotals = allRunTotals;
	}
	/**
	 * @param biggestClimbElevationGain the biggestClimbElevationGain to set
	 */
	public void setBiggestClimbElevationGain(final Float biggestClimbElevationGain) {
		this.biggestClimbElevationGain = biggestClimbElevationGain;
	}
	/**
	 * @param biggestRideDistance the biggestRideDistance to set
	 */
	public void setBiggestRideDistance(final Float biggestRideDistance) {
		this.biggestRideDistance = biggestRideDistance;
	}
	/**
	 * @param recentRideTotals the recentRideTotals to set
	 */
	public void setRecentRideTotals(final StravaStatisticsEntry recentRideTotals) {
		this.recentRideTotals = recentRideTotals;
	}
	/**
	 * @param recentRunTotals the recentRunTotals to set
	 */
	public void setRecentRunTotals(final StravaStatisticsEntry recentRunTotals) {
		this.recentRunTotals = recentRunTotals;
	}
	/**
	 * @param ytdRideTotals the ytdRideTotals to set
	 */
	public void setYtdRideTotals(final StravaStatisticsEntry ytdRideTotals) {
		this.ytdRideTotals = ytdRideTotals;
	}
	/**
	 * @param ytdRunTotals the ytdRunTotals to set
	 */
	public void setYtdRunTotals(final StravaStatisticsEntry ytdRunTotals) {
		this.ytdRunTotals = ytdRunTotals;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaStatistics [biggestRideDistance=" + this.biggestRideDistance + ", biggestClimbElevationGain=" + this.biggestClimbElevationGain
				+ ", recentRideTotals=" + this.recentRideTotals + ", recentRunTotals=" + this.recentRunTotals + ", ytdRideTotals=" + this.ytdRideTotals
				+ ", ytdRunTotals=" + this.ytdRunTotals + ", allRideTotals=" + this.allRideTotals + ", allRunTotals=" + this.allRunTotals + "]";
	}
}
