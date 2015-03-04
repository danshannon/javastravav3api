package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.service.ClubServices;
import javastrava.api.v3.service.GearServices;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>
 * Implementation of {@link ClubServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class GearServicesImpl extends StravaServiceImpl<GearServicesRetrofit> implements GearServices {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is via the {@link #implementation(String)} method
	 * </p>
	 * 
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	private GearServicesImpl(final Token token) {
		super(GearServicesRetrofit.class, token);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link GearServices gear services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static GearServices implementation(final Token token) {
		// Get the service from the token's cache
		GearServices service = token.getService(GearServices.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new GearServicesImpl(token);
			token.addService(GearServices.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.GearServices#getGear(java.lang.String)
	 */
	@Override
	public StravaGear getGear(final String id) {
		try {
			return this.restService.getGear(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				StravaGear gear = new StravaGear();
				gear.setId(id);
				return gear;
			} else {
				throw e;
			}
		}
	}

}
