package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.service.ActivityServices;

/**
 * @author Dan Shannon
 *
 */
public interface ActivityServicesRetrofit extends ActivityServices {
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	@GET("/activities/{id}")
	public Activity getActivity(@Path("id") Integer id, Boolean includeAllEfforts);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#createManualActivity(com.danshannon.strava.api.model.Activity)
	 */
	@Override
	@POST("/activities")
	public void createManualActivity(Activity activity);
	
	@PUT("/activities/{id}")
	public Activity updateActivity(@Path("id") Integer id, Activity activity);
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@Override
	@DELETE("/activities/{id}")
	public void deleteActivity(@Path("id") Integer id);
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@GET("/athlete/activities")
	public Activity[] listAuthenticatedAthleteActivities(Integer before, Integer after, Integer page, Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@GET("/activities/following")
	public Activity[] listFriendsActivities(Integer page, Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	@GET("/activities/{id}/zones")
	public ActivityZone[] listActivityZones(@Path("id") Integer id);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@Override
	@GET("/activities/{id}/laps")
	public Lap[] listActivityLaps(@Path("id") Integer id);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@GET("/activities/{id}/comments")
	public Comment[] listActivityComments(@Path("id") Integer id, Boolean markdown, Integer page, Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@GET("/activities/{id}/kudos")
	public Athlete[] listActivityKudoers(@Path("id") Integer id, Integer page, Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	@GET("/activities/{id}/photos")
	public Photo[] listActivityPhotos(Integer id);

}


