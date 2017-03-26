package javastrava.api.v3.rest;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClubEvent;
import javastrava.api.v3.model.StravaClubEventJoinResponse;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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

}
