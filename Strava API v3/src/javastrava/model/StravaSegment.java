package javastrava.model;

import java.time.ZonedDateTime;

import com.google.gson.annotations.SerializedName;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSegmentActivityType;
import javastrava.service.SegmentService;
import javastrava.service.StreamService;

/**
 * <p>
 * {@link StravaSegment Segments} are specific sections of road. {@link StravaAthlete Athletes}' {@link StravaSegmentEffort efforts} are compared on these segments and leaderboards are created.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSegment implements StravaCacheableEntity<Integer> {
	/**
	 * Strava's unique identifier for a segment
	 */
	private Integer id;

	/**
	 * Current state of this resource on Strava
	 */
	private StravaResourceState resourceState;

	/**
	 * Name of the segment
	 */
	private String name;

	/**
	 * Type of activity - 'ride' or 'run'
	 */
	private StravaSegmentActivityType activityType;

	/**
	 * Total distance in metres
	 */
	private Float distance;

	/**
	 * Average grade in percent
	 */
	private Float averageGrade;

	/**
	 * Maximum grade in percent
	 */
	private Float maximumGrade;

	/**
	 * Maximum elevation in metres above sea level
	 */
	private Float elevationHigh;

	/**
	 * Minimum elevation in metres above sea level
	 */
	private Float elevationLow;

	/**
	 * Start co-ordinates for this segment
	 */
	private StravaMapPoint startLatlng;

	/**
	 * End co-ordinates for this segment
	 */
	private StravaMapPoint endLatlng;

	/**
	 * Calculated climb category
	 */
	private StravaClimbCategory climbCategory;

	/**
	 * City / suburb in which this segment starts
	 */
	private String city;

	/**
	 * County / state / canton / territory etc. that this segment starts in
	 */
	private String state;

	/**
	 * Country in which this segment starts
	 */
	private String country;

	/**
	 * Is set to <code>true</code> if the owner has flagged the segment as private
	 */
	@SerializedName("private")
	private Boolean privateSegment; // is "private" in JSON API

	/**
	 * Is set to <code>true</code> if the authenticated athlete has starred this segment
	 */
	private Boolean starred; // true if authenticated athlete has starred segment

	/**
	 * Date and time the segment was created on Strava
	 */
	private ZonedDateTime createdAt;

	/**
	 * Date and time the segment was last updated on Strava
	 */
	private ZonedDateTime updatedAt;

	/**
	 * total elevation gain in metres
	 */
	private Float totalElevationGain;

	/**
	 * Map of the segment (as 2 polylines usable with Google maps; if you want GPS coordinates then go take a look at
	 * {@link StreamService#getSegmentStreams(Integer, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)}
	 * )
	 */
	private StravaMap map;

	/**
	 * Total number of efforts recorded on Strava against this segment
	 */
	private Integer effortCount;

	/**
	 * Total number of athletes who have recorded an effort on this segment
	 */
	private Integer athleteCount;

	/**
	 * Is set to <code>true</code> if someone has flagged this segment as hazardous. If so, leaderboards will not be available.
	 */
	private Boolean hazardous;

	/**
	 * Total number of athletes who have starred this segment
	 */
	private Integer starCount;

	/**
	 * The authenticated athlete's fastest effort on this segment - only returned with {@link SegmentService#listStarredSegments(Integer)}
	 */
	private StravaSegmentEffort athletePrEffort;

	/**
	 * Date the athlete starred the segment (only returned when listing starred segments)
	 */
	private ZonedDateTime starredDate;

	/**
	 * Start latitude
	 */
	@Deprecated
	private Float startLatitude;

	/**
	 * Start longitude
	 */
	@Deprecated
	private Float startLongitude;

	/**
	 * End latitude
	 */
	@Deprecated
	private Float endLatitude;

	/**
	 * End longitude
	 */
	@Deprecated
	private Float endLongitude;

	/**
	 * Athlete's best time (returned only when listing starred segments)
	 */
	@Deprecated
	private Integer prTime;

	/**
	 * Athlete segment statistics
	 */
	@Deprecated
	private StravaAthleteSegmentStats athleteSegmentStats;

	/**
	 * No args constructor
	 */
	public StravaSegment() {
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
		if (!(obj instanceof StravaSegment)) {
			return false;
		}
		final StravaSegment other = (StravaSegment) obj;
		if (this.activityType != other.activityType) {
			return false;
		}
		if (this.athleteCount == null) {
			if (other.athleteCount != null) {
				return false;
			}
		} else if (!this.athleteCount.equals(other.athleteCount)) {
			return false;
		}
		if (this.athletePrEffort == null) {
			if (other.athletePrEffort != null) {
				return false;
			}
		} else if (!this.athletePrEffort.equals(other.athletePrEffort)) {
			return false;
		}
		if (this.athleteSegmentStats == null) {
			if (other.athleteSegmentStats != null) {
				return false;
			}
		} else if (!this.athleteSegmentStats.equals(other.athleteSegmentStats)) {
			return false;
		}
		if (this.averageGrade == null) {
			if (other.averageGrade != null) {
				return false;
			}
		} else if (!this.averageGrade.equals(other.averageGrade)) {
			return false;
		}
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.climbCategory != other.climbCategory) {
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
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.effortCount == null) {
			if (other.effortCount != null) {
				return false;
			}
		} else if (!this.effortCount.equals(other.effortCount)) {
			return false;
		}
		if (this.elevationHigh == null) {
			if (other.elevationHigh != null) {
				return false;
			}
		} else if (!this.elevationHigh.equals(other.elevationHigh)) {
			return false;
		}
		if (this.elevationLow == null) {
			if (other.elevationLow != null) {
				return false;
			}
		} else if (!this.elevationLow.equals(other.elevationLow)) {
			return false;
		}
		if (this.endLatitude == null) {
			if (other.endLatitude != null) {
				return false;
			}
		} else if (!this.endLatitude.equals(other.endLatitude)) {
			return false;
		}
		if (this.endLatlng == null) {
			if (other.endLatlng != null) {
				return false;
			}
		} else if (!this.endLatlng.equals(other.endLatlng)) {
			return false;
		}
		if (this.endLongitude == null) {
			if (other.endLongitude != null) {
				return false;
			}
		} else if (!this.endLongitude.equals(other.endLongitude)) {
			return false;
		}
		if (this.hazardous == null) {
			if (other.hazardous != null) {
				return false;
			}
		} else if (!this.hazardous.equals(other.hazardous)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!this.map.equals(other.map)) {
			return false;
		}
		if (this.maximumGrade == null) {
			if (other.maximumGrade != null) {
				return false;
			}
		} else if (!this.maximumGrade.equals(other.maximumGrade)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.prTime == null) {
			if (other.prTime != null) {
				return false;
			}
		} else if (!this.prTime.equals(other.prTime)) {
			return false;
		}
		if (this.privateSegment == null) {
			if (other.privateSegment != null) {
				return false;
			}
		} else if (!this.privateSegment.equals(other.privateSegment)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.starCount == null) {
			if (other.starCount != null) {
				return false;
			}
		} else if (!this.starCount.equals(other.starCount)) {
			return false;
		}
		if (this.starred == null) {
			if (other.starred != null) {
				return false;
			}
		} else if (!this.starred.equals(other.starred)) {
			return false;
		}
		if (this.starredDate == null) {
			if (other.starredDate != null) {
				return false;
			}
		} else if (!this.starredDate.equals(other.starredDate)) {
			return false;
		}
		if (this.startLatitude == null) {
			if (other.startLatitude != null) {
				return false;
			}
		} else if (!this.startLatitude.equals(other.startLatitude)) {
			return false;
		}
		if (this.startLatlng == null) {
			if (other.startLatlng != null) {
				return false;
			}
		} else if (!this.startLatlng.equals(other.startLatlng)) {
			return false;
		}
		if (this.startLongitude == null) {
			if (other.startLongitude != null) {
				return false;
			}
		} else if (!this.startLongitude.equals(other.startLongitude)) {
			return false;
		}
		if (this.state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!this.state.equals(other.state)) {
			return false;
		}
		if (this.totalElevationGain == null) {
			if (other.totalElevationGain != null) {
				return false;
			}
		} else if (!this.totalElevationGain.equals(other.totalElevationGain)) {
			return false;
		}
		if (this.updatedAt == null) {
			if (other.updatedAt != null) {
				return false;
			}
		} else if (!this.updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activityType
	 */
	public StravaSegmentActivityType getActivityType() {
		return this.activityType;
	}

	/**
	 * @return the athleteCount
	 */
	public Integer getAthleteCount() {
		return this.athleteCount;
	}

	/**
	 * @return the athletePrEffort
	 */
	public StravaSegmentEffort getAthletePrEffort() {
		return this.athletePrEffort;
	}

	/**
	 * @return the athleteSegmentStats
	 */
	@Deprecated
	public StravaAthleteSegmentStats getAthleteSegmentStats() {
		return this.athleteSegmentStats;
	}

	/**
	 * @return the averageGrade
	 */
	public Float getAverageGrade() {
		return this.averageGrade;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return the climbCategory
	 */
	public StravaClimbCategory getClimbCategory() {
		return this.climbCategory;
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
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}

	/**
	 * @return the elevationHigh
	 */
	public Float getElevationHigh() {
		return this.elevationHigh;
	}

	/**
	 * @return the elevationLow
	 */
	public Float getElevationLow() {
		return this.elevationLow;
	}

	/**
	 * @return the endLatitude
	 */
	@Deprecated
	public Float getEndLatitude() {
		return this.endLatitude;
	}

	/**
	 * @return the endLatlng
	 */
	public StravaMapPoint getEndLatlng() {
		return this.endLatlng;
	}

	/**
	 * @return the endLongitude
	 */
	@Deprecated
	public Float getEndLongitude() {
		return this.endLongitude;
	}

	/**
	 * @return the hazardous
	 */
	public Boolean getHazardous() {
		return this.hazardous;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the map
	 */
	public StravaMap getMap() {
		return this.map;
	}

	/**
	 * @return the maximumGrade
	 */
	public Float getMaximumGrade() {
		return this.maximumGrade;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the privateSegment
	 */
	public Boolean getPrivateSegment() {
		return this.privateSegment;
	}

	/**
	 * @return the prTime
	 */
	@Deprecated
	public Integer getPrTime() {
		return this.prTime;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the starCount
	 */
	public Integer getStarCount() {
		return this.starCount;
	}

	/**
	 * @return the starred
	 */
	public Boolean getStarred() {
		return this.starred;
	}

	/**
	 * @return the starredDate
	 */
	public ZonedDateTime getStarredDate() {
		return this.starredDate;
	}

	/**
	 * @return the startLatitude
	 */
	@Deprecated
	public Float getStartLatitude() {
		return this.startLatitude;
	}

	/**
	 * @return the startLatlng
	 */
	public StravaMapPoint getStartLatlng() {
		return this.startLatlng;
	}

	/**
	 * @return the startLongitude
	 */
	@Deprecated
	public Float getStartLongitude() {
		return this.startLongitude;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}

	/**
	 * @return the updatedAt
	 */
	public ZonedDateTime getUpdatedAt() {
		return this.updatedAt;
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
		result = (prime * result) + ((this.activityType == null) ? 0 : this.activityType.hashCode());
		result = (prime * result) + ((this.athleteCount == null) ? 0 : this.athleteCount.hashCode());
		result = (prime * result) + ((this.athletePrEffort == null) ? 0 : this.athletePrEffort.hashCode());
		result = (prime * result) + ((this.athleteSegmentStats == null) ? 0 : this.athleteSegmentStats.hashCode());
		result = (prime * result) + ((this.averageGrade == null) ? 0 : this.averageGrade.hashCode());
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.climbCategory == null) ? 0 : this.climbCategory.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.effortCount == null) ? 0 : this.effortCount.hashCode());
		result = (prime * result) + ((this.elevationHigh == null) ? 0 : this.elevationHigh.hashCode());
		result = (prime * result) + ((this.elevationLow == null) ? 0 : this.elevationLow.hashCode());
		result = (prime * result) + ((this.endLatitude == null) ? 0 : this.endLatitude.hashCode());
		result = (prime * result) + ((this.endLatlng == null) ? 0 : this.endLatlng.hashCode());
		result = (prime * result) + ((this.endLongitude == null) ? 0 : this.endLongitude.hashCode());
		result = (prime * result) + ((this.hazardous == null) ? 0 : this.hazardous.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
		result = (prime * result) + ((this.maximumGrade == null) ? 0 : this.maximumGrade.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.prTime == null) ? 0 : this.prTime.hashCode());
		result = (prime * result) + ((this.privateSegment == null) ? 0 : this.privateSegment.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.starCount == null) ? 0 : this.starCount.hashCode());
		result = (prime * result) + ((this.starred == null) ? 0 : this.starred.hashCode());
		result = (prime * result) + ((this.starredDate == null) ? 0 : this.starredDate.hashCode());
		result = (prime * result) + ((this.startLatitude == null) ? 0 : this.startLatitude.hashCode());
		result = (prime * result) + ((this.startLatlng == null) ? 0 : this.startLatlng.hashCode());
		result = (prime * result) + ((this.startLongitude == null) ? 0 : this.startLongitude.hashCode());
		result = (prime * result) + ((this.state == null) ? 0 : this.state.hashCode());
		result = (prime * result) + ((this.totalElevationGain == null) ? 0 : this.totalElevationGain.hashCode());
		result = (prime * result) + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		return result;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(final StravaSegmentActivityType activityType) {
		this.activityType = activityType;
	}

	/**
	 * @param athleteCount
	 *            the athleteCount to set
	 */
	public void setAthleteCount(final Integer athleteCount) {
		this.athleteCount = athleteCount;
	}

	/**
	 * @param athletePrEffort
	 *            the athletePrEffort to set
	 */
	public void setAthletePrEffort(final StravaSegmentEffort athletePrEffort) {
		this.athletePrEffort = athletePrEffort;
	}

	/**
	 * @param athleteSegmentStats
	 *            the athleteSegmentStats to set
	 */
	@Deprecated
	public void setAthleteSegmentStats(final StravaAthleteSegmentStats athleteSegmentStats) {
		this.athleteSegmentStats = athleteSegmentStats;
	}

	/**
	 * @param averageGrade
	 *            the averageGrade to set
	 */
	public void setAverageGrade(final Float averageGrade) {
		this.averageGrade = averageGrade;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param climbCategory
	 *            the climbCategory to set
	 */
	public void setClimbCategory(final StravaClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
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
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param effortCount
	 *            the effortCount to set
	 */
	public void setEffortCount(final Integer effortCount) {
		this.effortCount = effortCount;
	}

	/**
	 * @param elevationHigh
	 *            the elevationHigh to set
	 */
	public void setElevationHigh(final Float elevationHigh) {
		this.elevationHigh = elevationHigh;
	}

	/**
	 * @param elevationLow
	 *            the elevationLow to set
	 */
	public void setElevationLow(final Float elevationLow) {
		this.elevationLow = elevationLow;
	}

	/**
	 * @param endLatitude
	 *            the endLatitude to set
	 */
	@Deprecated
	public void setEndLatitude(final Float endLatitude) {
		this.endLatitude = endLatitude;
	}

	/**
	 * @param endLatlng
	 *            the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}

	/**
	 * @param endLongitude
	 *            the endLongitude to set
	 */
	@Deprecated
	public void setEndLongitude(final Float endLongitude) {
		this.endLongitude = endLongitude;
	}

	/**
	 * @param hazardous
	 *            the hazardous to set
	 */
	public void setHazardous(final Boolean hazardous) {
		this.hazardous = hazardous;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(final StravaMap map) {
		this.map = map;
	}

	/**
	 * @param maximumGrade
	 *            the maximumGrade to set
	 */
	public void setMaximumGrade(final Float maximumGrade) {
		this.maximumGrade = maximumGrade;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param privateSegment
	 *            the privateSegment to set
	 */
	public void setPrivateSegment(final Boolean privateSegment) {
		this.privateSegment = privateSegment;
	}

	/**
	 * @param prTime
	 *            the prTime to set
	 */
	@Deprecated
	public void setPrTime(final Integer prTime) {
		this.prTime = prTime;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param starCount
	 *            the starCount to set
	 */
	public void setStarCount(final Integer starCount) {
		this.starCount = starCount;
	}

	/**
	 * @param starred
	 *            the starred to set
	 */
	public void setStarred(final Boolean starred) {
		this.starred = starred;
	}

	/**
	 * @param starredDate
	 *            the starredDate to set
	 */
	public void setStarredDate(final ZonedDateTime starredDate) {
		this.starredDate = starredDate;
	}

	/**
	 * @param startLatitude
	 *            the startLatitude to set
	 */
	@Deprecated
	public void setStartLatitude(final Float startLatitude) {
		this.startLatitude = startLatitude;
	}

	/**
	 * @param startLatlng
	 *            the startLatlng to set
	 */
	public void setStartLatlng(final StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}

	/**
	 * @param startLongitude
	 *            the startLongitude to set
	 */
	@Deprecated
	public void setStartLongitude(final Float startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param totalElevationGain
	 *            the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaSegment [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", activityType=" + this.activityType + ", distance=" + this.distance //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", averageGrade=" + this.averageGrade //$NON-NLS-1$
				+ ", maximumGrade=" + this.maximumGrade + ", elevationHigh=" + this.elevationHigh + ", elevationLow=" + this.elevationLow + ", startLatlng=" + this.startLatlng + ", endLatlng=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.endLatlng
				+ ", climbCategory=" + this.climbCategory + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", privateSegment=" + this.privateSegment + ", starred=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.starred + ", createdAt=" //$NON-NLS-1$
				+ this.createdAt + ", updatedAt=" + this.updatedAt + ", totalElevationGain=" + this.totalElevationGain + ", map=" + this.map + ", effortCount=" + this.effortCount + ", athleteCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.athleteCount
				+ ", hazardous=" + this.hazardous + ", starCount=" + this.starCount + ", athletePrEffort=" + this.athletePrEffort + ", starredDate=" + this.starredDate + ", startLatitude=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.startLatitude
				+ ", startLongitude=" + this.startLongitude + ", endLatitude=" + this.endLatitude + ", endLongitude=" + this.endLongitude + ", prTime=" + this.prTime + ", athleteSegmentStats=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.athleteSegmentStats
				+ "]"; //$NON-NLS-1$
	}
}
