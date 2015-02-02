package stravajava.api.v3.model;

import stravajava.api.v3.model.reference.StravaClimbCategory;
import lombok.Data;

/**
 * Summary of segment returned by the segment explorer
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaSegmentExplorerResponseSegment {
	public StravaSegmentExplorerResponseSegment() {
		// Required
		super();
	}
	
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
