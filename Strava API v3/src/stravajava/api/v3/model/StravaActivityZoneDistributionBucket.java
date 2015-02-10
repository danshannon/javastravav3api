package stravajava.api.v3.model;

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
	private Integer max;
	private Integer min;
	private Integer time;
}
