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
	 * @return the entryCount
	 */
	public Integer getEntryCount() {
		return this.entryCount;
	}
	/**
	 * @param entryCount the entryCount to set
	 */
	public void setEntryCount(final Integer entryCount) {
		this.entryCount = entryCount;
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
	 * @return the neighborhoodCount
	 */
	public Integer getNeighborhoodCount() {
		return this.neighborhoodCount;
	}
	/**
	 * @param neighborhoodCount the neighborhoodCount to set
	 */
	public void setNeighborhoodCount(final Integer neighborhoodCount) {
		this.neighborhoodCount = neighborhoodCount;
	}
	/**
	 * @return the komType
	 */
	public String getKomType() {
		return this.komType;
	}
	/**
	 * @param komType the komType to set
	 */
	public void setKomType(final String komType) {
		this.komType = komType;
	}
	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @return the entries
	 */
	public List<StravaSegmentLeaderboardEntry> getEntries() {
		return this.entries;
	}
	/**
	 * @param entries the entries to set
	 */
	public void setEntries(final List<StravaSegmentLeaderboardEntry> entries) {
		this.entries = entries;
	}
	/**
	 * @return the athleteEntries
	 */
	public List<StravaSegmentLeaderboardEntry> getAthleteEntries() {
		return this.athleteEntries;
	}
	/**
	 * @param athleteEntries the athleteEntries to set
	 */
	public void setAthleteEntries(final List<StravaSegmentLeaderboardEntry> athleteEntries) {
		this.athleteEntries = athleteEntries;
	}
}
