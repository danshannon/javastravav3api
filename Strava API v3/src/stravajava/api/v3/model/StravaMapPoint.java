package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Representation of a GPS co-ordinate
 * </p>
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaMapPoint {
	/**
	 * Latitude. Negative values are south of the equator.
	 */
	private Float latitude;
	/**
	 * Longitude. Negative values are west of the Greenwich meridian.
	 */
	private Float longitude;

	/**
	 * Constructor allows creation of a map point by specifying latitude and longitude
	 * @param latitude
	 * @param longitude
	 */
	public StravaMapPoint(final Float latitude, final Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
