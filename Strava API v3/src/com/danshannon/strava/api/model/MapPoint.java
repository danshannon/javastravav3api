package com.danshannon.strava.api.model;


/**
 * @author dshannon
 *
 */
public class MapPoint {
	private Float latitude;
	private Float longitude;
	
	public MapPoint(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
// TODO	@JsonValue
	public Float[] getValue() {
		return new Float[]{this.latitude,this.longitude};
	}
	
// TODO	@JsonCreator
	public MapPoint create(Float[] latlng) {
		return new MapPoint(latlng[0],latlng[1]);
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

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
