package javastrava.api.v3.model;

import java.util.Date;

import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentActivityType;
import javastrava.api.v3.service.SegmentServices;
import javastrava.api.v3.service.StreamServices;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * {@link StravaSegment Segments} are specific sections of road. {@link StravaAthlete Athletes}' {@link StravaSegmentEffort efforts} are compared on these
 * segments and leaderboards are created.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
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
	private Date createdAt;
	/**
	 * Date and time the segment was last updated on Strava
	 */
	private Date updatedAt;
	/**
	 * total elevation gain in metres
	 */
	private Float totalElevationGain;
	/**
	 * Map of the segment (as 2 polylines usable with Google maps; if you want GPS coordinates then go take a look at {@link StreamServices#getSegmentStreams(Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)})
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
	 * The authenticated athlete's fastest effort on this segment - only returned with {@link SegmentServices#listStarredSegments(Integer)}
	 */
	private StravaSegmentEffort athletePrEffort;
	
	/**
	 * Date the athlete starred the segment (only returned when listing starred segments)
	 */
	private Date starredDate;
}
