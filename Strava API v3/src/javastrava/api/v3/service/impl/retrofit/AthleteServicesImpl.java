/**
 * 
 */
package javastrava.api.v3.service.impl.retrofit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.service.AthleteServices;
import javastrava.api.v3.service.PagingCallback;
import javastrava.api.v3.service.PagingHandler;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.util.Paging;

/**
 * <p>
 * Implementation of {@link AthleteServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class AthleteServicesImpl implements AthleteServices {
	private AthleteServicesImpl(final String token) {
		this.restService = Retrofit.retrofit(AthleteServicesRetrofit.class, token, AthleteServicesRetrofit.LOG_LEVEL);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link AthleteServices athlete services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the athlete services
	 */
	public static AthleteServices implementation(final String token) {
		AthleteServices restService = restServices.get(token);
		if (restService == null) {
			restService = new AthleteServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<String, AthleteServices> restServices = new HashMap<String, AthleteServices>();

	final AthleteServicesRetrofit restService;

	/**
	 * @see javastrava.api.v3.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@Override
	public StravaAthlete getAuthenticatedAthlete() {
		return this.restService.getAuthenticatedAthlete();
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#getAthlete(java.lang.Integer)
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
	 * @see javastrava.api.v3.service.AthleteServices#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	@Override
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.restService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteKOMs(Integer, Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegmentEffort>() {
			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServicesImpl.this.restService.listAthleteKOMs(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends(Paging)
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServicesImpl.this.restService.listAuthenticatedAthleteFriends(thisPage.getPage(), thisPage.getPageSize()));
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteFriends(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServicesImpl.this.restService.listAthleteFriends(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthletesBothFollowing(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(AthleteServicesImpl.this.restService.listAthletesBothFollowing(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id) {
		return listAthleteKOMs(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends() {
		return listAuthenticatedAthleteFriends(null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer id) {
		return listAthleteFriends(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id) {
		return listAthletesBothFollowing(id, null);
	}

	private boolean accessTokenIsValid() {
		try {
			getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}

	/**
	 * @see javastrava.api.v3.service.AthleteServices#statistics(Integer)
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

}
