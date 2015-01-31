package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
@Data
public class BestEffort {
	public BestEffort() {
		// Required
		super();
	}
	
	private Integer id;
	private ResourceState resourceState;
	private String name;
	private Segment segment;
	private Activity activity;
	private Athlete athlete;
	private Integer komRank;
	private Integer prRank;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
}
