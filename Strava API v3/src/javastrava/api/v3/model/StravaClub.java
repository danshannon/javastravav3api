package javastrava.api.v3.model;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaClubType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSportType;

/**
 * <p>
 * Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned
 * in summary or detailed {@link StravaResourceState representations}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaClub {
	/**
	 * Strava's unique identifier for this club
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Club name
	 */
	private String name;
	/**
	 * URL to a 62x62 pixel profile picture
	 */
	private String profileMedium;
	/**
	 * URL to a 124x124 pixel profile picture
	 */
	private String profile;
	/**
	 * Description of the club
	 */
	private String description;
	/**
	 * Type of club: casual_club, racing_team, shop, company, other
	 */
	private StravaClubType clubType;
	/**
	 * cycling, running, triathlon, other
	 */
	private StravaSportType sportType;
	/**
	 * City that the club is based in
	 */
	private String city;
	/**
	 * State, territory, county, canton etc. that the club is based in
	 */
	private String state;
	/**
	 * Country that the club is based in
	 */
	private String country;
	/**
	 * Is set to <code>true</code> if the club is private (and therefore you require permission to join)
	 */
	@SerializedName("private")
	private Boolean privateClub;
	/**
	 * Number of club members (calculated by Strava, not checked or re-calculated by javastrava)
	 */
	private Integer memberCount;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @return the profileMedium
	 */
	public String getProfileMedium() {
		return this.profileMedium;
	}
	/**
	 * @param profileMedium the profileMedium to set
	 */
	public void setProfileMedium(final String profileMedium) {
		this.profileMedium = profileMedium;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return this.profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(final String profile) {
		this.profile = profile;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 * @return the clubType
	 */
	public StravaClubType getClubType() {
		return this.clubType;
	}
	/**
	 * @param clubType the clubType to set
	 */
	public void setClubType(final StravaClubType clubType) {
		this.clubType = clubType;
	}
	/**
	 * @return the sportType
	 */
	public StravaSportType getSportType() {
		return this.sportType;
	}
	/**
	 * @param sportType the sportType to set
	 */
	public void setSportType(final StravaSportType sportType) {
		this.sportType = sportType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
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
	public void setState(final String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	/**
	 * @return the privateClub
	 */
	public Boolean getPrivateClub() {
		return this.privateClub;
	}
	/**
	 * @param privateClub the privateClub to set
	 */
	public void setPrivateClub(final Boolean privateClub) {
		this.privateClub = privateClub;
	}
	/**
	 * @return the memberCount
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}
	/**
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(final Integer memberCount) {
		this.memberCount = memberCount;
	}
}
