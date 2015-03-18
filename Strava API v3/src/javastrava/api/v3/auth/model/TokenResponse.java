package javastrava.api.v3.auth.model;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.rest.API;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * The TokenResponse is returned by authorisation services; it contains user
 * details and the access token which is then used for authentication purposes
 * for all other Strava API access
 * </p>
 * 
 * @author Dan Shannon
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class TokenResponse {
	/**
	 * The value of the access token
	 */
	private String accessToken;
	/**
	 * The type of token (usually "Bearer" - is used to create the authentication request header - see {@link API#instance(Class, Token)}
	 */
	private String tokenType;
	/**
	 * Strava returns details of the athlete along with the access token
	 */
	private StravaAthlete athlete;
}
