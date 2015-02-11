package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * @author dshannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaBestRunningEffort {
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
