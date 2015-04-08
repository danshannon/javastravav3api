package javastrava.api.v3.model;

import javastrava.api.v3.service.ActivityService;

/**
 * <p>
 * Split time data associated with runs. Comes in metric (1km) and imperial (1 mile) versions. See {@link ActivityService#getActivity(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaSplit {
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Elapsed time for the split in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * elevation difference in metres
	 */
	private Float elevationDifference;
	/**
	 * Moving time for the split in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Order of the split within the run
	 */
	private Integer split;
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
	 * @return the elevationDifference
	 */
	public Float getElevationDifference() {
		return this.elevationDifference;
	}
	/**
	 * @param elevationDifference the elevationDifference to set
	 */
	public void setElevationDifference(final Float elevationDifference) {
		this.elevationDifference = elevationDifference;
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
	 * @return the split
	 */
	public Integer getSplit() {
		return this.split;
	}
	/**
	 * @param split the split to set
	 */
	public void setSplit(final Integer split) {
		this.split = split;
	}
}
