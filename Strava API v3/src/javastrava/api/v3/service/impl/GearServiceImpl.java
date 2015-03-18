package javastrava.api.v3.service.impl;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.rest.GearAPI;
import javastrava.api.v3.service.ClubService;
import javastrava.api.v3.service.GearService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>
 * Implementation of {@link ClubService}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class GearServiceImpl extends StravaServiceImpl<GearAPI> implements GearService {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is via the {@link #instance(Token)} method
	 * </p>
	 * 
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	private GearServiceImpl(final Token token) {
		super(GearAPI.class, token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link GearService gear services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the club services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static GearService instance(final Token token) {
		// Get the service from the token's cache
		GearService service = token.getService(GearService.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new GearServiceImpl(token);
			token.addService(GearService.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.GearService#getGear(java.lang.String)
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
