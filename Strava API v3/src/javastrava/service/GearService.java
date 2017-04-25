package javastrava.service;

import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaActivity;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaGear;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * {@link StravaGear} related services.
 * </p>
 *
 * <p>
 * StravaGear represents equipment used during an {@link StravaActivity}.
 * </p>
 *
 * <p>
 * The object is returned in summary or detailed {@link StravaResourceState representations}.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface GearService extends StravaService {
	/**
	 * <p>
	 * Retrieve details about a specific item of {@link StravaGear}. The requesting {@link StravaAthlete} must own the {@link StravaGear}. At this time it is
	 * not possible to view just anyone's gear type and usage.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/gear/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/gear/#show">http://strava.github.io/api/v3/gear/#show</a>
	 *
	 * @param gearId
	 *            The id of the {@link StravaGear} to be retrieved.
	 * @return Returns a detailed {@link StravaGear} representation.
	 * @throws UnauthorizedException
	 *             If service token is invalid
	 */
	public StravaGear getGear(final String gearId);

	/**
	 * <p>
	 * Retrieve details about a specific item of {@link StravaGear}. The requesting {@link StravaAthlete} must own the {@link StravaGear}. At this time it is
	 * not possible to view just anyone's gear type and usage.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/gear/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/gear/#show">http://strava.github.io/api/v3/gear/#show</a>
	 *
	 * @param gearId
	 *            The id of the {@link StravaGear} to be retrieved.
	 * @return Returns a detailed {@link StravaGear} representation.
	 * @throws UnauthorizedException
	 *             If service token is invalid
	 */
	public CompletableFuture<StravaGear> getGearAsync(final String gearId);
}
