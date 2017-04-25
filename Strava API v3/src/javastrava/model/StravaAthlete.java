package javastrava.model;

import java.time.ZonedDateTime;
import java.util.List;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaAthleteType;
import javastrava.model.reference.StravaFollowerState;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaMeasurementMethod;
import javastrava.model.reference.StravaResourceState;

/**
 * Detailed representation of an StravaAthlete
 *
 * See http://strava.github.io/api/v3/athlete/
 *
 * @author Dan Shannon
 *
 */
public class StravaAthlete implements StravaCacheableEntity<Integer> {
	/**
	 * Unique id of the athlete
	 */
	private Integer id;

	/**
	 * State of this representation
	 */
	private StravaResourceState resourceState;

	/**
	 * Athlete's first name
	 */
	private String firstname;

	/**
	 * Athlete's last name
	 */
	private String lastname;

	/**
	 * Profile photo (medium size)
	 */
	private String profileMedium;

	/**
	 * Profile photo (full size)
	 */
	private String profile;

	/**
	 * City the athlete lives in
	 */
	private String city;

	/**
	 * State, county, canton etc that the athlete lives in
	 */
	private String state;

	/**
	 * Country the athlete lives in
	 */
	private String country;

	/**
	 * Athlete's gender
	 */
	private StravaGender sex;

	/**
	 * Is this athlete a friend of the authenticated athlete on Strava? (That is, does the authenticated athlete follow this athlete)
	 */
	private StravaFollowerState friend;

	/**
	 * Is this athlete following the authenticated athlete on Strava?
	 */
	private StravaFollowerState follower;

	/**
	 * Is this athlete a premium Strava customer?
	 */
	private Boolean premium;

	/**
	 * Date and time the athlete's account was created on Strava
	 */
	private ZonedDateTime createdAt;

	/**
	 * Date and time the athlete's account was last updated on Strava
	 */
	private ZonedDateTime updatedAt;

	/**
	 * Does the athlete require approval of other athletes who have requested to follow them?
	 */
	private Boolean approveFollowers;

	/**
	 * Number of athletes following this athlete on Strava
	 */
	private Integer followerCount;

	/**
	 * Number of athletes this athlete is following on Strava
	 */
	private Integer friendCount;

	/**
	 * Number of athletes being followed by both this athlete and the authenticated athlete
	 */
	private Integer mutualFriendCount;

	/**
	 * Date format preference
	 */
	private String datePreference;

	/**
	 * Measurement preference (metric or imperial)
	 */
	private StravaMeasurementMethod measurementPreference;

	/**
	 * Athlete's email address (and username)
	 */
	private String email;

	/**
	 * Functional Threshold Power (in watts)
	 */
	private Integer ftp;

	/**
	 * Clubs the athlete belongs to
	 */
	private List<StravaClub> clubs;

	/**
	 * Bikes the athlete has used on activities
	 */
	private List<StravaGear> bikes;

	/**
	 * Shoes the athlete has used on runs
	 */
	private List<StravaGear> shoes;

	/**
	 * Athlete's weight (in kilograms)
	 */
	private Float weight;

	/**
	 * ???
	 */
	private Integer badgeTypeId;

	/**
	 * Athlete's default sport type
	 */
	private StravaAthleteType athleteType;

	/**
	 * Athlete user name
	 */
	private String username;

	/**
	 * no args constructor
	 */
	public StravaAthlete() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaAthlete)) {
			return false;
		}
		final StravaAthlete other = (StravaAthlete) obj;
		if (this.approveFollowers == null) {
			if (other.approveFollowers != null) {
				return false;
			}
		} else if (!this.approveFollowers.equals(other.approveFollowers)) {
			return false;
		}
		if (this.athleteType != other.athleteType) {
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
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
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

	/**
	 * @return the approveFollowers
	 */
	public Boolean getApproveFollowers() {
		return this.approveFollowers;
	}

	/**
	 * @return the athleteType
	 */
	public StravaAthleteType getAthleteType() {
		return this.athleteType;
	}

	/**
	 * @return the badgeTypeId
	 */
	public Integer getBadgeTypeId() {
		return this.badgeTypeId;
	}

	/**
	 * @return the bikes
	 */
	public List<StravaGear> getBikes() {
		return this.bikes;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return the clubs
	 */
	public List<StravaClub> getClubs() {
		return this.clubs;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * @return the datePreference
	 */
	public String getDatePreference() {
		return this.datePreference;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * @return the follower
	 */
	public StravaFollowerState getFollower() {
		return this.follower;
	}

	/**
	 * @return the followerCount
	 */
	public Integer getFollowerCount() {
		return this.followerCount;
	}

	/**
	 * @return the friend
	 */
	public StravaFollowerState getFriend() {
		return this.friend;
	}

	/**
	 * @return the friendCount
	 */
	public Integer getFriendCount() {
		return this.friendCount;
	}

	/**
	 * @return the ftp
	 */
	public Integer getFtp() {
		return this.ftp;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * @return the measurementPreference
	 */
	public StravaMeasurementMethod getMeasurementPreference() {
		return this.measurementPreference;
	}

	/**
	 * @return the mutualFriendCount
	 */
	public Integer getMutualFriendCount() {
		return this.mutualFriendCount;
	}

	/**
	 * @return the premium
	 */
	public Boolean getPremium() {
		return this.premium;
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
	 * @return the sex
	 */
	public StravaGender getSex() {
		return this.sex;
	}

	/**
	 * @return the shoes
	 */
	public List<StravaGear> getShoes() {
		return this.shoes;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @return the updatedAt
	 */
	public ZonedDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return the weight
	 */
	public Float getWeight() {
		return this.weight;
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
		result = (prime * result) + ((this.approveFollowers == null) ? 0 : this.approveFollowers.hashCode());
		result = (prime * result) + ((this.athleteType == null) ? 0 : this.athleteType.hashCode());
		result = (prime * result) + ((this.badgeTypeId == null) ? 0 : this.badgeTypeId.hashCode());
		result = (prime * result) + ((this.bikes == null) ? 0 : this.bikes.hashCode());
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.clubs == null) ? 0 : this.clubs.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.datePreference == null) ? 0 : this.datePreference.hashCode());
		result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
		result = (prime * result) + ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = (prime * result) + ((this.follower == null) ? 0 : this.follower.hashCode());
		result = (prime * result) + ((this.followerCount == null) ? 0 : this.followerCount.hashCode());
		result = (prime * result) + ((this.friend == null) ? 0 : this.friend.hashCode());
		result = (prime * result) + ((this.friendCount == null) ? 0 : this.friendCount.hashCode());
		result = (prime * result) + ((this.ftp == null) ? 0 : this.ftp.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.lastname == null) ? 0 : this.lastname.hashCode());
		result = (prime * result) + ((this.measurementPreference == null) ? 0 : this.measurementPreference.hashCode());
		result = (prime * result) + ((this.mutualFriendCount == null) ? 0 : this.mutualFriendCount.hashCode());
		result = (prime * result) + ((this.premium == null) ? 0 : this.premium.hashCode());
		result = (prime * result) + ((this.profile == null) ? 0 : this.profile.hashCode());
		result = (prime * result) + ((this.profileMedium == null) ? 0 : this.profileMedium.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.sex == null) ? 0 : this.sex.hashCode());
		result = (prime * result) + ((this.shoes == null) ? 0 : this.shoes.hashCode());
		result = (prime * result) + ((this.state == null) ? 0 : this.state.hashCode());
		result = (prime * result) + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		result = (prime * result) + ((this.username == null) ? 0 : this.username.hashCode());
		result = (prime * result) + ((this.weight == null) ? 0 : this.weight.hashCode());
		return result;
	}

	/**
	 * @param approveFollowers
	 *            the approveFollowers to set
	 */
	public void setApproveFollowers(final Boolean approveFollowers) {
		this.approveFollowers = approveFollowers;
	}

	/**
	 * @param athleteType
	 *            the athleteType to set
	 */
	public void setAthleteType(final StravaAthleteType athleteType) {
		this.athleteType = athleteType;
	}

	/**
	 * @param badgeTypeId
	 *            the badgeTypeId to set
	 */
	public void setBadgeTypeId(final Integer badgeTypeId) {
		this.badgeTypeId = badgeTypeId;
	}

	/**
	 * @param bikes
	 *            the bikes to set
	 */
	public void setBikes(final List<StravaGear> bikes) {
		this.bikes = bikes;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param clubs
	 *            the clubs to set
	 */
	public void setClubs(final List<StravaClub> clubs) {
		this.clubs = clubs;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param datePreference
	 *            the datePreference to set
	 */
	public void setDatePreference(final String datePreference) {
		this.datePreference = datePreference;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param follower
	 *            the follower to set
	 */
	public void setFollower(final StravaFollowerState follower) {
		this.follower = follower;
	}

	/**
	 * @param followerCount
	 *            the followerCount to set
	 */
	public void setFollowerCount(final Integer followerCount) {
		this.followerCount = followerCount;
	}

	/**
	 * @param friend
	 *            the friend to set
	 */
	public void setFriend(final StravaFollowerState friend) {
		this.friend = friend;
	}

	/**
	 * @param friendCount
	 *            the friendCount to set
	 */
	public void setFriendCount(final Integer friendCount) {
		this.friendCount = friendCount;
	}

	/**
	 * @param ftp
	 *            the ftp to set
	 */
	public void setFtp(final Integer ftp) {
		this.ftp = ftp;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param measurementPreference
	 *            the measurementPreference to set
	 */
	public void setMeasurementPreference(final StravaMeasurementMethod measurementPreference) {
		this.measurementPreference = measurementPreference;
	}

	/**
	 * @param mutualFriendCount
	 *            the mutualFriendCount to set
	 */
	public void setMutualFriendCount(final Integer mutualFriendCount) {
		this.mutualFriendCount = mutualFriendCount;
	}

	/**
	 * @param premium
	 *            the premium to set
	 */
	public void setPremium(final Boolean premium) {
		this.premium = premium;
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
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(final StravaGender sex) {
		this.sex = sex;
	}

	/**
	 * @param shoes
	 *            the shoes to set
	 */
	public void setShoes(final List<StravaGear> shoes) {
		this.shoes = shoes;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(final Float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "StravaAthlete [id=" + this.id + ", resourceState=" + this.resourceState + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", profileMedium=" + this.profileMedium //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", profile=" + this.profile + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", sex=" + this.sex + ", friend=" + this.friend + ", follower=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				+ this.follower + ", premium=" + this.premium + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", approveFollowers=" + this.approveFollowers + ", followerCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.followerCount + ", friendCount=" + this.friendCount + ", mutualFriendCount=" + this.mutualFriendCount + ", datePreference=" + this.datePreference + ", measurementPreference=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.measurementPreference + ", email=" + this.email + ", ftp=" + this.ftp + ", clubs=" + this.clubs + ", bikes=" + this.bikes + ", shoes=" + this.shoes + ", weight=" + this.weight //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ ", badgeTypeId=" + this.badgeTypeId + ", athleteType=" + this.athleteType + ", username=" + this.username + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
