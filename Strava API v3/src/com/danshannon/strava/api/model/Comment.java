package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author dshannon
 *
 */
@Data
public class Comment {
	public Comment() {
		// Required
		super();
	}
	
	private Integer id;
	private ResourceState resourceState;
	private Integer activityId;
	private String text;
	private Athlete athlete;
	private Date createdAt;
}
