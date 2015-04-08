package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaClimbCategory;

/**
 * <p>
 * Summary of segment returned by the segment explorer
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaSegmentExplorerResponseSegment {
	/**
	 * Strava's unique identifier of the {@link StravaSegment segment}
	 */
	private Integer id;
	/**
	 * Segment name
	 */
	private String name;
	/**
	 * Climb category
	 */
	private StravaClimbCategory climbCategory;
	/**
	 * Description of the climb category (see {@link StravaClimbCategory#getDescription()})
	 */
	private String climbCategoryDesc;
	/**
	 * Average grade in percent
	 */
	private Float avgGrade;
	/**
	 * Start co-ordinates of the segment
	 */
	private StravaMapPoint startLatlng;
	/**
	 * End co-ordinates of the segment
	 */
	private StravaMapPoint endLatlng;
	/**
	 * Total elevation difference in metres
	 */
	private Float elevDifference;
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Polyline for rendering with Google maps
	 */
	private String points;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @return the climbCategory
	 */
	public StravaClimbCategory getClimbCategory() {
		return this.climbCategory;
	}
	/**
	 * @param climbCategory the climbCategory to set
	 */
	public void setClimbCategory(final StravaClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
	}
	/**
	 * @return the climbCategoryDesc
	 */
	public String getClimbCategoryDesc() {
		return this.climbCategoryDesc;
	}
	/**
	 * @param climbCategoryDesc the climbCategoryDesc to set
	 */
	public void setClimbCategoryDesc(final String climbCategoryDesc) {
		this.climbCategoryDesc = climbCategoryDesc;
	}
	/**
	 * @return the avgGrade
	 */
	public Float getAvgGrade() {
		return this.avgGrade;
	}
	/**
	 * @param avgGrade the avgGrade to set
	 */
	public void setAvgGrade(final Float avgGrade) {
		this.avgGrade = avgGrade;
	}
	/**
	 * @return the startLatlng
	 */
	public StravaMapPoint getStartLatlng() {
		return this.startLatlng;
	}
	/**
	 * @param startLatlng the startLatlng to set
	 */
	public void setStartLatlng(final StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}
	/**
	 * @return the endLatlng
	 */
	public StravaMapPoint getEndLatlng() {
		return this.endLatlng;
	}
	/**
	 * @param endLatlng the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}
	/**
	 * @return the elevDifference
	 */
	public Float getElevDifference() {
		return this.elevDifference;
	}
	/**
	 * @param elevDifference the elevDifference to set
	 */
	public void setElevDifference(final Float elevDifference) {
		this.elevDifference = elevDifference;
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
	 * @return the points
	 */
	public String getPoints() {
		return this.points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(final String points) {
		this.points = points;
	}
}
