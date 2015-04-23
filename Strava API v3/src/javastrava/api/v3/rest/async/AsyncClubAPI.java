/**
 *
 */
package javastrava.api.v3.rest.async;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubAnnouncement;
import javastrava.api.v3.model.StravaClubMembershipResponse;
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
public interface AsyncClubAPI {
	/**
	 * @see javastrava.api.v3.service.ClubService#getClub(java.lang.Integer)
	 *
	 * @param clubId Club identifier
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public void getClub(@Path("id") final Integer clubId, final StravaAPICallback<StravaClub> callback) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#joinClub(java.lang.Integer)
	 *
	 * @param clubId The club the authenticated athlete wishes to join
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/join")
	public void joinClub(@Path("id") final Integer clubId, final StravaAPICallback<StravaClubMembershipResponse> callback) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#leaveClub(java.lang.Integer)
	 *
	 * @param clubId The club the authenticated athlete wishes to leave
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/leave")
	public void leaveClub(@Path("id") final Integer clubId, StravaAPICallback<StravaClubMembershipResponse> callback) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listAuthenticatedAthleteClubs()
	 *
	 * @param callback The callback to execute on completion
	 */
	@GET("/athlete/clubs")
	public void listAuthenticatedAthleteClubs(final StravaAPICallback<StravaClub[]> callback);

	/**
	 * @param clubId The club for which to list announcements
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club does not exist
	 */
	@GET("/clubs/{id}/announcements")
	public void listClubAnnouncements(@Path("id") final Integer clubId, StravaAPICallback<StravaClubAnnouncement[]> callback) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(Integer, javastrava.util.Paging)
	 *
	 * @param clubId CLub identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/members")
	public void listClubMembers(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaAthlete[]> callback) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(Integer, javastrava.util.Paging)
	 *
	 * @param clubId Club identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 */
	@GET("/clubs/{id}/activities")
	public void listRecentClubActivities(@Path("id") final Integer clubId, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaActivity[]> callback)
			throws NotFoundException, BadRequestException;
}
