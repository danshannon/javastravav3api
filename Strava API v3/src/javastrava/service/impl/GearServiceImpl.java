package javastrava.service.impl;

import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaGear;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ClubService;
import javastrava.service.GearService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.PrivacyUtils;

/**
 * <p>
 * Implementation of {@link ClubService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class GearServiceImpl extends StravaServiceImpl implements GearService {
	/**
	 * <p>
	 * Returns an instance of {@link GearService gear services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the club services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static GearService instance(final Token token) {
		// Get the service from the token's cache
		GearService service = token.getService(GearService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new GearServiceImpl(token);
			token.addService(GearService.class, service);
		}
		return service;
	}

	/**
	 * Cache of gear information
	 */
	private final StravaCache<StravaGear, String> gearCache;

	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is via the {@link #instance(Token)} method
	 * </p>
	 *
	 * @param token
	 *            The access token to be used to authenticate to the Strava API
	 */
	private GearServiceImpl(final Token token) {
		super(token);
		this.gearCache = new StravaCacheImpl<StravaGear, String>(StravaGear.class, token);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		this.gearCache.removeAll();
	}

	/**
	 * @see javastrava.service.GearService#getGear(java.lang.String)
	 */
	@Override
	public StravaGear getGear(final String gearId) {
		// If the id is null, return null
		if (gearId == null) {
			return null;
		}

		// Attempt to get the gear from cache
		StravaGear gear = this.gearCache.get(gearId);
		if ((gear != null) && (gear.getResourceState() != StravaResourceState.META)) {
			return gear;
		}

		// If it wasn't in cache, try to get it from the API
		try {
			gear = this.api.getGear(gearId);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			gear = PrivacyUtils.privateGear(gearId);
		}

		// Put the gear in cache and return it
		this.gearCache.put(gear);
		return gear;
	}

	/**
	 * @see javastrava.service.GearService#getGearAsync(java.lang.String)
	 */
	@Override
	public CompletableFuture<StravaGear> getGearAsync(final String gearId) {
		return StravaServiceImpl.future(() -> {
			return getGear(gearId);
		});
	}

}
