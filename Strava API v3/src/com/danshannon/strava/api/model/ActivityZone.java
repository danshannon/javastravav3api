package com.danshannon.strava.api.model;

import java.util.List;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ActivityZoneType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
@Data
public class ActivityZone {
	public ActivityZone() {
		// Required
		super();
	}
	
	private Integer score;
	private List<ActivityZoneDistributionBucket> distributionBuckets;
	private ActivityZoneType type;
	private ResourceState resourceState;
	private Boolean sensorBased;
	private Integer points;
	private Boolean customZones;
	private Integer max;
}
