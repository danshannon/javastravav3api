package javastrava.api.v3.rest;

import javastrava.api.v3.model.StravaRunningRace;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * A featured set of running races taking place across the world. Each race has an associated race page with course information,
 * athlete goals, and results.
 *
 * @author Dan Shannon
 *
 */
public interface RunningRaceAPI {
	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return List of running races as summary representations
	 */
	@GET("/running_races")
	public StravaRunningRace[] listRaces(@Query("year") final Integer year);

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @param races
	 *            Future containing list of running races as summary representations
	 */
	@GET("/running_races")
	public void listRacesAsync(@Query("year") final Integer year, final StravaAPICallback<StravaRunningRace[]> races);

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A detailed representation of the running race
	 * @throws NotFoundException
	 *             If the race does not exist
	 * @throws UnauthorizedException
	 *             If the race is private or a security exception has occurred
	 */
	@GET("/running_races/{id}")
	public StravaRunningRace getRace(@Path("id") final Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @param callback
	 *            A future which will return a detailed representation of the running race
	 * @throws NotFoundException
	 *             If the race does not exist
	 * @throws UnauthorizedException
	 *             If the race is private or a security exception has occurred
	 */
	@GET("/running/races/{id}")
	public void getRaceAsync(@Path("id") final Integer id, final StravaAPICallback<StravaRunningRace> callback);
}
