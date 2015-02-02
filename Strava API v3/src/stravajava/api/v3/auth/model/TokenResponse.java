package stravajava.api.v3.auth.model;

import stravajava.api.v3.model.StravaAthlete;
import lombok.Data;

/**
 * <p>The TokenResponse is returned by authorisation services; it contains the access token which is then used for authentication purposes for all other Strava API access</p>
 * 
 * @author Dan Shannon
 */
@Data 
public class TokenResponse {
	private String accessToken;
	private String tokenType;
	private StravaAthlete athlete;
}
