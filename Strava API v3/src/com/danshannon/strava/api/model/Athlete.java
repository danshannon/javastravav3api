package com.danshannon.strava.api.model;

import java.util.Date;
import java.util.List;

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
public class Athlete {
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
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
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	/**
	 * @return the sex
	 */
	public Gender getSex() {
		return this.sex;
	}
	/**
	 * @return the friend
	 */
	public FollowerState getFriend() {
		return this.friend;
	}
	/**
	 * @return the follower
	 */
	public FollowerState getFollower() {
		return this.follower;
	}
	/**
	 * @return the premium
	 */
	public Boolean getPremium() {
		return this.premium;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return this.createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	/**
	 * @return the approveFollowers
	 */
	public Boolean getApproveFollowers() {
		return this.approveFollowers;
	}
	/**
	 * @return the followerCount
	 */
	public Integer getFollowerCount() {
		return this.followerCount;
	}
	/**
	 * @return the friendCount
	 */
	public Integer getFriendCount() {
		return this.friendCount;
	}
	/**
	 * @return the mutualFriendCount
	 */
	public Integer getMutualFriendCount() {
		return this.mutualFriendCount;
	}
	/**
	 * @return the dateFormatPreference
	 */
	public String getDateFormatPreference() {
		return this.dateFormatPreference;
	}
	/**
	 * @return the measurementPreference
	 */
	public MeasurementMethod getMeasurementPreference() {
		return this.measurementPreference;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @return the ftp
	 */
	public Integer getFtp() {
		return this.ftp;
	}
	/**
	 * @return the clubs
	 */
	public List<Club> getClubs() {
		return this.clubs;
	}
	/**
	 * @return the bikes
	 */
	public List<Gear> getBikes() {
		return this.bikes;
	}
	/**
	 * @return the shoes
	 */
	public List<Gear> getShoes() {
		return this.shoes;
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
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Gender sex) {
		this.sex = sex;
	}
	/**
	 * @param friend the friend to set
	 */
	public void setFriend(FollowerState friend) {
		this.friend = friend;
	}
	/**
	 * @param follower the follower to set
	 */
	public void setFollower(FollowerState follower) {
		this.follower = follower;
	}
	/**
	 * @param premium the premium to set
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @param approveFollowers the approveFollowers to set
	 */
	public void setApproveFollowers(Boolean approveFollowers) {
		this.approveFollowers = approveFollowers;
	}
	/**
	 * @param followerCount the followerCount to set
	 */
	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
	/**
	 * @param friendCount the friendCount to set
	 */
	public void setFriendCount(Integer friendCount) {
		this.friendCount = friendCount;
	}
	/**
	 * @param mutualFriendCount the mutualFriendCount to set
	 */
	public void setMutualFriendCount(Integer mutualFriendCount) {
		this.mutualFriendCount = mutualFriendCount;
	}
	/**
	 * @param dateFormatPreference the dateFormatPreference to set
	 */
	public void setDateFormatPreference(String dateFormatPreference) {
		this.dateFormatPreference = dateFormatPreference;
	}
	/**
	 * @param measurementPreference the measurementPreference to set
	 */
	public void setMeasurementPreference(MeasurementMethod measurementPreference) {
		this.measurementPreference = measurementPreference;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param ftp the ftp to set
	 */
	public void setFtp(Integer ftp) {
		this.ftp = ftp;
	}
	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	/**
	 * @param bikes the bikes to set
	 */
	public void setBikes(List<Gear> bikes) {
		this.bikes = bikes;
	}
	/**
	 * @param shoes the shoes to set
	 */
	public void setShoes(List<Gear> shoes) {
		this.shoes = shoes;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Athlete [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.firstName != null ? "firstName=" + this.firstName + ", " : "")
				+ (this.lastName != null ? "lastName=" + this.lastName + ", " : "")
				+ (this.profileMedium != null ? "profileMedium=" + this.profileMedium + ", " : "")
				+ (this.profile != null ? "profile=" + this.profile + ", " : "")
				+ (this.city != null ? "city=" + this.city + ", " : "") + (this.state != null ? "state=" + this.state + ", " : "")
				+ (this.country != null ? "country=" + this.country + ", " : "")
				+ (this.sex != null ? "sex=" + this.sex + ", " : "") + (this.friend != null ? "friend=" + this.friend + ", " : "")
				+ (this.follower != null ? "follower=" + this.follower + ", " : "")
				+ (this.premium != null ? "premium=" + this.premium + ", " : "")
				+ (this.createdAt != null ? "createdAt=" + this.createdAt + ", " : "")
				+ (this.updatedAt != null ? "updatedAt=" + this.updatedAt + ", " : "")
				+ (this.approveFollowers != null ? "approveFollowers=" + this.approveFollowers + ", " : "")
				+ (this.followerCount != null ? "followerCount=" + this.followerCount + ", " : "")
				+ (this.friendCount != null ? "friendCount=" + this.friendCount + ", " : "")
				+ (this.mutualFriendCount != null ? "mutualFriendCount=" + this.mutualFriendCount + ", " : "")
				+ (this.dateFormatPreference != null ? "dateFormatPreference=" + this.dateFormatPreference + ", " : "")
				+ (this.measurementPreference != null ? "measurementPreference=" + this.measurementPreference + ", " : "")
				+ (this.email != null ? "email=" + this.email + ", " : "") + (this.ftp != null ? "ftp=" + this.ftp + ", " : "")
				+ (this.clubs != null ? "clubs=" + this.clubs + ", " : "")
				+ (this.bikes != null ? "bikes=" + this.bikes + ", " : "") + (this.shoes != null ? "shoes=" + this.shoes : "")
				+ "]";
	}
	/**
	 * @return the weight
	 */
	public Float getWeight() {
		return this.weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
}
