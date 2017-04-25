package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.ref.AuthorisationScope;
import javastrava.model.StravaRoute;
import retrofit.http.Path;

/**
 * <p>
 * Routes are manually-created paths made up of sections called legs. Currently it is only possible to create routes using the
 * Routebuilder web interface.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface RouteService extends StravaService {
	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating
	 * user and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private} permissions. For raw data associated with a route
	 * see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The route
	 */
	public StravaRoute getRoute(@Path("id") Integer routeId);

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating
	 * user and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private} permissions. For raw data associated with a route
	 * see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The future on which to execute get() to retrieve the route
	 */
	public CompletableFuture<StravaRoute> getRouteAsync(Integer routeId);

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own
	 * routes and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The route
	 */
	public List<StravaRoute> listAthleteRoutes(Integer id);

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own
	 * routes and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The future to execute get() on to return the routes
	 */
	public CompletableFuture<List<StravaRoute>> listAthleteRoutesAsync(Integer id);

}
