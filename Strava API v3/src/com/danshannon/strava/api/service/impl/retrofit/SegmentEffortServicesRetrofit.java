package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;

import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.service.exception.NotFoundException;

/**
 * @author Dan Shannon
 *
 */
public interface SegmentEffortServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.SegmentEffortServices#getSegmentEffort(java.lang.Integer)
	 */
	@GET("/segment_efforts/{id}")
	public SegmentEffort getSegmentEffort(@Path("id") Integer id) throws NotFoundException;

}
