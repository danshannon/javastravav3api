/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaClub;
import stravajava.api.v3.model.StravaClubMembershipResponse;
import stravajava.api.v3.service.exception.NotFoundException;

/**
 * <p>
 * Retrofit definition of the endpoints for club services
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface ClubServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see stravajava.api.v3.service.ClubServices#getClub(java.lang.Integer)
	 */
	@GET("/clubs/{id}")
	public StravaClub getClub(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@GET("/athlete/clubs")
	public StravaClub[] listAuthenticatedAthleteClubs();

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(Integer, stravajava.util.Paging)
	 */
	@GET("/clubs/{id}/members")
	public StravaAthlete[] listClubMembers(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(Integer, stravajava.util.Paging)
	 */
	@GET("/clubs/{id}/activities")
	public StravaActivity[] listRecentClubActivities(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/join")
	public StravaClubMembershipResponse join(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/leave")
	public StravaClubMembershipResponse leave(@Path("id") final Integer id) throws NotFoundException;
}
