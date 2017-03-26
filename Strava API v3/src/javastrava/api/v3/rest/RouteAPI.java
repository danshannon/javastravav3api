package javastrava.api.v3.rest;

import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaRoute;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * <p>
 * Routes are manually-created paths made up of sections called legs. Currently it is only possible to create routes using the Routebuilder web interface.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface RouteAPI {
	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The route
	 * @throws NotFoundException
	 *             If the route does not exist
	 * @throws BadRequestException
	 *             If the id is not an integer
	 * @throws UnauthorizedException
	 *             If the route is private and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private}
	 */
	@GET("/routes/{id}")
	public StravaRoute getRoute(@Path("id") Integer routeId) throws NotFoundException, BadRequestException, UnauthorizedException;

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The route
	 * @throws NotFoundException
	 *             If the route does not exist
	 * @throws BadRequestException
	 *             If the id is not an integer
	 * @throws UnauthorizedException
	 *             If the route is private and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private}
	 */
	@GET("/routes/{id}")
	public Response getRouteRaw(@Path("id") Integer routeId) throws NotFoundException, BadRequestException, UnauthorizedException;

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @param callback
	 *            The callback to execute to retrieve the route
	 * @throws NotFoundException
	 *             If the route does not exist
	 * @throws BadRequestException
	 *             If the id is not an integer
	 * @throws UnauthorizedException
	 *             If the route is private and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private}
	 */
	@GET("/routes/{id}")
	public void getRoute(@Path("id") Integer routeId, StravaAPICallback<StravaRoute> callback);

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The route
	 */
	@GET("/athletes/{id}/routes")
	public StravaRoute[] listAthleteRoutes(@Path("id") Integer id);

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The route
	 */
	@GET("/athletes/{id}/routes")
	public Response listAthleteRoutesRaw(@Path("id") Integer id);

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param callback
	 *            The callback to execute to return the routes
	 */
	@GET("/athletes/{id}/routes")
	public void listAthleteRoutes(@Path("id") Integer id, StravaAPICallback<StravaRoute[]> callback);
}
