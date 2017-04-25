package javastrava.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaRunningRace;
import javastrava.service.RunningRaceService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Implementation of the running race service
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class RunningRaceServiceImpl extends StravaServiceImpl implements RunningRaceService {
	/**
	 * <p>
	 * Returns an instance of {@link RunningRaceService running race services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the activity services
	 */
	public static RunningRaceService instance(final Token token) {
		// Get the service from the token's cache
		RunningRaceService service = token.getService(RunningRaceService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new RunningRaceServiceImpl(token);
			token.addService(RunningRaceService.class, service);
		}
		return service;
	}

	private final StravaCacheImpl<StravaRunningRace, Integer> runningRaceCache;

	/**
	 * <p>
	 * Private constructor requires a valid access token
	 * </p>
	 *
	 * @param token
	 *            Access token from Strava OAuth process
	 */
	private RunningRaceServiceImpl(final Token token) {
		super(token);
		this.runningRaceCache = new StravaCacheImpl<StravaRunningRace, Integer>(StravaRunningRace.class, token);
	}

	@Override
	public void clearCache() {
		this.runningRaceCache.removeAll();
	}

	@Override
	public StravaRunningRace getRace(Integer id) {
		// If the id is null, return null
		if (id == null) {
			return null;
		}

		try {
			return this.api.getRace(id);
		} catch (final NotFoundException e) {
			return null;
		}
	}

	@Override
	public CompletableFuture<StravaRunningRace> getRaceAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return getRace(id);
		});
	}

	@Override
	public List<StravaRunningRace> listRaces(Integer year) {
		try {
			return Arrays.asList(this.api.listRaces(year));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaRunningRace>();
		}
	}

	@Override
	public CompletableFuture<List<StravaRunningRace>> listRacesAsync(Integer year) {
		return StravaServiceImpl.future(() -> {
			return listRaces(year);
		});
	}

}
