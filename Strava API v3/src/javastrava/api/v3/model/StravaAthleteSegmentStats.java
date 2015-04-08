package javastrava.api.v3.model;

import java.time.LocalDate;

/**
 * <p>
 * Athlete's statistics for a segment, returned by Strava with a segment effort
 * </p>
 * @author Dan Shannon
 *
 */
public class StravaAthleteSegmentStats {
	/**
	 * No args constructor
	 */
	public StravaAthleteSegmentStats() {
		super();
	}
	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}
	/**
	 * @param effortCount the effortCount to set
	 */
	public void setEffortCount(final Integer effortCount) {
		this.effortCount = effortCount;
	}
	/**
	 * @return the prElapsedTime
	 */
	public Integer getPrElapsedTime() {
		return this.prElapsedTime;
	}
	/**
	 * @param prElapsedTime the prElapsedTime to set
	 */
	public void setPrElapsedTime(final Integer prElapsedTime) {
		this.prElapsedTime = prElapsedTime;
	}
	/**
	 * @return the prDate
	 */
	public LocalDate getPrDate() {
		return this.prDate;
	}
	/**
	 * @param prDate the prDate to set
	 */
	public void setPrDate(final LocalDate prDate) {
		this.prDate = prDate;
	}
	private Integer effortCount;
	private Integer prElapsedTime;
	private LocalDate prDate;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaAthleteSegmentStats [effortCount=" + this.effortCount + ", prElapsedTime=" + this.prElapsedTime + ", prDate=" + this.prDate + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.effortCount == null) ? 0 : this.effortCount.hashCode());
		result = prime * result + ((this.prDate == null) ? 0 : this.prDate.hashCode());
		result = prime * result + ((this.prElapsedTime == null) ? 0 : this.prElapsedTime.hashCode());
		return result;
	}
	/* (non-Javadoc)
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
		StravaAthleteSegmentStats other = (StravaAthleteSegmentStats) obj;
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
}
