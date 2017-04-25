package javastrava.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaActivity;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaClub;
import javastrava.model.StravaClubAnnouncement;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubMembershipResponse;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ClubService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;
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
	/**
	 * Generates a response indicating that the club membership activity (either a {@link ClubService#joinClub(Integer)} or a {@link ClubService#leaveClub(Integer)}) failed
	 *
	 * @return The response
	 */
	private static StravaClubMembershipResponse failedClubMembershipResponse() {
		final StravaClubMembershipResponse response = new StravaClubMembershipResponse();
		response.setActive(Boolean.FALSE);
		response.setSuccess(Boolean.FALSE);
		return response;
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
	 * Cached club instances
	 */
	private final StravaCache<StravaClub, Integer> clubCache;

	/**
	 * Private constructor requires a valid access token; see {@link #instance(Token)}
	 *
	 * @param token
	 *            A valid token from the Strava OAuth process
	 */
	private ClubServiceImpl(final Token token) {
		super(token);
		this.clubCache = new StravaCacheImpl<StravaClub, Integer>(StravaClub.class, token);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		this.clubCache.removeAll();
	}

	/**
	 * @see javastrava.service.ClubService#getClub(java.lang.Integer)
	 */
	@Override
	public StravaClub getClub(final Integer id) {
		// If the id is null, return null
		if (id == null) {
			return null;
		}

		// Attempt to get the club from the cache
		StravaClub club = this.clubCache.get(id);
		if ((club != null) && (club.getResourceState() != StravaResourceState.META)) {
			return club;
		}

		// If it wasn't in cache, get it from Strava
		try {
			club = this.api.getClub(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			club = PrivacyUtils.privateClubRepresentation(id);
		}

		// Put it in the cache and return it
		this.clubCache.put(club);
		return club;
	}

	/**
	 * @see javastrava.service.ClubService#getClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClub> getClubAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return getClub(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#joinClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse joinClub(final Integer id) {
		try {
			return this.api.joinClub(id);
		} catch (final NotFoundException e) {
			return failedClubMembershipResponse();
		} catch (final UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			}
			throw e;
		}
	}

	/**
	 * @see javastrava.service.ClubService#joinClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClubMembershipResponse> joinClubAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return joinClub(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#leaveClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse leaveClub(final Integer id) {
		try {
			return this.api.leaveClub(id);
		} catch (final UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return failedClubMembershipResponse();
			}
			throw e;
		} catch (final NotFoundException e) {
			return failedClubMembershipResponse();
		}
	}

	/**
	 * @see javastrava.service.ClubService#leaveClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClubMembershipResponse> leaveClubAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return leaveClub(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listAllClubAdmins(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllClubAdmins(final Integer clubId) {
		return PagingHandler.handleListAll(thisPage -> listClubAdmins(clubId, thisPage));
	}

	/**
	 * @see javastrava.service.ClubService#listAllClubAdminsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllClubAdminsAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listAllClubAdmins(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listAllClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllClubMembers(final Integer clubId) {
		return PagingHandler.handleListAll(thisPage -> listClubMembers(clubId, thisPage));

	}

	/**
	 * @see javastrava.service.ClubService#listAllClubMembersAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllClubMembersAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listAllClubMembers(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listAllRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId) {
		return PagingHandler.handleListAll(thisPage -> listRecentClubActivities(clubId, thisPage));
	}

	/**
	 * @see javastrava.service.ClubService#listAllRecentClubActivitiesAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllRecentClubActivitiesAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listAllRecentClubActivities(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<StravaClub> listAuthenticatedAthleteClubs() {
		return Arrays.asList(this.api.listAuthenticatedAthleteClubs());
	}

	/**
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaClub>> listAuthenticatedAthleteClubsAsync() {
		return StravaServiceImpl.future(() -> {
			return listAuthenticatedAthleteClubs();
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubAdmins(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubAdmins(final Integer clubId) {
		return listClubAdmins(clubId, null);
	}

	/**
	 * @see javastrava.service.ClubService#listClubAdmins(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listClubAdmins(final Integer clubId, final Paging paging) {
		try {
			return PagingHandler.handlePaging(paging, thisPage -> Arrays.asList(this.api.listClubAdmins(clubId, paging.getPage(), paging.getPageSize())));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaAthlete>();
		}
	}

	/**
	 * @see javastrava.service.ClubService#listClubAdminsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listClubAdmins(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubAdminsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId, final Paging paging) {
		return StravaServiceImpl.future(() -> {
			return listClubAdmins(clubId, paging);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubAnnouncements(java.lang.Integer)
	 */
	@Override
	public List<StravaClubAnnouncement> listClubAnnouncements(final Integer clubId) {
		List<StravaClubAnnouncement> announcements;
		try {
			announcements = Arrays.asList(this.api.listClubAnnouncements(clubId));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaClubAnnouncement>();
		}
		return announcements;
	}

	/**
	 * @see javastrava.service.ClubService#listClubAnnouncementsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaClubAnnouncement>> listClubAnnouncementsAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listClubAnnouncements(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubGroupEvents(java.lang.Integer)
	 */
	@Override
	public List<StravaClubEvent> listClubGroupEvents(final Integer clubId) {
		List<StravaClubEvent> events;
		try {
			events = Arrays.asList(this.api.listClubGroupEvents(clubId));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaClubEvent>();
		}
		return events;
	}

	/**
	 * @see javastrava.service.ClubService#listClubGroupEventsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaClubEvent>> listClubGroupEventsAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return Arrays.asList(this.api.listClubGroupEvents(clubId));
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id) {
		return listClubMembers(id, null);
	}

	/**
	 * @see javastrava.service.ClubService#listClubMembers(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer id, final Paging pagingInstruction) {
		try {
			return PagingHandler.handlePaging(pagingInstruction, thisPage -> Arrays.asList(this.api.listClubMembers(id, thisPage.getPage(), thisPage.getPageSize())));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaAthlete>();
		}
	}

	/**
	 * @see javastrava.service.ClubService#listClubMembersAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listClubMembers(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listClubMembersAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId, final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listClubMembers(clubId, pagingInstruction);
		});

	}

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer id) {
		return listRecentClubActivities(id, null);
	}

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivities(Integer, Paging)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer id, final Paging pagingInstruction) {
		if (id == null) {
			throw new IllegalArgumentException("Club id is required"); //$NON-NLS-1$
		}

		// Check that the club exists
		try {
			this.api.getClub(id);
		} catch (final NotFoundException e) {
			return null;
		}

		List<StravaActivity> activities;
		try {
			if (pagingInstruction == null) {
				activities = Arrays.asList(this.api.listRecentClubActivities(id, null, null));
			} else {
				activities = PagingHandler.handlePaging(pagingInstruction,
						thisPage -> Arrays.asList(ClubServiceImpl.this.api.listRecentClubActivities(id, thisPage.getPage(), thisPage.getPageSize())));
			}
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaActivity>();
		}

		// Strava API returns NULL instead of an empty array
		if (activities == null) {
			activities = new ArrayList<StravaActivity>();
		}
		return PrivacyUtils.handlePrivateActivities(activities, this.getToken());
	}

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivitiesAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId) {
		return StravaServiceImpl.future(() -> {
			return listRecentClubActivities(clubId);
		});
	}

	/**
	 * @see javastrava.service.ClubService#listRecentClubActivitiesAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId, final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listRecentClubActivities(clubId, pagingInstruction);
		});
	}

}
