package com.danshannon.strava.api.model;

import lombok.Data;


/**
 * @author dshannon
 *
 */
@Data
public class MapPoint {
	private Float latitude;
	private Float longitude;
	
	public MapPoint() {
		// Required
		super();
	}
	
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

}
