package com.danshannon.strava.api.model;

import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class ActivityZoneDistributionBucket {
	private Integer max;
	private Integer min;
	private Integer time;
	
	public ActivityZoneDistributionBucket() {
		// Required
		super();
	}
}
