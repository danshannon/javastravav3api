package stravajava.api.v3.model;

import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public class StravaMap {
	public StravaMap() {
		// Required
		super();
	}
	
	private String id;
	private String polyline;
	private String summaryPolyline;
	private StravaResourceState resourceState;
}
