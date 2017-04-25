package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaActivityType;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaWorkoutType;
import javastrava.service.ActivityService;
import javastrava.service.StreamService;

/**
 * <p>
 * A run, ride, or other activity
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaActivity implements StravaCacheableEntity<Long> {
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
	 * Name of the activity. Defaults to boring things, but athletes with imagination will come up with witty and interesting names on a regular basis!
	 */
	private String name;

	/**
	 * Detailed description of the activity
	 */
	private String description;

	/**
	 * Distance travelled in metres. If you want it in funny old imperial, that's up to you to convert it
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
	 * State or county or canton or whatever other thing that the activity started in
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
	 * Total number of achievements for this activity (returned by Strava, not recalculated by javastrava)
	 * </p>
	 */
	private Integer achievementCount;

	/**
	 * <p>
	 * Total number of athletes who have left kudos on this activity (returned by Strava, not recalculated by javastrava)
	 * </p>
	 *
	 * <p>
	 * To get the actual list of athletes who have left kudos, see {@link ActivityService#listActivityKudoers(Long, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer kudosCount;

	/**
	 * <p>
	 * Total number of comments left on this activity (returned by Strava, not recalculated by javastrava)
	 * </p>
	 *
	 * <p>
	 * To get the actual list of comments, see {@link ActivityService#listActivityComments(Long, Boolean, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer commentCount;

	/**
	 * <p>
	 * If Strava thinks this was a group activity, this is the number of athletes taking part
	 * </p>
	 *
	 * <p>
	 * To get the list of activities by all the other people who also took part, see {@link ActivityService#listRelatedActivities(Long, javastrava.util.Paging)}
	 * </p>
	 */
	private Integer athleteCount;

	/**
	 * <p>
	 * Total number of photos attached to this activity by the athlete on Instagram
	 * </p>
	 *
	 * <p>
	 * To get the actual photo details, see {@link ActivityService#listActivityPhotos(Long)}
	 * </p>
	 */
	private Integer photoCount;

	/**
	 * <p>
	 * Total number of photos attached to this activity by the athlete on Instagram <b>and</b> Strava
	 * </p>
	 *
	 * <p>
	 * To get the actual photo details, see {@link ActivityService#listActivityPhotos(Long)}
	 * </p>
	 */
	private Integer totalPhotoCount;

	/**
	 * <p>
	 * Weird map representation returned with the activity, basically contains polylines for use on Google maps
	 * </p>
	 *
	 * <p>
	 * If you want the actual set of GPS coordinates of the activity, then you need to use
	 * {@link StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)}
	 * </p>
	 */
	private StravaMap map;

	/**
	 * Is set to <code>true</code> if Strava believes the ride was done on an indoor trainer
	 */
	private Boolean trainer;

	/**
	 * Is set to <code>true</code> if the activity was flagged as a commute
	 * </p>
	 */
	private Boolean commute;

	/**
	 * Is set to <code>true</code> if the activity was manually entered into Strava, rather than being uploaded as a file from some GPS device, or your phone
	 * </p>
	 */
	private Boolean manual;

	/**
	 * Is set to <code>true</code> if the activity has been flagged as private by the athlete
	 */
	@SerializedName("private")
	private Boolean privateActivity;

	/**
	 * Is set to <code>true</code> if the activity has been flagged as suspicious by another user on Strava, usually because it has crazy speeds because it was done in a car, not on a bike
	 */
	private Boolean flagged;

	/**
	 * For runs only, 0 -> ‘default’, 1 -> ‘race’, 2 -> ‘long run’, 3 -> ‘intervals’
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
	 * Average speed (in metres per second) of the activity (as calculated by Strava; is not recalculated or checked by javastrava)
	 */
	private Float averageSpeed;

	/**
	 * Maximum speed (in metres per second) achieved during the activity (quite often as a result of GPS inaccuracies). Calculated by Strava and not recalculated or checked by javastrava.
	 */
	private Float maxSpeed;

	/**
	 * Average RPM if cadence data was provided with the uploaded activity
	 */
	private Float averageCadence;

	/**
	 * Average temperature (in degrees Celsius) if temperature data was provided with the uploaded activity
	 */
	private Float averageTemp;

	/**
	 * Average power (in watts) for rides only. Strava calculates an estimate for this if power meter data is not provided with the upload.
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
	 * Is set to <code>true</code> if power meter data was provided with the upload
	 */
	private Boolean deviceWatts;

	/**
	 * Is set to <code>true</code> if the activity was recorded with heartrate
	 */
	private Boolean hasHeartrate;

	/**
	 * Average heart rate (in beats per minute) if heart rate data was provided with the upload
	 */
	private Float averageHeartrate;

	/**
	 * Maximum heart rate (in beats per minute) if heart rate data was provided with the upload
	 */
	private Integer maxHeartrate;

	/**
	 * Kilocalories expended (calculated by Strava)
	 */
	private Float calories;

	/**
	 * Is set to <code>true</code> if the currently authenticated athlete has kudoed this activity
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
	 * Runs only - list of imperial splits ("standard" hahahaha you Americans are so funny)
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
	 * The token used to construct an embed URL in the form
	 * <a href= "https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]">https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]</a>
	 */
	private String embedToken;

	/**
	 * The name of the device used to record the activity
	 */
	private String deviceName;

	/**
	 * A measure of heartrate intensity, available on premium users’ activities only
	 */
	private Integer sufferScore;

	/**
	 * UNDOCUMENTED
	 */
	private String utcOffset;

	/**
	 * Maximum elevation in metres above sea level
	 */
	private Float elevHigh;

	/**
	 * Minimum elevation in metres above sea level
	 */
	private Float elevLow;

	/**
	 * UNDOCUMENTED (but presumably is the number of achievements counted on the activity)
	 */
	private Integer prCount;

	/**
	 * Maximum recorded power in watts (rides with power metre data only)
	 */
	private Integer maxWatts;

	/**
	 * Laps associated with the activity
	 */
	private List<StravaLap> laps;

	/**
	 * UNDOCUMENTED
	 */
	private StravaSimilarActivities similarActivities;

	/**
	 * No args constructor
	 */
	public StravaActivity() {
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
		if (this.deviceName == null) {
			if (other.deviceName != null) {
				return false;
			}
		} else if (!this.deviceName.equals(other.deviceName)) {
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
		if (this.elevHigh == null) {
			if (other.elevHigh != null) {
				return false;
			}
		} else if (!this.elevHigh.equals(other.elevHigh)) {
			return false;
		}
		if (this.elevLow == null) {
			if (other.elevLow != null) {
				return false;
			}
		} else if (!this.elevLow.equals(other.elevLow)) {
			return false;
		}
		if (this.embedToken == null) {
			if (other.embedToken != null) {
				return false;
			}
		} else if (!this.embedToken.equals(other.embedToken)) {
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
		if (this.hasHeartrate == null) {
			if (other.hasHeartrate != null) {
				return false;
			}
		} else if (!this.hasHeartrate.equals(other.hasHeartrate)) {
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
		if (this.laps == null) {
			if (other.laps != null) {
				return false;
			}
		} else if (!this.laps.equals(other.laps)) {
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
		if (this.maxWatts == null) {
			if (other.maxWatts != null) {
				return false;
			}
		} else if (!this.maxWatts.equals(other.maxWatts)) {
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
		if (this.prCount == null) {
			if (other.prCount != null) {
				return false;
			}
		} else if (!this.prCount.equals(other.prCount)) {
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
		if (this.similarActivities == null) {
			if (other.similarActivities != null) {
				return false;
			}
		} else if (!this.similarActivities.equals(other.similarActivities)) {
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
		if (this.sufferScore == null) {
			if (other.sufferScore != null) {
				return false;
			}
		} else if (!this.sufferScore.equals(other.sufferScore)) {
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
		if (this.totalPhotoCount == null) {
			if (other.totalPhotoCount != null) {
				return false;
			}
		} else if (!this.totalPhotoCount.equals(other.totalPhotoCount)) {
			return false;
		}
		if (this.trainer == null) {
			if (other.trainer != null) {
				return false;
			}
		} else if (!this.trainer.equals(other.trainer)) {
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
		if (this.utcOffset == null) {
			if (other.utcOffset != null) {
				return false;
			}
		} else if (!this.utcOffset.equals(other.utcOffset)) {
			return false;
		}
		if (this.video == null) {
			if (other.video != null) {
				return false;
			}
		} else if (!this.video.equals(other.video)) {
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
	 * @return the elevHigh
	 */
	public Float getElevHigh() {
		return this.elevHigh;
	}

	/**
	 * @return the elevLow
	 */
	public Float getElevLow() {
		return this.elevLow;
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
	 * @return the laps
	 */
	public List<StravaLap> getLaps() {
		return this.laps;
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
	 * @return the maxWatts
	 */
	public Integer getMaxWatts() {
		return this.maxWatts;
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
	 * @return the prCount
	 */
	public Integer getPrCount() {
		return this.prCount;
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
	 * @return the similarActivities
	 */
	public StravaSimilarActivities getSimilarActivities() {
		return this.similarActivities;
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
	 * @return the sufferScore
	 */
	public Integer getSufferScore() {
		return this.sufferScore;
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
	 * @return the utcOffset
	 */
	public String getUtcOffset() {
		return this.utcOffset;
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

	/*
	 * (non-Javadoc)
	 *
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
		result = (prime * result) + ((this.deviceName == null) ? 0 : this.deviceName.hashCode());
		result = (prime * result) + ((this.deviceWatts == null) ? 0 : this.deviceWatts.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elapsedTime == null) ? 0 : this.elapsedTime.hashCode());
		result = (prime * result) + ((this.elevHigh == null) ? 0 : this.elevHigh.hashCode());
		result = (prime * result) + ((this.elevLow == null) ? 0 : this.elevLow.hashCode());
		result = (prime * result) + ((this.embedToken == null) ? 0 : this.embedToken.hashCode());
		result = (prime * result) + ((this.endLatlng == null) ? 0 : this.endLatlng.hashCode());
		result = (prime * result) + ((this.externalId == null) ? 0 : this.externalId.hashCode());
		result = (prime * result) + ((this.flagged == null) ? 0 : this.flagged.hashCode());
		result = (prime * result) + ((this.gear == null) ? 0 : this.gear.hashCode());
		result = (prime * result) + ((this.gearId == null) ? 0 : this.gearId.hashCode());
		result = (prime * result) + ((this.hasHeartrate == null) ? 0 : this.hasHeartrate.hashCode());
		result = (prime * result) + ((this.hasKudoed == null) ? 0 : this.hasKudoed.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.instagramPrimaryPhoto == null) ? 0 : this.instagramPrimaryPhoto.hashCode());
		result = (prime * result) + ((this.kilojoules == null) ? 0 : this.kilojoules.hashCode());
		result = (prime * result) + ((this.kudosCount == null) ? 0 : this.kudosCount.hashCode());
		result = (prime * result) + ((this.laps == null) ? 0 : this.laps.hashCode());
		result = (prime * result) + ((this.locationCity == null) ? 0 : this.locationCity.hashCode());
		result = (prime * result) + ((this.locationCountry == null) ? 0 : this.locationCountry.hashCode());
		result = (prime * result) + ((this.locationState == null) ? 0 : this.locationState.hashCode());
		result = (prime * result) + ((this.manual == null) ? 0 : this.manual.hashCode());
		result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
		result = (prime * result) + ((this.maxHeartrate == null) ? 0 : this.maxHeartrate.hashCode());
		result = (prime * result) + ((this.maxSpeed == null) ? 0 : this.maxSpeed.hashCode());
		result = (prime * result) + ((this.maxWatts == null) ? 0 : this.maxWatts.hashCode());
		result = (prime * result) + ((this.movingTime == null) ? 0 : this.movingTime.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.photoCount == null) ? 0 : this.photoCount.hashCode());
		result = (prime * result) + ((this.photos == null) ? 0 : this.photos.hashCode());
		result = (prime * result) + ((this.prCount == null) ? 0 : this.prCount.hashCode());
		result = (prime * result) + ((this.privateActivity == null) ? 0 : this.privateActivity.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.segmentEfforts == null) ? 0 : this.segmentEfforts.hashCode());
		result = (prime * result) + ((this.similarActivities == null) ? 0 : this.similarActivities.hashCode());
		result = (prime * result) + ((this.splitsMetric == null) ? 0 : this.splitsMetric.hashCode());
		result = (prime * result) + ((this.splitsStandard == null) ? 0 : this.splitsStandard.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		result = (prime * result) + ((this.startLatitude == null) ? 0 : this.startLatitude.hashCode());
		result = (prime * result) + ((this.startLatlng == null) ? 0 : this.startLatlng.hashCode());
		result = (prime * result) + ((this.startLongitude == null) ? 0 : this.startLongitude.hashCode());
		result = (prime * result) + ((this.sufferScore == null) ? 0 : this.sufferScore.hashCode());
		result = (prime * result) + ((this.timezone == null) ? 0 : this.timezone.hashCode());
		result = (prime * result) + ((this.totalElevationGain == null) ? 0 : this.totalElevationGain.hashCode());
		result = (prime * result) + ((this.totalPhotoCount == null) ? 0 : this.totalPhotoCount.hashCode());
		result = (prime * result) + ((this.trainer == null) ? 0 : this.trainer.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.uploadId == null) ? 0 : this.uploadId.hashCode());
		result = (prime * result) + ((this.utcOffset == null) ? 0 : this.utcOffset.hashCode());
		result = (prime * result) + ((this.video == null) ? 0 : this.video.hashCode());
		result = (prime * result) + ((this.weightedAverageWatts == null) ? 0 : this.weightedAverageWatts.hashCode());
		result = (prime * result) + ((this.workoutType == null) ? 0 : this.workoutType.hashCode());
		return result;
	}

	/**
	 * @param achievementCount
	 *            the achievementCount to set
	 */
	public void setAchievementCount(final Integer achievementCount) {
		this.achievementCount = achievementCount;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param athleteCount
	 *            the athleteCount to set
	 */
	public void setAthleteCount(final Integer athleteCount) {
		this.athleteCount = athleteCount;
	}

	/**
	 * @param averageCadence
	 *            the averageCadence to set
	 */
	public void setAverageCadence(final Float averageCadence) {
		this.averageCadence = averageCadence;
	}

	/**
	 * @param averageHeartrate
	 *            the averageHeartrate to set
	 */
	public void setAverageHeartrate(final Float averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}

	/**
	 * @param averageSpeed
	 *            the averageSpeed to set
	 */
	public void setAverageSpeed(final Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	/**
	 * @param averageTemp
	 *            the averageTemp to set
	 */
	public void setAverageTemp(final Float averageTemp) {
		this.averageTemp = averageTemp;
	}

	/**
	 * @param averageWatts
	 *            the averageWatts to set
	 */
	public void setAverageWatts(final Float averageWatts) {
		this.averageWatts = averageWatts;
	}

	/**
	 * @param bestEfforts
	 *            the bestEfforts to set
	 */
	public void setBestEfforts(final List<StravaBestRunningEffort> bestEfforts) {
		this.bestEfforts = bestEfforts;
	}

	/**
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories(final Float calories) {
		this.calories = calories;
	}

	/**
	 * @param commentCount
	 *            the commentCount to set
	 */
	public void setCommentCount(final Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @param commute
	 *            the commute to set
	 */
	public void setCommute(final Boolean commute) {
		this.commute = commute;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param deviceName
	 *            The device name
	 */
	public void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @param deviceWatts
	 *            the deviceWatts to set
	 */
	public void setDeviceWatts(final Boolean deviceWatts) {
		this.deviceWatts = deviceWatts;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param elapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(final Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * @param elevHigh
	 *            the elevHigh to set
	 */
	public void setElevHigh(Float elevHigh) {
		this.elevHigh = elevHigh;
	}

	/**
	 * @param elevLow
	 *            the elevLow to set
	 */
	public void setElevLow(Float elevLow) {
		this.elevLow = elevLow;
	}

	/**
	 * @param embedToken
	 *            the embedToken to set
	 */
	public void setEmbedToken(final String embedToken) {
		this.embedToken = embedToken;
	}

	/**
	 * @param endLatlng
	 *            the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}

	/**
	 * @param externalId
	 *            the externalId to set
	 */
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @param flagged
	 *            the flagged to set
	 */
	public void setFlagged(final Boolean flagged) {
		this.flagged = flagged;
	}

	/**
	 * @param gear
	 *            the gear to set
	 */
	public void setGear(final StravaGear gear) {
		this.gear = gear;
	}

	/**
	 * @param gearId
	 *            the gearId to set
	 */
	public void setGearId(final String gearId) {
		this.gearId = gearId;
	}

	/**
	 * @param hasHeartrate
	 *            Does the activity have heart rate recorded?
	 */
	public void setHasHeartrate(final Boolean hasHeartrate) {
		this.hasHeartrate = hasHeartrate;
	}

	/**
	 * @param hasKudoed
	 *            the hasKudoed to set
	 */
	public void setHasKudoed(final Boolean hasKudoed) {
		this.hasKudoed = hasKudoed;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param instagramPrimaryPhoto
	 *            the instagramPrimaryPhoto to set
	 */
	public void setInstagramPrimaryPhoto(final String instagramPrimaryPhoto) {
		this.instagramPrimaryPhoto = instagramPrimaryPhoto;
	}

	/**
	 * @param kilojoules
	 *            the kilojoules to set
	 */
	public void setKilojoules(final Float kilojoules) {
		this.kilojoules = kilojoules;
	}

	/**
	 * @param kudosCount
	 *            the kudosCount to set
	 */
	public void setKudosCount(final Integer kudosCount) {
		this.kudosCount = kudosCount;
	}

	/**
	 * @param laps
	 *            the laps to set
	 */
	public void setLaps(List<StravaLap> laps) {
		this.laps = laps;
	}

	/**
	 * @param locationCity
	 *            the locationCity to set
	 */
	@Deprecated
	public void setLocationCity(final String locationCity) {
		this.locationCity = locationCity;
	}

	/**
	 * @param locationCountry
	 *            the locationCountry to set
	 */
	@Deprecated
	public void setLocationCountry(final String locationCountry) {
		this.locationCountry = locationCountry;
	}

	/**
	 * @param locationState
	 *            the locationState to set
	 */
	@Deprecated
	public void setLocationState(final String locationState) {
		this.locationState = locationState;
	}

	/**
	 * @param manual
	 *            the manual to set
	 */
	public void setManual(final Boolean manual) {
		this.manual = manual;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(final StravaMap map) {
		this.map = map;
	}

	/**
	 * @param maxHeartrate
	 *            the maxHeartrate to set
	 */
	public void setMaxHeartrate(final Integer maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(final Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @param maxWatts
	 *            the maxWatts to set
	 */
	public void setMaxWatts(Integer maxWatts) {
		this.maxWatts = maxWatts;
	}

	/**
	 * @param movingTime
	 *            the movingTime to set
	 */
	public void setMovingTime(final Integer movingTime) {
		this.movingTime = movingTime;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param photoCount
	 *            the photoCount to set
	 */
	public void setPhotoCount(final Integer photoCount) {
		this.photoCount = photoCount;
	}

	/**
	 * @param photos
	 *            the photos to set
	 */
	public void setPhotos(final StravaActivityPhotos photos) {
		this.photos = photos;
	}

	/**
	 * @param prCount
	 *            the prCount to set
	 */
	public void setPrCount(Integer prCount) {
		this.prCount = prCount;
	}

	/**
	 * @param privateActivity
	 *            the privateActivity to set
	 */
	public void setPrivateActivity(final Boolean privateActivity) {
		this.privateActivity = privateActivity;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param segmentEfforts
	 *            the segmentEfforts to set
	 */
	public void setSegmentEfforts(final List<StravaSegmentEffort> segmentEfforts) {
		this.segmentEfforts = segmentEfforts;
	}

	/**
	 * @param similarActivities
	 *            the similarActivities to set
	 */
	public void setSimilarActivities(StravaSimilarActivities similarActivities) {
		this.similarActivities = similarActivities;
	}

	/**
	 * @param splitsMetric
	 *            the splitsMetric to set
	 */
	public void setSplitsMetric(final List<StravaSplit> splitsMetric) {
		this.splitsMetric = splitsMetric;
	}

	/**
	 * @param splitsStandard
	 *            the splitsStandard to set
	 */
	public void setSplitsStandard(final List<StravaSplit> splitsStandard) {
		this.splitsStandard = splitsStandard;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final ZonedDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param startDateLocal
	 *            the startDateLocal to set
	 */
	public void setStartDateLocal(final LocalDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}

	/**
	 * @param startLatitude
	 *            the startLatitude to set
	 */
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
	public void setStartLongitude(final Float startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * @param sufferScore
	 *            the sufferScore to set
	 */
	public void setSufferScore(Integer sufferScore) {
		this.sufferScore = sufferScore;
	}

	/**
	 * @param timezone
	 *            the timezone to set
	 */
	public void setTimezone(final String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @param totalElevationGain
	 *            the totalElevationGain to set
	 */
	public void setTotalElevationGain(final Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	/**
	 * @param totalPhotoCount
	 *            the totalPhotoCount to set
	 */
	public void setTotalPhotoCount(final Integer totalPhotoCount) {
		this.totalPhotoCount = totalPhotoCount;
	}

	/**
	 * @param trainer
	 *            the trainer to set
	 */
	public void setTrainer(final Boolean trainer) {
		this.trainer = trainer;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final StravaActivityType type) {
		this.type = type;
	}

	/**
	 * @param uploadId
	 *            the uploadId to set
	 */
	public void setUploadId(final Long uploadId) {
		this.uploadId = uploadId;
	}

	/**
	 * @param utcOffset
	 *            the utcOffset to set
	 */
	public void setUtcOffset(String utcOffset) {
		this.utcOffset = utcOffset;
	}

	/**
	 * @param video
	 *            the video to set
	 */
	public void setVideo(final StravaVideo video) {
		this.video = video;
	}

	/**
	 * @param weightedAverageWatts
	 *            the weightedAverageWatts to set
	 */
	public void setWeightedAverageWatts(final Float weightedAverageWatts) {
		this.weightedAverageWatts = weightedAverageWatts;
	}

	/**
	 * @param workoutType
	 *            the workoutType to set
	 */
	public void setWorkoutType(final StravaWorkoutType workoutType) {
		this.workoutType = workoutType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivity [id=" + this.id + ", resourceState=" + this.resourceState + ", externalId=" + this.externalId + ", athlete=" + this.athlete + ", name=" + this.name + ", description=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.description + ", distance=" + this.distance + ", movingTime=" + this.movingTime + ", elapsedTime=" + this.elapsedTime + ", totalElevationGain=" + this.totalElevationGain //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", type=" + this.type //$NON-NLS-1$
				+ ", startDate=" //$NON-NLS-1$
				+ this.startDate + ", startDateLocal=" + this.startDateLocal + ", timezone=" + this.timezone + ", startLatlng=" + this.startLatlng + ", endLatlng=" + this.endLatlng + ", locationCity=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.locationCity + ", locationState=" + this.locationState + ", locationCountry=" + this.locationCountry + ", achievementCount=" + this.achievementCount + ", kudosCount=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.kudosCount + ", commentCount=" //$NON-NLS-1$
				+ this.commentCount + ", athleteCount=" + this.athleteCount + ", photoCount=" + this.photoCount + ", totalPhotoCount=" + this.totalPhotoCount + ", map=" + this.map + ", trainer=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.trainer + ", commute=" //$NON-NLS-1$
				+ this.commute + ", manual=" + this.manual + ", privateActivity=" + this.privateActivity + ", flagged=" + this.flagged + ", workoutType=" + this.workoutType + ", gearId=" + this.gearId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", gear=" + this.gear //$NON-NLS-1$
				+ ", averageSpeed=" + this.averageSpeed + ", maxSpeed=" + this.maxSpeed + ", averageCadence=" + this.averageCadence + ", averageTemp=" + this.averageTemp + ", averageWatts=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.averageWatts + ", weightedAverageWatts=" + this.weightedAverageWatts + ", kilojoules=" + this.kilojoules + ", deviceWatts=" + this.deviceWatts + ", hasHeartrate=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.hasHeartrate + ", averageHeartrate=" //$NON-NLS-1$
				+ this.averageHeartrate + ", maxHeartrate=" + this.maxHeartrate + ", calories=" + this.calories + ", hasKudoed=" + this.hasKudoed + ", segmentEfforts=" + this.segmentEfforts //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", splitsMetric=" + this.splitsMetric //$NON-NLS-1$
				+ ", splitsStandard=" + this.splitsStandard + ", bestEfforts=" + this.bestEfforts + ", uploadId=" + this.uploadId + ", startLatitude=" + this.startLatitude + ", startLongitude=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.startLongitude + ", instagramPrimaryPhoto=" + this.instagramPrimaryPhoto + ", photos=" + this.photos + ", video=" + this.video + ", embedToken=" + this.embedToken //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", deviceName=" + this.deviceName //$NON-NLS-1$
				+ ", sufferScore=" //$NON-NLS-1$
				+ this.sufferScore + ", utcOffset=" + this.utcOffset + ", elevHigh=" + this.elevHigh + ", elevLow=" + this.elevLow + ", prCount=" + this.prCount + ", maxWatts=" + this.maxWatts //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", laps=" + this.laps //$NON-NLS-1$
				+ ", similarActivities=" + this.similarActivities + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
