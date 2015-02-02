package stravajava.api.v3.model;

import java.util.Date;

import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public class StravaBestRunningEffort {
	public StravaBestRunningEffort() {
		// Required
		super();
	}
	
	private Integer id;
	private StravaResourceState resourceState;
	private String name;
	private StravaSegment segment;
	private StravaActivity activity;
	private StravaAthlete athlete;
	private Integer komRank;
	private Integer prRank;
	private Integer elapsedTime;
	private Integer movingTime;
	private Date startDate;
	private Date startDateLocal;
	private Float distance;
}
