package javastrava.auth;

import javastrava.auth.model.Token;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.UnauthorizedException;

/**
 * <h2>Authentication</h2> 
 *
 * <p>
 * Strava uses OAuth2 as an authentication protocol. It allows external applications to request authorisation to a user's private data without requiring their
 * Strava username and password. It allows users to grant and revoke API access on a per-application basis and keeps users' authentication details safe.
 * </p>
 *
 * <p>
 * All developers need to register their application before getting started. A registered application will be assigned a Client ID and Client SECRET. The SECRET
 * should never be shared.
 * </p>
 *
 * <h2>Web Application Flow</h2>
 *
 * <p>
 * The process begins by redirecting a browser to a Strava URL with a set of query parameters that indicate the type of Strava API access the application
 * requires. Strava handles the user authentication and consent.
 * </p>
 *
 * <p>
 * If the user authorises the application, Strava will return an authorisation code to the web server application. The application must still complete the
 * process by exchanging the code for an access token.
 * </p>
 *
 * <p>
 * This is done by presenting a client_id and client_secret (obtained during application registration), along with the authorisation code, to Strava. Upon
 * success, an access token will be returned that can be used to access the API on behalf of the user.
 * </p>
 *
 * <p>
 * Users can revoke access on their settings page.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface AuthorisationService {

	/**
	 * <p>
	 * Strava will respond to the authorization request by redirecting the user/browser to the redirect_uri provided.
	 * </p>
	 * 
	 * <p>
	 * On success, a code will be included in the query string.
	 * </p>
	 * 
	 * <p>
	 * If access is denied, error=access_denied will be included in the query string.
	 * </p>
	 * 
	 * <p>
	 * In both cases, if provided, the state argument will also be included.
	 * </p>
	 * 
	 * <p>
	 * If the user accepts the request to share access to their Strava data, Strava will redirect back to redirect_uri with the authorization code. The
	 * application must now exchange the temporary authorization code for an access token, using its client ID and client secret.
	 * </p>
	 * 
	 * <p>
	 * The application will now be able to make requests on the user's behalf using the access_token query string parameter (GET) or POST/PUT body, or the
	 * Authorization header.
	 * </p>
	 * 
	 * <p>
	 * Applications should check for a 401 Unauthorised response. Access for those tokens has been revoked by the user.
	 * </p>
	 * 
	 * <p>
	 * URL POST https://www.strava.com/oauth/token
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/oauth/#post-token">http://strava.github.io/api/v3/oauth/#post-token</a>
	 * 
	 * @param clientId
	 *            application's ID, obtained during registration
	 * @param clientSecret
	 *            application's secret, obtained during registration
	 * @param code
	 *            authorisation code
	 * @param scopes the requested authorisation scopes
	 * @return Returns an access token containing a detailed representation of the current athlete.
	 * @throws BadRequestException Where the request does not contain all the required information
	 * @throws UnauthorizedException If client secret is invalid
	 */
	public Token tokenExchange(final Integer clientId, final String clientSecret, final String code, final AuthorisationScope... scopes) throws BadRequestException, UnauthorizedException;
}
