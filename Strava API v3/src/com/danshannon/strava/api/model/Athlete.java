package com.danshannon.strava.api.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import com.danshannon.strava.api.model.reference.FollowerState;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.model.reference.MeasurementMethod;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * Detailed representation of an Athlete
 * 
 * See http://strava.github.io/api/v3/athlete/
 * 
 * @author dshannon
 *
 */
@Data
public class Athlete {
	public Athlete() {
		// Required
		super();
	}
	
	private Integer id;
	private ResourceState resourceState;
	private String firstName;
	private String lastName;
	private String profileMedium;
	private String profile;
	private String city;
	private String state;
	private String country;
	private Gender sex;
	private FollowerState friend;
	private FollowerState follower;
	private Boolean premium;
	private Date createdAt;
	private Date updatedAt;
	private Boolean approveFollowers;
	private Integer followerCount;
	private Integer friendCount;
	private Integer mutualFriendCount;
	private String dateFormatPreference;
	private MeasurementMethod measurementPreference;
	private String email;
	private Integer ftp;
	private List<Club> clubs;
	private List<Gear> bikes;
	private List<Gear> shoes;
	private Float weight;
}
