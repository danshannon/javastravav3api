package stravajava.api.v3.service.impl.retrofit;

import stravajava.api.v3.service.AthleteServices;
import stravajava.api.v3.service.exception.UnauthorizedException;

public abstract class StravaServiceImpl {
	private final AthleteServices athleteService;
	
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
