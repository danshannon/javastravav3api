package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.service.ActivityServices;

/**
 * @author Dan Shannon
 *
 */
public interface ActivityServicesImpl extends ActivityServices {
	public static final ActivityServicesImpl REST_SERVICE = new RestAdapter.Builder().setEndpoint(ENDPOINT).build().create(ActivityServicesImpl.class);
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	@GET("/activities/{id}")
	public Activity getActivity(@Path("id") Integer id, Boolean includeAllEfforts);

}


