package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaActivity;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaClub;
import javastrava.model.StravaClubAnnouncement;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubMembershipResponse;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definition of the endpoints for club services
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface ClubAPI {
	/**
	 * @see javastrava.service.ClubService#getClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            Club identifier
	 * @return Club details
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public StravaClub getClub(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#getClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public void getClub(@Path("id") final Integer clubId, final StravaAPICallback<StravaClub> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#getClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            Club identifier
	 * @return Club details
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public Response getClubRaw(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#joinClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/join")
	public StravaClubMembershipResponse joinClub(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#joinClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/join")
	public void joinClub(@Path("id") final Integer clubId, final StravaAPICallback<StravaClubMembershipResponse> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#leaveClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/leave")
	public StravaClubMembershipResponse leaveClub(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#leaveClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/leave")
	public void leaveClub(@Path("id") final Integer clubId, StravaAPICallback<StravaClubMembershipResponse> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @return Array of clubs that the authenticated athlete belongs to
	 */
	@GET("/athlete/clubs")
	public StravaClub[] listAuthenticatedAthleteClubs();

	/**
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @param callback
	 *            The callback to execute on completion
	 */
	@GET("/athlete/clubs")
	public void listAuthenticatedAthleteClubs(final StravaAPICallback<StravaClub[]> callback);

	/**
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @return Array of clubs that the authenticated athlete belongs to
	 */
	@GET("/athlete/clubs")
	public Response listAuthenticatedAthleteClubsRaw();

	/**
	 * Retrieve summary information about admins of a specific club, with the owner on top and sorted by first names. Pagination is supported.
	 *
	 * @param clubId
	 *            The club for which to list admins
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of {@link StravaAthlete}s
	 * @throws NotFoundException
	 *             if club id is invalid
	 */
	@GET("/clubs/{id}/admins")
	public StravaAthlete[] listClubAdmins(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * Retrieve summary information about admins of a specific club, with the owner on top and sorted by first names. Pagination is supported.
	 *
	 * @param clubId
	 *            The club for which to list admins
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @param callback
	 *            Callback used to return the athletes
	 * @throws NotFoundException
	 *             if club id is invalid
	 */
	@GET("/clubs/{id}/admins")
	public void listClubAdmins(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaAthlete[]> callback)
			throws NotFoundException;

	/**
	 * Retrieve summary information about admins of a specific club, with the owner on top and sorted by first names. Pagination is supported.
	 *
	 * @param clubId
	 *            The club for which to list admins
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of {@link StravaAthlete}s
	 * @throws NotFoundException
	 *             if club id is invalid
	 */
	@GET("/clubs/{id}/admins")
	public Response listClubAdminsRaw(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @param clubId
	 *            The club for which to list announcements
	 * @return Array of {@link StravaClubAnnouncement}s
	 * @throws NotFoundException
	 *             If the club does not exist
	 */
	@GET("/clubs/{id}/announcements")
	public StravaClubAnnouncement[] listClubAnnouncements(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @param clubId
	 *            The club for which to list announcements
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club does not exist
	 */
	@GET("/clubs/{id}/announcements")
	public void listClubAnnouncements(@Path("id") final Integer clubId, StravaAPICallback<StravaClubAnnouncement[]> callback) throws NotFoundException;

	/**
	 * @param clubId
	 *            The club for which to list announcements
	 * @return Array of {@link StravaClubAnnouncement}s
	 * @throws NotFoundException
	 *             If the club does not exist
	 */
	@GET("/clubs/{id}/announcements")
	public Response listClubAnnouncementsRaw(@Path("id") final Integer clubId) throws NotFoundException;

	/**
	 * @param clubId
	 *            Unique id of the club whose events should be listed
	 * @return Array of summary events
	 */
	@GET("/clubs/{id}/group_events")
	public StravaClubEvent[] listClubGroupEvents(@Path("id") final Integer clubId);

	/**
	 * @param clubId
	 *            Unique id of the club whose events should be listed
	 * @param callback
	 *            The callback to execute on completion
	 */
	@GET("/clubs/{id}/group_events")
	public void listClubGroupEvents(@Path("id") final Integer clubId, final StravaAPICallback<StravaClubEvent[]> callback);

	/**
	 * @param clubId
	 *            Unique id of the club whose events should be listed
	 * @return Array of summary events
	 */
	@GET("/clubs/{id}/group_events")
	public Response listClubGroupEventsRaw(@Path("id") final Integer clubId);

	/**
	 * @see javastrava.service.ClubService#listClubMembers(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            CLub identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who are members of the identified club
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/members")
	public StravaAthlete[] listClubMembers(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.ClubService#listClubMembers(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            CLub identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/members")
	public void listClubMembers(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaAthlete[]> callback)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.ClubService#listClubMembers(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            CLub identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who are members of the identified club
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/members")
	public Response listClubMembersRaw(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivities(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200 will be returned)
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/activities")
	public StravaActivity[] listRecentClubActivities(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivities(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/activities")
	public void listRecentClubActivities(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage,
			final StravaAPICallback<StravaActivity[]> callback) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivities(Integer, javastrava.util.Paging)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200 will be returned)
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/activities")
	public Response listRecentClubActivitiesRaw(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException, BadRequestException;
}
