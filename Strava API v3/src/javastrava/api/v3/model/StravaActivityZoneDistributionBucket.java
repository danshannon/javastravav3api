package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaActivityZoneDistributionBucket {
	/**
	 * Maximum value of heart rate or power for this zone. Note that this returns as -1 if the maximum is in fact infinity
	 */
	private Integer max;
	/**
	 * Minimum value of heart rate or power for this zone
	 */
	private Integer min;
	/**
	 * Total time in seconds spent in this zone
	 */
	private Integer time;
}
