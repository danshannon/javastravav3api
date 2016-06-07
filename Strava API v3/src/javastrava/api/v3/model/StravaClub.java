package javastrava.api.v3.model;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaClubMembershipStatus;
import javastrava.api.v3.model.reference.StravaClubType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSportType;
import javastrava.cache.StravaCacheable;

/**
 * <p>
 * Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned
 * in summary or detailed {@link StravaResourceState representations}.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClub implements StravaCacheable<Integer>{
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
	 * NOT DOCUMENTED
	 */
	private Boolean featured;
	
	/**
	 * Membership status of the requesting athlete: 
	 * "member", "pending", null (not a member and have not requested join)
	 */
	private StravaClubMembershipStatus membership;
	
	/**
	 * Is <code>true</code> if the requesting user is an admin of the club
	 */
	private Boolean admin;
	
	/**
	 * Is <code>true</code> if the requesting athlete is the owner of the club
	 */
	private Boolean owner;
	
	/**
	 * total number of members the authenticated user is currently following
	 */
	private Integer followingCount;
	/**
	 * No args constructor
	 */
	public StravaClub() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StravaClub other = (StravaClub) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (clubType != other.clubType)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (featured == null) {
			if (other.featured != null)
				return false;
		} else if (!featured.equals(other.featured))
			return false;
		if (followingCount == null) {
			if (other.followingCount != null)
				return false;
		} else if (!followingCount.equals(other.followingCount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberCount == null) {
			if (other.memberCount != null)
				return false;
		} else if (!memberCount.equals(other.memberCount))
			return false;
		if (membership != other.membership)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (privateClub == null) {
			if (other.privateClub != null)
				return false;
		} else if (!privateClub.equals(other.privateClub))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (profileMedium == null) {
			if (other.profileMedium != null)
				return false;
		} else if (!profileMedium.equals(other.profileMedium))
			return false;
		if (resourceState != other.resourceState)
			return false;
		if (sportType != other.sportType)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @return the clubType
	 */
	public StravaClubType getClubType() {
		return this.clubType;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the memberCount
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the privateClub
	 */
	public Boolean getPrivateClub() {
		return this.privateClub;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return this.profile;
	}
	/**
	 * @return the profileMedium
	 */
	public String getProfileMedium() {
		return this.profileMedium;
	}
	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the sportType
	 */
	public StravaSportType getSportType() {
		return this.sportType;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((clubType == null) ? 0 : clubType.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((featured == null) ? 0 : featured.hashCode());
		result = prime * result + ((followingCount == null) ? 0 : followingCount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberCount == null) ? 0 : memberCount.hashCode());
		result = prime * result + ((membership == null) ? 0 : membership.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((privateClub == null) ? 0 : privateClub.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((profileMedium == null) ? 0 : profileMedium.hashCode());
		result = prime * result + ((resourceState == null) ? 0 : resourceState.hashCode());
		result = prime * result + ((sportType == null) ? 0 : sportType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	/**
	 * @param clubType the clubType to set
	 */
	public void setClubType(final StravaClubType clubType) {
		this.clubType = clubType;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(final Integer memberCount) {
		this.memberCount = memberCount;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @param privateClub the privateClub to set
	 */
	public void setPrivateClub(final Boolean privateClub) {
		this.privateClub = privateClub;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(final String profile) {
		this.profile = profile;
	}
	/**
	 * @param profileMedium the profileMedium to set
	 */
	public void setProfileMedium(final String profileMedium) {
		this.profileMedium = profileMedium;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param sportType the sportType to set
	 */
	public void setSportType(final StravaSportType sportType) {
		this.sportType = sportType;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StravaClub [id=" + id + ", resourceState=" + resourceState + ", name=" + name + ", profileMedium="
				+ profileMedium + ", profile=" + profile + ", description=" + description + ", clubType=" + clubType
				+ ", sportType=" + sportType + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", privateClub=" + privateClub + ", memberCount=" + memberCount + ", featured=" + featured
				+ ", membership=" + membership + ", admin=" + admin + ", owner=" + owner + ", followingCount="
				+ followingCount + "]";
	}
	public Boolean getFeatured() {
		return featured;
	}
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	public StravaClubMembershipStatus getMembership() {
		return membership;
	}
	public void setMembership(StravaClubMembershipStatus membership) {
		this.membership = membership;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean getOwner() {
		return owner;
	}
	public void setOwner(Boolean owner) {
		this.owner = owner;
	}
	public Integer getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}
}
