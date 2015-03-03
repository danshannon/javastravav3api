package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.AthleteServices;
import javastrava.api.v3.service.exception.UnauthorizedException;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * Base class for all implementations of Strava services
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Log4j2
public abstract class StravaServiceImpl {
	private final AthleteServices athleteService;
	
	// TODO Parameterise in config files
	/**
	 * The percentage of request limits that, if exceeded, should log a warning
	 */
	private static int warnAtRequestLimitPercent = 10;
	
	/**
	 * Current request rate over the last 15 minutes
	 */
	public static int requestRate = 0;
	/**
	 * Current request rate over the last day
	 */
	public static int requestRateDaily = 0;
	/**
	 * Request limit every 15 minutes (default is 600)
	 */
	public static int requestLimit = 0;
	/**
	 * Daily request limit (default is 30,000)
	 */
	public static int requestLimitDaily = 0;
	
	private StravaAthlete authenticatedAthlete;
	
	/**
	 * Calculates the percentage of the per-15-minute request limit that has been used, issues a warning if required
	 * 
	 * @return Percentage used.
	 */
	public static float requestRatePercentage() {
		float percent = (requestLimit == 0 ? 0 : 100 * new Float(requestRate) / new Float(requestLimit));
		if (percent > StravaServiceImpl.warnAtRequestLimitPercent) {
			log.warn("Approaching rate limit - " + requestRate + " of " + requestLimit + " requests (" + percent + "%) used");
		}
		return percent;
	}
	
	/**
	 * Calculates the percentage of the daily request limit that has been used, issues a warning if required
	 * 
	 * @return Percentage used.
	 */
	public static float requestRateDailyPercentage() {
		float percent = (requestLimitDaily == 0 ? 0 : 100 * new Float(requestRateDaily) / new Float(requestLimitDaily));
		if (percent > warnAtRequestLimitPercent) {
			log.warn("Approaching rate limit - " + requestRateDaily + " of " + requestLimitDaily + " requests (" + percent + "%) used");
		}
		return percent;
	}

	/**
	 * <p>
	 * Protected constructor prevents user from getting a service instance without a valid token
	 * </p>
	 * 
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	protected StravaServiceImpl(final Token token) {
		this.athleteService = AthleteServicesImpl.implementation(token);
		if (!accessTokenIsValid()) {
			throw new UnauthorizedException("Access token " + token + " is invalid");
		}
	}

	/**
	 * <p>
	 * Work out if the access token is valid (i.e. has not been revoked)
	 * </p>
	 * 
	 * @return <code>true</code> if the token can be used to get the authenticated athlete, <code>false</code> otherwise
	 */
	protected boolean accessTokenIsValid() {
		try {
			this.authenticatedAthlete = this.athleteService.getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}
	
	protected StravaAthlete getAuthenticatedAthlete() {
		return this.authenticatedAthlete;
	}
}
