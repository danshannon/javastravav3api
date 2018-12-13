package javastrava.api.v3.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import javastrava.api.v3.service.ActivityService;
import javastrava.api.v3.service.StreamService;
import javastrava.cache.StravaCacheable;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * A run, ride, or other activity
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaActivity implements StravaCacheable<Integer>{
	/**
	 * Strava's unique identifier for the activity
	 */
	private Integer id;
	/**
	 * State of the resource (summary, detailed, etc.)
	 */
	private StravaResourceState resourceState;
	/**
	 * The identifier given to the activity by the application that uploaded it
	 */
	private String externalId;
	/**
	 * The athlete performing the activity
	 */
	private StravaAthlete athlete;
	/**
	 * Name of the activity. Defaults to boring things, but athletes with
	 * imagination will come up with witty and interesting names on a regular
	 * basis!
	 */
	private String name;
	/**
	 * Detailed description of the activity
	 */
	private String description;
	/**
	 * Distance travelled in metres. If you want it in funny old imperial,
	 * that's up to you to convert it
	 */
	private Float distance;
	/**
	 * Total moving time in seconds.
	 */
	private Integer movingTime;
	/**
	 * Total time including stopped time, in seconds
	 */
	private Integer elapsedTime;
	/**
	 * Total elevation gain in metres
	 */
	private Float totalElevationGain;
	/**
	 * Type of activity
	 */
	private StravaActivityType type;
	/**
	 * Date and time the activity was started
	 */
	private ZonedDateTime startDate;
	/**
	 * Date and time the activity was started, in local time zone
	 */
	private LocalDateTime startDateLocal;
	/**
	 * Time zone
	 */
	private String timezone;
	/**
	 * Start location
	 */
	private StravaMapPoint startLatlng;
	/**
	 * End location
	 */
	private StravaMapPoint endLatlng;
	/**
	 * City the activity started in
	 */
	@Deprecated
	private String locationCity;
	/**
	 * State or county or canton or whatever other thing that the activity
	 * started in
	 */
	@Deprecated
	private String locationState;
	/**
	 * Country that the activity started in
	 */
	@Deprecated
	private String locationCountry;
	/**
	 * <p>
	 * Total number of achievements for this activity (returned by Strava, not
	 * recalculated by javastrava)
	 * </p>
	 */
	private Integer achievementCount;
	/**
	 * <p>
	 * Total number of athletes who have left kudos on this activity (returned
	 * by Strava, not recalculated by javastrava)
	 * </p>
	 *
	 * <p>
	 * To get the actual list of athletes who have left kudos, see
	 * {@link ActivityService#listActivityKudoers(Integer, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer kudosCount;
	/**
	 * <p>
	 * Total number of comments left on this activity (returned by Strava, not
	 * recalculated by javastrava)
	 * </p>
	 *
	 * <p>
	 * To get the actual list of comments, see
	 * {@link ActivityService#listActivityComments(Integer, Boolean, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer commentCount;
	/**
	 * <p>
	 * If Strava thinks this was a group activity, this is the number of
	 * athletes taking part
	 * </p>
	 *
	 * <p>
	 * To get the list of activities by all the other people who also took part,
	 * see
	 * {@link ActivityService#listRelatedActivities(Integer, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer athleteCount;
	/**
	 * <p>
	 * Total number of photos attached to this activity by the athlete on Instagram
	 * </p>
	 *
	 * <p>
	 * To get the actual photo details, see
	 * {@link ActivityService#listActivityPhotos(Integer)}
	 * </p>
	 */
	private Integer photoCount;

	/**
	 * <p>
	 * Total number of photos attached to this activity by the athlete on Instagram <b>and</b> Strava
	 * </p>
	 *
	 * <p>
	 * To get the actual photo details, see
	 * {@link ActivityService#listActivityPhotos(Integer)}
	 * </p>
	 */
	private Integer totalPhotoCount;
	/**
	 * <p>
	 * Weird map representation returned with the activity, basically contains
	 * polylines for use on Google maps
	 * </p>
	 *
	 * <p>
	 * If you want the actual set of GPS coordinates of the activity, then you
	 * need to use
	 * {@link StreamService#getActivityStreams(Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)}
	 * </p>
	 */
	private StravaMap map;
	/**
	 * Is set to <code>true</code> if Strava believes the ride was done on an
	 * indoor trainer
	 */
	private Boolean trainer;
	/**
	 * Is set to <code>true</code> if the activity was flagged as a commute</p>
	 */
	private Boolean commute;
	/**
	 * Is set to <code>true</code> if the activity was manually entered into
	 * Strava, rather than being uploaded as a file from some GPS device, or
	 * your phone</p>
	 */
	private Boolean manual;
	/**
	 * Is set to <code>true</code> if the activity has been flagged as private
	 * by the athlete
	 */
	@SerializedName("private")
	private Boolean privateActivity;
	/**
	 * Is set to <code>true</code> if the activity has been flagged as
	 * suspicious by another user on Strava, usually because it has crazy speeds
	 * because it was done in a car, not on a bike
	 */
	private Boolean flagged;
	/**
	 * For runs only, 0 -> ‘default’, 1 -> ‘race’, 2 -> ‘long run’, 3 ->
	 * ‘intervals’
	 */
	private StravaWorkoutType workoutType;
	/**
	 * Unique identifier of the {@link StravaGear} used on this activity
	 */
	private String gearId;
	/**
	 * Summary representation of the gear used for the activity
	 */
	private StravaGear gear;
	/**
	 * Average speed (in metres per second) of the activity (as calculated by
	 * Strava; is not recalculated or checked by javastrava)
	 */
	private Float averageSpeed;
	/**
	 * Maximum speed (in metres per second) achieved during the activity (quite
	 * often as a result of GPS inaccuracies). Calculated by Strava and not
	 * recalculated or checked by javastrava.
	 */
	private Float maxSpeed;
	/**
	 * Average RPM if cadence data was provided with the uploaded activity
	 */
	private Float averageCadence;
	/**
	 * Average temperature (in degrees Celsius) if temperature data was provided
	 * with the uploaded activity
	 */
	private Float averageTemp;
	/**
	 * Average power (in watts) for rides only. Strava calculates an estimate
	 * for this if power meter data is not provided with the upload.
	 */
	private Float averageWatts;
	/**
	 * Weighted average power (in watts) for rides with power meter data only.
	 */
	private Float weightedAverageWatts;
	/**
	 * Total energy expended by the rider in kilojoules
	 */
	private Float kilojoules;
	/**
	 * Is set to <code>true</code> if power meter data was provided with the
	 * upload
	 */
	private Boolean deviceWatts;
	
	/**
	 * Is set to <code>true</code> if the activity was recorded with heartrate
	 */
	private Boolean hasHeartrate;
	/**
	 * Average heart rate (in beats per minute) if heart rate data was provided
	 * with the upload
	 */
	private Float averageHeartrate;
	/**
	 * Maximum heart rate (in beats per minute) if heart rate data was provided
	 * with the upload
	 */
	private Integer maxHeartrate;
	/**
	 * Kilocalories expended (calculated by Strava)
	 */
	private Float calories;

	/**
	 * Is set to <code>true</code> if the currently authenticated athlete has
	 * kudoed this activity
	 */
	private Boolean hasKudoed;
	/**
	 * Segment efforts associated with the activity
	 */
	private List<StravaSegmentEffort> segmentEfforts;
	/**
	 * Runs only - list of metric splits
	 */
	private List<StravaSplit> splitsMetric;
	/**
	 * Runs only - list of imperial splits ("standard" hahahaha you Americans
	 * are so funny)
	 */
	private List<StravaSplit> splitsStandard;
	/**
	 * Runs only - list of best efforts
	 */
	private List<StravaBestRunningEffort> bestEfforts;
	/**
	 * Identifier of the original upload
	 */
	private Long uploadId;

	/**
	 * Latitude of the start point of the activity
	 */
	private Float startLatitude;

	/**
	 * Longitude of the start point of the activity
	 */
	private Float startLongitude;
	/**
	 * Instagram primary photo
	 */
	private String instagramPrimaryPhoto;
	/**
	 * Slightly weird summaries of the photos associated with the activity
	 */
	private StravaActivityPhotos photos;

	/**
	 * Seems to be the video used when doing the activity
	 */
	private StravaVideo video;

	/**
	 * The token used to construct an embed URL in the form <a href="https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]">https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]</a>
	 */
	private String embedToken;
	
	/**
	 * The name of the device used to record the activity
	 */
	private String deviceName;
	/**
	 * No args constructor
	 */
	public StravaActivity() {
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
		StravaActivity other = (StravaActivity) obj;
		if (achievementCount == null) {
			if (other.achievementCount != null)
				return false;
		} else if (!achievementCount.equals(other.achievementCount))
			return false;
		if (athlete == null) {
			if (other.athlete != null)
				return false;
		} else if (!athlete.equals(other.athlete))
			return false;
		if (athleteCount == null) {
			if (other.athleteCount != null)
				return false;
		} else if (!athleteCount.equals(other.athleteCount))
			return false;
		if (averageCadence == null) {
			if (other.averageCadence != null)
				return false;
		} else if (!averageCadence.equals(other.averageCadence))
			return false;
		if (averageHeartrate == null) {
			if (other.averageHeartrate != null)
				return false;
		} else if (!averageHeartrate.equals(other.averageHeartrate))
			return false;
		if (averageSpeed == null) {
			if (other.averageSpeed != null)
				return false;
		} else if (!averageSpeed.equals(other.averageSpeed))
			return false;
		if (averageTemp == null) {
			if (other.averageTemp != null)
				return false;
		} else if (!averageTemp.equals(other.averageTemp))
			return false;
		if (averageWatts == null) {
			if (other.averageWatts != null)
				return false;
		} else if (!averageWatts.equals(other.averageWatts))
			return false;
		if (bestEfforts == null) {
			if (other.bestEfforts != null)
				return false;
		} else if (!bestEfforts.equals(other.bestEfforts))
			return false;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (commentCount == null) {
			if (other.commentCount != null)
				return false;
		} else if (!commentCount.equals(other.commentCount))
			return false;
		if (commute == null) {
			if (other.commute != null)
				return false;
		} else if (!commute.equals(other.commute))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		if (deviceWatts == null) {
			if (other.deviceWatts != null)
				return false;
		} else if (!deviceWatts.equals(other.deviceWatts))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (elapsedTime == null) {
			if (other.elapsedTime != null)
				return false;
		} else if (!elapsedTime.equals(other.elapsedTime))
			return false;
		if (embedToken == null) {
			if (other.embedToken != null)
				return false;
		} else if (!embedToken.equals(other.embedToken))
			return false;
		if (endLatlng == null) {
			if (other.endLatlng != null)
				return false;
		} else if (!endLatlng.equals(other.endLatlng))
			return false;
		if (externalId == null) {
			if (other.externalId != null)
				return false;
		} else if (!externalId.equals(other.externalId))
			return false;
		if (flagged == null) {
			if (other.flagged != null)
				return false;
		} else if (!flagged.equals(other.flagged))
			return false;
		if (gear == null) {
			if (other.gear != null)
				return false;
		} else if (!gear.equals(other.gear))
			return false;
		if (gearId == null) {
			if (other.gearId != null)
				return false;
		} else if (!gearId.equals(other.gearId))
			return false;
		if (hasHeartrate == null) {
			if (other.hasHeartrate != null)
				return false;
		} else if (!hasHeartrate.equals(other.hasHeartrate))
			return false;
		if (hasKudoed == null) {
			if (other.hasKudoed != null)
				return false;
		} else if (!hasKudoed.equals(other.hasKudoed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instagramPrimaryPhoto == null) {
			if (other.instagramPrimaryPhoto != null)
				return false;
		} else if (!instagramPrimaryPhoto.equals(other.instagramPrimaryPhoto))
			return false;
		if (kilojoules == null) {
			if (other.kilojoules != null)
				return false;
		} else if (!kilojoules.equals(other.kilojoules))
			return false;
		if (kudosCount == null) {
			if (other.kudosCount != null)
				return false;
		} else if (!kudosCount.equals(other.kudosCount))
			return false;
		if (locationCity == null) {
			if (other.locationCity != null)
				return false;
		} else if (!locationCity.equals(other.locationCity))
			return false;
		if (locationCountry == null) {
			if (other.locationCountry != null)
				return false;
		} else if (!locationCountry.equals(other.locationCountry))
			return false;
		if (locationState == null) {
			if (other.locationState != null)
				return false;
		} else if (!locationState.equals(other.locationState))
			return false;
		if (manual == null) {
			if (other.manual != null)
				return false;
		} else if (!manual.equals(other.manual))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (maxHeartrate == null) {
			if (other.maxHeartrate != null)
				return false;
		} else if (!maxHeartrate.equals(other.maxHeartrate))
			return false;
		if (maxSpeed == null) {
			if (other.maxSpeed != null)
				return false;
		} else if (!maxSpeed.equals(other.maxSpeed))
			return false;
		if (movingTime == null) {
			if (other.movingTime != null)
				return false;
		} else if (!movingTime.equals(other.movingTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoCount == null) {
			if (other.photoCount != null)
				return false;
		} else if (!photoCount.equals(other.photoCount))
			return false;
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		if (privateActivity == null) {
			if (other.privateActivity != null)
				return false;
		} else if (!privateActivity.equals(other.privateActivity))
			return false;
		if (resourceState != other.resourceState)
			return false;
		if (segmentEfforts == null) {
			if (other.segmentEfforts != null)
				return false;
		} else if (!segmentEfforts.equals(other.segmentEfforts))
			return false;
		if (splitsMetric == null) {
			if (other.splitsMetric != null)
				return false;
		} else if (!splitsMetric.equals(other.splitsMetric))
			return false;
		if (splitsStandard == null) {
			if (other.splitsStandard != null)
				return false;
		} else if (!splitsStandard.equals(other.splitsStandard))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startDateLocal == null) {
			if (other.startDateLocal != null)
				return false;
		} else if (!startDateLocal.equals(other.startDateLocal))
			return false;
		if (startLatitude == null) {
			if (other.startLatitude != null)
				return false;
		} else if (!startLatitude.equals(other.startLatitude))
			return false;
		if (startLatlng == null) {
			if (other.startLatlng != null)
				return false;
		} else if (!startLatlng.equals(other.startLatlng))
			return false;
		if (startLongitude == null) {
			if (other.startLongitude != null)
				return false;
		} else if (!startLongitude.equals(other.startLongitude))
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		if (totalElevationGain == null) {
			if (other.totalElevationGain != null)
				return false;
		} else if (!totalElevationGain.equals(other.totalElevationGain))
			return false;
		if (totalPhotoCount == null) {
			if (other.totalPhotoCount != null)
				return false;
		} else if (!totalPhotoCount.equals(other.totalPhotoCount))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		if (type != other.type)
			return false;
		if (uploadId == null) {
			if (other.uploadId != null)
				return false;
		} else if (!uploadId.equals(other.uploadId))
			return false;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		if (weightedAverageWatts == null) {
			if (other.weightedAverageWatts != null)
				return false;
		} else if (!weightedAverageWatts.equals(other.weightedAverageWatts))
			return false;
		if (workoutType != other.workoutType)
			return false;
		return true;
	}
	/**
	 * @return the achievementCount
	 */
	public Integer getAchievementCount() {
		return this.achievementCount;
	}
	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @return the athleteCount
	 */
	public Integer getAthleteCount() {
		return this.athleteCount;
	}
	/**
	 * @return the averageCadence
	 */
	public Float getAverageCadence() {
		return this.averageCadence;
	}
	/**
	 * @return the averageHeartrate
	 */
	public Float getAverageHeartrate() {
		return this.averageHeartrate;
	}
	/**
	 * @return the averageSpeed
	 */
	public Float getAverageSpeed() {
		return this.averageSpeed;
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
	 * @return the bestEfforts
	 */
	public List<StravaBestRunningEffort> getBestEfforts() {
		return this.bestEfforts;
	}
	/**
	 * @return the calories
	 */
	public Float getCalories() {
		return this.calories;
	}
	/**
	 * @return the commentCount
	 */
	public Integer getCommentCount() {
		return this.commentCount;
	}
	/**
	 * @return the commute
	 */
	public Boolean getCommute() {
		return this.commute;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @return the deviceWatts
	 */
	public Boolean getDeviceWatts() {
		return this.deviceWatts;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @return the elapsedTime
	 */
	public Integer getElapsedTime() {
		return this.elapsedTime;
	}
	/**
	 * @return the embedToken
	 */
	public String getEmbedToken() {
		return this.embedToken;
	}
	/**
	 * @return the endLatlng
	 */
	public StravaMapPoint getEndLatlng() {
		return this.endLatlng;
	}
	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return this.externalId;
	}
	/**
	 * @return the flagged
	 */
	public Boolean getFlagged() {
		return this.flagged;
	}
	/**
	 * @return the gear
	 */
	public StravaGear getGear() {
		return this.gear;
	}
	/**
	 * @return the gearId
	 */
	public String getGearId() {
		return this.gearId;
	}
	/**
	 * @return the hasKudoed
	 */
	public Boolean getHasKudoed() {
		return this.hasKudoed;
	}
	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the instagramPrimaryPhoto
	 */
	public String getInstagramPrimaryPhoto() {
		return this.instagramPrimaryPhoto;
	}
	/**
	 * @return the kilojoules
	 */
	public Float getKilojoules() {
		return this.kilojoules;
	}
	/**
	 * @return the kudosCount
	 */
	public Integer getKudosCount() {
		return this.kudosCount;
	}
	/**
	 * @return the locationCity
	 */
	@Deprecated
	public String getLocationCity() {
		return this.locationCity;
	}
	/**
	 * @return the locationCountry
	 */
	@Deprecated
	public String getLocationCountry() {
		return this.locationCountry;
	}
	/**
	 * @return the locationState
	 */
	@Deprecated
	public String getLocationState() {
		return this.locationState;
	}
	/**
	 * @return the manual
	 */
	public Boolean getManual() {
		return this.manual;
	}
	/**
	 * @return the map
	 */
	public StravaMap getMap() {
		return this.map;
	}
	/**
	 * @return the maxHeartrate
	 */
	public Integer getMaxHeartrate() {
		return this.maxHeartrate;
	}
	/**
	 * @return the maxSpeed
	 */
	public Float getMaxSpeed() {
		return this.maxSpeed;
	}
	/**
	 * @return the movingTime
	 */
	public Integer getMovingTime() {
		return this.movingTime;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the photoCount
	 */
	public Integer getPhotoCount() {
		return this.photoCount;
	}
	/**
	 * @return the photos
	 */
	public StravaActivityPhotos getPhotos() {
		return this.photos;
	}
	/**
	 * @return the privateActivity
	 */
	public Boolean getPrivateActivity() {
		return this.privateActivity;
	}
	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the segmentEfforts
	 */
	public List<StravaSegmentEffort> getSegmentEfforts() {
		return this.segmentEfforts;
	}
	/**
	 * @return the splitsMetric
	 */
	public List<StravaSplit> getSplitsMetric() {
		return this.splitsMetric;
	}
	/**
	 * @return the splitsStandard
	 */
	public List<StravaSplit> getSplitsStandard() {
		return this.splitsStandard;
	}
	/**
	 * @return the startDate
	 */
	public ZonedDateTime getStartDate() {
		return this.startDate;
	}
	/**
	 * @return the startDateLocal
	 */
	public LocalDateTime getStartDateLocal() {
		return this.startDateLocal;
	}
	/**
	 * @return the startLatitude
	 */
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
	public Float getStartLongitude() {
		return this.startLongitude;
	}
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return this.timezone;
	}
	/**
	 * @return the totalElevationGain
	 */
	public Float getTotalElevationGain() {
		return this.totalElevationGain;
	}
	/**
	 * @return the totalPhotoCount
	 */
	public Integer getTotalPhotoCount() {
		return this.totalPhotoCount;
	}
	/**
	 * @return the trainer
	 */
	public Boolean getTrainer() {
		return this.trainer;
	}
	/**
	 * @return the type
	 */
	public StravaActivityType getType() {
		return this.type;
	}
	/**
	 * @return the uploadId
	 */
	public Long getUploadId() {
		return this.uploadId;
	}
	/**
	 * @return the video
	 */
	public StravaVideo getVideo() {
		return this.video;
	}
	/**
	 * @return the weightedAverageWatts
	 */
	public Float getWeightedAverageWatts() {
		return this.weightedAverageWatts;
	}
	/**
	 * @return the workoutType
	 */
	public StravaWorkoutType getWorkoutType() {
		return this.workoutType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achievementCount == null) ? 0 : achievementCount.hashCode());
		result = prime * result + ((athlete == null) ? 0 : athlete.hashCode());
		result = prime * result + ((athleteCount == null) ? 0 : athleteCount.hashCode());
		result = prime * result + ((averageCadence == null) ? 0 : averageCadence.hashCode());
		result = prime * result + ((averageHeartrate == null) ? 0 : averageHeartrate.hashCode());
		result = prime * result + ((averageSpeed == null) ? 0 : averageSpeed.hashCode());
		result = prime * result + ((averageTemp == null) ? 0 : averageTemp.hashCode());
		result = prime * result + ((averageWatts == null) ? 0 : averageWatts.hashCode());
		result = prime * result + ((bestEfforts == null) ? 0 : bestEfforts.hashCode());
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
		result = prime * result + ((commute == null) ? 0 : commute.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result + ((deviceWatts == null) ? 0 : deviceWatts.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((elapsedTime == null) ? 0 : elapsedTime.hashCode());
		result = prime * result + ((embedToken == null) ? 0 : embedToken.hashCode());
		result = prime * result + ((endLatlng == null) ? 0 : endLatlng.hashCode());
		result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
		result = prime * result + ((flagged == null) ? 0 : flagged.hashCode());
		result = prime * result + ((gear == null) ? 0 : gear.hashCode());
		result = prime * result + ((gearId == null) ? 0 : gearId.hashCode());
		result = prime * result + ((hasHeartrate == null) ? 0 : hasHeartrate.hashCode());
		result = prime * result + ((hasKudoed == null) ? 0 : hasKudoed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instagramPrimaryPhoto == null) ? 0 : instagramPrimaryPhoto.hashCode());
		result = prime * result + ((kilojoules == null) ? 0 : kilojoules.hashCode());
		result = prime * result + ((kudosCount == null) ? 0 : kudosCount.hashCode());
		result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
		result = prime * result + ((locationCountry == null) ? 0 : locationCountry.hashCode());
		result = prime * result + ((locationState == null) ? 0 : locationState.hashCode());
		result = prime * result + ((manual == null) ? 0 : manual.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((maxHeartrate == null) ? 0 : maxHeartrate.hashCode());
		result = prime * result + ((maxSpeed == null) ? 0 : maxSpeed.hashCode());
		result = prime * result + ((movingTime == null) ? 0 : movingTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoCount == null) ? 0 : photoCount.hashCode());
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
		result = prime * result + ((privateActivity == null) ? 0 : privateActivity.hashCode());
		result = prime * result + ((resourceState == null) ? 0 : resourceState.hashCode());
		result = prime * result + ((segmentEfforts == null) ? 0 : segmentEfforts.hashCode());
		result = prime * result + ((splitsMetric == null) ? 0 : splitsMetric.hashCode());
		result = prime * result + ((splitsStandard == null) ? 0 : splitsStandard.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startDateLocal == null) ? 0 : startDateLocal.hashCode());
		result = prime * result + ((startLatitude == null) ? 0 : startLatitude.hashCode());
		result = prime * result + ((startLatlng == null) ? 0 : startLatlng.hashCode());
		result = prime * result + ((startLongitude == null) ? 0 : startLongitude.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		result = prime * result + ((totalElevationGain == null) ? 0 : totalElevationGain.hashCode());
		result = prime * result + ((totalPhotoCount == null) ? 0 : totalPhotoCount.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uploadId == null) ? 0 : uploadId.hashCode());
		result = prime * result + ((video == null) ? 0 : video.hashCode());
		result = prime * result + ((weightedAverageWatts == null) ? 0 : weightedAverageWatts.hashCode());
		result = prime * result + ((workoutType == null) ? 0 : workoutType.hashCode());
		return result;
	}
	/**
	 * @param achievementCount the achievementCount to set
	 */
	public void setAchievementCount(final Integer achievementCount) {
		this.achievementCount = achievementCount;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}
	/**
	 * @param athleteCount the athleteCount to set
	 */
	public void setAthleteCount(final Integer athleteCount) {
		this.athleteCount = athleteCount;
	}
	/**
	 * @param averageCadence the averageCadence to set
	 */
	public void setAverageCadence(final Float averageCadence) {
		this.averageCadence = averageCadence;
	}
	/**
	 * @param averageHeartrate the averageHeartrate to set
	 */
	public void setAverageHeartrate(final Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}
	/**
	 * @param averageSpeed the averageSpeed to set
	 */
	public void setAverageSpeed(final Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	/**
	 * @param averageTemp the averageTemp to set
	 */
	public void setAverageTemp(final Float averageTemp) {
		this.averageTemp = averageTemp;
	}
	/**
	 * @param averageWatts the averageWatts to set
	 */
	public void setAverageWatts(final Float averageWatts) {
		this.averageWatts = averageWatts;
	}
	/**
	 * @param bestEfforts the bestEfforts to set
	 */
	public void setBestEfforts(final List<StravaBestRunningEffort> bestEfforts) {
		this.bestEfforts = bestEfforts;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(final Float calories) {
		this.calories = calories;
	}
	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(final Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * @param commute the commute to set
	 */
	public void setCommute(final Boolean commute) {
		this.commute = commute;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 * @param deviceWatts the deviceWatts to set
	 */
	public void setDeviceWatts(final Boolean deviceWatts) {
		this.deviceWatts = deviceWatts;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}
	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * @param embedToken the embedToken to set
	 */
	public void setEmbedToken(final String embedToken) {
		this.embedToken = embedToken;
	}
	/**
	 * @param endLatlng the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}
	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @param flagged the flagged to set
	 */
	public void setFlagged(final Boolean flagged) {
		this.flagged = flagged;
	}
	/**
	 * @param gear the gear to set
	 */
	public void setGear(final StravaGear gear) {
		this.gear = gear;
	}
	/**
	 * @param gearId the gearId to set
	 */
	public void setGearId(final String gearId) {
		this.gearId = gearId;
	}
	/**
	 * @param hasKudoed the hasKudoed to set
	 */
	public void setHasKudoed(final Boolean hasKudoed) {
		this.hasKudoed = hasKudoed;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @param instagramPrimaryPhoto the instagramPrimaryPhoto to set
	 */
	public void setInstagramPrimaryPhoto(final String instagramPrimaryPhoto) {
		this.instagramPrimaryPhoto = instagramPrimaryPhoto;
	}
	/**
	 * @param kilojoules the kilojoules to set
	 */
	public void setKilojoules(final Float kilojoules) {
		this.kilojoules = kilojoules;
	}
	/**
	 * @param kudosCount the kudosCount to set
	 */
	public void setKudosCount(final Integer kudosCount) {
		this.kudosCount = kudosCount;
	}
	/**
	 * @param locationCity the locationCity to set
	 */
	@Deprecated
	public void setLocationCity(final String locationCity) {
		this.locationCity = locationCity;
	}
	/**
	 * @param locationCountry the locationCountry to set
	 */
	@Deprecated
	public void setLocationCountry(final String locationCountry) {
		this.locationCountry = locationCountry;
	}
	/**
	 * @param locationState the locationState to set
	 */
	@Deprecated
	public void setLocationState(final String locationState) {
		this.locationState = locationState;
	}
	/**
	 * @param manual the manual to set
	 */
	public void setManual(final Boolean manual) {
		this.manual = manual;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(final StravaMap map) {
		this.map = map;
	}
	/**
	 * @param maxHeartrate the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}
	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(final Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	/**
	 * @param movingTime the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @param photoCount the photoCount to set
	 */
	public void setPhotoCount(final Integer photoCount) {
		this.photoCount = photoCount;
	}
	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(final StravaActivityPhotos photos) {
		this.photos = photos;
	}
	/**
	 * @param privateActivity the privateActivity to set
	 */
	public void setPrivateActivity(final Boolean privateActivity) {
		this.privateActivity = privateActivity;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param segmentEfforts the segmentEfforts to set
	 */
	public void setSegmentEfforts(final List<StravaSegmentEffort> segmentEfforts) {
		this.segmentEfforts = segmentEfforts;
	}
	/**
	 * @param splitsMetric the splitsMetric to set
	 */
	public void setSplitsMetric(final List<StravaSplit> splitsMetric) {
		this.splitsMetric = splitsMetric;
	}
	/**
	 * @param splitsStandard the splitsStandard to set
	 */
	public void setSplitsStandard(final List<StravaSplit> splitsStandard) {
		this.splitsStandard = splitsStandard;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final ZonedDateTime startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param startDateLocal the startDateLocal to set
	 */
	public void setStartDateLocal(final LocalDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}
	/**
	 * @param startLatitude the startLatitude to set
	 */
	public void setStartLatitude(final Float startLatitude) {
		this.startLatitude = startLatitude;
	}
	/**
	 * @param startLatlng the startLatlng to set
	 */
	public void setStartLatlng(final StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}
	/**
	 * @param startLongitude the startLongitude to set
	 */
	public void setStartLongitude(final Float startLongitude) {
		this.startLongitude = startLongitude;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(final String timezone) {
		this.timezone = timezone;
	}
	/**
	 * @param totalElevationGain the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}
	/**
	 * @param totalPhotoCount the totalPhotoCount to set
	 */
	public void setTotalPhotoCount(final Integer totalPhotoCount) {
		this.totalPhotoCount = totalPhotoCount;
	}
	/**
	 * @param trainer the trainer to set
	 */
	public void setTrainer(final Boolean trainer) {
		this.trainer = trainer;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final StravaActivityType type) {
		this.type = type;
	}
	/**
	 * @param uploadId the uploadId to set
	 */
	public void setUploadId(final Long uploadId) {
		this.uploadId = uploadId;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(final StravaVideo video) {
		this.video = video;
	}
	/**
	 * @param weightedAverageWatts the weightedAverageWatts to set
	 */
	public void setWeightedAverageWatts(final Float weightedAverageWatts) {
		this.weightedAverageWatts = weightedAverageWatts;
	}
	/**
	 * @param workoutType the workoutType to set
	 */
	public void setWorkoutType(final StravaWorkoutType workoutType) {
		this.workoutType = workoutType;
	}
	@Override
	public String toString() {
		return "StravaActivity [id=" + id + ", resourceState=" + resourceState + ", externalId=" + externalId
				+ ", athlete=" + athlete + ", name=" + name + ", description=" + description + ", distance=" + distance
				+ ", movingTime=" + movingTime + ", elapsedTime=" + elapsedTime + ", totalElevationGain="
				+ totalElevationGain + ", type=" + type + ", startDate=" + startDate + ", startDateLocal="
				+ startDateLocal + ", timezone=" + timezone + ", startLatlng=" + startLatlng + ", endLatlng="
				+ endLatlng + ", locationCity=" + locationCity + ", locationState=" + locationState
				+ ", locationCountry=" + locationCountry + ", achievementCount=" + achievementCount + ", kudosCount="
				+ kudosCount + ", commentCount=" + commentCount + ", athleteCount=" + athleteCount + ", photoCount="
				+ photoCount + ", totalPhotoCount=" + totalPhotoCount + ", map=" + map + ", trainer=" + trainer
				+ ", commute=" + commute + ", manual=" + manual + ", privateActivity=" + privateActivity + ", flagged="
				+ flagged + ", workoutType=" + workoutType + ", gearId=" + gearId + ", gear=" + gear + ", averageSpeed="
				+ averageSpeed + ", maxSpeed=" + maxSpeed + ", averageCadence=" + averageCadence + ", averageTemp="
				+ averageTemp + ", averageWatts=" + averageWatts + ", weightedAverageWatts=" + weightedAverageWatts
				+ ", kilojoules=" + kilojoules + ", deviceWatts=" + deviceWatts + ", hasHeartrate=" + hasHeartrate
				+ ", averageHeartrate=" + averageHeartrate + ", maxHeartrate=" + maxHeartrate + ", calories=" + calories
				+ ", hasKudoed=" + hasKudoed + ", segmentEfforts=" + segmentEfforts + ", splitsMetric=" + splitsMetric
				+ ", splitsStandard=" + splitsStandard + ", bestEfforts=" + bestEfforts + ", uploadId=" + uploadId
				+ ", startLatitude=" + startLatitude + ", startLongitude=" + startLongitude + ", instagramPrimaryPhoto="
				+ instagramPrimaryPhoto + ", photos=" + photos + ", video=" + video + ", embedToken=" + embedToken
				+ ", deviceName=" + deviceName + "]";
	}
	public Boolean getHasHeartrate() {
		return hasHeartrate;
	}
	public void setHasHeartrate(Boolean hasHeartrate) {
		this.hasHeartrate = hasHeartrate;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
