package stravajava.api.v3.model;

import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dshannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaMap {
	private String id;
	private String polyline;
	private String summaryPolyline;
	private StravaResourceState resourceState;
}
