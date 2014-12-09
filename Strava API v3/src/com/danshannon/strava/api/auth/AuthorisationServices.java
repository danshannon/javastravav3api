package com.danshannon.strava.api.auth;

import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;

/**
 * <p>Authentication</p>
 * 
 * <p>Strava uses OAuth2 as an authentication protocol. It allows external applications to request authorisation to a user�s private data without requiring their Strava username and password. It allows users to grant and revoke API access on a per-application basis and keeps users� authentication details safe.</p>
 * 
 * <p>All developers need to register their application before getting started. A registered application will be assigned a Client ID and Client SECRET. The SECRET should never be shared.</p>
 * 
 * <p>Web Application Flow</p>
 * 
 * <p>The process begins by redirecting a browser to a Strava URL with a set of query parameters that indicate the type of Strava API access the application requires. Strava handles the user authentication and consent.</p>
 * 
 * <p>If the user authorises the application, Strava will return an authorisation code to the web server application. The application must still complete the process by exchanging the code for an access token.</p>
 * 
 * <p>This is done by presenting a client_id and client_secret (obtained during application registration), along with the authorisation code, to Strava. Upon success, an access token will be returned that can be used to access the API on behalf of the user.</p>
 * 
 * <p>Users can revoke access on their settings page.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface AuthorisationServices {
	
	/**
	 * <p>To request access on behalf of a user, redirect the user to Strava�s authorisation page,  https://www.strava.com/oauth/authorize (example). The page will prompt the user to authorize the app while providing basic information about what is being asked.</p>
	 * 
	 * <p>By default, applications can only view a user�s public data.</p>
	 * 
	 * <p>The scope parameter can be used to request more access. It is recommended to only requested the minimum amount of access necessary.</p>
	 * 
	 * <p>URL GET https://www.strava.com/oauth/authorize</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/oauth/#get-authorize">http://strava.github.io/api/v3/oauth/#get-authorize</a>
	 * 
	 * @param clientId application�s ID, obtained during registration
	 * @param redirectURI URL to which the user will be redirected with the authorisation code, must be to the callback domain associated with the application, or its sub-domain (localhost and 127.0.0.1 are white-listed)
	 * @param responseType must be �code�
	 * @param approvalPrompt (Optional) �force� or �auto�, use �force� to always show the authorisation prompt even if the user has already authorized the current application, default is �auto�
	 * @param scope (Optional) comma delimited string of �view_private� and/or �write�, leave blank for read-only permissions.
	 * @param state (Optional)returned to your application, useful if the authentication is done from various points in an app
	 */
	public void requestAccess(Integer clientId, String redirectURI, AuthorisationResponseType responseType, AuthorisationApprovalPrompt approvalPrompt, AuthorisationScope[] scope, String state);
	
	/**
	 * <p>Strava will respond to the authorization request by redirecting the user/browser to the redirect_uri provided.</p>
	 * 
	 * <p>On success, a code will be included in the query string.</p>
	 * 
	 * <p>If access is denied, error=access_denied will be included in the query string.</p>
	 * 
	 * <p>In both cases, if provided, the state argument will also be included.</p>
	 * 
	 * <p>If the user accepts the request to share access to their Strava data, Strava will redirect back to redirect_uri with the authorization code. The application must now exchange the temporary authorization code for an access token, using its client ID and client secret.</p>
	 * 
	 * <p>The application will now be able to make requests on the user�s behalf using the access_token query string parameter (GET) or POST/PUT body, or the Authorization header.</p>
	 * 
	 * <p>Applications should check for a 401 Unauthorised response. Access for those tokens has been revoked by the user.</p>
	 * 
	 * <p>URL POST https://www.strava.com/oauth/token</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/oauth/#post-token">http://strava.github.io/api/v3/oauth/#post-token</a>
	 * 
	 * @param clientId application�s ID, obtained during registration
	 * @param clientSecret application�s secret, obtained during registration
	 * @param code authorisation code
	 * @return Returns an access_token and a detailed representation of the current athlete.
	 */
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code);
	
	/**
	 * <p>Allows an application to revoke its access to an athlete�s data.</p>
	 * 
	 * <p>This will invalidate all access tokens associated with the �athlete,application� pair used to create the token. The application will be removed from the Athlete Settings page on Strava.</p>
	 * 
	 * <p>All requests made using invalidated tokens will receive a 401 Unauthorised response.</p>
	 * 
	 * <p>URL POST https://www.strava.com/oauth/deauthorize</p>
	 * 
	 * @see <a href = "http://strava.github.io/api/v3/oauth/#deauthorize">http://strava.github.io/api/v3/oauth/#deauthorize</a>
	 * 
	 * @param accessToken The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 */
	public TokenResponse deauthorise(String accessToken);
	
	/**
	 * <p>Login to the Strava application</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY as it's not genuinely useful and you shouldn't be asking other people for their Strava password</p>
	 * 
	 * <p>URL POST https://www.strava.com/session</p>
	 * 
	 * TODO Session cookie?
	 * 
	 * @param email Email address associated with the user account
	 * @param password Password associated with the user account
	 * @return <code>true</code> if login is successful, <code>false</code> otherwise
	 */
	public boolean login(String email, String password);
	
	/**
	 * <p>Indicate that the user has allowed the application to access their Strava data</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY</p>
	 * 
	 * TODO Session cookie??
	 * 
	 * @param clientId The application's ID, obtained during registration
	 * @param redirectURI URI to which a redirect should be issued
	 * @param responseType must be "code"
	 * @return The code used by {@link #tokenExchange(Integer, String, String)} to get an access token
	 */
	public String acceptApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType);
	
	/**
	 * <p>Indicate that the user has DENIED the application access to their Strava data</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY</p>
	 * 
	 * TODO Session cookie??
	 * 
	 * @param clientId The application's ID, obtained during registration
	 * @param redirectURI URI to which a redirect should be issued
	 * @param responseType must be "code"
	 */
	public void rejectApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType);
}
