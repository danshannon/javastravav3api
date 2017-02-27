package javastrava.api.v3.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import javastrava.api.v3.service.ActivityService;
import javastrava.api.v3.service.StreamService;
import javastrava.cache.StravaCacheable;

/**
 * <p>
 * A run, ride, or other activity
 * </p>
 *
 * @author Dan Shannon
 */
public class StravaActivity implements StravaCacheable<Long> {
    /**
     * Strava's unique identifier for the activity
     */
    private Long id;
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
     * <p>
     * <p>
     * To get the actual list of athletes who have left kudos, see
     * {@link ActivityService#listActivityKudoers(Long, javastrava.util.Paging)}
     * </p>
     */
    private Integer kudosCount;
    /**
     * <p>
     * Total number of comments left on this activity (returned by Strava, not
     * recalculated by javastrava)
     * </p>
     * <p>
     * <p>
     * To get the actual list of comments, see
     * {@link ActivityService#listActivityComments(Long, Boolean, javastrava.util.Paging)}
     * </p>
     */
    private Integer commentCount;
    /**
     * <p>
     * If Strava thinks this was a group activity, this is the number of
     * athletes taking part
     * </p>
     * <p>
     * <p>
     * To get the list of activities by all the other people who also took part,
     * see
     * {@link ActivityService#listRelatedActivities(Long, javastrava.util.Paging)}
     * </p>
     */
    private Integer athleteCount;
    /**
     * <p>
     * Total number of photos attached to this activity by the athlete on Instagram
     * </p>
     * <p>
     * <p>
     * To get the actual photo details, see
     * {@link ActivityService#listActivityPhotos(Long)}
     * </p>
     */
    private Integer photoCount;

    /**
     * <p>
     * Total number of photos attached to this activity by the athlete on Instagram <b>and</b> Strava
     * </p>
     * <p>
     * <p>
     * To get the actual photo details, see
     * {@link ActivityService#listActivityPhotos(Long)}
     * </p>
     */
    private Integer totalPhotoCount;
    /**
     * <p>
     * Weird map representation returned with the activity, basically contains
     * polylines for use on Google maps
     * </p>
     * <p>
     * <p>
     * If you want the actual set of GPS coordinates of the activity, then you
     * need to use
     * {@link StreamService#getActivityStreams(Long, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)}
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
    private Integer uploadId;

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
     * a measure of heartrate intensity, available on premium users’ activities only
     */
    private Integer sufferScore;

    /**
     * No args constructor
     */
    public StravaActivity() {
        super();
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
     * @return Device name
     */
    public String getDeviceName() {
        return this.deviceName;
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
     * @return Whether the activity was recorded with heart-rate monitor
     */
    public Boolean getHasHeartrate() {
        return this.hasHeartrate;
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
    public Long getId() {
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
    public Integer getUploadId() {
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
     * @param deviceName The device name
     */
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
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
     * @param hasHeartrate Does the activity have heart rate recorded?
     */
    public void setHasHeartrate(final Boolean hasHeartrate) {
        this.hasHeartrate = hasHeartrate;
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
    public void setId(final Long id) {
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
    public void setUploadId(final Integer uploadId) {
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

    public Integer getSufferScore() {
        return sufferScore;
    }

    public void setSufferScore(Integer sufferScore) {
        this.sufferScore = sufferScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StravaActivity that = (StravaActivity) o;

        if (!id.equals(that.id)) return false;
        if (resourceState != that.resourceState) return false;
        if (externalId != null ? !externalId.equals(that.externalId) : that.externalId != null) return false;
        if (athlete != null ? !athlete.equals(that.athlete) : that.athlete != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (movingTime != null ? !movingTime.equals(that.movingTime) : that.movingTime != null) return false;
        if (elapsedTime != null ? !elapsedTime.equals(that.elapsedTime) : that.elapsedTime != null) return false;
        if (totalElevationGain != null ? !totalElevationGain.equals(that.totalElevationGain) : that.totalElevationGain != null)
            return false;
        if (type != that.type) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (startDateLocal != null ? !startDateLocal.equals(that.startDateLocal) : that.startDateLocal != null)
            return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;
        if (startLatlng != null ? !startLatlng.equals(that.startLatlng) : that.startLatlng != null) return false;
        if (endLatlng != null ? !endLatlng.equals(that.endLatlng) : that.endLatlng != null) return false;
        if (locationCity != null ? !locationCity.equals(that.locationCity) : that.locationCity != null) return false;
        if (locationState != null ? !locationState.equals(that.locationState) : that.locationState != null)
            return false;
        if (locationCountry != null ? !locationCountry.equals(that.locationCountry) : that.locationCountry != null)
            return false;
        if (achievementCount != null ? !achievementCount.equals(that.achievementCount) : that.achievementCount != null)
            return false;
        if (kudosCount != null ? !kudosCount.equals(that.kudosCount) : that.kudosCount != null) return false;
        if (commentCount != null ? !commentCount.equals(that.commentCount) : that.commentCount != null) return false;
        if (athleteCount != null ? !athleteCount.equals(that.athleteCount) : that.athleteCount != null) return false;
        if (photoCount != null ? !photoCount.equals(that.photoCount) : that.photoCount != null) return false;
        if (totalPhotoCount != null ? !totalPhotoCount.equals(that.totalPhotoCount) : that.totalPhotoCount != null)
            return false;
        if (map != null ? !map.equals(that.map) : that.map != null) return false;
        if (trainer != null ? !trainer.equals(that.trainer) : that.trainer != null) return false;
        if (commute != null ? !commute.equals(that.commute) : that.commute != null) return false;
        if (manual != null ? !manual.equals(that.manual) : that.manual != null) return false;
        if (privateActivity != null ? !privateActivity.equals(that.privateActivity) : that.privateActivity != null)
            return false;
        if (flagged != null ? !flagged.equals(that.flagged) : that.flagged != null) return false;
        if (workoutType != that.workoutType) return false;
        if (gearId != null ? !gearId.equals(that.gearId) : that.gearId != null) return false;
        if (gear != null ? !gear.equals(that.gear) : that.gear != null) return false;
        if (averageSpeed != null ? !averageSpeed.equals(that.averageSpeed) : that.averageSpeed != null) return false;
        if (maxSpeed != null ? !maxSpeed.equals(that.maxSpeed) : that.maxSpeed != null) return false;
        if (averageCadence != null ? !averageCadence.equals(that.averageCadence) : that.averageCadence != null)
            return false;
        if (averageTemp != null ? !averageTemp.equals(that.averageTemp) : that.averageTemp != null) return false;
        if (averageWatts != null ? !averageWatts.equals(that.averageWatts) : that.averageWatts != null) return false;
        if (weightedAverageWatts != null ? !weightedAverageWatts.equals(that.weightedAverageWatts) : that.weightedAverageWatts != null)
            return false;
        if (kilojoules != null ? !kilojoules.equals(that.kilojoules) : that.kilojoules != null) return false;
        if (deviceWatts != null ? !deviceWatts.equals(that.deviceWatts) : that.deviceWatts != null) return false;
        if (hasHeartrate != null ? !hasHeartrate.equals(that.hasHeartrate) : that.hasHeartrate != null) return false;
        if (averageHeartrate != null ? !averageHeartrate.equals(that.averageHeartrate) : that.averageHeartrate != null)
            return false;
        if (maxHeartrate != null ? !maxHeartrate.equals(that.maxHeartrate) : that.maxHeartrate != null) return false;
        if (calories != null ? !calories.equals(that.calories) : that.calories != null) return false;
        if (hasKudoed != null ? !hasKudoed.equals(that.hasKudoed) : that.hasKudoed != null) return false;
        if (segmentEfforts != null ? !segmentEfforts.equals(that.segmentEfforts) : that.segmentEfforts != null)
            return false;
        if (splitsMetric != null ? !splitsMetric.equals(that.splitsMetric) : that.splitsMetric != null) return false;
        if (splitsStandard != null ? !splitsStandard.equals(that.splitsStandard) : that.splitsStandard != null)
            return false;
        if (bestEfforts != null ? !bestEfforts.equals(that.bestEfforts) : that.bestEfforts != null) return false;
        if (uploadId != null ? !uploadId.equals(that.uploadId) : that.uploadId != null) return false;
        if (startLatitude != null ? !startLatitude.equals(that.startLatitude) : that.startLatitude != null)
            return false;
        if (startLongitude != null ? !startLongitude.equals(that.startLongitude) : that.startLongitude != null)
            return false;
        if (instagramPrimaryPhoto != null ? !instagramPrimaryPhoto.equals(that.instagramPrimaryPhoto) : that.instagramPrimaryPhoto != null)
            return false;
        if (photos != null ? !photos.equals(that.photos) : that.photos != null) return false;
        if (video != null ? !video.equals(that.video) : that.video != null) return false;
        if (embedToken != null ? !embedToken.equals(that.embedToken) : that.embedToken != null) return false;
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) return false;
        return sufferScore != null ? sufferScore.equals(that.sufferScore) : that.sufferScore == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (resourceState != null ? resourceState.hashCode() : 0);
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        result = 31 * result + (athlete != null ? athlete.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (movingTime != null ? movingTime.hashCode() : 0);
        result = 31 * result + (elapsedTime != null ? elapsedTime.hashCode() : 0);
        result = 31 * result + (totalElevationGain != null ? totalElevationGain.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (startDateLocal != null ? startDateLocal.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (startLatlng != null ? startLatlng.hashCode() : 0);
        result = 31 * result + (endLatlng != null ? endLatlng.hashCode() : 0);
        result = 31 * result + (locationCity != null ? locationCity.hashCode() : 0);
        result = 31 * result + (locationState != null ? locationState.hashCode() : 0);
        result = 31 * result + (locationCountry != null ? locationCountry.hashCode() : 0);
        result = 31 * result + (achievementCount != null ? achievementCount.hashCode() : 0);
        result = 31 * result + (kudosCount != null ? kudosCount.hashCode() : 0);
        result = 31 * result + (commentCount != null ? commentCount.hashCode() : 0);
        result = 31 * result + (athleteCount != null ? athleteCount.hashCode() : 0);
        result = 31 * result + (photoCount != null ? photoCount.hashCode() : 0);
        result = 31 * result + (totalPhotoCount != null ? totalPhotoCount.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
        result = 31 * result + (commute != null ? commute.hashCode() : 0);
        result = 31 * result + (manual != null ? manual.hashCode() : 0);
        result = 31 * result + (privateActivity != null ? privateActivity.hashCode() : 0);
        result = 31 * result + (flagged != null ? flagged.hashCode() : 0);
        result = 31 * result + (workoutType != null ? workoutType.hashCode() : 0);
        result = 31 * result + (gearId != null ? gearId.hashCode() : 0);
        result = 31 * result + (gear != null ? gear.hashCode() : 0);
        result = 31 * result + (averageSpeed != null ? averageSpeed.hashCode() : 0);
        result = 31 * result + (maxSpeed != null ? maxSpeed.hashCode() : 0);
        result = 31 * result + (averageCadence != null ? averageCadence.hashCode() : 0);
        result = 31 * result + (averageTemp != null ? averageTemp.hashCode() : 0);
        result = 31 * result + (averageWatts != null ? averageWatts.hashCode() : 0);
        result = 31 * result + (weightedAverageWatts != null ? weightedAverageWatts.hashCode() : 0);
        result = 31 * result + (kilojoules != null ? kilojoules.hashCode() : 0);
        result = 31 * result + (deviceWatts != null ? deviceWatts.hashCode() : 0);
        result = 31 * result + (hasHeartrate != null ? hasHeartrate.hashCode() : 0);
        result = 31 * result + (averageHeartrate != null ? averageHeartrate.hashCode() : 0);
        result = 31 * result + (maxHeartrate != null ? maxHeartrate.hashCode() : 0);
        result = 31 * result + (calories != null ? calories.hashCode() : 0);
        result = 31 * result + (hasKudoed != null ? hasKudoed.hashCode() : 0);
        result = 31 * result + (segmentEfforts != null ? segmentEfforts.hashCode() : 0);
        result = 31 * result + (splitsMetric != null ? splitsMetric.hashCode() : 0);
        result = 31 * result + (splitsStandard != null ? splitsStandard.hashCode() : 0);
        result = 31 * result + (bestEfforts != null ? bestEfforts.hashCode() : 0);
        result = 31 * result + (uploadId != null ? uploadId.hashCode() : 0);
        result = 31 * result + (startLatitude != null ? startLatitude.hashCode() : 0);
        result = 31 * result + (startLongitude != null ? startLongitude.hashCode() : 0);
        result = 31 * result + (instagramPrimaryPhoto != null ? instagramPrimaryPhoto.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (embedToken != null ? embedToken.hashCode() : 0);
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (sufferScore != null ? sufferScore.hashCode() : 0);
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StravaActivity [id=" + this.id + ", resourceState=" + this.resourceState + ", externalId=" + this.externalId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", athlete=" + this.athlete + ", name=" + this.name + ", description=" + this.description + ", distance=" + this.distance //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                + ", movingTime=" + this.movingTime + ", elapsedTime=" + this.elapsedTime + ", totalElevationGain=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.totalElevationGain + ", type=" + this.type + ", startDate=" + this.startDate + ", startDateLocal=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.startDateLocal + ", timezone=" + this.timezone + ", startLatlng=" + this.startLatlng + ", endLatlng=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.endLatlng + ", locationCity=" + this.locationCity + ", locationState=" + this.locationState //$NON-NLS-1$ //$NON-NLS-2$
                + ", locationCountry=" + this.locationCountry + ", achievementCount=" + this.achievementCount + ", kudosCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.kudosCount + ", commentCount=" + this.commentCount + ", athleteCount=" + this.athleteCount + ", photoCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.photoCount + ", totalPhotoCount=" + this.totalPhotoCount + ", map=" + this.map + ", trainer=" + this.trainer //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", commute=" + this.commute + ", manual=" + this.manual + ", privateActivity=" + this.privateActivity + ", flagged=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                + this.flagged + ", workoutType=" + this.workoutType + ", gearId=" + this.gearId + ", gear=" + this.gear + ", averageSpeed=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                + this.averageSpeed + ", maxSpeed=" + this.maxSpeed + ", averageCadence=" + this.averageCadence + ", averageTemp=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.averageTemp + ", averageWatts=" + this.averageWatts + ", weightedAverageWatts=" + this.weightedAverageWatts //$NON-NLS-1$ //$NON-NLS-2$
                + ", kilojoules=" + this.kilojoules + ", deviceWatts=" + this.deviceWatts + ", hasHeartrate=" + this.hasHeartrate //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", averageHeartrate=" + this.averageHeartrate + ", maxHeartrate=" + this.maxHeartrate + ", calories=" + this.calories //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", hasKudoed=" + this.hasKudoed + ", segmentEfforts=" + this.segmentEfforts + ", splitsMetric=" + this.splitsMetric //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", splitsStandard=" + this.splitsStandard + ", bestEfforts=" + this.bestEfforts + ", uploadId=" + this.uploadId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", startLatitude=" + this.startLatitude + ", startLongitude=" + this.startLongitude + ", instagramPrimaryPhoto=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.instagramPrimaryPhoto + ", photos=" + this.photos + ", video=" + this.video + ", embedToken=" + this.embedToken //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", deviceName=" + this.deviceName + "sufferScore=" + sufferScore + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }


}
