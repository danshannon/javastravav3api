package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaRunningRace;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * A featured set of running races taking place across the world. Each race has an associated race page with course information, athlete goals, and results.
 *
 * @author Dan Shannon
 *
 */
public interface RunningRaceAPI {
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
	@GET("/running_races/{id}")
	public void getRace(@Path("id") final Integer id, final StravaAPICallback<StravaRunningRace> callback);

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
	public Response getRaceRaw(@Path("id") final Integer id) throws NotFoundException, UnauthorizedException;

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
	public void listRaces(@Query("year") final Integer year, final StravaAPICallback<StravaRunningRace[]> races);

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
	public Response listRacesRaw(@Query("year") final Integer year);
}
