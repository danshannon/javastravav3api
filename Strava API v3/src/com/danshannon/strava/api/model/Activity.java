package com.danshannon.strava.api.model;

import java.util.Date;
import java.util.List;

import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.WorkoutType;

/**
 * @author Dan Shannon
 *
 */
public class Activity {
	private Integer id;
	private ResourceState resourceState;
	private String externalId;
	private Athlete athlete;
	private String name;
	private String description;
	private Float distance;
	private Integer movingTime;
	private Integer elapsedTime;
	private Float totalElevationGain;
	/**
	 * Note that this is kind of almost an enum, but it isn't???
	 */
	private ActivityType type;
	private Date startDate;
	private Date startDateLocal;
	private String timeZone;
	private MapPoint startLatLng;
	private MapPoint endLatLng;
	private String locationCity;
	private String locationState;
	private String locationCountry;
	private Integer achievementCount;
	private Integer kudosCount;
	private Integer commentCount;
	private Integer athleteCount;
	private Integer photoCount;
	private Map map;
	private Boolean trainer;
	private Boolean commute;
	private Boolean manual;
	/**
	 * NB Is "private" in the API
	 */
	private Boolean privateActivity;
	private Boolean flagged;
	private WorkoutType workoutType;
	private String gearId;
	private Gear gear;
	private Float averageSpeed;
	private Float maxSpeed;
	private Float averageCadence;
	private Float averageTemp;
	private Float averageWatts;
	private Float weightedAverageWatts;
	private Float kilojoules;
	private Boolean deviceWatts;
	private Float averageHeartrate;
	private Integer maxHeartrate;
	private Float calories;
	private Integer truncated;
	private Boolean hasKudoed;
	private List<SegmentEffort> segmentEfforts;
	private List<Split> splitsMetric;
	private List<Split> splitsStandard;
	private List<BestEffort> bestEfforts;
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
	 * @return the externalId
	 */
	public String getExternalId() {
		return this.externalId;
	}
	/**
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}
	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}
	/**
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}
	/**
	 * @return the type
	 */
	public ActivityType getType() {
		return this.type;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	/**
	 * @return the startDateLocal
	 */
	public Date getStartDateLocal() {
		return this.startDateLocal;
	}
	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return this.timeZone;
	}
	/**
	 * @return the startLatLng
	 */
	public MapPoint getStartLatLng() {
		return this.startLatLng;
	}
	/**
	 * @return the endLatLng
	 */
	public MapPoint getEndLatLng() {
		return this.endLatLng;
	}
	/**
	 * @return the locationCity
	 */
	public String getLocationCity() {
		return this.locationCity;
	}
	/**
	 * @return the locationState
	 */
	public String getLocationState() {
		return this.locationState;
	}
	/**
	 * @return the locationCountry
	 */
	public String getLocationCountry() {
		return this.locationCountry;
	}
	/**
	 * @return the achievementCount
	 */
	public Integer getAchievementCount() {
		return this.achievementCount;
	}
	/**
	 * @return the kudosCount
	 */
	public Integer getKudosCount() {
		return this.kudosCount;
	}
	/**
	 * @return the commentCount
	 */
	public Integer getCommentCount() {
		return this.commentCount;
	}
	/**
	 * @return the athleteCount
	 */
	public Integer getAthleteCount() {
		return this.athleteCount;
	}
	/**
	 * @return the photoCount
	 */
	public Integer getPhotoCount() {
		return this.photoCount;
	}
	/**
	 * @return the map
	 */
	public Map getMap() {
		return this.map;
	}
	/**
	 * @return the trainer
	 */
	public Boolean getTrainer() {
		return this.trainer;
	}
	/**
	 * @return the commute
	 */
	public Boolean getCommute() {
		return this.commute;
	}
	/**
	 * @return the manual
	 */
	public Boolean getManual() {
		return this.manual;
	}
	/**
	 * @return the privateActivity
	 */
	public Boolean getPrivateActivity() {
		return this.privateActivity;
	}
	/**
	 * @return the flagged
	 */
	public Boolean getFlagged() {
		return this.flagged;
	}
	/**
	 * @return the workoutType
	 */
	public WorkoutType getWorkoutType() {
		return this.workoutType;
	}
	/**
	 * @return the gearId
	 */
	public String getGearId() {
		return this.gearId;
	}
	/**
	 * @return the gear
	 */
	public Gear getGear() {
		return this.gear;
	}
	/**
	 * @return the averageSpeed
	 */
	public Float getAverageSpeed() {
		return this.averageSpeed;
	}
	/**
	 * @return the maxSpeed
	 */
	public Float getMaxSpeed() {
		return this.maxSpeed;
	}
	/**
	 * @return the averageCadence
	 */
	public Float getAverageCadence() {
		return this.averageCadence;
	}
	/**
	 * @return the averageTemp
	 */
	public Float getAverageTemp() {
		return this.averageTemp;
	}
	/**
	 * @return the averageWatts
	 */
	public Float getAverageWatts() {
		return this.averageWatts;
	}
	/**
	 * @return the weightedAverageWatts
	 */
	public Float getWeightedAverageWatts() {
		return this.weightedAverageWatts;
	}
	/**
	 * @return the kilojoules
	 */
	public Float getKilojoules() {
		return this.kilojoules;
	}
	/**
	 * @return the deviceWatts
	 */
	public Boolean getDeviceWatts() {
		return this.deviceWatts;
	}
	/**
	 * @return the averageHeartrate
	 */
	public Float getAverageHeartrate() {
		return this.averageHeartrate;
	}
	/**
	 * @return the maxHeartrate
	 */
	public Integer getMaxHeartrate() {
		return this.maxHeartrate;
	}
	/**
	 * @return the calories
	 */
	public Float getCalories() {
		return this.calories;
	}
	/**
	 * @return the truncated
	 */
	public Integer getTruncated() {
		return this.truncated;
	}
	/**
	 * @return the hasKudoed
	 */
	public Boolean getHasKudoed() {
		return this.hasKudoed;
	}
	/**
	 * @return the segmentEfforts
	 */
	public List<SegmentEffort> getSegmentEfforts() {
		return this.segmentEfforts;
	}
	/**
	 * @return the splitsMetric
	 */
	public List<Split> getSplitsMetric() {
		return this.splitsMetric;
	}
	/**
	 * @return the splitsStandard
	 */
	public List<Split> getSplitsStandard() {
		return this.splitsStandard;
	}
	/**
	 * @return the bestEfforts
	 */
	public List<BestEffort> getBestEfforts() {
		return this.bestEfforts;
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
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @param totalElevationGain the totalElevationGain to set
	 */
	public void setTotalElevationGain(Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ActivityType type) {
		this.type = type;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param startDateLocal the startDateLocal to set
	 */
	public void setStartDateLocal(Date startDateLocal) {
		this.startDateLocal = startDateLocal;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @param startLatLng the startLatLng to set
	 */
	public void setStartLatLng(MapPoint startLatLng) {
		this.startLatLng = startLatLng;
	}
	/**
	 * @param endLatLng the endLatLng to set
	 */
	public void setEndLatLng(MapPoint endLatLng) {
		this.endLatLng = endLatLng;
	}
	/**
	 * @param locationCity the locationCity to set
	 */
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	/**
	 * @param locationState the locationState to set
	 */
	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}
	/**
	 * @param locationCountry the locationCountry to set
	 */
	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}
	/**
	 * @param achievementCount the achievementCount to set
	 */
	public void setAchievementCount(Integer achievementCount) {
		this.achievementCount = achievementCount;
	}
	/**
	 * @param kudosCount the kudosCount to set
	 */
	public void setKudosCount(Integer kudosCount) {
		this.kudosCount = kudosCount;
	}
	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * @param athleteCount the athleteCount to set
	 */
	public void setAthleteCount(Integer athleteCount) {
		this.athleteCount = athleteCount;
	}
	/**
	 * @param photoCount the photoCount to set
	 */
	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * @param trainer the trainer to set
	 */
	public void setTrainer(Boolean trainer) {
		this.trainer = trainer;
	}
	/**
	 * @param commute the commute to set
	 */
	public void setCommute(Boolean commute) {
		this.commute = commute;
	}
	/**
	 * @param manual the manual to set
	 */
	public void setManual(Boolean manual) {
		this.manual = manual;
	}
	/**
	 * @param privateActivity the privateActivity to set
	 */
	public void setPrivateActivity(Boolean privateActivity) {
		this.privateActivity = privateActivity;
	}
	/**
	 * @param flagged the flagged to set
	 */
	public void setFlagged(Boolean flagged) {
		this.flagged = flagged;
	}
	/**
	 * @param workoutType the workoutType to set
	 */
	public void setWorkoutType(WorkoutType workoutType) {
		this.workoutType = workoutType;
	}
	/**
	 * @param gearId the gearId to set
	 */
	public void setGearId(String gearId) {
		this.gearId = gearId;
	}
	/**
	 * @param gear the gear to set
	 */
	public void setGear(Gear gear) {
		this.gear = gear;
	}
	/**
	 * @param averageSpeed the averageSpeed to set
	 */
	public void setAverageSpeed(Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	/**
	 * @param averageCadence the averageCadence to set
	 */
	public void setAverageCadence(Float averageCadence) {
		this.averageCadence = averageCadence;
	}
	/**
	 * @param averageTemp the averageTemp to set
	 */
	public void setAverageTemp(Float averageTemp) {
		this.averageTemp = averageTemp;
	}
	/**
	 * @param averageWatts the averageWatts to set
	 */
	public void setAverageWatts(Float averageWatts) {
		this.averageWatts = averageWatts;
	}
	/**
	 * @param weightedAverageWatts the weightedAverageWatts to set
	 */
	public void setWeightedAverageWatts(Float weightedAverageWatts) {
		this.weightedAverageWatts = weightedAverageWatts;
	}
	/**
	 * @param kilojoules the kilojoules to set
	 */
	public void setKilojoules(Float kilojoules) {
		this.kilojoules = kilojoules;
	}
	/**
	 * @param deviceWatts the deviceWatts to set
	 */
	public void setDeviceWatts(Boolean deviceWatts) {
		this.deviceWatts = deviceWatts;
	}
	/**
	 * @param averageHeartrate the averageHeartrate to set
	 */
	public void setAverageHeartrate(Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}
	/**
	 * @param maxHeartrate the maxHeartrate to set
	 */
	public void setMaxHeartrate(Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(Float calories) {
		this.calories = calories;
	}
	/**
	 * @param truncated the truncated to set
	 */
	public void setTruncated(Integer truncated) {
		this.truncated = truncated;
	}
	/**
	 * @param hasKudoed the hasKudoed to set
	 */
	public void setHasKudoed(Boolean hasKudoed) {
		this.hasKudoed = hasKudoed;
	}
	/**
	 * @param segmentEfforts the segmentEfforts to set
	 */
	public void setSegmentEfforts(List<SegmentEffort> segmentEfforts) {
		this.segmentEfforts = segmentEfforts;
	}
	/**
	 * @param splitsMetric the splitsMetric to set
	 */
	public void setSplitsMetric(List<Split> splitsMetric) {
		this.splitsMetric = splitsMetric;
	}
	/**
	 * @param splitsStandard the splitsStandard to set
	 */
	public void setSplitsStandard(List<Split> splitsStandard) {
		this.splitsStandard = splitsStandard;
	}
	/**
	 * @param bestEfforts the bestEfforts to set
	 */
	public void setBestEfforts(List<BestEffort> bestEfforts) {
		this.bestEfforts = bestEfforts;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Activity [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState + ", " : "")
				+ (this.externalId != null ? "externalId=" + this.externalId + ", " : "")
				+ (this.athlete != null ? "athlete=" + this.athlete + ", " : "")
				+ (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.description != null ? "description=" + this.description + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance + ", " : "")
				+ (this.movingTime != null ? "movingTime=" + this.movingTime + ", " : "")
				+ (this.elapsedTime != null ? "elapsedTime=" + this.elapsedTime + ", " : "")
				+ (this.totalElevationGain != null ? "totalElevationGain=" + this.totalElevationGain + ", " : "")
				+ (this.type != null ? "type=" + this.type + ", " : "")
				+ (this.startDate != null ? "startDate=" + this.startDate + ", " : "")
				+ (this.startDateLocal != null ? "startDateLocal=" + this.startDateLocal + ", " : "")
				+ (this.timeZone != null ? "timeZone=" + this.timeZone + ", " : "")
				+ (this.startLatLng != null ? "startLatLng=" + this.startLatLng + ", " : "")
				+ (this.endLatLng != null ? "endLatLng=" + this.endLatLng + ", " : "")
				+ (this.locationCity != null ? "locationCity=" + this.locationCity + ", " : "")
				+ (this.locationState != null ? "locationState=" + this.locationState + ", " : "")
				+ (this.locationCountry != null ? "locationCountry=" + this.locationCountry + ", " : "")
				+ (this.achievementCount != null ? "achievementCount=" + this.achievementCount + ", " : "")
				+ (this.kudosCount != null ? "kudosCount=" + this.kudosCount + ", " : "")
				+ (this.commentCount != null ? "commentCount=" + this.commentCount + ", " : "")
				+ (this.athleteCount != null ? "athleteCount=" + this.athleteCount + ", " : "")
				+ (this.photoCount != null ? "photoCount=" + this.photoCount + ", " : "")
				+ (this.map != null ? "map=" + this.map + ", " : "")
				+ (this.trainer != null ? "trainer=" + this.trainer + ", " : "")
				+ (this.commute != null ? "commute=" + this.commute + ", " : "")
				+ (this.manual != null ? "manual=" + this.manual + ", " : "")
				+ (this.privateActivity != null ? "privateActivity=" + this.privateActivity + ", " : "")
				+ (this.flagged != null ? "flagged=" + this.flagged + ", " : "")
				+ (this.workoutType != null ? "workoutType=" + this.workoutType + ", " : "")
				+ (this.gearId != null ? "gearId=" + this.gearId + ", " : "")
				+ (this.gear != null ? "gear=" + this.gear + ", " : "")
				+ (this.averageSpeed != null ? "averageSpeed=" + this.averageSpeed + ", " : "")
				+ (this.maxSpeed != null ? "maxSpeed=" + this.maxSpeed + ", " : "")
				+ (this.averageCadence != null ? "averageCadence=" + this.averageCadence + ", " : "")
				+ (this.averageTemp != null ? "averageTemp=" + this.averageTemp + ", " : "")
				+ (this.averageWatts != null ? "averageWatts=" + this.averageWatts + ", " : "")
				+ (this.weightedAverageWatts != null ? "weightedAverageWatts=" + this.weightedAverageWatts + ", " : "")
				+ (this.kilojoules != null ? "kilojoules=" + this.kilojoules + ", " : "")
				+ (this.deviceWatts != null ? "deviceWatts=" + this.deviceWatts + ", " : "")
				+ (this.averageHeartrate != null ? "averageHeartrate=" + this.averageHeartrate + ", " : "")
				+ (this.maxHeartrate != null ? "maxHeartrate=" + this.maxHeartrate + ", " : "")
				+ (this.calories != null ? "calories=" + this.calories + ", " : "")
				+ (this.truncated != null ? "truncated=" + this.truncated + ", " : "")
				+ (this.hasKudoed != null ? "hasKudoed=" + this.hasKudoed + ", " : "")
				+ (this.segmentEfforts != null ? "segmentEfforts=" + this.segmentEfforts + ", " : "")
				+ (this.splitsMetric != null ? "splitsMetric=" + this.splitsMetric + ", " : "")
				+ (this.splitsStandard != null ? "splitsStandard=" + this.splitsStandard + ", " : "")
				+ (this.bestEfforts != null ? "bestEfforts=" + this.bestEfforts : "") + "]";
	}
	
	
}
