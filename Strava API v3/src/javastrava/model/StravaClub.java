package javastrava.model;

import com.google.gson.annotations.SerializedName;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaClubMembershipStatus;
import javastrava.model.reference.StravaClubType;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSportType;

/**
 * <p>
 * Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned in summary or detailed
 * {@link StravaResourceState representations}.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClub implements StravaCacheableEntity<Integer> {

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

	private String	city;
	/**
	 * State, territory, county, canton etc. that the club is based in
	 */

	private String	state;
	/**
	 * Country that the club is based in
	 */

	private String	country;
	/**
	 * Is set to <code>true</code> if the club is private (and therefore you require permission to join)
	 */

	@SerializedName("private")
	private Boolean	privateClub;
	/**
	 * Number of club members (calculated by Strava, not checked or re-calculated by javastrava)
	 */

	private Integer	memberCount;

	/**
	 * NOT DOCUMENTED
	 */
	private Boolean featured;

	/**
	 * Membership status of the requesting athlete: "member", "pending", null (not a member and have not requested join)
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
	 * Vanity URL for the club
	 */
	private String url;

	/**
	 * Undocumented
	 */
	private Boolean verified;

	/**
	 * Undocumented
	 */
	private Integer postCount;

	/**
	 * URL to a ~1185x580 pixel cover photo
	 */
	private String coverPhoto;

	/**
	 * URL to a ~360x176 pixel cover photo
	 */
	private String coverPhotoSmall;

	/**
	 * No args constructor
	 */
	public StravaClub() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaClub)) {
			return false;
		}
		final StravaClub other = (StravaClub) obj;
		if (this.admin == null) {
			if (other.admin != null) {
				return false;
			}
		} else if (!this.admin.equals(other.admin)) {
			return false;
		}
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.clubType != other.clubType) {
			return false;
		}
		if (this.country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!this.country.equals(other.country)) {
			return false;
		}
		if (this.coverPhoto == null) {
			if (other.coverPhoto != null) {
				return false;
			}
		} else if (!this.coverPhoto.equals(other.coverPhoto)) {
			return false;
		}
		if (this.coverPhotoSmall == null) {
			if (other.coverPhotoSmall != null) {
				return false;
			}
		} else if (!this.coverPhotoSmall.equals(other.coverPhotoSmall)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.featured == null) {
			if (other.featured != null) {
				return false;
			}
		} else if (!this.featured.equals(other.featured)) {
			return false;
		}
		if (this.followingCount == null) {
			if (other.followingCount != null) {
				return false;
			}
		} else if (!this.followingCount.equals(other.followingCount)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.memberCount == null) {
			if (other.memberCount != null) {
				return false;
			}
		} else if (!this.memberCount.equals(other.memberCount)) {
			return false;
		}
		if (this.membership != other.membership) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!this.owner.equals(other.owner)) {
			return false;
		}
		if (this.postCount == null) {
			if (other.postCount != null) {
				return false;
			}
		} else if (!this.postCount.equals(other.postCount)) {
			return false;
		}
		if (this.privateClub == null) {
			if (other.privateClub != null) {
				return false;
			}
		} else if (!this.privateClub.equals(other.privateClub)) {
			return false;
		}
		if (this.profile == null) {
			if (other.profile != null) {
				return false;
			}
		} else if (!this.profile.equals(other.profile)) {
			return false;
		}
		if (this.profileMedium == null) {
			if (other.profileMedium != null) {
				return false;
			}
		} else if (!this.profileMedium.equals(other.profileMedium)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.sportType != other.sportType) {
			return false;
		}
		if (this.state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!this.state.equals(other.state)) {
			return false;
		}
		if (this.url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!this.url.equals(other.url)) {
			return false;
		}
		if (this.verified == null) {
			if (other.verified != null) {
				return false;
			}
		} else if (!this.verified.equals(other.verified)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the admin status of the authenticated athlete
	 */
	public Boolean getAdmin() {
		return this.admin;
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
	 * @return the coverPhoto
	 */
	public String getCoverPhoto() {
		return this.coverPhoto;
	}

	/**
	 * @return the coverPhotoSmall
	 */
	public String getCoverPhotoSmall() {
		return this.coverPhotoSmall;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the featured flag
	 */
	public Boolean getFeatured() {
		return this.featured;
	}

	/**
	 * @return follower count
	 */
	public Integer getFollowingCount() {
		return this.followingCount;
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
	 * @return the membership status of the authenticated athlete
	 */
	public StravaClubMembershipStatus getMembership() {
		return this.membership;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return true if the authenticated athlete is the owner of the club
	 */
	public Boolean getOwner() {
		return this.owner;
	}

	/**
	 * @return the postCount
	 */
	public Integer getPostCount() {
		return this.postCount;
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

	/**
	 * @return The vanity URL for the club
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * @return the verified
	 */
	public Boolean getVerified() {
		return this.verified;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.admin == null) ? 0 : this.admin.hashCode());
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.clubType == null) ? 0 : this.clubType.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.coverPhoto == null) ? 0 : this.coverPhoto.hashCode());
		result = (prime * result) + ((this.coverPhotoSmall == null) ? 0 : this.coverPhotoSmall.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.featured == null) ? 0 : this.featured.hashCode());
		result = (prime * result) + ((this.followingCount == null) ? 0 : this.followingCount.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.memberCount == null) ? 0 : this.memberCount.hashCode());
		result = (prime * result) + ((this.membership == null) ? 0 : this.membership.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.owner == null) ? 0 : this.owner.hashCode());
		result = (prime * result) + ((this.postCount == null) ? 0 : this.postCount.hashCode());
		result = (prime * result) + ((this.privateClub == null) ? 0 : this.privateClub.hashCode());
		result = (prime * result) + ((this.profile == null) ? 0 : this.profile.hashCode());
		result = (prime * result) + ((this.profileMedium == null) ? 0 : this.profileMedium.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.sportType == null) ? 0 : this.sportType.hashCode());
		result = (prime * result) + ((this.state == null) ? 0 : this.state.hashCode());
		result = (prime * result) + ((this.url == null) ? 0 : this.url.hashCode());
		result = (prime * result) + ((this.verified == null) ? 0 : this.verified.hashCode());
		return result;
	}

	/**
	 * @param admin
	 *            the admin status of the authenticated athlete
	 */
	public void setAdmin(final Boolean admin) {
		this.admin = admin;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	public void setClubType(final StravaClubType clubType) {
		this.clubType = clubType;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @param coverPhoto
	 *            the coverPhoto to set
	 */
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	/**
	 * @param coverPhotoSmall
	 *            the coverPhotoSmall to set
	 */
	public void setCoverPhotoSmall(String coverPhotoSmall) {
		this.coverPhotoSmall = coverPhotoSmall;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param featured
	 *            the featured flag
	 */
	public void setFeatured(final Boolean featured) {
		this.featured = featured;
	}

	/**
	 * @param followingCount
	 *            follower count
	 */
	public void setFollowingCount(final Integer followingCount) {
		this.followingCount = followingCount;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param memberCount
	 *            the memberCount to set
	 */
	public void setMemberCount(final Integer memberCount) {
		this.memberCount = memberCount;
	}

	/**
	 * @param membership
	 *            the membership status
	 */
	public void setMembership(final StravaClubMembershipStatus membership) {
		this.membership = membership;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param owner
	 *            true if the authenticated athlete is the owner of the club
	 */
	public void setOwner(final Boolean owner) {
		this.owner = owner;
	}

	/**
	 * @param postCount
	 *            the postCount to set
	 */
	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	/**
	 * @param privateClub
	 *            the privateClub to set
	 */
	public void setPrivateClub(final Boolean privateClub) {
		this.privateClub = privateClub;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(final String profile) {
		this.profile = profile;
	}

	/**
	 * @param profileMedium
	 *            the profileMedium to set
	 */
	public void setProfileMedium(final String profileMedium) {
		this.profileMedium = profileMedium;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param sportType
	 *            the sportType to set
	 */
	public void setSportType(final StravaSportType sportType) {
		this.sportType = sportType;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param url
	 *            The Vanity URL for the club
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param verified
	 *            the verified to set
	 */
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "StravaClub [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", profileMedium=" + this.profileMedium + ", profile=" + this.profile + ", description=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.description + ", clubType=" + this.clubType + ", sportType=" + this.sportType + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", privateClub=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.privateClub + ", memberCount=" + this.memberCount + ", featured=" + this.featured + ", membership=" + this.membership + ", admin=" + this.admin + ", owner=" + this.owner //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", followingCount=" + this.followingCount + ", url=" + this.url + ", verified=" + this.verified + ", postCount=" + this.postCount + ", coverPhoto=" + this.coverPhoto //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", coverPhotoSmall=" + this.coverPhotoSmall + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
