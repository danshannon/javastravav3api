package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;

import com.danshannon.strava.api.model.Gear;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

public interface GearServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.GearServices#getGear(java.lang.String)
	 */
	@GET("/gear/{id}")
	public Gear getGear(@Path("id") String id) throws NotFoundException, UnauthorizedException;

}
