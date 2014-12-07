package com.danshannon.strava.api.service.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.Gear;
import com.danshannon.strava.api.service.GearServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

public class GearServicesImpl implements GearServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private GearServicesImpl(GearServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * TODO Should move all of this into a single big StravaAPIRetrofit interface
	 * 
	 * <p>Returns an implementation of {@link GearServices gear services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static GearServices implementation(String token) throws UnauthorizedException {
		GearServices restService = restServices.get(token);
		if (restService == null) {
			restService = new GearServicesImpl(new RestAdapter.Builder()
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(LOG_LEVEL)
				.setEndpoint(Strava.ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(GearServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			// TODO restService.listAuthenticatedAthleteClubs();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,GearServices> restServices = new HashMap<String,GearServices>();
	
	private GearServicesRetrofit restService;
	

	@Override
	public Gear getGear(String id) {
		try {
			return restService.getGear(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

}
