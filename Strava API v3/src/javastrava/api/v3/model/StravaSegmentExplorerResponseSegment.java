package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaClimbCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Summary of segment returned by the segment explorer
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentExplorerResponseSegment {
	/**
	 * Strava's unique identifier of the {@link StravaSegment segment}
	 */
	private Integer id;
	/**
	 * Segment name
	 */
	private String name;
	/**
	 * Climb category
	 */
	private StravaClimbCategory climbCategory;
	/**
	 * Description of the climb category (see {@link StravaClimbCategory#getDescription()})
	 */
	private String climbCategoryDesc;
	/**
	 * Average grade in percent
	 */
	private Float avgGrade;
	/**
	 * Start co-ordinates of the segment
	 */
	private StravaMapPoint startLatlng;
	/**
	 * End co-ordinates of the segment
	 */
	private StravaMapPoint endLatlng;
	/**
	 * Total elevation difference in metres
	 */
	private Float elevDifference;
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Polyline for rendering with Google maps
	 */
	private String points;
}
