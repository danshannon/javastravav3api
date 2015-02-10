package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.StravaStatistics;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.service.AthleteServices;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * Retrofit definitions for implementation of {@link AthleteServices}
 * 
 * @author Dan Shannon
 *
 */
public interface AthleteServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.BASIC;
	
	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@GET("/athlete")
	public StravaAthlete getAuthenticatedAthlete();

	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAthlete(java.lang.Integer)
	 */
	@GET("/athletes/{id}")
	public StravaAthlete getAthlete(@Path("id") Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#updateAuthenticatedAthlete()
	 */
	@PUT("/athlete")
	public StravaAthlete updateAuthenticatedAthlete(@Query("city") String city, @Query("state") String state, @Query("country") String country, @Query("sex") StravaGender sex, @Query("weight") Float weight);

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/koms")
	public StravaSegmentEffort[] listAthleteKOMs(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athlete/friends")
	public StravaAthlete[] listAuthenticatedAthleteFriends(@Query("page") Integer page, @Query("per_page") Integer perPage);

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/friends")
	public StravaAthlete[] listAthleteFriends(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/both-following")
	public StravaAthlete[] listAthletesBothFollowing(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException;
	
	/**
	 * @see stravajava.api.v3.service.AthleteServices#statistics(Integer)
	 */
	@GET("/athletes/{id}/stats")
	public StravaStatistics statistics(@Path("id") Integer id) throws NotFoundException;
	
}
