package javastrava.api.v3.model;

import com.google.gson.annotations.SerializedName;

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
	 * No args constructor
	 */
	public StravaClub() {
		super();
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
		if (!(obj instanceof StravaClub)) {
			return false;
		}
		final StravaClub other = (StravaClub) obj;
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
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
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
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.clubType == null) ? 0 : this.clubType.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.memberCount == null) ? 0 : this.memberCount.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.privateClub == null) ? 0 : this.privateClub.hashCode());
		result = (prime * result) + ((this.profile == null) ? 0 : this.profile.hashCode());
		result = (prime * result) + ((this.profileMedium == null) ? 0 : this.profileMedium.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.sportType == null) ? 0 : this.sportType.hashCode());
		result = (prime * result) + ((this.state == null) ? 0 : this.state.hashCode());
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
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClub [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", profileMedium=" + this.profileMedium //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", profile=" + this.profile + ", description=" + this.description + ", clubType=" + this.clubType + ", sportType=" + this.sportType //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", privateClub=" + this.privateClub + ", memberCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.memberCount + "]"; //$NON-NLS-1$
	}
}
