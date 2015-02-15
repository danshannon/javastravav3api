package stravajava.api.v3.service.impl.retrofit;

import lombok.extern.log4j.Log4j2;
import stravajava.api.v3.service.AthleteServices;
import stravajava.api.v3.service.exception.UnauthorizedException;

@Log4j2
public abstract class StravaServiceImpl {
	private final AthleteServices athleteService;
	
	// TODO Parameterise in config files
	public static int warnAtRequestLimitPercent = 10;
	
	public static int requestRate = 0;
	public static int requestRateDaily = 0;
	public static int requestLimit = 0;
	public static int requestLimitDaily = 0;
	
	public static float requestRatePercentage() {
		float percent = (requestLimit == 0 ? 0 : 100 * new Float(requestRate) / new Float(requestLimit));
		if (percent > StravaServiceImpl.warnAtRequestLimitPercent) {
			log.warn("Approaching rate limit - " + requestRate + " of " + requestLimit + " requests (" + percent + "%) used");
		}
		return percent;
	}
	
	public static float requestRateDailyPercentage() {
		float percent = (requestLimitDaily == 0 ? 0 : 100 * new Float(requestRateDaily) / new Float(requestLimitDaily));
		if (percent > warnAtRequestLimitPercent) {
			log.warn("Approaching rate limit - " + requestRateDaily + " of " + requestLimitDaily + " requests (" + percent + "%) used");
		}
		return percent;
	}

	protected StravaServiceImpl(final String token) {
		this.athleteService = AthleteServicesImpl.implementation(token);
		if (!accessTokenIsValid()) {
			throw new UnauthorizedException("Access token " + token + " is invalid");
		}
	}

	protected boolean accessTokenIsValid() {
		try {
			this.athleteService.getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}
}
