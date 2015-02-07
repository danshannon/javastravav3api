package stravajava.api.v3.service.impl.retrofit;

import stravajava.api.v3.service.AthleteServices;
import stravajava.api.v3.service.exception.UnauthorizedException;

public abstract class StravaServiceImpl {
	private AthleteServices athleteService;
	
	protected StravaServiceImpl(String token) {
		this.athleteService = AthleteServicesImpl.implementation(token);
		if (!accessTokenIsValid()) {
			throw new UnauthorizedException("Access token " + token + " is invalid");
		}
 	}
	
	protected boolean accessTokenIsValid() {
		try {
			athleteService.getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}
}
