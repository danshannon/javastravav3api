package stravajava.api.v3.model;

import java.util.List;

import stravajava.api.v3.model.reference.StravaActivityZoneType;
import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaActivityZone {
	public StravaActivityZone() {
		// Required
		super();
	}
	
	private Integer score;
	private List<StravaActivityZoneDistributionBucket> distributionBuckets;
	private StravaActivityZoneType type;
	private StravaResourceState resourceState;
	private Boolean sensorBased;
	private Integer points;
	private Boolean customZones;
	private Integer max;
}
