package stravajava.api.v3.service.impl.retrofit;

import java.util.HashMap;

import stravajava.api.v3.model.StravaGear;
import stravajava.api.v3.service.GearServices;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

public class GearServicesImpl implements GearServices {
	private GearServicesImpl(String token) {
		this.restService = Retrofit.retrofit(GearServicesRetrofit.class, token, GearServicesRetrofit.LOG_LEVEL);
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface, so that there's only one instance of one big service per token???
	 * 
	 * <p>Returns an implementation of {@link GearServices gear services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static GearServices implementation(final String token) {
		GearServices restService = restServices.get(token);
		if (restService == null) {
			restService = new GearServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,GearServices> restServices = new HashMap<String,GearServices>();
	
	private GearServicesRetrofit restService;
	

	@Override
	public StravaGear getGear(String id) throws UnauthorizedException {
		try {
			return restService.getGear(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

}
