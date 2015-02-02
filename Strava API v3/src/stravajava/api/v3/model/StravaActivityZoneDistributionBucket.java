package stravajava.api.v3.model;

import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaActivityZoneDistributionBucket {
	private Integer max;
	private Integer min;
	private Integer time;
	
	public StravaActivityZoneDistributionBucket() {
		// Required
		super();
	}
}
