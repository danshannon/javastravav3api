/**
 * 
 */
package javastrava.api.v3.service.impl;

import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.rest.AthleteAPI;
import javastrava.api.v3.service.AthleteService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.util.Paging;
import javastrava.util.PagingCallback;
import javastrava.util.PagingHandler;

/**
 * <p>
 * Implementation of {@link AthleteService}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class AthleteServiceImpl extends StravaServiceImpl<AthleteAPI> implements AthleteService {
	private AthleteServiceImpl(final Token token) {
		super(AthleteAPI.class, token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link AthleteService athlete services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the athlete services
	 */
	public static AthleteService instance(final Token token) {
		// Get the service from the token's cache
		AthleteService service = token.getService(AthleteService.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new AthleteServiceImpl(token);
			token.addService(AthleteService.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#getAuthenticatedAthlete()
	 */
	@Override
	public StravaAthlete getAuthenticatedAthlete() {
		return this.restService.getAuthenticatedAthlete();
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#getAthlete(java.lang.Integer)
	 */
	@Override
	public StravaAthlete getAthlete(final Integer id) {
		try {
			return this.restService.getAthlete(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				StravaAthlete athlete = new StravaAthlete();
				athlete.setId(id);
				return athlete;
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	@Override
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.restService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthleteKOMs(Integer, Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id, final Paging pagingInstruction) {
		List<StravaSegmentEffort> efforts = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegmentEffort>() {
			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServiceImpl.this.restService.listAthleteKOMs(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		// This is a workaround for issue javastrava-api #26 (https://github.com/danshannon/javastravav3api/issues/26)
		if (efforts != null) {
    		for (StravaSegmentEffort effort : efforts) {
    			if (effort != null && effort.getActivity() != null && effort.getActivity().getResourceState() == null) {
    				effort.getActivity().setResourceState(StravaResourceState.META);
    			}
    		}
		}
		// End of workaround

		// This is a workaround for issue javastrava-api #27 (https://github.com/danshannon/javastravav3api/issues/27)
		if (efforts != null) {
    		for (StravaSegmentEffort effort : efforts) {
    			if (effort != null && effort.getAthlete() != null && effort.getAthlete().getResourceState() == null) {
    				effort.getAthlete().setResourceState(StravaResourceState.META);
    			}
    		}
		}		
		// End of workaround
		return efforts;
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAuthenticatedAthleteFriends(Paging)
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServiceImpl.this.restService.listAuthenticatedAthleteFriends(thisPage.getPage(), thisPage.getPageSize()));
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthleteFriends(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServiceImpl.this.restService.listAthleteFriends(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthletesBothFollowing(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServiceImpl.this.restService.listAthletesBothFollowing(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id) {
		return listAthleteKOMs(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends() {
		return listAuthenticatedAthleteFriends(null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer id) {
		return listAthleteFriends(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id) {
		return listAthletesBothFollowing(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#statistics(Integer)
	 */
	@Override
	public StravaStatistics statistics(final Integer id) {
		try {
			return this.restService.statistics(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new StravaStatistics();
			}
			throw e;
		}
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAllAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllAthleteFriends(final Integer athleteId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAthleteFriends(athleteId, thisPage);
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAllAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAllAuthenticatedAthleteFriends() {
		return PagingHandler.handleListAll(new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAuthenticatedAthleteFriends(thisPage);
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAllAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllAthleteKOMs(final Integer athleteId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaSegmentEffort>() {

			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAthleteKOMs(athleteId, thisPage);
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteService#listAllAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllAthletesBothFollowing(final Integer athleteId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAthletesBothFollowing(athleteId, thisPage);
			}

		});

	}

}
