package com.danshannon.strava.api.model;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
public class Map {
	private String id;
	private String polyline;
	private String summaryPolyline;
	private ResourceState resourceState;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @return the polyline
	 */
	public String getPolyline() {
		return this.polyline;
	}
	/**
	 * @return the summaryPolyline
	 */
	public String getSummaryPolyline() {
		return this.summaryPolyline;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param polyline the polyline to set
	 */
	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}
	/**
	 * @param summaryPolyline the summaryPolyline to set
	 */
	public void setSummaryPolyline(String summaryPolyline) {
		this.summaryPolyline = summaryPolyline;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	
}
