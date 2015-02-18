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

/**<p>
 * Retrofit declarations of activity service endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface ActivityServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL; // TODO parameterise in config

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	/**
	 * @param id
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @param includeAllEfforts
	 *            (Optional) Used to include all segment efforts in the result (if omitted or <code>false</code> then only
	 *            "important" efforts are returned).
	 * @return Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns
	 *         a summary representation for all other requests.
	 * @throws NotFoundException If the activity does not exist
	 */
	@GET("/activities/{id}")
	public StravaActivity getActivity(@Path("id") final Integer id, @Query("include_all_efforts") final Boolean includeAllEfforts) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#createManualActivity(stravajava.api.v3.model.StravaActivity)
	 */
	/**
	 * @param activity The activity to be created on Strava
	 * @return The activity as stored by Strava
	 * @throws BadRequestException If the activity is malformed and can't be uploaded
	 */
	@POST("/activities")
	public StravaActivity createManualActivity(@Body final StravaActivity activity) throws BadRequestException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#updateActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@PUT("/activities/{id}")
	public StravaActivity updateActivity(@Path("id") final Integer id, @Body final StravaActivity activity) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@DELETE("/activities/{id}")
	public StravaActivity deleteActivity(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@GET("/athlete/activities")
	public StravaActivity[] listAuthenticatedAthleteActivities(@Query("before") final Integer before, @Query("after") final Integer after, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage);

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/following")
	public StravaActivity[] listFriendsActivities(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@GET("/activities/{id}/zones")
	public StravaActivityZone[] listActivityZones(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@GET("/activities/{id}/laps")
	public StravaLap[] listActivityLaps(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/comments")
	public StravaComment[] listActivityComments(@Path("id") final Integer id, @Query("markdown") final Boolean markdown, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/activities/{id}/kudos")
	public StravaAthlete[] listActivityKudoers(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@GET("/activities/{id}/photos")
	public StravaPhoto[] listActivityPhotos(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer, stravajava.util.Paging)
	 */
	@GET("/activities/{id}/related")
	public StravaActivity[] listRelatedActivities(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

}
