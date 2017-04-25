package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Representation of a GPS co-ordinate
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaMapPoint implements StravaEntity {
	/**
	 * Latitude. Negative values are south of the equator.
	 */
	private Float latitude;

	/**
	 * Longitude. Negative values are west of the Greenwich meridian.
	 */
	private Float longitude;

	/**
	 * No args constructor
	 */
	public StravaMapPoint() {
		super();
	}

	/**
	 * Constructor allows creation of a map point by specifying latitude and longitude
	 *
	 * @param latitude
	 *            Latitude of the point to be created
	 * @param longitude
	 *            Longitude of the point to be created
	 */
	public StravaMapPoint(final Float latitude, final Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaMapPoint)) {
			return false;
		}
		final StravaMapPoint other = (StravaMapPoint) obj;
		if (this.latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!this.latitude.equals(other.latitude)) {
			return false;
		}
		if (this.longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!this.longitude.equals(other.longitude)) {
			return false;
		}
		return true;
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

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.latitude == null) ? 0 : this.latitude.hashCode());
		result = (prime * result) + ((this.longitude == null) ? 0 : this.longitude.hashCode());
		return result;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(final Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(final Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaMapPoint [latitude=" + this.latitude + ", longitude=" + this.longitude + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
