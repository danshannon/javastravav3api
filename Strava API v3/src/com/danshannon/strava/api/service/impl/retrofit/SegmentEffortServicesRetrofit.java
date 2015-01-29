package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;

import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface SegmentEffortServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.SegmentEffortServices#getSegmentEffort(java.lang.Long)
	 */
	@GET("/segment_efforts/{id}")
	public SegmentEffort getSegmentEffort(@Path("id") Long id) throws NotFoundException, UnauthorizedException;

}
