package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.service.AthleteServices;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * Retrofit definitions for implementation of {@link AthleteServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface AthleteServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#getAuthenticatedAthlete()
	 * 
	 * @return Full details of the authenticated athlete
	 */
	@GET("/athlete")
	public StravaAthlete getAuthenticatedAthlete();

	/**
	 * @see javastrava.api.v3.service.AthleteServices#getAthlete(java.lang.Integer)
	 * 
	 * @param id Athlete identifier
	 * @return Details of the athlete, will be somewhat anonymised if the athlete is private
	 * @throws NotFoundException If the athlete doesn't exist
	 */
	@GET("/athletes/{id}")
	public StravaAthlete getAthlete(@Path("id") final Integer id) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#updateAuthenticatedAthlete(String, String, String, StravaGender, Float)
	 * 
	 * @param city City the athlete is from
	 * @param state State/county/territory/canton/departement/whatever the athlete is from
	 * @param country Country the athlete is from
	 * @param sex Gender of the athlete
	 * @param weight Weight in kilograms
	 * @return Athlete details as updated on Strava
	 */
	@PUT("/athlete")
	public StravaAthlete updateAuthenticatedAthlete(@Query("city") final String city, @Query("state") final String state, @Query("country") final String country,
			@Query("sex") final StravaGender sex, @Query("weight") final Float weight);

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteKOMs(Integer, javastrava.util.Paging)
	 * 
	 * @param id Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of segment efforts which represent the athlete's KOM/QOM's
	 * @throws NotFoundException If the athlete doesn't exist
	 */
	@GET("/athletes/{id}/koms")
	public StravaSegmentEffort[] listAthleteKOMs(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends(javastrava.util.Paging)
	 * 
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the authenticated athlete is following
	 */
	@GET("/athlete/friends")
	public StravaAthlete[] listAuthenticatedAthleteFriends(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteFriends(Integer, javastrava.util.Paging)
	 * 
	 * @param id Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the identified athlete is following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 */
	@GET("/athletes/{id}/friends")
	public StravaAthlete[] listAthleteFriends(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthletesBothFollowing(Integer, javastrava.util.Paging)
	 * 
	 * @param id Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who both the identified athlete and the authenticated athlete are following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 */
	@GET("/athletes/{id}/both-following")
	public StravaAthlete[] listAthletesBothFollowing(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#statistics(Integer)
	 * 
	 * @param id Athlete identifier
	 * @return Statistics summary for the identified athlete
	 * @throws NotFoundException If the identified athlete doesn't exist
	 */
	@GET("/athletes/{id}/stats")
	public StravaStatistics statistics(@Path("id") final Integer id) throws NotFoundException;

}
