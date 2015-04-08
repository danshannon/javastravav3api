package javastrava.api.v3.model;

import java.util.List;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * {@link StravaSegment} leaderboard
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSegmentLeaderboard {
	private Integer entryCount;
	private Integer effortCount;
	private Integer neighborhoodCount;
	private String komType;
	private StravaResourceState resourceState;
	/**
	 * The entries that were actually asked for
	 */
	private List<StravaSegmentLeaderboardEntry> entries;
	/**
	 * The entries relative to the athlete
	 */
	private List<StravaSegmentLeaderboardEntry> athleteEntries;
	/**
	 * No args constructor
	 */
	public StravaSegmentLeaderboard() {
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
		if (!(obj instanceof StravaSegmentLeaderboard)) {
			return false;
		}
		final StravaSegmentLeaderboard other = (StravaSegmentLeaderboard) obj;
		if (this.athleteEntries == null) {
			if (other.athleteEntries != null) {
				return false;
			}
		} else if (!this.athleteEntries.equals(other.athleteEntries)) {
			return false;
		}
		if (this.effortCount == null) {
			if (other.effortCount != null) {
				return false;
			}
		} else if (!this.effortCount.equals(other.effortCount)) {
			return false;
		}
		if (this.entries == null) {
			if (other.entries != null) {
				return false;
			}
		} else if (!this.entries.equals(other.entries)) {
			return false;
		}
		if (this.entryCount == null) {
			if (other.entryCount != null) {
				return false;
			}
		} else if (!this.entryCount.equals(other.entryCount)) {
			return false;
		}
		if (this.komType == null) {
			if (other.komType != null) {
				return false;
			}
		} else if (!this.komType.equals(other.komType)) {
			return false;
		}
		if (this.neighborhoodCount == null) {
			if (other.neighborhoodCount != null) {
				return false;
			}
		} else if (!this.neighborhoodCount.equals(other.neighborhoodCount)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		return true;
	}
	/**
	 * @return the athleteEntries
	 */
	public List<StravaSegmentLeaderboardEntry> getAthleteEntries() {
		return this.athleteEntries;
	}
	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}
	/**
	 * @return the entries
	 */
	public List<StravaSegmentLeaderboardEntry> getEntries() {
		return this.entries;
	}
	/**
	 * @return the entryCount
	 */
	public Integer getEntryCount() {
		return this.entryCount;
	}
	/**
	 * @return the komType
	 */
	public String getKomType() {
		return this.komType;
	}
	/**
	 * @return the neighborhoodCount
	 */
	public Integer getNeighborhoodCount() {
		return this.neighborhoodCount;
	}
	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.athleteEntries == null) ? 0 : this.athleteEntries.hashCode());
		result = (prime * result) + ((this.effortCount == null) ? 0 : this.effortCount.hashCode());
		result = (prime * result) + ((this.entries == null) ? 0 : this.entries.hashCode());
		result = (prime * result) + ((this.entryCount == null) ? 0 : this.entryCount.hashCode());
		result = (prime * result) + ((this.komType == null) ? 0 : this.komType.hashCode());
		result = (prime * result) + ((this.neighborhoodCount == null) ? 0 : this.neighborhoodCount.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		return result;
	}
	/**
	 * @param athleteEntries the athleteEntries to set
	 */
	public void setAthleteEntries(final List<StravaSegmentLeaderboardEntry> athleteEntries) {
		this.athleteEntries = athleteEntries;
	}
	/**
	 * @param effortCount the effortCount to set
	 */
	public void setEffortCount(final Integer effortCount) {
		this.effortCount = effortCount;
	}
	/**
	 * @param entries the entries to set
	 */
	public void setEntries(final List<StravaSegmentLeaderboardEntry> entries) {
		this.entries = entries;
	}
	/**
	 * @param entryCount the entryCount to set
	 */
	public void setEntryCount(final Integer entryCount) {
		this.entryCount = entryCount;
	}
	/**
	 * @param komType the komType to set
	 */
	public void setKomType(final String komType) {
		this.komType = komType;
	}
	/**
	 * @param neighborhoodCount the neighborhoodCount to set
	 */
	public void setNeighborhoodCount(final Integer neighborhoodCount) {
		this.neighborhoodCount = neighborhoodCount;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaSegmentLeaderboard [entryCount=" + this.entryCount + ", effortCount=" + this.effortCount + ", neighborhoodCount="
				+ this.neighborhoodCount + ", komType=" + this.komType + ", resourceState=" + this.resourceState + ", entries=" + this.entries
				+ ", athleteEntries=" + this.athleteEntries + "]";
	}
}
