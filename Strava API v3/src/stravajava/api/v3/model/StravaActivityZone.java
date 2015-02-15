package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaActivityZoneType;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Heart rate and power zones are set by the {@link StravaAthlete athlete}. This class returns the
 * time (in seconds) spent in each zone during an {@link StravaActivity activity}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaActivityZone {
	/**
	 * Strava suffer score for the activity
	 */
	private Integer score;
	/**
	 * Data on each zone and time spent in it
	 */
	private List<StravaActivityZoneDistributionBucket> distributionBuckets;
	/**
	 * The type of activity zone - {@link StravaActivityZoneType#HEARTRATE} or {@link StravaActivityZoneType#POWER}
	 */
	private StravaActivityZoneType type;
	/**
	 * State of this resource
	 */
	private StravaResourceState resourceState;
	/**
	 * Is set to <code>true</code> if the information is based on sensor data included with the upload of the activity (i.e. a heart rate monitor for heart rates, or a power meter for power)
	 */
	private Boolean sensorBased;
	/**
	 * Points in this zone (contributing to the total suffer score) TODO ???
	 */
	private Integer points;
	/**
	 * Is set to <code>true</code> if the athlete has customised the zones
	 */
	private Boolean customZones;
	/**
	 * Maximum heart rate reached during the activity
	 */
	private Integer max;
}
