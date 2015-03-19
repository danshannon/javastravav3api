package javastrava.api.v3.rest;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.service.AthleteService;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definitions for implementation of {@link AthleteService}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface AthleteAPI {
	/**
	 * @see javastrava.api.v3.service.AthleteService#getAuthenticatedAthlete()
	 * 
	 * @return Full details of the authenticated athlete
	 */
	@GET("/athlete")
	public StravaAthlete getAuthenticatedAthlete();

	/**
	 * @see javastrava.api.v3.service.AthleteService#getAthlete(java.lang.Integer)
	 * 
	 * @param athleteId Athlete identifier
	 * @return Details of the athlete, will be somewhat anonymised if the athlete is private
	 * @throws NotFoundException If the athlete doesn't exist
	 */
	@GET("/athletes/{id}")
	public StravaAthlete getAthlete(@Path("id") final Integer athleteId) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteService#updateAuthenticatedAthlete(String, String, String, StravaGender, Float)
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
	 * @see javastrava.api.v3.service.AthleteService#listAthleteKOMs(Integer, javastrava.util.Paging)
	 * 
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of segment efforts which represent the athlete's KOM/QOM's
	 * @throws NotFoundException If the athlete doesn't exist
	 */
	@GET("/athletes/{id}/koms")
	public StravaSegmentEffort[] listAthleteKOMs(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAuthenticatedAthleteFriends(javastrava.util.Paging)
	 * 
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the authenticated athlete is following
	 */
	@GET("/athlete/friends")
	public StravaAthlete[] listAuthenticatedAthleteFriends(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthleteFriends(Integer, javastrava.util.Paging)
	 * 
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the identified athlete is following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 */
	@GET("/athletes/{id}/friends")
	public StravaAthlete[] listAthleteFriends(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthletesBothFollowing(Integer, javastrava.util.Paging)
	 * 
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who both the identified athlete and the authenticated athlete are following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 */
	@GET("/athletes/{id}/both-following")
	public StravaAthlete[] listAthletesBothFollowing(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see javastrava.api.v3.service.AthleteService#statistics(Integer)
	 * 
	 * @param athleteId Athlete identifier
	 * @return Statistics summary for the identified athlete
	 * @throws NotFoundException If the identified athlete doesn't exist
	 */
	@GET("/athletes/{id}/stats")
	public StravaStatistics statistics(@Path("id") final Integer athleteId) throws NotFoundException;

}
