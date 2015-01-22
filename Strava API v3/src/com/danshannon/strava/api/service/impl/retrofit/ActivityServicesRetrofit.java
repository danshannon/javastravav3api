package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface ActivityServicesRetrofit {
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@GET("/activities/{id}")
	public Activity getActivity(@Path("id") Integer id, @Query("include_all_efforts") Boolean includeAllEfforts) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#createManualActivity(com.danshannon.strava.api.model.Activity)
	 */
	@POST("/activities")
	public Activity createManualActivity(@Body Activity activity) throws UnauthorizedException, BadRequestException;
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#updateActivity(com.danshannon.strava.api.model.Activity)
	 */
	@PUT("/activities/{id}")
	public Activity updateActivity(@Path("id") Integer id, @Body Activity activity) throws NotFoundException, UnauthorizedException;
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@DELETE("/activities/{id}")
	public Activity deleteActivity(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athlete/activities")
	public Activity[] listAuthenticatedAthleteActivities(@Query("before") Integer before, @Query("after") Integer after, @Query("page") Integer page, @Query("per_page") Integer perPage) throws UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/following")
	public Activity[] listFriendsActivities(@Query("page") Integer page, @Query("per_page") Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@GET("/activities/{id}/zones")
	public ActivityZone[] listActivityZones(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@GET("/activities/{id}/laps")
	public Lap[] listActivityLaps(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/comments")
	public Comment[] listActivityComments(@Path("id") Integer id, @Query("markdown") Boolean markdown, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/kudos")
	public Athlete[] listActivityKudoers(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page")Integer perPage) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@GET("/activities/{id}/photos")
	public Photo[] listActivityPhotos(@Path("id") Integer id) throws NotFoundException;
	
	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listRelatedActivities(java.lang.Integer, com.danshannon.strava.util.Paging)
	 */
	@GET("/activities/{id}/related")
	public Activity[] listRelatedActivities(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

}


