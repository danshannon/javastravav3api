package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Strava's representation of a map. Contains only a pair of polylines suitable for use with Google maps.
 * </p>
 * 
 * <p>
 * If you want a detailed map that consists of GPS co-ordinates as in the original upload, then you're after {@link StravaStream}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaMap {
	/**
	 * Strava's unique identifier for the map
	 */
	private String id;
	/**
	 * Detailed polyline (give it to Google maps for rendering)
	 */
	private String polyline;
	/**
	 * Summary polyline (give it to Google maps for rendering)
	 */
	private String summaryPolyline;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
}
