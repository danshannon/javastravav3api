/**
 *
 */
package javastrava.api.v3.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.service.ClubService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.util.Paging;
import javastrava.util.PagingCallback;
import javastrava.util.PagingHandler;
import javastrava.util.PrivacyUtils;

/**
 * <p>
 * Implementation of {@link ClubService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class ClubServiceImpl extends StravaServiceImpl implements ClubService {
	private ClubServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link ClubService club services}
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
	public static ClubService instance(final Token token) {
		// Get the service from the token's cache
		ClubService service = token.getService(ClubService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new ClubServiceImpl(token);
			token.addService(ClubService.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#getClub(java.lang.Integer)
	 */
	@Override
	public StravaClub getClub(final Integer id) {
		try {
			return api.getClub(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return PrivacyUtils.privateClubRepresentation(id);
		}
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<StravaClub> listAuthenticatedAthleteClubs() {
		return Arrays.asList(api.listAuthenticatedAthleteClubs());
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ClubServiceImpl.this.api.listClubMembers(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(Integer, Paging)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer id, final Paging pagingInstruction) {
		final List<StravaActivity> activities = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ClubServiceImpl.this.api.listRecentClubActivities(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		return PrivacyUtils.handlePrivateActivities(activities, this.getToken());
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#joinClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse joinClub(final Integer id) {
		try {
			return api.join(id);
		} catch (final NotFoundException e) {
			return failedClubMembershipResponse();
		} catch (final UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			} else {
				throw e;
			}
		}
	}

	private static StravaClubMembershipResponse failedClubMembershipResponse() {
		final StravaClubMembershipResponse response = new StravaClubMembershipResponse();
		response.setActive(Boolean.FALSE);
		response.setSuccess(Boolean.FALSE);
		return response;
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#leaveClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse leaveClub(final Integer id) {
		try {
			return api.leave(id);
		} catch (final UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			} else {
				throw e;
			}
		} catch (final NotFoundException e) {
			return failedClubMembershipResponse();
		}
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id) {
		return listClubMembers(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(java.lang.Integer)
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

	/**
	 * @see javastrava.api.v3.service.ClubService#listAllClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllClubMembers(final Integer clubId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listClubMembers(clubId,thisPage);
			}

		});

	}

	/**
	 * @see javastrava.api.v3.service.ClubService#listAllRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaActivity>() {

			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listRecentClubActivities(clubId, thisPage);
			}

		});
	}

}
