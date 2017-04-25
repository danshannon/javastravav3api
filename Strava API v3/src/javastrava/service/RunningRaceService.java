/**
 *
 */
package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaRunningRace;

/**
 * <p>
 * Service specs for running race endpoint
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface RunningRaceService extends StravaService {
	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A detailed representation of the running race
	 */
	public StravaRunningRace getRace(final Integer id);

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A future which will return a detailed representation of the running race
	 */
	public CompletableFuture<StravaRunningRace> getRaceAsync(final Integer id);

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return List of running races as summary representations
	 */
	public List<StravaRunningRace> listRaces(final Integer year);

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return Future containing list of running races as summary representations
	 */
	public CompletableFuture<List<StravaRunningRace>> listRacesAsync(final Integer year);

}
