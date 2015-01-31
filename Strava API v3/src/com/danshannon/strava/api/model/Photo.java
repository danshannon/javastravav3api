package com.danshannon.strava.api.model;

import java.util.Date;

import lombok.Data;

import com.danshannon.strava.api.model.reference.PhotoType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * @author Dan Shannon
 *
 */
@Data
public class Photo {
	public Photo() {
		// Required
		super();
	}
	
	private Integer id;
	private Integer activityId;
	private ResourceState resourceState;
	private String ref;
	private String uid;
	private String caption;
	private PhotoType type;
	private Date uploadedAt;
	private Date createdAt;
	private MapPoint location;
}
