package javastrava.api.v3.service.impl;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Base class for all implementations of Strava services
 * </p>
 *
 * @author Dan Shannon
 *
 */
public abstract class StravaServiceImpl {
	/**
	 * Logger
	 */
	private static Logger log = LogManager.getLogger();

	/**
	 * Current request rate over the last 15 minutes
	 */
	public static int requestRate = 0;

	/**
	 * Current request rate over the last day
	 */
	public static int requestRateDaily = 0;

	/**
	 * Calculates the percentage of the daily request limit that has been used,
	 * issues a warning if required
	 *
	 * @return Percentage used.
	 */
	public static float requestRateDailyPercentage() {
		final float percent = (StravaConfig.RATE_LIMIT_DAILY == 0 ? 0
				: (100 * new Float(requestRateDaily).floatValue()) / new Float(StravaConfig.RATE_LIMIT_DAILY).floatValue());
		if (percent > 100) {
			log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimitDaily"), Integer.valueOf(requestRateDaily), //$NON-NLS-1$
					Integer.valueOf(StravaConfig.RATE_LIMIT_DAILY), Float.valueOf(percent)));
		} else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimitDaily"), Integer.valueOf(requestRateDaily), //$NON-NLS-1$
					Integer.valueOf(StravaConfig.RATE_LIMIT_DAILY), Float.valueOf(percent)));
		}
		return percent;
	}

	/**
	 * Calculates the percentage of the per-15-minute request limit that has
	 * been used, issues a warning if required
	 *
	 * @return Percentage used.
	 */
	public static float requestRatePercentage() {
		final float percent = (StravaConfig.RATE_LIMIT == 0 ? 0
				: (100 * new Float(requestRate).floatValue()) / new Float(StravaConfig.RATE_LIMIT).floatValue());
		if (percent > 100) {
			log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimit"), Integer.valueOf(requestRate), //$NON-NLS-1$
					Integer.valueOf(StravaConfig.RATE_LIMIT), Float.valueOf(percent)));
		} else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimit"), Integer.valueOf(requestRate), //$NON-NLS-1$
					Integer.valueOf(StravaConfig.RATE_LIMIT), Float.valueOf(percent)));
		}
		return percent;
	}

	/**
	 * The Strava access token in use
	 */
	private final Token token;

	/**
	 * API instance in use
	 */
	protected final API api;

	/**
	 * <p>
	 * Protected constructor prevents user from getting a service instance
	 * without a valid token
	 * </p>
	 *
	 * @param token
	 *        The access token to be used to authenticate to the Strava API
	 */
	protected StravaServiceImpl(final Token token) {
		this.token = token;
		this.api = new API(token);
	}

	/**
	 * <p>
	 * Work out if the access token is valid (i.e. has not been revoked)
	 * </p>
	 *
	 * @return <code>true</code> if the token can be used to get the
	 *         authenticated athlete, <code>false</code> otherwise
	 */
	protected boolean accessTokenIsValid() {
		try {
			this.api.getAuthenticatedAthlete();
			return true;
		} catch (final UnauthorizedException e) {
			return false;
		}
	}

	/**
	 * Get the Strava access token associated with this service
	 * @return The token
	 */
	protected final Token getToken() {
		return this.token;
	}

}
