package javastrava.auth;

import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.service.StravaService;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Authentication
 * </p>
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
 * <p>
 * Web Application Flow
 * </p>
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
public interface TokenService extends StravaService {
	/**
	 * <p>
	 * Allows an application to revoke its access to an athlete's data.
	 * </p>
	 * 
	 * <p>
	 * This will invalidate all access tokens associated with the athlete, application pair used to create the token. The application will be removed from the
	 * StravaAthlete Settings page on Strava.
	 * </p>
	 * 
	 * <p>
	 * All requests made using invalidated tokens will receive a 401 Unauthorised response.
	 * </p>
	 * 
	 * <p>
	 * URL POST https://www.strava.com/oauth/deauthorize
	 * </p>
	 * 
	 * @see <a href = "http://strava.github.io/api/v3/oauth/#deauthorize">http://strava.github.io/api/v3/oauth/#deauthorize</a>
	 * 
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 */
	public TokenResponse deauthorise(final Token accessToken) throws UnauthorizedException;
}
