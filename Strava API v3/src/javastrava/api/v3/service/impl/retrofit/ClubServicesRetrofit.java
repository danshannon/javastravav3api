/**
 * 
 */
package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * Retrofit definition of the endpoints for club services
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface ClubServicesRetrofit {
	/**
	 * @see javastrava.api.v3.service.ClubServices#getClub(java.lang.Integer)
	 * 
	 * @param id Club identifier
	 * @return Club details
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}")
	public StravaClub getClub(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubServices#listAuthenticatedAthleteClubs()
	 * 
	 * @return Array of clubs that the authenticated athlete belongs to
	 */
	@GET("/athlete/clubs")
	public StravaClub[] listAuthenticatedAthleteClubs();

	/**
	 * @see javastrava.api.v3.service.ClubServices#listClubMembers(Integer, javastrava.util.Paging)
	 * 
	 * @param id CLub identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who are members of the identified club
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}/members")
	public StravaAthlete[] listClubMembers(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubServices#listRecentClubActivities(Integer, javastrava.util.Paging)
	 * 
	 * @param id Club identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200 will be returned)
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@GET("/clubs/{id}/activities")
	public StravaActivity[] listRecentClubActivities(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubServices#joinClub(java.lang.Integer)
	 * 
	 * @param id The club the authenticated athlete wishes to join
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/join")
	public StravaClubMembershipResponse join(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.ClubServices#leaveClub(java.lang.Integer)
	 * 
	 * @param id The club the authenticated athlete wishes to leave
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException If the club with the given id doesn't exist
	 */
	@POST("/clubs/{id}/leave")
	public StravaClubMembershipResponse leave(@Path("id") final Integer id) throws NotFoundException;
}
