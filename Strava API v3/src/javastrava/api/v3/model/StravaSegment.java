package javastrava.api.v3.model;

import java.time.ZonedDateTime;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentActivityType;
import javastrava.api.v3.service.SegmentService;
import javastrava.api.v3.service.StreamService;

/**
 * <p>
 * {@link StravaSegment Segments} are specific sections of road. {@link StravaAthlete Athletes}' {@link StravaSegmentEffort efforts} are compared on these
 * segments and leaderboards are created.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaSegment {
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
	 * {@link StreamService#getSegmentStreams(Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)}
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
	 * @return the activityType
	 */
	public StravaSegmentActivityType getActivityType() {
		return this.activityType;
	}

	/**
	 * @param activityType the activityType to set
	 */
	public void setActivityType(final StravaSegmentActivityType activityType) {
		this.activityType = activityType;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the averageGrade
	 */
	public Float getAverageGrade() {
		return this.averageGrade;
	}

	/**
	 * @param averageGrade the averageGrade to set
	 */
	public void setAverageGrade(final Float averageGrade) {
		this.averageGrade = averageGrade;
	}

	/**
	 * @return the maximumGrade
	 */
	public Float getMaximumGrade() {
		return this.maximumGrade;
	}

	/**
	 * @param maximumGrade the maximumGrade to set
	 */
	public void setMaximumGrade(final Float maximumGrade) {
		this.maximumGrade = maximumGrade;
	}

	/**
	 * @return the elevationHigh
	 */
	public Float getElevationHigh() {
		return this.elevationHigh;
	}

	/**
	 * @param elevationHigh the elevationHigh to set
	 */
	public void setElevationHigh(final Float elevationHigh) {
		this.elevationHigh = elevationHigh;
	}

	/**
	 * @return the elevationLow
	 */
	public Float getElevationLow() {
		return this.elevationLow;
	}

	/**
	 * @param elevationLow the elevationLow to set
	 */
	public void setElevationLow(final Float elevationLow) {
		this.elevationLow = elevationLow;
	}

	/**
	 * @return the startLatlng
	 */
	public StravaMapPoint getStartLatlng() {
		return this.startLatlng;
	}

	/**
	 * @param startLatlng the startLatlng to set
	 */
	public void setStartLatlng(final StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}

	/**
	 * @return the endLatlng
	 */
	public StravaMapPoint getEndLatlng() {
		return this.endLatlng;
	}

	/**
	 * @param endLatlng the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}

	/**
	 * @return the climbCategory
	 */
	public StravaClimbCategory getClimbCategory() {
		return this.climbCategory;
	}

	/**
	 * @param climbCategory the climbCategory to set
	 */
	public void setClimbCategory(final StravaClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
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
	 * @return the privateSegment
	 */
	public Boolean getPrivateSegment() {
		return this.privateSegment;
	}

	/**
	 * @param privateSegment the privateSegment to set
	 */
	public void setPrivateSegment(final Boolean privateSegment) {
		this.privateSegment = privateSegment;
	}

	/**
	 * @return the starred
	 */
	public Boolean getStarred() {
		return this.starred;
	}

	/**
	 * @param starred the starred to set
	 */
	public void setStarred(final Boolean starred) {
		this.starred = starred;
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
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}

	/**
	 * @param totalElevationGain the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	/**
	 * @return the map
	 */
	public StravaMap getMap() {
		return this.map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(final StravaMap map) {
		this.map = map;
	}

	/**
	 * @return the effortCount
	 */
	public Integer getEffortCount() {
		return this.effortCount;
	}

	/**
	 * @param effortCount the effortCount to set
	 */
	public void setEffortCount(final Integer effortCount) {
		this.effortCount = effortCount;
	}

	/**
	 * @return the athleteCount
	 */
	public Integer getAthleteCount() {
		return this.athleteCount;
	}

	/**
	 * @param athleteCount the athleteCount to set
	 */
	public void setAthleteCount(final Integer athleteCount) {
		this.athleteCount = athleteCount;
	}

	/**
	 * @return the hazardous
	 */
	public Boolean getHazardous() {
		return this.hazardous;
	}

	/**
	 * @param hazardous the hazardous to set
	 */
	public void setHazardous(final Boolean hazardous) {
		this.hazardous = hazardous;
	}

	/**
	 * @return the starCount
	 */
	public Integer getStarCount() {
		return this.starCount;
	}

	/**
	 * @param starCount the starCount to set
	 */
	public void setStarCount(final Integer starCount) {
		this.starCount = starCount;
	}

	/**
	 * @return the athletePrEffort
	 */
	public StravaSegmentEffort getAthletePrEffort() {
		return this.athletePrEffort;
	}

	/**
	 * @param athletePrEffort the athletePrEffort to set
	 */
	public void setAthletePrEffort(final StravaSegmentEffort athletePrEffort) {
		this.athletePrEffort = athletePrEffort;
	}

	/**
	 * @return the starredDate
	 */
	public ZonedDateTime getStarredDate() {
		return this.starredDate;
	}

	/**
	 * @param starredDate the starredDate to set
	 */
	public void setStarredDate(final ZonedDateTime starredDate) {
		this.starredDate = starredDate;
	}

	/**
	 * @return the startLatitude
	 */
	@Deprecated
	public Float getStartLatitude() {
		return this.startLatitude;
	}

	/**
	 * @param startLatitude the startLatitude to set
	 */
	@Deprecated
	public void setStartLatitude(final Float startLatitude) {
		this.startLatitude = startLatitude;
	}

	/**
	 * @return the startLongitude
	 */
	@Deprecated
	public Float getStartLongitude() {
		return this.startLongitude;
	}

	/**
	 * @param startLongitude the startLongitude to set
	 */
	@Deprecated
	public void setStartLongitude(final Float startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * @return the endLatitude
	 */
	@Deprecated
	public Float getEndLatitude() {
		return this.endLatitude;
	}

	/**
	 * @param endLatitude the endLatitude to set
	 */
	@Deprecated
	public void setEndLatitude(final Float endLatitude) {
		this.endLatitude = endLatitude;
	}

	/**
	 * @return the endLongitude
	 */
	@Deprecated
	public Float getEndLongitude() {
		return this.endLongitude;
	}

	/**
	 * @param endLongitude the endLongitude to set
	 */
	@Deprecated
	public void setEndLongitude(final Float endLongitude) {
		this.endLongitude = endLongitude;
	}

	/**
	 * @return the prTime
	 */
	@Deprecated
	public Integer getPrTime() {
		return this.prTime;
	}

	/**
	 * @param prTime the prTime to set
	 */
	@Deprecated
	public void setPrTime(final Integer prTime) {
		this.prTime = prTime;
	}

	/**
	 * @return the athleteSegmentStats
	 */
	@Deprecated
	public StravaAthleteSegmentStats getAthleteSegmentStats() {
		return this.athleteSegmentStats;
	}

	/**
	 * @param athleteSegmentStats the athleteSegmentStats to set
	 */
	@Deprecated
	public void setAthleteSegmentStats(final StravaAthleteSegmentStats athleteSegmentStats) {
		this.athleteSegmentStats = athleteSegmentStats;
	}
}
