package javastrava.api.v3.model;

/**
 * <p>
 * Detail of athlete {@link StravaStatistics}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
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
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return this.count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}
	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @return the elevationGain
	 */
	public Float getElevationGain() {
		return this.elevationGain;
	}
	/**
	 * @param elevationGain the elevationGain to set
	 */
	public void setElevationGain(final Float elevationGain) {
		this.elevationGain = elevationGain;
	}
	/**
	 * @return the achievementCount
	 */
	public Integer getAchievementCount() {
		return this.achievementCount;
	}
	/**
	 * @param achievementCount the achievementCount to set
	 */
	public void setAchievementCount(final Integer achievementCount) {
		this.achievementCount = achievementCount;
	}
}
