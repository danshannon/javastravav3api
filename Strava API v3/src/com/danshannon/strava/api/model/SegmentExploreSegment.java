package com.danshannon.strava.api.model;

import com.danshannon.strava.api.model.reference.ClimbCategory;

/**
 * Summary of segment returned by the segment explorer
 * 
 * @author Dan Shannon
 *
 */
public class SegmentExploreSegment {
	private Integer id;
	private String name;
	private ClimbCategory climbCategory;
	private String climbCategoryDesc;
	private Float avgGrade;
	private MapPoint startLatlng;
	private MapPoint endLatlng;
	private Float elevDifference;
	private Float distance;
	private String points;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the climbCategory
	 */
	public ClimbCategory getClimbCategory() {
		return this.climbCategory;
	}
	/**
	 * @return the climbCategoryDesc
	 */
	public String getClimbCategoryDesc() {
		return this.climbCategoryDesc;
	}
	/**
	 * @return the avgGrade
	 */
	public Float getAvgGrade() {
		return this.avgGrade;
	}
	/**
	 * @return the startLatlng
	 */
	public MapPoint getStartLatlng() {
		return this.startLatlng;
	}
	/**
	 * @return the endLatlng
	 */
	public MapPoint getEndLatlng() {
		return this.endLatlng;
	}
	/**
	 * @return the elevDifference
	 */
	public Float getElevDifference() {
		return this.elevDifference;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @return the points
	 */
	public String getPoints() {
		return this.points;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param climbCategory the climbCategory to set
	 */
	public void setClimbCategory(ClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
	}
	/**
	 * @param climbCategoryDesc the climbCategoryDesc to set
	 */
	public void setClimbCategoryDesc(String climbCategoryDesc) {
		this.climbCategoryDesc = climbCategoryDesc;
	}
	/**
	 * @param avgGrade the avgGrade to set
	 */
	public void setAvgGrade(Float avgGrade) {
		this.avgGrade = avgGrade;
	}
	/**
	 * @param startLatlng the startLatlng to set
	 */
	public void setStartLatlng(MapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}
	/**
	 * @param endLatlng the endLatlng to set
	 */
	public void setEndLatlng(MapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}
	/**
	 * @param elevDifference the elevDifference to set
	 */
	public void setElevDifference(Float elevDifference) {
		this.elevDifference = elevDifference;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(String points) {
		this.points = points;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentExploreSegment [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.climbCategory != null ? "climbCategory=" + this.climbCategory + ", " : "")
				+ (this.climbCategoryDesc != null ? "climbCategoryDesc=" + this.climbCategoryDesc + ", " : "")
				+ (this.avgGrade != null ? "avgGrade=" + this.avgGrade + ", " : "")
				+ (this.startLatlng != null ? "startLatlng=" + this.startLatlng + ", " : "")
				+ (this.endLatlng != null ? "endLatlng=" + this.endLatlng + ", " : "")
				+ (this.elevDifference != null ? "elevDifference=" + this.elevDifference + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance + ", " : "")
				+ (this.points != null ? "points=" + this.points : "") + "]";
	}
	
}
