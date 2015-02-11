package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * A lap within an {@link StravaActivity}
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaLap {
	private Integer id;
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
	private Float totalElevationGain;
	private Float averageSpeed;
	private Float maxSpeed;
	private Float averageCadence;
	private Float averageWatts;
	private Boolean deviceWatts;
	private Float averageHeartrate;
	private Float maxHeartrate;
	private Integer lapIndex;
}
