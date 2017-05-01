package javastrava.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.model.StravaRoute;
import javastrava.service.RouteService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Routes are manually-created paths made up of sections called legs. Currently it is only possible to create routes using the Routebuilder web interface.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class RouteServiceImpl extends StravaServiceImpl implements RouteService {

	/**
	 * <p>
	 * Returns an instance of {@link RouteService route services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the stream services
	 */
	public static RouteService instance(final Token token) {
		// Get the service from the token's cache
		RouteService service = token.getService(RouteService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new RouteServiceImpl(token);
			token.addService(RouteService.class, service);
		}
		return service;
	}

	/**
	 * <p>
	 * Private constructor prevents anyone from getting an instance without a valid access token
	 * </p>
	 *
	 * @param token
	 *            The access token to be used to authenticate to the Strava API
	 */
	private RouteServiceImpl(final Token token) {
		super(token);
	}

	@Override
	public void clearCache() {
		// Nothing to do - not cached

	}

	@Override
	public StravaRoute getRoute(Integer routeId) {
		if (routeId == null) {
			return null;
		}

		try {
			return this.api.getRoute(routeId);
		} catch (final NotFoundException e) {
			return null;
		}
	}

	@Override
	public CompletableFuture<StravaRoute> getRouteAsync(Integer routeId) {
		return StravaServiceImpl.future(() -> getRoute(routeId));
	}

	@Override
	public List<StravaRoute> listAthleteRoutes(Integer id) {
		try {
			return Arrays.asList(this.api.listAthleteRoutes(id, null, null));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaRoute>();
		}
	}

	@Override
	public CompletableFuture<List<StravaRoute>> listAthleteRoutesAsync(Integer id) {
		return StravaServiceImpl.future(() -> listAthleteRoutes(id));
	}

}
