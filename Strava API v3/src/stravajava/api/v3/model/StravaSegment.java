package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.model.reference.StravaSegmentActivityType;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * {@link StravaSegment Segments} are specific sections of road. {@link StravaAthlete Athletes}&apos; {@link StravaSegmentEffort efforts} are compared on these
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
	private Integer id;
	private StravaResourceState resourceState;
	private String name;
	private StravaSegmentActivityType activityType;
	private Float distance;
	private Float averageGrade;
	private Float maximumGrade;
	private Float elevationHigh;
	private Float elevationLow;
	private StravaMapPoint startLatlng;
	private StravaMapPoint endLatlng;
	private StravaClimbCategory climbCategory;
	private String city;
	private String state;
	private String country;
	@SerializedName("private")
	private Boolean privateSegment; // is "private" in JSON API
	private Boolean starred; // true if authenticated athlete has starred segment
	private Date createdAt;
	private Date updatedAt;
	private Float totalElevationGain;
	private StravaMap map;
	private Integer effortCount;
	private Integer athleteCount;
	private Boolean hazardous;
	private Integer starCount;
	private StravaSegmentEffort athletePrEffort; // See {@link SegmentServices#listStarredSegments()}
}
