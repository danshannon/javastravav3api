package com.danshannon.strava.api.model;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ClubType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SportType;

/**
 * <p>Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class Club {
	public Club() {
		// Required
		super();
	}
	
	private Integer id;
	private ResourceState resourceState;
	private String name;
	private String profileMedium;
	private String profile;
	private String description;
	private ClubType clubType;
	private SportType sportType;
	private String city;
	private String state;
	private String country;
	/**
	 * NB is "private" in the API
	 */
	private Boolean privateClub;
	private Integer memberCount;
}
