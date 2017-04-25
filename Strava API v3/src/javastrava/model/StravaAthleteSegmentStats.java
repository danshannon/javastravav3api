package javastrava.model;

import java.time.LocalDate;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Athlete's statistics for a segment, returned by Strava with a segment effort
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaAthleteSegmentStats implements StravaEntity {
	/**
	 * Number of efforts by the authenticated athlete on the segment
	 */
	private Integer		effortCount;
	/**
	 * Elapsed time of the athlete's personal record for this segment
	 */
	private Integer		prElapsedTime;
	/**
	 * Date on which the athlete's personal record was set
	 */
	private LocalDate	prDate;

	/**
	 * No args constructor
	 */
	public StravaAthleteSegmentStats() {
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
		if (!(obj instanceof StravaAthleteSegmentStats)) {
			return false;
		}
		final StravaAthleteSegmentStats other = (StravaAthleteSegmentStats) obj;
		if (this.effortCount == null) {
			if (other.effortCount != null) {
				return false;
			}
		} else if (!this.effortCount.equals(other.effortCount)) {
			return false;
		}
		if (this.prDate == null) {
			if (other.prDate != null) {
				return false;
			}
		} else if (!this.prDate.equals(other.prDate)) {
			return false;
		}
		if (this.prElapsedTime == null) {
			if (other.prElapsedTime != null) {
				return false;
			}
		} else if (!this.prElapsedTime.equals(other.prElapsedTime)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}

	/**
	 * @return the prDate
	 */
	public LocalDate getPrDate() {
		return this.prDate;
	}

	/**
	 * @return the prElapsedTime
	 */
	public Integer getPrElapsedTime() {
		return this.prElapsedTime;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.effortCount == null) ? 0 : this.effortCount.hashCode());
		result = (prime * result) + ((this.prDate == null) ? 0 : this.prDate.hashCode());
		result = (prime * result) + ((this.prElapsedTime == null) ? 0 : this.prElapsedTime.hashCode());
		return result;
	}

	/**
	 * @param effortCount
	 *            the effortCount to set
	 */
	public void setEffortCount(final Integer effortCount) {
		this.effortCount = effortCount;
	}

	/**
	 * @param prDate
	 *            the prDate to set
	 */
	public void setPrDate(final LocalDate prDate) {
		this.prDate = prDate;
	}

	/**
	 * @param prElapsedTime
	 *            the prElapsedTime to set
	 */
	public void setPrElapsedTime(final Integer prElapsedTime) {
		this.prElapsedTime = prElapsedTime;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaAthleteSegmentStats [effortCount=" + this.effortCount + ", prElapsedTime=" + this.prElapsedTime + ", prDate=" + this.prDate + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
