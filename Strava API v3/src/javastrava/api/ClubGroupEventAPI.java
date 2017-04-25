package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.auth.model.Token;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubEventJoinResponse;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definition for the endpoints for Club Group Event services
 * </p>
 *
 * <p>
 * See <a href="http://strava.github.io/api/v3/club_group_events/">http://strava.github.io/api/v3/club_group_events/</a>
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface ClubGroupEventAPI {
	/**
	 * <p>
	 * Deletes (and cancels) an event, which must be editable by the authenticating user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event to be deleted
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the authenticated athlete does not have permission to delete the event
	 */
	@DELETE("/group_events/{id}")
	public void deleteEvent(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Deletes (and cancels) an event, which must be editable by the authenticating user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event to be deleted
	 * @param callback
	 *            Callback that can be used later to get results
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the authenticated athlete does not have permission to delete the event
	 */
	@DELETE("/group_events/{id}")
	public void deleteEvent(@Path("id") Integer id, StravaAPICallback<Void> callback) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The group event
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	@GET("/group_events/{id}")
	public StravaClubEvent getEvent(@Path("id") Integer id) throws NotFoundException;

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @param callback
	 *            The callback which will be used to retrieve the group event at some point in the future
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	@GET("/group_events/{id}")
	public void getEvent(@Path("id") Integer id, StravaAPICallback<StravaClubEvent> callback) throws NotFoundException;

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The group event as a raw Retrofit response
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	@GET("/group_events/{id}")
	public Response getEventRaw(@Path("id") Integer id) throws NotFoundException;

	/**
	 * <p>
	 * Join a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, join the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@POST("/group_events/{id}/rsvps")
	public StravaClubEventJoinResponse joinEvent(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Join a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, join the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @param callback
	 *            The callback which can be used to get the response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@POST("/group_events/{id}/rsvps")
	public void joinEvent(@Path("id") Integer id, StravaAPICallback<StravaClubEventJoinResponse> callback) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Join a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, join the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@POST("/group_events/{id}/rsvps")
	public Response joinEventRaw(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Leave a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, leave the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@DELETE("/group_events/{id}/rsvps")
	public StravaClubEventJoinResponse leaveEvent(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Leave a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, leave the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @param callback
	 *            The callback which can be used to get the response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@DELETE("/group_events/{id}/rsvps")
	public void leaveEvent(@Path("id") Integer id, StravaAPICallback<StravaClubEventJoinResponse> callback) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Leave a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, leave the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	@DELETE("/group_events/{id}/rsvps")
	public Response leaveEventRaw(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Retrieve summary information about athletes joined a specific group event, or the upcoming occurrence for recurring events.
	 * </p>
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * <p>
	 * Returns an array of athletes summary representations with athletes who the authenticated athlete is following first.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event for which athletes should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who have joined the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the event is private??
	 */
	@GET("/group_events/{id}/athletes")
	public StravaAthlete[] listEventJoinedAthletes(@Path("id") Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Retrieve summary information about athletes joined a specific group event, or the upcoming occurrence for recurring events.
	 * </p>
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * <p>
	 * Returns an array of athletes summary representations with athletes who the authenticated athlete is following first.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event for which athletes should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @param callback
	 *            Callback which can be used to get the array of athletes who have joined the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the event is private??
	 */
	@GET("/group_events/{id}/athletes")
	public void listEventJoinedAthletes(@Path("id") Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage, StravaAPICallback<StravaAthlete[]> callback)
			throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Retrieve summary information about athletes joined a specific group event, or the upcoming occurrence for recurring events.
	 * </p>
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * <p>
	 * Returns an array of athletes summary representations with athletes who the authenticated athlete is following first.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event for which athletes should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who have joined the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the event is private??
	 */
	@GET("/group_events/{id}/athletes")
	public Response listEventJoinedAthletesRaw(@Path("id") Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, UnauthorizedException;

}
