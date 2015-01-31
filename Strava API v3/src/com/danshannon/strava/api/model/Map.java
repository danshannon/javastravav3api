package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
@Data
public class Map {
	public Map() {
		// Required
		super();
	}
	
	private String id;
	private String polyline;
	private String summaryPolyline;
	private ResourceState resourceState;
}
