package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaActivityZone;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaComment;
import stravajava.api.v3.model.StravaLap;
import stravajava.api.v3.model.StravaPhoto;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface ActivityServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	

	
	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@GET("/activities/{id}")
	public StravaActivity getActivity(@Path("id") Integer id, @Query("include_all_efforts") Boolean includeAllEfforts) throws NotFoundException, UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#createManualActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@POST("/activities")
	public StravaActivity createManualActivity(@Body StravaActivity activity) throws UnauthorizedException, BadRequestException;
	
	/**
	 * @see stravajava.api.v3.service.ActivityServices#updateActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@PUT("/activities/{id}")
	public StravaActivity updateActivity(@Path("id") Integer id, @Body StravaActivity activity) throws NotFoundException, UnauthorizedException;
	
	/**
	 * @see stravajava.api.v3.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@DELETE("/activities/{id}")
	public StravaActivity deleteActivity(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;
	
	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athlete/activities")
	public StravaActivity[] listAuthenticatedAthleteActivities(@Query("before") Integer before, @Query("after") Integer after, @Query("page") Integer page, @Query("per_page") Integer perPage) throws UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/following")
	public StravaActivity[] listFriendsActivities(@Query("page") Integer page, @Query("per_page") Integer perPage);

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@GET("/activities/{id}/zones")
	public StravaActivityZone[] listActivityZones(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@GET("/activities/{id}/laps")
	public StravaLap[] listActivityLaps(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/comments")
	public StravaComment[] listActivityComments(@Path("id") Integer id, @Query("markdown") Boolean markdown, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/kudos")
	public StravaAthlete[] listActivityKudoers(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page")Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@GET("/activities/{id}/photos")
	public StravaPhoto[] listActivityPhotos(@Path("id") Integer id) throws NotFoundException;
	
	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer, stravajava.util.Paging)
	 */
	@GET("/activities/{id}/related")
	public StravaActivity[] listRelatedActivities(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

}


