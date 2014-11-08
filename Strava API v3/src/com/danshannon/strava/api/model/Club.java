package com.danshannon.strava.api.model;

import com.danshannon.strava.api.model.reference.ClubType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SportType;

/**
 * <p>Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public class Club {
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
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the profileMedium
	 */
	public String getProfileMedium() {
		return this.profileMedium;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return this.profile;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @return the clubType
	 */
	public ClubType getClubType() {
		return this.clubType;
	}
	/**
	 * @return the sportType
	 */
	public SportType getSportType() {
		return this.sportType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	/**
	 * @return the privateClub
	 */
	public Boolean getPrivateClub() {
		return this.privateClub;
	}
	/**
	 * @return the memberCount
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param profileMedium the profileMedium to set
	 */
	public void setProfileMedium(String profileMedium) {
		this.profileMedium = profileMedium;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param clubType the clubType to set
	 */
	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}
	/**
	 * @param sportType the sportType to set
	 */
	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @param privateClub the privateClub to set
	 */
	public void setPrivateClub(Boolean privateClub) {
		this.privateClub = privateClub;
	}
	/**
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
}
