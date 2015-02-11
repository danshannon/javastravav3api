package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaClimbCategory;

/**
 * Summary of segment returned by the segment explorer
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentExplorerResponseSegment {
	private Integer id;
	private String name;
	private StravaClimbCategory climbCategory;
	private String climbCategoryDesc;
	private Float avgGrade;
	private StravaMapPoint startLatlng;
	private StravaMapPoint endLatlng;
	private Float elevDifference;
	private Float distance;
	private String points;
}
