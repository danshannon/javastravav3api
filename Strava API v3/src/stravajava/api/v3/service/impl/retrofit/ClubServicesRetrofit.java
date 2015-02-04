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
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface ClubServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;

	/**
	 * @see stravajava.api.v3.service.ClubServices#getClub(java.lang.Integer)
	 */
	@GET("/clubs/{id}")
	public StravaClub getClub(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@GET("/athlete/clubs")
	public StravaClub[] listAuthenticatedAthleteClubs() throws UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/clubs/{id}/members")
	public StravaAthlete[] listClubMembers(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException, UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/clubs/{id}/activities")
	public StravaActivity[] listRecentClubActivities(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException, UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/join")
	public StravaClubMembershipResponse join(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;		
	
	/**
	 * @see stravajava.api.v3.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/leave")
	public StravaClubMembershipResponse leave(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;
}
