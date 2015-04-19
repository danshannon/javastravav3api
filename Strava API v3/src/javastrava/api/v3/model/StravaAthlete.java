package javastrava.api.v3.model;

import java.time.ZonedDateTime;
import java.util.List;

import javastrava.api.v3.model.reference.StravaFollowerState;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.cache.StravaCacheable;

/**
 * Detailed representation of an StravaAthlete
 * 
 * See http://strava.github.io/api/v3/athlete/
 * 
 * @author Dan Shannon
 *
 */
public class StravaAthlete implements StravaCacheable<Integer>{
	/**
	 * no args constructor
	 */
	public StravaAthlete() {
		super();
	}
	private Integer id;
	private StravaResourceState resourceState;
	private String firstname;
	private String lastname;
	private String profileMedium;
	private String profile;
	private String city;
	private String state;
	private String country;
	private StravaGender sex;
	private StravaFollowerState friend;
	private StravaFollowerState follower;
	private Boolean premium;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	private Boolean approveFollowers;
	private Integer followerCount;
	private Integer friendCount;
	private Integer mutualFriendCount;
	private String datePreference;
	private StravaMeasurementMethod measurementPreference;
	private String email;
	private Integer ftp;
	private List<StravaClub> clubs;
	private List<StravaGear> bikes;
	private List<StravaGear> shoes;
	private Float weight;
	private Integer badgeTypeId;
	/**
	 * @return the id
	 */
	@Override
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(final String lastname) {
		this.lastname = lastname;
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
	 * @return the sex
	 */
	public StravaGender getSex() {
		return this.sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(final StravaGender sex) {
		this.sex = sex;
	}
	/**
	 * @return the friend
	 */
	public StravaFollowerState getFriend() {
		return this.friend;
	}
	/**
	 * @param friend the friend to set
	 */
	public void setFriend(final StravaFollowerState friend) {
		this.friend = friend;
	}
	/**
	 * @return the follower
	 */
	public StravaFollowerState getFollower() {
		return this.follower;
	}
	/**
	 * @param follower the follower to set
	 */
	public void setFollower(final StravaFollowerState follower) {
		this.follower = follower;
	}
	/**
	 * @return the premium
	 */
	public Boolean getPremium() {
		return this.premium;
	}
	/**
	 * @param premium the premium to set
	 */
	public void setPremium(final Boolean premium) {
		this.premium = premium;
	}
	/**
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public ZonedDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(final ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the approveFollowers
	 */
	public Boolean getApproveFollowers() {
		return this.approveFollowers;
	}
	/**
	 * @param approveFollowers the approveFollowers to set
	 */
	public void setApproveFollowers(final Boolean approveFollowers) {
		this.approveFollowers = approveFollowers;
	}
	/**
	 * @return the followerCount
	 */
	public Integer getFollowerCount() {
		return this.followerCount;
	}
	/**
	 * @param followerCount the followerCount to set
	 */
	public void setFollowerCount(final Integer followerCount) {
		this.followerCount = followerCount;
	}
	/**
	 * @return the friendCount
	 */
	public Integer getFriendCount() {
		return this.friendCount;
	}
	/**
	 * @param friendCount the friendCount to set
	 */
	public void setFriendCount(final Integer friendCount) {
		this.friendCount = friendCount;
	}
	/**
	 * @return the mutualFriendCount
	 */
	public Integer getMutualFriendCount() {
		return this.mutualFriendCount;
	}
	/**
	 * @param mutualFriendCount the mutualFriendCount to set
	 */
	public void setMutualFriendCount(final Integer mutualFriendCount) {
		this.mutualFriendCount = mutualFriendCount;
	}
	/**
	 * @return the datePreference
	 */
	public String getDatePreference() {
		return this.datePreference;
	}
	/**
	 * @param datePreference the datePreference to set
	 */
	public void setDatePreference(final String datePreference) {
		this.datePreference = datePreference;
	}
	/**
	 * @return the measurementPreference
	 */
	public StravaMeasurementMethod getMeasurementPreference() {
		return this.measurementPreference;
	}
	/**
	 * @param measurementPreference the measurementPreference to set
	 */
	public void setMeasurementPreference(final StravaMeasurementMethod measurementPreference) {
		this.measurementPreference = measurementPreference;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
	/**
	 * @return the ftp
	 */
	public Integer getFtp() {
		return this.ftp;
	}
	/**
	 * @param ftp the ftp to set
	 */
	public void setFtp(final Integer ftp) {
		this.ftp = ftp;
	}
	/**
	 * @return the clubs
	 */
	public List<StravaClub> getClubs() {
		return this.clubs;
	}
	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(final List<StravaClub> clubs) {
		this.clubs = clubs;
	}
	/**
	 * @return the bikes
	 */
	public List<StravaGear> getBikes() {
		return this.bikes;
	}
	/**
	 * @param bikes the bikes to set
	 */
	public void setBikes(final List<StravaGear> bikes) {
		this.bikes = bikes;
	}
	/**
	 * @return the shoes
	 */
	public List<StravaGear> getShoes() {
		return this.shoes;
	}
	/**
	 * @param shoes the shoes to set
	 */
	public void setShoes(final List<StravaGear> shoes) {
		this.shoes = shoes;
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
	public void setWeight(final Float weight) {
		this.weight = weight;
	}
	/**
	 * @return the badgeTypeId
	 */
	public Integer getBadgeTypeId() {
		return this.badgeTypeId;
	}
	/**
	 * @param badgeTypeId the badgeTypeId to set
	 */
	public void setBadgeTypeId(final Integer badgeTypeId) {
		this.badgeTypeId = badgeTypeId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaAthlete [id=" + this.id + ", resourceState=" + this.resourceState + ", firstname=" + this.firstname + ", lastname=" + this.lastname //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", profileMedium=" + this.profileMedium + ", profile=" + this.profile + ", city=" + this.city + ", state=" + this.state + ", country=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.country + ", sex=" + this.sex + ", friend=" + this.friend + ", follower=" + this.follower + ", premium=" + this.premium + ", createdAt=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.createdAt + ", updatedAt=" + this.updatedAt + ", approveFollowers=" + this.approveFollowers + ", followerCount=" + this.followerCount //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", friendCount=" + this.friendCount + ", mutualFriendCount=" + this.mutualFriendCount + ", datePreference=" + this.datePreference //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", measurementPreference=" + this.measurementPreference + ", email=" + this.email + ", ftp=" + this.ftp + ", clubs=" + this.clubs + ", bikes=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.bikes + ", shoes=" + this.shoes + ", weight=" + this.weight + ", badgeTypeId=" + this.badgeTypeId + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.approveFollowers == null) ? 0 : this.approveFollowers.hashCode());
		result = prime * result + ((this.badgeTypeId == null) ? 0 : this.badgeTypeId.hashCode());
		result = prime * result + ((this.bikes == null) ? 0 : this.bikes.hashCode());
		result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
		result = prime * result + ((this.clubs == null) ? 0 : this.clubs.hashCode());
		result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
		result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = prime * result + ((this.datePreference == null) ? 0 : this.datePreference.hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = prime * result + ((this.follower == null) ? 0 : this.follower.hashCode());
		result = prime * result + ((this.followerCount == null) ? 0 : this.followerCount.hashCode());
		result = prime * result + ((this.friend == null) ? 0 : this.friend.hashCode());
		result = prime * result + ((this.friendCount == null) ? 0 : this.friendCount.hashCode());
		result = prime * result + ((this.ftp == null) ? 0 : this.ftp.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.lastname == null) ? 0 : this.lastname.hashCode());
		result = prime * result + ((this.measurementPreference == null) ? 0 : this.measurementPreference.hashCode());
		result = prime * result + ((this.mutualFriendCount == null) ? 0 : this.mutualFriendCount.hashCode());
		result = prime * result + ((this.premium == null) ? 0 : this.premium.hashCode());
		result = prime * result + ((this.profile == null) ? 0 : this.profile.hashCode());
		result = prime * result + ((this.profileMedium == null) ? 0 : this.profileMedium.hashCode());
		result = prime * result + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = prime * result + ((this.sex == null) ? 0 : this.sex.hashCode());
		result = prime * result + ((this.shoes == null) ? 0 : this.shoes.hashCode());
		result = prime * result + ((this.state == null) ? 0 : this.state.hashCode());
		result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
		return result;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaAthlete)) {
			return false;
		}
		StravaAthlete other = (StravaAthlete) obj;
		if (this.approveFollowers == null) {
			if (other.approveFollowers != null) {
				return false;
			}
		} else if (!this.approveFollowers.equals(other.approveFollowers)) {
			return false;
		}
		if (this.badgeTypeId == null) {
			if (other.badgeTypeId != null) {
				return false;
			}
		} else if (!this.badgeTypeId.equals(other.badgeTypeId)) {
			return false;
		}
		if (this.bikes == null) {
			if (other.bikes != null) {
				return false;
			}
		} else if (!this.bikes.equals(other.bikes)) {
			return false;
		}
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.clubs == null) {
			if (other.clubs != null) {
				return false;
			}
		} else if (!this.clubs.equals(other.clubs)) {
			return false;
		}
		if (this.country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!this.country.equals(other.country)) {
			return false;
		}
		if (this.createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!this.createdAt.equals(other.createdAt)) {
			return false;
		}
		if (this.datePreference == null) {
			if (other.datePreference != null) {
				return false;
			}
		} else if (!this.datePreference.equals(other.datePreference)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.firstname == null) {
			if (other.firstname != null) {
				return false;
			}
		} else if (!this.firstname.equals(other.firstname)) {
			return false;
		}
		if (this.follower != other.follower) {
			return false;
		}
		if (this.followerCount == null) {
			if (other.followerCount != null) {
				return false;
			}
		} else if (!this.followerCount.equals(other.followerCount)) {
			return false;
		}
		if (this.friend != other.friend) {
			return false;
		}
		if (this.friendCount == null) {
			if (other.friendCount != null) {
				return false;
			}
		} else if (!this.friendCount.equals(other.friendCount)) {
			return false;
		}
		if (this.ftp == null) {
			if (other.ftp != null) {
				return false;
			}
		} else if (!this.ftp.equals(other.ftp)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.lastname == null) {
			if (other.lastname != null) {
				return false;
			}
		} else if (!this.lastname.equals(other.lastname)) {
			return false;
		}
		if (this.measurementPreference != other.measurementPreference) {
			return false;
		}
		if (this.mutualFriendCount == null) {
			if (other.mutualFriendCount != null) {
				return false;
			}
		} else if (!this.mutualFriendCount.equals(other.mutualFriendCount)) {
			return false;
		}
		if (this.premium == null) {
			if (other.premium != null) {
				return false;
			}
		} else if (!this.premium.equals(other.premium)) {
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
		if (this.sex != other.sex) {
			return false;
		}
		if (this.shoes == null) {
			if (other.shoes != null) {
				return false;
			}
		} else if (!this.shoes.equals(other.shoes)) {
			return false;
		}
		if (this.state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!this.state.equals(other.state)) {
			return false;
		}
		if (this.updatedAt == null) {
			if (other.updatedAt != null) {
				return false;
			}
		} else if (!this.updatedAt.equals(other.updatedAt)) {
			return false;
		}
		if (this.weight == null) {
			if (other.weight != null) {
				return false;
			}
		} else if (!this.weight.equals(other.weight)) {
			return false;
		}
		return true;
	}
}