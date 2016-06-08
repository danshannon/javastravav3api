/**
 *
 */
package javastrava.api.v3.rest;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubAnnouncement;
import javastrava.api.v3.model.StravaClubEvent;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
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
	 * @see javastrava.api.v3.service.ClubService#getClub(java.lang.Integer)
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
	 * @see javastrava.api.v3.service.ClubService#getClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public void getClub(@Path("id") final Integer clubId, final StravaAPICallback<StravaClub> callback)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#joinClub(java.lang.Integer)
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
	 * @see javastrava.api.v3.service.ClubService#joinClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/join")
	public void joinClub(@Path("id") final Integer clubId,
			final StravaAPICallback<StravaClubMembershipResponse> callback) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#leaveClub(java.lang.Integer)
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
	 * @see javastrava.api.v3.service.ClubService#leaveClub(java.lang.Integer)
	 *
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/leave")
	public void leaveClub(@Path("id") final Integer clubId, StravaAPICallback<StravaClubMembershipResponse> callback)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @return Array of clubs that the authenticated athlete belongs to
	 */
	@GET("/athlete/clubs")
	public StravaClub[] listAuthenticatedAthleteClubs();

	/**
	 * @see javastrava.api.v3.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @param callback
	 *            The callback to execute on completion
	 */
	@GET("/athlete/clubs")
	public void listAuthenticatedAthleteClubs(final StravaAPICallback<StravaClub[]> callback);

	/**
	 * Retrieve summary information about admins of a specific club, with the
	 * owner on top and sorted by first names. Pagination is supported.
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
	public StravaAthlete[] listClubAdmins(@Path("id") final Integer clubId, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * Retrieve summary information about admins of a specific club, with the
	 * owner on top and sorted by first names. Pagination is supported.
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
	public void listClubAdmins(@Path("id") final Integer clubId, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage, final StravaAPICallback<StravaAthlete[]> callback)
					throws NotFoundException;

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
	public void listClubAnnouncements(@Path("id") final Integer clubId,
			StravaAPICallback<StravaClubAnnouncement[]> callback) throws NotFoundException;

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
	public void listClubGroupEvents(@Path("id") final Integer clubId,
			final StravaAPICallback<StravaClubEvent[]> callback);

	/**
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(Integer,
	 *      javastrava.util.Paging)
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
	public StravaAthlete[] listClubMembers(@Path("id") final Integer clubId, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(Integer,
	 *      javastrava.util.Paging)
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
	public void listClubMembers(@Path("id") final Integer clubId, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage, final StravaAPICallback<StravaAthlete[]> callback)
					throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(Integer,
	 *      javastrava.util.Paging)
	 *
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200
	 *         will be returned)
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/activities")
	public StravaActivity[] listRecentClubActivities(@Path("id") final Integer clubId,
			@Query("page") final Integer page, @Query("per_page") final Integer perPage)
					throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(Integer,
	 *      javastrava.util.Paging)
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
	public void listRecentClubActivities(@Path("id") final Integer clubId, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage, final StravaAPICallback<StravaActivity[]> callback)
					throws NotFoundException, BadRequestException;
}
