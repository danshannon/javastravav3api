package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaActivityZoneType;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaActivityZone {
	private Integer score;
	private List<StravaActivityZoneDistributionBucket> distributionBuckets;
	private StravaActivityZoneType type;
	private StravaResourceState resourceState;
	private Boolean sensorBased;
	private Integer points;
	private Boolean customZones;
	private Integer max;
}
