package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.model.StravaRoute;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

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
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param page
	 *            Page of the results to return
	 * @param perPage
	 *            Number of items per page
	 * @return The array of routes
	 * @throws NotFoundException
	 *             If the athlete does not exist
	 * @throws BadRequestException
	 *             If the paging parameters are invalid
	 */
	@GET("/athletes/{id}/routes")
	public StravaRoute[] listAthleteRoutes(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param callback
	 *            The callback to execute to return the routes
	 * @param page
	 *            Page of the results to return
	 * @param perPage
	 *            Number of items per page
	 * @throws NotFoundException
	 *             If the athlete does not exist
	 * @throws BadRequestException
	 *             If the paging parameters are invalid
	 */
	@GET("/athletes/{id}/routes")
	public void listAthleteRoutes(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage, StravaAPICallback<StravaRoute[]> callback)
			throws NotFoundException, BadRequestException;

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param page
	 *            Page of the results to return
	 * @param perPage
	 *            Number of items per page
	 * @throws NotFoundException
	 *             If the athlete does not exist
	 * @throws BadRequestException
	 *             If the paging parameters are invalid
	 * @return The route
	 */
	@GET("/athletes/{id}/routes")
	public Response listAthleteRoutesRaw(@Path("id") Integer id, @Query("page") Integer page, @Query("per_page") Integer perPage) throws NotFoundException, BadRequestException;
}
