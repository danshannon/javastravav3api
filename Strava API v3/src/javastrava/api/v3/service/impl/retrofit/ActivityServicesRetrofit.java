package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaComment;
import javastrava.api.v3.model.StravaLap;
import javastrava.api.v3.model.StravaPhoto;
import javastrava.api.v3.model.StravaResponse;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**<p>
 * Retrofit declarations of activity service endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface ActivityServicesRetrofit {
	
	/**
	 * @see javastrava.api.v3.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
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
	 * @see javastrava.api.v3.service.ActivityServices#createManualActivity(javastrava.api.v3.model.StravaActivity)
	 * 
	 * @param activity The activity to be created on Strava
	 * @return The activity as stored by Strava
	 * @throws BadRequestException If the activity is malformed and can't be uploaded
	 */
	@POST("/activities")
	public StravaActivity createManualActivity(@Body final StravaActivity activity) throws BadRequestException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#updateActivity(javastrava.api.v3.model.StravaActivity)
	 * 
	 * @param id The id of the activity to update
	 * @param activity The activity details
	 * @return The activity as stored on Strava
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@PUT("/activities/{id}")
	public StravaActivity updateActivity(@Path("id") final Integer id, @Body final StravaActivityUpdate activity) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#deleteActivity(java.lang.Integer)
	 * 
	 * @param id Activity identifier
	 * @return Activity that has been successfully deleted from Strava
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@DELETE("/activities/{id}")
	public StravaActivity deleteActivity(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.util.Calendar, java.util.Calendar, javastrava.util.Paging)
	 * 
	 * @param before Unix epoch time in seconds - return activities before this time
	 * @param after Unix epoch time in seconds - return activities after this time
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return List of Strava activities in the given time frame
	 */
	@GET("/athlete/activities")
	public StravaActivity[] listAuthenticatedAthleteActivities(@Query("before") final Integer before, @Query("after") final Integer after, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage);

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listFriendsActivities(javastrava.util.Paging)
	 * 
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return List of Strava activities belonging to friends of the authenticated athlete
	 */
	@GET("/activities/following")
	public StravaActivity[] listFriendsActivities(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listActivityZones(java.lang.Integer)
	 * 
	 * @param id The activity identifier
	 * @return Array of activity zones for the activity
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/zones")
	public StravaActivityZone[] listActivityZones(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 * 
	 * @param id The activity identifier
	 * @return Array of laps belonging to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/laps")
	public StravaLap[] listActivityLaps(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listActivityComments(Integer, Boolean, javastrava.util.Paging)
	 * 
	 * @param id Activity identifier
	 * @param markdown Whether or not to return comments including markdown
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned 
	 * @return Array of comments belonging to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/comments")
	public StravaComment[] listActivityComments(@Path("id") final Integer id, @Query("markdown") final Boolean markdown, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listActivityKudoers(Integer, javastrava.util.Paging)
	 * 
	 * @param id Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return Array of athletes who have kudoed the activity
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/kudos")
	public StravaAthlete[] listActivityKudoers(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 * 
	 * @param id Activity identifier
	 * @return Array of photos attached to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/photos")
	public StravaPhoto[] listActivityPhotos(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer, javastrava.util.Paging)
	 * 
	 * @param id Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return Array of activities that Strava judges was 'done with' the activity identified by the id
	 * @throws NotFoundException If the activity doesn't exist
	 */
	@GET("/activities/{id}/related")
	public StravaActivity[] listRelatedActivities(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @param id Activity identifier
	 * @param text Text of the comment to create
	 * @return The comment as posted
	 * @throws NotFoundException If the activity does not exist
	 * @throws BadRequestException If the comment text is null or the empty string
	 */
	@POST("/activities/{id}/comments")
	public StravaComment createComment(@Path("id") final Integer id, @Query("text") final String text) throws BadRequestException, NotFoundException;
	
	/**
	 * @param activityId Id of the activity the comment was posted to
	 * @param commentId Id of the comment
	 * @return 
	 */
	@DELETE("/activities/{activityId}/comments/{commentId}")
	public StravaResponse deleteComment(@Path("activityId") final Integer activityId, @Path("commentId") final Integer commentId) throws NotFoundException;
	
	@POST("/activities/{id}/kudos")
	public StravaResponse giveKudos(@Path("id") final Integer activityId) throws NotFoundException;
}
