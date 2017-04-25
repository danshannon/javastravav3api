package javastrava.service.impl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.model.StravaMapPoint;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaSegmentExplorerResponse;
import javastrava.model.StravaSegmentExplorerResponseSegment;
import javastrava.model.StravaSegmentLeaderboard;
import javastrava.model.StravaSegmentLeaderboardEntry;
import javastrava.model.reference.StravaAgeGroup;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaLeaderboardDateRange;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSegmentExplorerActivityType;
import javastrava.model.reference.StravaWeightClass;
import javastrava.service.SegmentService;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;
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
	 * Cache of segment data
	 */
	private final StravaCache<StravaSegment, Integer> segmentCache;

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
		this.segmentCache = new StravaCacheImpl<StravaSegment, Integer>(StravaSegment.class, token);
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
	private List<StravaSegmentLeaderboardEntry> calculateAthleteEntries(final StravaSegmentLeaderboard leaderboard, final Paging pagingInstruction, final Integer contextSize) {
		// Get the entries
		final List<StravaSegmentLeaderboardEntry> entries = leaderboard.getEntries();
		if (entries == null) {
			return null;
		}

		final List<StravaSegmentLeaderboardEntry> athleteEntries = new ArrayList<StravaSegmentLeaderboardEntry>();

		// If there are two neighbourhoods, then the first is the overall and
		// the second is the athlete bit
		if (leaderboard.getNeighborhoodCount().intValue() == 2) {
			for (final StravaSegmentLeaderboardEntry entry : entries) {
				if (entry.getNeighborhoodIndex().intValue() == 1) {
					athleteEntries.add(entry);
				}
			}
		}

		// If there is only one neighbourhood, and the athlete has completed the
		// segment, then it must be the athlete one
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

		// TODO What if the current athlete is within the main returned
		// leaderboard??

		return athleteEntries;

	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		this.segmentCache.removeAll();
	}

	/**
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId) {
		return getAllSegmentLeaderboard(segmentId, null, null, null, null, null, null);
	}

	/**
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		boolean loop = true;
		StravaSegmentLeaderboard leaderboard = new StravaSegmentLeaderboard();

		int page = 0;
		while (loop) {
			page++;
			StravaSegmentLeaderboard currentPage;
			try {
				currentPage = getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, new Paging(Integer.valueOf(page), StravaConfig.MAX_PAGE_SIZE),
						Integer.valueOf(2));
			} catch (final UnauthorizedException e) {
				return new StravaSegmentLeaderboard();
			}
			if (currentPage == null) {
				return null; // Activity doesn't exist
			}
			if ((currentPage.getEntries() == null) || (currentPage.getEntries().size() < StravaConfig.MAX_PAGE_SIZE.intValue())) {
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
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboardAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> {
			return getAllSegmentLeaderboard(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboardAsync(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		return StravaServiceImpl.future(() -> {
			return getAllSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(final Integer segmentId) {
		// If the id is null, return null
		if (segmentId == null) {
			return null;
		}

		// Try to get the segment from cache
		StravaSegment segment = this.segmentCache.get(segmentId);
		if ((segment != null) && (segment.getResourceState() != StravaResourceState.META)) {
			return segment;
		}

		try {
			segment = this.api.getSegment(segmentId);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			segment = PrivacyUtils.privateSegment(segmentId);
		}

		// TODO Workaround for javastrava-api #70
		// If the segment is private and the token doesn't have view_private
		// scope, then return an empty segment
		if ((segment.getResourceState() != StravaResourceState.PRIVATE) && segment.getPrivateSegment().equals(Boolean.TRUE) && !getToken().hasViewPrivate()) {
			segment = PrivacyUtils.privateSegment(segmentId);
		}
		// End of workaround

		// Put the segment in cache and return it
		this.segmentCache.put(segment);
		return segment;

	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegment> getSegmentAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> {
			return getSegment(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId) {
		return getSegmentLeaderboard(segmentId, null);
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final Paging pagingInstruction) {
		return getSegmentLeaderboard(segmentId, null, null, null, null, null, null, pagingInstruction, null);
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer, StravaLeaderboardDateRange, Paging, Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction, final Integer contextEntries) {
		// Check that the paging arguments are valid
		PagingUtils.validatePagingArguments(pagingInstruction);

		// Check that the segment is valid
		final StravaSegment segment = getSegment(segmentId);

		// If the segment doesn't exist, return null
		if (segment == null) {
			return null;
		}

		StravaSegmentLeaderboard leaderboard = new StravaSegmentLeaderboard();

		// If the segment is private and inaccessible, then don't return a
		// leaderboard
		if (segment.getResourceState() == StravaResourceState.PRIVATE) {
			return PrivacyUtils.privateSegmentLeaderboard();
		}

		// If null, then the default value for contextEntries is 2; the max is
		// 15
		final Integer context = (contextEntries == null ? Integer.valueOf(2) : Integer.valueOf(Math.max(0, Math.min(15, contextEntries.intValue()))));
		final Integer contextSize = Integer.valueOf((context.intValue() * 2) + 1);

		try {
			for (final Paging paging : PagingUtils.convertToStravaPaging(pagingInstruction)) {
				final StravaSegmentLeaderboard current = this.api.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, paging.getPage(), paging.getPageSize(),
						context);
				if (current.getEntries().isEmpty()) {
					current.setAthleteEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
					if (leaderboard.getEntries() == null) {
						leaderboard = current;
					}
					break;
				}
				current.setAthleteEntries(calculateAthleteEntries(current, paging, contextSize));
				current.getEntries().removeAll(current.getAthleteEntries());
				current.setEntries(PagingUtils.ignoreLastN(current.getEntries(), paging.getIgnoreLastN()));
				current.setEntries(PagingUtils.ignoreFirstN(current.getEntries(), paging.getIgnoreFirstN()));
				if (leaderboard.getEntries() == null) {
					leaderboard = current;
				} else {
					leaderboard.getEntries().addAll(current.getEntries());
					leaderboard.getAthleteEntries().addAll(current.getAthleteEntries());
				}
			}
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return PrivacyUtils.privateSegmentLeaderboard();
		} catch (final BadRequestException e) {
			return null;
		}
		leaderboard.setResourceState(StravaResourceState.DETAILED);
		return leaderboard;
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> {
			return getSegmentLeaderboard(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return getSegmentLeaderboard(segmentId, pagingInstruction);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange, javastrava.util.Paging,
	 *      java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction,
			final Integer contextEntries) {
		return StravaServiceImpl.future(() -> {
			return getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, pagingInstruction, contextEntries);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAllAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments() {
		final List<StravaSegment> segments = PagingHandler.handleListAll(thisPage -> listAuthenticatedAthleteStarredSegments(thisPage));

		// // TODO Workaround for issue javastrava-api #71 (see
		// https://github.com/danshannon/javastravav3api/issues/71)
		// if (!this.getToken().hasViewPrivate()) {
		// final List<StravaSegment> filteredSegments = new
		// ArrayList<StravaSegment>();
		// for (final StravaSegment segment : segments) {
		// if (!segment.getPrivateSegment().equals(Boolean.TRUE)) {
		// filteredSegments.add(segment);
		// }
		// }
		// return filteredSegments;
		// }
		// // End of workaround

		return segments;
	}

	/**
	 * @see javastrava.service.SegmentService#listAllAuthenticatedAthleteStarredSegmentsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAllAuthenticatedAthleteStarredSegmentsAsync() {
		return StravaServiceImpl.future(() -> {
			return listAllAuthenticatedAthleteStarredSegments();
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAllSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId) {
		return listAllSegmentEfforts(segmentId, null, null, null);
	}

	/**
	 * @see javastrava.service.SegmentService#listAllSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		// TODO Workaround for issue javastrava-api #33
		// (https://github.com/danshannon/javastravav3api/issues/33)
		// TODO Workaround for issue javastrava-api #45
		// (https://github.com/danshannon/javastravav3api/issues/45)
		// Check if the segment is flagged as hazardous
		final StravaSegment segment = getSegment(segmentId);
		System.out.println(segment);

		// If the segment is null it doesn't exist, so return null
		if (segment == null) {
			return null;
		}

		final int parallelism = StravaConfig.PAGING_LIST_ALL_PARALLELISM;

		// TODO This is the workaround for issue #45
		if (segment.getResourceState() == StravaResourceState.META) {
			return new ArrayList<StravaSegmentEffort>();
		}

		// If the segment is hazardous, return an empty list
		if (segment.getHazardous() == Boolean.TRUE) {
			return new ArrayList<StravaSegmentEffort>();
		}
		// End of workaround
		return PagingHandler.handleListAll(thisPage -> listSegmentEfforts(segmentId, athleteId, startDate, endDate, thisPage), parallelism);
	}

	/**
	 * @see javastrava.service.SegmentService#listAllSegmentEffortsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> {
			return listAllSegmentEfforts(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAllSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		return StravaServiceImpl.future(() -> {
			return listAllSegmentEfforts(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAllStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listAllStarredSegments(final Integer athleteId) {
		return PagingHandler.handleListAll(thisPage -> listStarredSegments(athleteId, thisPage));
	}

	/**
	 * @see javastrava.service.SegmentService#listAllStarredSegmentsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAllStarredSegmentsAsync(final Integer athleteId) {
		return StravaServiceImpl.future(() -> {
			return listAllStarredSegments(athleteId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return listAuthenticatedAthleteStarredSegments(null);
	}

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments(Paging)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		final List<StravaSegment> segments = PagingHandler.handlePaging(pagingInstruction,
				thisPage -> Arrays.asList(SegmentServiceImpl.this.api.listAuthenticatedAthleteStarredSegments(thisPage.getPage(), thisPage.getPageSize())));

		// TODO This is a workaround for issue javastrava-api #81
		// (https://github.com/danshannon/javastravav3api/issues/81)
		if (segments != null) {
			for (final StravaSegment segment : segments) {
				if ((segment.getAthletePrEffort() != null) && (segment.getAthletePrEffort().getResourceState() == null)) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return PrivacyUtils.handlePrivateSegments(segments, this.getToken());
	}

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegmentsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync() {
		return StravaServiceImpl.future(() -> {
			return listAuthenticatedAthleteStarredSegments();
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegmentsAsync(javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync(final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listAuthenticatedAthleteStarredSegments(pagingInstruction);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId) {
		return listSegmentEfforts(segmentId, null, null, null, null);
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal) {
		return listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal, null);
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(Integer, Integer, LocalDateTime, LocalDateTime, Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocalTZ, final LocalDateTime endDateLocalTZ,
			final Paging pagingInstruction) {
		// TODO Workaround for issue javastrava-api #33
		// (https://github.com/danshannon/javastravav3api/issues/33)
		// Check if the segment is flagged as hazardous
		final StravaSegment segment = getSegment(segmentId);

		// If the segment is null it doesn't exist, so return null
		if (segment == null) {
			return null;
		}

		// TODO This is the workaround for issue #45
		if (segment.getResourceState() == StravaResourceState.PRIVATE) {
			return new ArrayList<StravaSegmentEffort>();
		}

		// If the segment is hazardous, return an empty list
		if (segment.getHazardous() == Boolean.TRUE) {
			return new ArrayList<StravaSegmentEffort>();
		}
		// End of workaround

		LocalDateTime endDateLocal = endDateLocalTZ;
		LocalDateTime startDateLocal = startDateLocalTZ;

		// If start date is set, but end date isn't, then Strava likes it to be
		// set to something high
		if ((endDateLocalTZ == null) && (startDateLocalTZ != null)) {
			endDateLocal = LocalDateTime.of(9999, Month.DECEMBER, 31, 23, 59, 59);
		}

		// Similarly if the end date is set but start date isn't, Strava likes
		// it to be set to something low
		if ((startDateLocalTZ == null) && (endDateLocalTZ != null)) {
			startDateLocal = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0, 0);
		}

		final String start = (startDateLocal == null ? null : startDateLocal.toString());
		final String end = (endDateLocal == null ? null : endDateLocal.toString());

		final List<StravaSegmentEffort> efforts;

		try {
			efforts = PagingHandler.handlePaging(pagingInstruction,
					thisPage -> Arrays.asList(SegmentServiceImpl.this.api.listSegmentEfforts(segmentId, athleteId, start, end, thisPage.getPage(), thisPage.getPageSize())));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaSegmentEffort>();
		}

		return PrivacyUtils.handlePrivateSegmentEfforts(efforts, this.getToken());
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Paging pagingInstruction) {
		return listSegmentEfforts(segmentId, null, null, null, pagingInstruction);
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId) {
		return StravaServiceImpl.future(() -> {
			return listSegmentEfforts(segmentId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal,
			final LocalDateTime endDateLocal) {
		return StravaServiceImpl.future(() -> {
			return listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal,
			final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal, pagingInstruction);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listSegmentEfforts(segmentId, pagingInstruction);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer athleteId) {
		return listStarredSegments(athleteId, null);
	}

	/**
	 * @see javastrava.service.SegmentService#listStarredSegments(Integer, Paging)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer athleteId, final Paging pagingInstruction) {
		final List<StravaSegment> segments;

		try {
			segments = PagingHandler.handlePaging(pagingInstruction, thisPage -> Arrays.asList(SegmentServiceImpl.this.api.listStarredSegments(athleteId, thisPage.getPage(), thisPage.getPageSize())));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaSegment>();
		}

		// TODO This is a workaround for issue javastrava-api #25
		// (https://github.com/danshannon/javastravav3api/issues/25)
		if (segments != null) {
			for (final StravaSegment segment : segments) {
				if ((segment.getAthletePrEffort() != null) && (segment.getAthletePrEffort().getResourceState() == null)) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return PrivacyUtils.handlePrivateSegments(segments, this.getToken());
	}

	/**
	 * @see javastrava.service.SegmentService#listStarredSegmentsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId) {
		return StravaServiceImpl.future(() -> {
			return listStarredSegments(athleteId);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#listStarredSegmentsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId, final Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listStarredSegments(athleteId, pagingInstruction);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#segmentExplore(StravaMapPoint, StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory, StravaClimbCategory)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner, final StravaSegmentExplorerActivityType activityType,
			final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		final String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," //$NON-NLS-1$ //$NON-NLS-2$
				+ northeastCorner.getLatitude() + "," //$NON-NLS-1$
				+ northeastCorner.getLongitude();
		final StravaSegmentExplorerResponse response = this.api.segmentExplore(bounds, activityType, minCat, maxCat);
		for (final StravaSegmentExplorerResponseSegment segment : response.getSegments()) {
			segment.setResourceState(StravaResourceState.SUMMARY);
		}
		return response;
	}

	/**
	 * @see javastrava.service.SegmentService#segmentExploreAsync(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint,
	 *      javastrava.model.reference.StravaSegmentExplorerActivityType, javastrava.model.reference.StravaClimbCategory, javastrava.model.reference.StravaClimbCategory)
	 */
	@Override
	public CompletableFuture<StravaSegmentExplorerResponse> segmentExploreAsync(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		return StravaServiceImpl.future(() -> {
			return segmentExplore(southwestCorner, northeastCorner, activityType, minCat, maxCat);
		});
	}

	/**
	 * @see javastrava.service.SegmentService#starSegment(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public StravaSegment starSegment(Integer segmentId, Boolean starred) {
		// If the id is null, return null
		if (segmentId == null) {
			return null;
		}

		try {
			return this.api.starSegment(segmentId, starred);
		} catch (final UnauthorizedException e) {
			final StravaSegment segment = new StravaSegment();
			segment.setId(segmentId);
			segment.setResourceState(StravaResourceState.PRIVATE);
			return segment;
		} catch (final NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see javastrava.service.SegmentService#starSegmentAsync(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public CompletableFuture<StravaSegment> starSegmentAsync(Integer segmentId, Boolean starred) {
		return StravaServiceImpl.future(() -> {
			return starSegment(segmentId, starred);
		});
	}

}
