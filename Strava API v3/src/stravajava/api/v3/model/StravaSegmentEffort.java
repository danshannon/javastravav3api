package stravajava.api.v3.model;

import java.util.Date;

import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaSegmentEffort {
	public StravaSegmentEffort() {
		// Required
		super();
	}
	
	private Long id;
	private StravaResourceState resourceState;
	private String name;
	private StravaActivity activity;
	private StravaAthlete athlete;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
	private Integer startIndex;
	private Integer endIndex;
	private Float averageCadence;
	private Float averageWatts;
	private Float averageHeartrate;
	private Integer maxHeartrate;
	private StravaSegment segment;
	private Integer komRank;
	private Integer prRank;
	private Boolean hidden;
	private Boolean isKom; // see {@link SegmentServices#starredSegments()}
}
