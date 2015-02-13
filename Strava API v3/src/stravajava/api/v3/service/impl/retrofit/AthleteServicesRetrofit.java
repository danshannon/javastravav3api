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

/**
 * Retrofit definitions for implementation of {@link AthleteServices}
 * 
 * @author Dan Shannon
 *
 */
public interface AthleteServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@GET("/athlete")
	public StravaAthlete getAuthenticatedAthlete();

	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAthlete(java.lang.Integer)
	 */
	@GET("/athletes/{id}")
	public StravaAthlete getAthlete(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#updateAuthenticatedAthlete()
	 */
	@PUT("/athlete")
	public StravaAthlete updateAuthenticatedAthlete(@Query("city") final String city, @Query("state") final String state, @Query("country") final String country,
			@Query("sex") final StravaGender sex, @Query("weight") final Float weight);

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/koms")
	public StravaSegmentEffort[] listAthleteKOMs(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athlete/friends")
	public StravaAthlete[] listAuthenticatedAthleteFriends(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/friends")
	public StravaAthlete[] listAthleteFriends(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/both-following")
	public StravaAthlete[] listAthletesBothFollowing(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.AthleteServices#statistics(Integer)
	 */
	@GET("/athletes/{id}/stats")
	public StravaStatistics statistics(@Path("id") final Integer id) throws NotFoundException;

}
