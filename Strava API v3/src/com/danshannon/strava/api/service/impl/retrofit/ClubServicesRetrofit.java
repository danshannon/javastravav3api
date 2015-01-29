/**
 * 
 */
package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.ClubMembershipResponse;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface ClubServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#getClub(java.lang.Integer)
	 */
	@GET("/clubs/{id}")
	public Club getClub(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@GET("/athlete/clubs")
	public Club[] listAuthenticatedAthleteClubs() throws UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/clubs/{id}/members")
	public Athlete[] listClubMembers(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException, UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/clubs/{id}/activities")
	public Activity[] listRecentClubActivities(@Path("id") Integer id, @Query("page") Integer page, @Query("page") Integer perPage) throws NotFoundException, UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/join")
	public ClubMembershipResponse join(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;		
	
	/**
	 * @see com.danshannon.strava.api.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@POST("/clubs/{id}/leave")
	public ClubMembershipResponse leave(@Path("id") Integer id) throws NotFoundException, UnauthorizedException;
}
