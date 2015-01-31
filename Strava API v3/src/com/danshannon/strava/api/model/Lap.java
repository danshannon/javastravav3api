package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * A lap within an {@link Activity}
 * 
 * @author Dan Shannon
 *
 */
@Data
public class Lap {
	public Lap() {
		// Required
		super();
	}
	
	private Integer id;
	private ResourceState resourceState;
	private String name;
	private Activity activity;
	private Athlete athlete;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
	private Integer startIndex; 
	private Integer endIndex;
	private Float totalElevationGain;
	private Float averageSpeed;
	private Float maxSpeed;
	private Float averageCadence;
	private Float averageWatts;
	private Boolean deviceWatts;
	private Float averageHeartrate;
	private Float maxHeartrate;
	private Integer lapIndex;
}
