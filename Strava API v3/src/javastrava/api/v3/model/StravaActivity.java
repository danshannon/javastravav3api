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

/**
 * <p>
 * A run, ride, or other activity
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaActivity {
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
	private String locationCity;
	/**
	 * State or county or canton or whatever other thing that the activity
	 * started in
	 */
	private String locationState;
	/**
	 * Country that the activity started in
	 */
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
	 * Total number of photos attached to this activity by the athlete
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
	 * Only present if activity is owned by authenticated athlete, returns 0 if
	 * not truncated by privacy zones
	 */
	private Integer truncated;
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
	private String uploadId;

	private Float startLatitude;

	private Float startLongitude;
	private String instagramPrimaryPhoto;
	private StravaActivityPhotos photos;
	/**
	 * No args constructor
	 */
	public StravaActivity() {
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
		if (!(obj instanceof StravaActivity)) {
			return false;
		}
		final StravaActivity other = (StravaActivity) obj;
		if (this.achievementCount == null) {
			if (other.achievementCount != null) {
				return false;
			}
		} else if (!this.achievementCount.equals(other.achievementCount)) {
			return false;
		}
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.athleteCount == null) {
			if (other.athleteCount != null) {
				return false;
			}
		} else if (!this.athleteCount.equals(other.athleteCount)) {
			return false;
		}
		if (this.averageCadence == null) {
			if (other.averageCadence != null) {
				return false;
			}
		} else if (!this.averageCadence.equals(other.averageCadence)) {
			return false;
		}
		if (this.averageHeartrate == null) {
			if (other.averageHeartrate != null) {
				return false;
			}
		} else if (!this.averageHeartrate.equals(other.averageHeartrate)) {
			return false;
		}
		if (this.averageSpeed == null) {
			if (other.averageSpeed != null) {
				return false;
			}
		} else if (!this.averageSpeed.equals(other.averageSpeed)) {
			return false;
		}
		if (this.averageTemp == null) {
			if (other.averageTemp != null) {
				return false;
			}
		} else if (!this.averageTemp.equals(other.averageTemp)) {
			return false;
		}
		if (this.averageWatts == null) {
			if (other.averageWatts != null) {
				return false;
			}
		} else if (!this.averageWatts.equals(other.averageWatts)) {
			return false;
		}
		if (this.bestEfforts == null) {
			if (other.bestEfforts != null) {
				return false;
			}
		} else if (!this.bestEfforts.equals(other.bestEfforts)) {
			return false;
		}
		if (this.calories == null) {
			if (other.calories != null) {
				return false;
			}
		} else if (!this.calories.equals(other.calories)) {
			return false;
		}
		if (this.commentCount == null) {
			if (other.commentCount != null) {
				return false;
			}
		} else if (!this.commentCount.equals(other.commentCount)) {
			return false;
		}
		if (this.commute == null) {
			if (other.commute != null) {
				return false;
			}
		} else if (!this.commute.equals(other.commute)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.deviceWatts == null) {
			if (other.deviceWatts != null) {
				return false;
			}
		} else if (!this.deviceWatts.equals(other.deviceWatts)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.elapsedTime == null) {
			if (other.elapsedTime != null) {
				return false;
			}
		} else if (!this.elapsedTime.equals(other.elapsedTime)) {
			return false;
		}
		if (this.endLatlng == null) {
			if (other.endLatlng != null) {
				return false;
			}
		} else if (!this.endLatlng.equals(other.endLatlng)) {
			return false;
		}
		if (this.externalId == null) {
			if (other.externalId != null) {
				return false;
			}
		} else if (!this.externalId.equals(other.externalId)) {
			return false;
		}
		if (this.flagged == null) {
			if (other.flagged != null) {
				return false;
			}
		} else if (!this.flagged.equals(other.flagged)) {
			return false;
		}
		if (this.gear == null) {
			if (other.gear != null) {
				return false;
			}
		} else if (!this.gear.equals(other.gear)) {
			return false;
		}
		if (this.gearId == null) {
			if (other.gearId != null) {
				return false;
			}
		} else if (!this.gearId.equals(other.gearId)) {
			return false;
		}
		if (this.hasKudoed == null) {
			if (other.hasKudoed != null) {
				return false;
			}
		} else if (!this.hasKudoed.equals(other.hasKudoed)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.instagramPrimaryPhoto == null) {
			if (other.instagramPrimaryPhoto != null) {
				return false;
			}
		} else if (!this.instagramPrimaryPhoto.equals(other.instagramPrimaryPhoto)) {
			return false;
		}
		if (this.kilojoules == null) {
			if (other.kilojoules != null) {
				return false;
			}
		} else if (!this.kilojoules.equals(other.kilojoules)) {
			return false;
		}
		if (this.kudosCount == null) {
			if (other.kudosCount != null) {
				return false;
			}
		} else if (!this.kudosCount.equals(other.kudosCount)) {
			return false;
		}
		if (this.locationCity == null) {
			if (other.locationCity != null) {
				return false;
			}
		} else if (!this.locationCity.equals(other.locationCity)) {
			return false;
		}
		if (this.locationCountry == null) {
			if (other.locationCountry != null) {
				return false;
			}
		} else if (!this.locationCountry.equals(other.locationCountry)) {
			return false;
		}
		if (this.locationState == null) {
			if (other.locationState != null) {
				return false;
			}
		} else if (!this.locationState.equals(other.locationState)) {
			return false;
		}
		if (this.manual == null) {
			if (other.manual != null) {
				return false;
			}
		} else if (!this.manual.equals(other.manual)) {
			return false;
		}
		if (this.map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!this.map.equals(other.map)) {
			return false;
		}
		if (this.maxHeartrate == null) {
			if (other.maxHeartrate != null) {
				return false;
			}
		} else if (!this.maxHeartrate.equals(other.maxHeartrate)) {
			return false;
		}
		if (this.maxSpeed == null) {
			if (other.maxSpeed != null) {
				return false;
			}
		} else if (!this.maxSpeed.equals(other.maxSpeed)) {
			return false;
		}
		if (this.movingTime == null) {
			if (other.movingTime != null) {
				return false;
			}
		} else if (!this.movingTime.equals(other.movingTime)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.photoCount == null) {
			if (other.photoCount != null) {
				return false;
			}
		} else if (!this.photoCount.equals(other.photoCount)) {
			return false;
		}
		if (this.photos == null) {
			if (other.photos != null) {
				return false;
			}
		} else if (!this.photos.equals(other.photos)) {
			return false;
		}
		if (this.privateActivity == null) {
			if (other.privateActivity != null) {
				return false;
			}
		} else if (!this.privateActivity.equals(other.privateActivity)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.segmentEfforts == null) {
			if (other.segmentEfforts != null) {
				return false;
			}
		} else if (!this.segmentEfforts.equals(other.segmentEfforts)) {
			return false;
		}
		if (this.splitsMetric == null) {
			if (other.splitsMetric != null) {
				return false;
			}
		} else if (!this.splitsMetric.equals(other.splitsMetric)) {
			return false;
		}
		if (this.splitsStandard == null) {
			if (other.splitsStandard != null) {
				return false;
			}
		} else if (!this.splitsStandard.equals(other.splitsStandard)) {
			return false;
		}
		if (this.startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!this.startDate.equals(other.startDate)) {
			return false;
		}
		if (this.startDateLocal == null) {
			if (other.startDateLocal != null) {
				return false;
			}
		} else if (!this.startDateLocal.equals(other.startDateLocal)) {
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
		if (this.timezone == null) {
			if (other.timezone != null) {
				return false;
			}
		} else if (!this.timezone.equals(other.timezone)) {
			return false;
		}
		if (this.totalElevationGain == null) {
			if (other.totalElevationGain != null) {
				return false;
			}
		} else if (!this.totalElevationGain.equals(other.totalElevationGain)) {
			return false;
		}
		if (this.trainer == null) {
			if (other.trainer != null) {
				return false;
			}
		} else if (!this.trainer.equals(other.trainer)) {
			return false;
		}
		if (this.truncated == null) {
			if (other.truncated != null) {
				return false;
			}
		} else if (!this.truncated.equals(other.truncated)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (this.uploadId == null) {
			if (other.uploadId != null) {
				return false;
			}
		} else if (!this.uploadId.equals(other.uploadId)) {
			return false;
		}
		if (this.weightedAverageWatts == null) {
			if (other.weightedAverageWatts != null) {
				return false;
			}
		} else if (!this.weightedAverageWatts.equals(other.weightedAverageWatts)) {
			return false;
		}
		if (this.workoutType != other.workoutType) {
			return false;
		}
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
	public String getLocationCity() {
		return this.locationCity;
	}
	/**
	 * @return the locationCountry
	 */
	public String getLocationCountry() {
		return this.locationCountry;
	}
	/**
	 * @return the locationState
	 */
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
	 * @return the trainer
	 */
	public Boolean getTrainer() {
		return this.trainer;
	}
	/**
	 * @return the truncated
	 */
	public Integer getTruncated() {
		return this.truncated;
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
	public String getUploadId() {
		return this.uploadId;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.achievementCount == null) ? 0 : this.achievementCount.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.athleteCount == null) ? 0 : this.athleteCount.hashCode());
		result = (prime * result) + ((this.averageCadence == null) ? 0 : this.averageCadence.hashCode());
		result = (prime * result) + ((this.averageHeartrate == null) ? 0 : this.averageHeartrate.hashCode());
		result = (prime * result) + ((this.averageSpeed == null) ? 0 : this.averageSpeed.hashCode());
		result = (prime * result) + ((this.averageTemp == null) ? 0 : this.averageTemp.hashCode());
		result = (prime * result) + ((this.averageWatts == null) ? 0 : this.averageWatts.hashCode());
		result = (prime * result) + ((this.bestEfforts == null) ? 0 : this.bestEfforts.hashCode());
		result = (prime * result) + ((this.calories == null) ? 0 : this.calories.hashCode());
		result = (prime * result) + ((this.commentCount == null) ? 0 : this.commentCount.hashCode());
		result = (prime * result) + ((this.commute == null) ? 0 : this.commute.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.deviceWatts == null) ? 0 : this.deviceWatts.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.endLatlng == null) ? 0 : this.endLatlng.hashCode());
		result = (prime * result) + ((this.externalId == null) ? 0 : this.externalId.hashCode());
		result = (prime * result) + ((this.flagged == null) ? 0 : this.flagged.hashCode());
		result = (prime * result) + ((this.gear == null) ? 0 : this.gear.hashCode());
		result = (prime * result) + ((this.gearId == null) ? 0 : this.gearId.hashCode());
		result = (prime * result) + ((this.hasKudoed == null) ? 0 : this.hasKudoed.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.instagramPrimaryPhoto == null) ? 0 : this.instagramPrimaryPhoto.hashCode());
		result = (prime * result) + ((this.kilojoules == null) ? 0 : this.kilojoules.hashCode());
		result = (prime * result) + ((this.kudosCount == null) ? 0 : this.kudosCount.hashCode());
		result = (prime * result) + ((this.locationCity == null) ? 0 : this.locationCity.hashCode());
		result = (prime * result) + ((this.locationCountry == null) ? 0 : this.locationCountry.hashCode());
		result = (prime * result) + ((this.locationState == null) ? 0 : this.locationState.hashCode());
		result = (prime * result) + ((this.manual == null) ? 0 : this.manual.hashCode());
		result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
		result = (prime * result) + ((this.maxHeartrate == null) ? 0 : this.maxHeartrate.hashCode());
		result = (prime * result) + ((this.maxSpeed == null) ? 0 : this.maxSpeed.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.photoCount == null) ? 0 : this.photoCount.hashCode());
		result = (prime * result) + ((this.photos == null) ? 0 : this.photos.hashCode());
		result = (prime * result) + ((this.privateActivity == null) ? 0 : this.privateActivity.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.segmentEfforts == null) ? 0 : this.segmentEfforts.hashCode());
		result = (prime * result) + ((this.splitsMetric == null) ? 0 : this.splitsMetric.hashCode());
		result = (prime * result) + ((this.splitsStandard == null) ? 0 : this.splitsStandard.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		result = (prime * result) + ((this.startLatitude == null) ? 0 : this.startLatitude.hashCode());
		result = (prime * result) + ((this.startLatlng == null) ? 0 : this.startLatlng.hashCode());
		result = (prime * result) + ((this.startLongitude == null) ? 0 : this.startLongitude.hashCode());
		result = (prime * result) + ((this.timezone == null) ? 0 : this.timezone.hashCode());
		result = (prime * result) + ((this.totalElevationGain == null) ? 0 : this.totalElevationGain.hashCode());
		result = (prime * result) + ((this.trainer == null) ? 0 : this.trainer.hashCode());
		result = (prime * result) + ((this.truncated == null) ? 0 : this.truncated.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.uploadId == null) ? 0 : this.uploadId.hashCode());
		result = (prime * result) + ((this.weightedAverageWatts == null) ? 0 : this.weightedAverageWatts.hashCode());
		result = (prime * result) + ((this.workoutType == null) ? 0 : this.workoutType.hashCode());
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
	public void setLocationCity(final String locationCity) {
		this.locationCity = locationCity;
	}
	/**
	 * @param locationCountry the locationCountry to set
	 */
	public void setLocationCountry(final String locationCountry) {
		this.locationCountry = locationCountry;
	}
	/**
	 * @param locationState the locationState to set
	 */
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
	 * @param trainer the trainer to set
	 */
	public void setTrainer(final Boolean trainer) {
		this.trainer = trainer;
	}
	/**
	 * @param truncated the truncated to set
	 */
	public void setTruncated(final Integer truncated) {
		this.truncated = truncated;
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
	public void setUploadId(final String uploadId) {
		this.uploadId = uploadId;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivity [id=" + this.id + ", resourceState=" + this.resourceState + ", externalId=" + this.externalId + ", athlete=" + this.athlete //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", name=" + this.name + ", description=" + this.description + ", distance=" + this.distance + ", movingTime=" + this.movingTime //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", elapsedTime=" + this.elapsedTime + ", totalElevationGain=" + this.totalElevationGain + ", type=" + this.type + ", startDate=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.startDate + ", startDateLocal=" + this.startDateLocal + ", timezone=" + this.timezone + ", startLatlng=" + this.startLatlng //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", endLatlng=" + this.endLatlng + ", locationCity=" + this.locationCity + ", locationState=" + this.locationState + ", locationCountry=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.locationCountry + ", achievementCount=" + this.achievementCount + ", kudosCount=" + this.kudosCount + ", commentCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.commentCount + ", athleteCount=" + this.athleteCount + ", photoCount=" + this.photoCount + ", map=" + this.map + ", trainer=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.trainer + ", commute=" + this.commute + ", manual=" + this.manual + ", privateActivity=" + this.privateActivity + ", flagged=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.flagged + ", workoutType=" + this.workoutType + ", gearId=" + this.gearId + ", gear=" + this.gear + ", averageSpeed=" + this.averageSpeed //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", maxSpeed=" + this.maxSpeed + ", averageCadence=" + this.averageCadence + ", averageTemp=" + this.averageTemp + ", averageWatts=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.averageWatts + ", weightedAverageWatts=" + this.weightedAverageWatts + ", kilojoules=" + this.kilojoules + ", deviceWatts=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.deviceWatts + ", averageHeartrate=" + this.averageHeartrate + ", maxHeartrate=" + this.maxHeartrate + ", calories=" + this.calories //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", truncated=" + this.truncated + ", hasKudoed=" + this.hasKudoed + ", segmentEfforts=" + this.segmentEfforts + ", splitsMetric=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.splitsMetric + ", splitsStandard=" + this.splitsStandard + ", bestEfforts=" + this.bestEfforts + ", uploadId=" + this.uploadId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", startLatitude=" + this.startLatitude + ", startLongitude=" + this.startLongitude + ", instagramPrimaryPhoto=" + this.instagramPrimaryPhoto //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", photos=" + this.photos + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
