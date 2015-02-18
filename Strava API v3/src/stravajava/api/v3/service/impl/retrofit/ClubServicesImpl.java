/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaClub;
import stravajava.api.v3.model.StravaClubMembershipResponse;
import stravajava.api.v3.service.ClubServices;
import stravajava.api.v3.service.PagingCallback;
import stravajava.api.v3.service.PagingHandler;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * <p>
 * Implementation of {@link ClubServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class ClubServicesImpl extends StravaServiceImpl implements ClubServices {
	private ClubServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(ClubServicesRetrofit.class, token, ClubServicesRetrofit.LOG_LEVEL);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link ClubServices club services}
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
	public static ClubServices implementation(final String token) {
		ClubServices restService = restServices.get(token);
		if (restService == null) {
			restService = new ClubServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<String, ClubServices> restServices = new HashMap<String, ClubServices>();

	final ClubServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.ClubServices#getClub(java.lang.Integer)
	 */
	@Override
	public StravaClub getClub(final Integer id) {
		try {
			return this.restService.getClub(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				// If we get here, the access token is valid
				// Therefore the club is private, so return an empty club
				return privateClubRepresentation(id);
			} else {
				throw e;
			}
		}
	}

	private StravaClub privateClubRepresentation(final Integer id) {
		StravaClub club = new StravaClub();
		club.setId(id);
		return club;
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<StravaClub> listAuthenticatedAthleteClubs() {
		return Arrays.asList(this.restService.listAuthenticatedAthleteClubs());
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ClubServicesImpl.this.restService.listClubMembers(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(Integer, Paging)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ClubServicesImpl.this.restService.listRecentClubActivities(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse joinClub(final Integer id) {
		try {
			return this.restService.join(id);
		} catch (NotFoundException e) {
			return failedClubMembershipResponse();
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			} else {
				throw e;
			}
		}
	}

	private StravaClubMembershipResponse failedClubMembershipResponse() {
		StravaClubMembershipResponse response = new StravaClubMembershipResponse();
		response.setActive(false);
		response.setSuccess(false);
		return response;
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse leaveClub(final Integer id) {
		try {
			return this.restService.leave(id);
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			} else {
				throw e;
			}
		} catch (NotFoundException e) {
			return failedClubMembershipResponse();
		}
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id) {
		return listClubMembers(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer id) {
		List<StravaActivity> activities = listRecentClubActivities(id, null);

		// Strava API returns NULL instead of an empty array
		if (activities == null) {
			activities = new ArrayList<StravaActivity>();
		}
		return activities;
	}

}
