package com.danshannon.strava.api.model;

import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class Split {
	public Split() {
		// Required
		super();
	}
	
	private Float distance;
	private Integer elapsedTime;
	private Float elevationDifference;
	private Integer movingTime;
	private Integer split;
}
