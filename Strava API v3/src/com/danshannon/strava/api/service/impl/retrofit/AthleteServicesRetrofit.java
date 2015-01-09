package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.AthleteServices;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * Retrofit definitions for implementation of {@link AthleteServices}
 * 
 * @author Dan Shannon
 *
 */
public interface AthleteServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@GET("/athlete")
	public Athlete getAuthenticatedAthlete() throws UnauthorizedException;

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#getAthlete(java.lang.Integer)
	 */
	@GET("/athletes/{id}")
	public Athlete getAthlete(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#updateAuthenticatedAthlete()
	 */
	@PUT("/athlete")
	public void updateAuthenticatedAthlete(@Query("city") String city, @Query("state") String state, @Query("country") String country, @Query("sex") Gender sex, @Query("weight") Float weight) throws UnauthorizedException, NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/koms")
	public SegmentEffort[] listAthleteKOMs(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athlete/friends")
	public Athlete[] listAuthenticatedAthleteFriends(@Query("page") Integer page, @Query("per_page") Integer perPage);

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/friends")
	public Athlete[] listAthleteFriends(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/both-following")
	public Athlete[] listAthletesBothFollowing(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;
	
}
