package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.model.reference.FrameType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * <p>Gear represents equipment used during an {@link Activity}.</p>
 * 
 * <p>The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class Gear {
	public Gear() {
		// Required
		super();
	}
	
	private String id;
	private Boolean primary;
	private String name;
	private Float distance;
	private String brandName;
	private String modelName;
	private FrameType frameType;
	private String description;
	private ResourceState resourceState;
}
