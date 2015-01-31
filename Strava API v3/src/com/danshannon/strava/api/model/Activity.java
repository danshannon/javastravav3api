package com.danshannon.strava.api.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.WorkoutType;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dan Shannon
 *
 */
@Data
public class Activity {
	public Activity() {
		// Required
		super();
	}
	
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
	@SerializedName("private")
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
}
