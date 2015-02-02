package stravajava.api.v3.model;

import java.util.Date;
import java.util.List;

import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.model.reference.StravaWorkoutType;
import lombok.Data;

import com.google.gson.annotations.SerializedName;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaActivity {
	public StravaActivity() {
		// Required
		super();
	}
	
	private Integer id;
	private StravaResourceState resourceState;
	private String externalId;
	private StravaAthlete athlete;
	private String name;
	private String description;
	private Float distance;
	private Integer movingTime;
	private Integer elapsedTime;
	private Float totalElevationGain;
	/**
	 * Note that this is kind of almost an enum, but it isn't???
	 */
	private StravaActivityType type;
	private Date startDate;
	private Date startDateLocal;
	private String timeZone;
	private StravaMapPoint startLatLng;
	private StravaMapPoint endLatLng;
	private String locationCity;
	private String locationState;
	private String locationCountry;
	private Integer achievementCount;
	private Integer kudosCount;
	private Integer commentCount;
	private Integer athleteCount;
	private Integer photoCount;
	private StravaMap map;
	private Boolean trainer;
	private Boolean commute;
	private Boolean manual;
	/**
	 * NB Is "private" in the API
	 */
	@SerializedName("private")
	private Boolean privateActivity;
	private Boolean flagged;
	private StravaWorkoutType workoutType;
	private String gearId;
	private StravaGear gear;
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
	private List<StravaSegmentEffort> segmentEfforts;
	private List<StravaSplit> splitsMetric;
	private List<StravaSplit> splitsStandard;
	private List<StravaBestRunningEffort> bestEfforts; 
}
