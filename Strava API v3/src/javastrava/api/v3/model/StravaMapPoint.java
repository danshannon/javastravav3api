package javastrava.api.v3.model;

/**
 * <p>
 * Representation of a GPS co-ordinate
 * </p>
 * @author Dan Shannon
 *
 */
public class StravaMapPoint {
	/**
	 * Latitude. Negative values are south of the equator.
	 */
	private final Float latitude;
	/**
	 * Longitude. Negative values are west of the Greenwich meridian.
	 */
	private final Float longitude;

	/**
	 * Constructor allows creation of a map point by specifying latitude and longitude
	 * @param latitude Latitude of the point to be created
	 * @param longitude Longitude of the point to be created
	 */
	public StravaMapPoint(final Float latitude, final Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Float getLatitude() {
		return this.latitude;
	}

	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return this.longitude;
	}

}
