/**
 *
 */
package javastrava.api.v3.service.impl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaMapPoint;
import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaSegmentExplorerResponse;
import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.api.v3.model.StravaSegmentLeaderboardEntry;
import javastrava.api.v3.model.reference.StravaAgeGroup;
import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.service.SegmentService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.util.Paging;
import javastrava.util.PagingCallback;
import javastrava.util.PagingHandler;
import javastrava.util.PagingUtils;
import javastrava.util.PrivacyUtils;

/**
 * <p>
 * Implementation of {@link SegmentService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class SegmentServiceImpl extends StravaServiceImpl implements SegmentService {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is via {@link #instance(Token)} with a valid access token
	 * </p>
	 *
	 * @param token
	 *            The access token to use for authentication with the Strava API
	 */
	private SegmentServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link SegmentService gear services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the segment services
	 */
	public static SegmentService instance(final Token token) {
		if (token == null) {
			throw new IllegalArgumentException(Messages.string("SegmentServiceImpl.cannotInstantiateWithNullToken")); //$NON-NLS-1$
		}
		final Class<SegmentService> class1 = SegmentService.class;
		// Get the service from the token's cache
		SegmentService service = token.getService(class1);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new SegmentServiceImpl(token);
			token.addService(class1, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(final Integer id) {
		StravaSegment segment = null;
		try {
			segment = api.getSegment(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return PrivacyUtils.privateSegment(id);
			} else {
				throw e;
			}
		}

		// TODO Workaround for javastrava-api #70
		// If the segment is private and the token doesn't have view_private scope, then return an empty segment
		if (segment.getPrivateSegment().equals(Boolean.TRUE) && !getToken().hasViewPrivate()) {
			return PrivacyUtils.privateSegment(id);
		}
		// End of workaround

		return segment;

	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAuthenticatedAthleteStarredSegments(Paging)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		final List<StravaSegment> segments = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServiceImpl.this.api.listAuthenticatedAthleteStarredSegments(thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		// TODO This is a workaround for issue javastrava-api #25 (https://github.com/danshannon/javastravav3api/issues/25)
		if (segments != null) {
			for (final StravaSegment segment : segments) {
				if (segment.getAthletePrEffort() != null && segment.getAthletePrEffort().getResourceState() == null) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return PrivacyUtils.handlePrivateSegments(segments, this.getToken());
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listStarredSegments(Integer, Paging)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id, final Paging pagingInstruction) {
		final List<StravaSegment> segments = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServiceImpl.this.api.listStarredSegments(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		// TODO This is a workaround for issue javastrava-api #25 (https://github.com/danshannon/javastravav3api/issues/25)
		if (segments != null) {
			for (final StravaSegment segment : segments) {
				if (segment.getAthletePrEffort() != null && segment.getAthletePrEffort().getResourceState() == null) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return segments;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(Integer, Integer, LocalDateTime, LocalDateTime, Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final LocalDateTime startDateLocalTZ,
			final LocalDateTime endDateLocalTZ, final Paging pagingInstruction) {
		// TODO Workaround for issue javastrava-api #33 (https://github.com/danshannon/javastravav3api/issues/33)
		// Check if the segment is flagged as hazardous
		final StravaSegment segment = getSegment(id);

		// If the segment is null it doesn't exist, so return null
		if (segment == null) {
			return null;
		}

		// TODO This is the workaround for issue #45
		if (segment.getResourceState() == StravaResourceState.META) {
			return new ArrayList<StravaSegmentEffort>();
		}

		// If the segment is hazardous, return an empty list
		if (segment.getHazardous() == Boolean.TRUE) {
			return new ArrayList<StravaSegmentEffort>();
		}
		// End of workaround

		LocalDateTime endDateLocal = endDateLocalTZ;
		LocalDateTime startDateLocal = startDateLocalTZ;

		// If start date is set, but end date isn't, then Strava likes it to be set to something high
		if (endDateLocalTZ == null && startDateLocalTZ != null) {
			endDateLocal = LocalDateTime.of(9999, Month.DECEMBER, 31, 23, 59, 59);
		}

		// Similarly if the end date is set but start date isn't, Strava likes it to be set to something low
		if (startDateLocalTZ == null && endDateLocalTZ != null) {
			startDateLocal = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0, 0);
		}

		final String start = (startDateLocal == null ? null : startDateLocal.toString());
		final String end = (endDateLocal == null ? null : endDateLocal.toString());

		final List<StravaSegmentEffort> efforts = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegmentEffort>() {
			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServiceImpl.this.api.listSegmentEfforts(id, athleteId, start, end, thisPage.getPage(),
						thisPage.getPageSize()));
			}
		});

		return efforts;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer,
	 *      StravaLeaderboardDateRange, Paging, Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange,
			final Paging pagingInstruction, final Integer contextEntries) {
		PagingUtils.validatePagingArguments(pagingInstruction);

		StravaSegmentLeaderboard leaderboard = null;

		// TODO Workaround for issue javastrava-api #73 (https://github.com/danshannon/javastravav3api/issues/73)
		if (!this.getToken().hasViewPrivate()) {
			final StravaSegment segment = getSegment(segmentId);
			if (segment == null) {
				return null;
			}
			if (segment.getPrivateSegment() != null && segment.getPrivateSegment().equals(Boolean.TRUE)) {
				leaderboard = new StravaSegmentLeaderboard();
				leaderboard.setNeighborhoodCount(1);
				leaderboard.setAthleteEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
				leaderboard.setEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
				leaderboard.setEffortCount(0);
				leaderboard.setEntryCount(0);
				return leaderboard;
			}
		}
		// End of workaround

		// If null, then the default value for contextEntries is 2; the max is 15
		final Integer context = (contextEntries == null ? Integer.valueOf(2) : Integer.valueOf(Math.max(0, Math.min(15, contextEntries.intValue()))));
		final Integer contextSize = Integer.valueOf(context.intValue() * 2 + 1);

		// TODO This is a workaround for issue javastrava-api #23 (https://github.com/danshannon/javastravav3api/issues/23) - see also the workaround in
		// SegmentAPI
		if (clubId != null) {
			try {
				api.getClub(clubId);
			} catch (final NotFoundException e) {
				// Club doesn't exist, so return null
				return null;
			}
		}
		// End of workaround

		try {
			for (final Paging paging : PagingUtils.convertToStravaPaging(pagingInstruction)) {
				final StravaSegmentLeaderboard current = api.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange,
						paging.getPage(), paging.getPageSize(), context);
				if (current.getEntries().isEmpty()) {
					if (leaderboard == null) {
						leaderboard = current;
						current.setAthleteEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
					}
					break;
				}
				current.setAthleteEntries(calculateAthleteEntries(current, paging, contextSize));
				current.getEntries().removeAll(current.getAthleteEntries());
				current.setEntries(PagingUtils.ignoreLastN(current.getEntries(), paging.getIgnoreLastN()));
				current.setEntries(PagingUtils.ignoreFirstN(current.getEntries(), paging.getIgnoreFirstN()));
				if (leaderboard == null) {
					leaderboard = current;
				} else {
					leaderboard.getEntries().addAll(current.getEntries());
					leaderboard.getAthleteEntries().addAll(current.getAthleteEntries());
				}
			}
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return null;
		}
		return leaderboard;
	}

	/**
	 * <p>
	 * Most irritatingly, Strava returns one list of leaderboard entries that is actually two lists
	 * </p>
	 *
	 * <p>
	 * This utility method separates them again.
	 * </p>
	 *
	 * @param leaderboard
	 *            The set of entries to be split out
	 * @param pagingInstruction
	 *            The paging instruction that was sent to Strava API, so we can work out how to split the entries up
	 * @param contextSize
	 *            Number of entries either side of the athlete's to return as context
	 * @return List of athlete entries
	 */
	private List<StravaSegmentLeaderboardEntry> calculateAthleteEntries(final StravaSegmentLeaderboard leaderboard, final Paging pagingInstruction,
			final Integer contextSize) {
		// Get the entries
		final List<StravaSegmentLeaderboardEntry> entries = leaderboard.getEntries();
		if (entries == null) {
			return null;
		}

		final List<StravaSegmentLeaderboardEntry> athleteEntries = new ArrayList<StravaSegmentLeaderboardEntry>();

		// If there are two neighbourhoods, then the first is the overall and the second is the athlete bit
		if (leaderboard.getNeighborhoodCount().intValue() == 2) {
			for (final StravaSegmentLeaderboardEntry entry : entries) {
				if (entry.getNeighborhoodIndex().intValue() == 1) {
					athleteEntries.add(entry);
				}
			}
		}

		// If there is only one neighbourhood, and the athlete has completed the segment, then it must be the athlete one
		if (leaderboard.getNeighborhoodCount().intValue() == 1) {
			if (entries.size() == contextSize.intValue()) {
				boolean foundAthlete = false;
				for (final StravaSegmentLeaderboardEntry entry : entries) {
					if (entry.getAthleteId().equals(super.getToken().getAthlete().getId())) {
						foundAthlete = true;
					}
				}
				if (foundAthlete) {
					athleteEntries.addAll(entries);
				}
			}
		}

		// TODO What if the current athlete is within the main returned leaderboard??

		return athleteEntries;

	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#segmentExplore(StravaMapPoint, StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory,
	 *      StravaClimbCategory)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		final String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," + northeastCorner.getLatitude() + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ northeastCorner.getLongitude();
		return api.segmentExplore(bounds, activityType, minCat, maxCat);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return listAuthenticatedAthleteStarredSegments(null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id) {
		return listStarredSegments(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal) {
		return listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Paging pagingInstruction) {
		return listSegmentEfforts(id, null, null, null, pagingInstruction);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id) {
		return listSegmentEfforts(id, null, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id, final Paging pagingInstruction) {
		return getSegmentLeaderboard(id, null, null, null, null, null, null, pagingInstruction, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id) {
		return getSegmentLeaderboard(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAllAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments() {
		final List<StravaSegment> segments = PagingHandler.handleListAll(new PagingCallback<StravaSegment>() {

			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAuthenticatedAthleteStarredSegments(thisPage);
			}

		});

		//		// TODO Workaround for issue javastrava-api #71 (see https://github.com/danshannon/javastravav3api/issues/71)
		//		if (!this.getToken().hasViewPrivate()) {
		//			final List<StravaSegment> filteredSegments = new ArrayList<StravaSegment>();
		//			for (final StravaSegment segment : segments) {
		//				if (!segment.getPrivateSegment().equals(Boolean.TRUE)) {
		//					filteredSegments.add(segment);
		//				}
		//			}
		//			return filteredSegments;
		//		}
		//		// End of workaround

		return segments;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAllStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listAllStarredSegments(final Integer athleteId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaSegment>() {

			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listStarredSegments(athleteId, thisPage);
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId) {
		return getAllSegmentLeaderboard(segmentId, null, null, null, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender, javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.api.v3.model.reference.StravaLeaderboardDateRange)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		boolean loop = true;
		StravaSegmentLeaderboard leaderboard = null;

		int page = 0;
		while (loop) {
			page++;
			StravaSegmentLeaderboard currentPage;
			try {
				currentPage = getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, new Paging(Integer.valueOf(page),
						StravaConfig.MAX_PAGE_SIZE), Integer.valueOf(2));
			} catch (final UnauthorizedException e) {
				return new StravaSegmentLeaderboard();
			}
			if (currentPage == null) {
				return null; // Activity doesn't exist
			}
			if (currentPage.getEntries().size() < StravaConfig.MAX_PAGE_SIZE.intValue()) {
				loop = false;
			}
			if (page == 1) {
				leaderboard = currentPage;
			} else {
				leaderboard.getEntries().addAll(currentPage.getEntries());
			}
		}
		return leaderboard;

	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAllSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId) {
		return listAllSegmentEfforts(segmentId, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentService#listAllSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		// TODO Workaround for issue javastrava-api #33 (https://github.com/danshannon/javastravav3api/issues/33)
		// TODO Workaround for issue javastrava-api #45 (https://github.com/danshannon/javastravav3api/issues/45)
		// Check if the segment is flagged as hazardous
		final StravaSegment segment = getSegment(segmentId);

		// If the segment is null it doesn't exist, so return null
		if (segment == null) {
			return null;
		}

		// TODO This is the workaround for issue #45
		if (segment.getResourceState() == StravaResourceState.META) {
			return new ArrayList<StravaSegmentEffort>();
		}

		// If the segment is hazardous, return an empty list
		if (segment.getHazardous() == Boolean.TRUE) {
			return new ArrayList<StravaSegmentEffort>();
		}
		// End of workaround
		return PagingHandler.handleListAll(new PagingCallback<StravaSegmentEffort>() {

			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listSegmentEfforts(segmentId, athleteId, startDate, endDate, thisPage);
			}

		});
	}

}
