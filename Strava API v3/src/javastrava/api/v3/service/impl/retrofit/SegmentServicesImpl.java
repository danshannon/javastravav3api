/**
 * 
 */
package javastrava.api.v3.service.impl.retrofit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import javastrava.api.v3.service.PagingCallback;
import javastrava.api.v3.service.PagingHandler;
import javastrava.api.v3.service.SegmentServices;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.util.Paging;

/**
 * <p>
 * Implementation of {@link SegmentServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class SegmentServicesImpl extends StravaServiceImpl<SegmentServicesRetrofit> implements SegmentServices {
	/**
	 * <p>
	 * Private constructor ensures that the only way to get an instance is via {@link #implementation(Token)} with a valid access token
	 * </p>
	 * 
	 * @param token
	 *            The access token to use for authentication with the Strava API
	 */
	private SegmentServicesImpl(final Token token) {
		super(SegmentServicesRetrofit.class, token);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link SegmentServices gear services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 */
	public static SegmentServices implementation(final Token token) {
		if (token == null) {
			throw new IllegalArgumentException(Messages.string("SegmentServicesImpl.cannotInstantiateWithNullToken")); //$NON-NLS-1$
		}
		Class<SegmentServices> class1 = SegmentServices.class;
		// Get the service from the token's cache
		SegmentServices service = token.getService(class1);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new SegmentServicesImpl(token);
			token.addService(class1, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(final Integer id) {
		try {
			return this.restService.getSegment(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				StravaSegment segment = new StravaSegment();
				segment.setId(id);
				segment.setResourceState(StravaResourceState.META);
				return segment;
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments(Paging)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		List<StravaSegment> segments = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listAuthenticatedAthleteStarredSegments(thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		// TODO This is a workaround for issue javastrava-api #25 (https://github.com/danshannon/javastravav3api/issues/25)
		if (segments != null) {
			for (StravaSegment segment : segments) {
				if (segment.getAthletePrEffort() != null && segment.getAthletePrEffort().getResourceState() == null) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return segments;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listStarredSegments(Integer, Paging)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id, final Paging pagingInstruction) {
		List<StravaSegment> segments = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listStarredSegments(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

		// TODO This is a workaround for issue javastrava-api #25 (https://github.com/danshannon/javastravav3api/issues/25)
		if (segments != null) {
			for (StravaSegment segment : segments) {
				if (segment.getAthletePrEffort() != null && segment.getAthletePrEffort().getResourceState() == null) {
					segment.getAthletePrEffort().setResourceState(StravaResourceState.SUMMARY);
				}
			}
		}

		return segments;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listSegmentEfforts(Integer, Integer, Calendar, Calendar, Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final Calendar startDateLocalTZ,
			final Calendar endDateLocalTZ, final Paging pagingInstruction) {
		// TODO Workaround for issue javastrava-api #33 (https://github.com/danshannon/javastravav3api/issues/33)
		// Check if the segment is flagged as hazardous
		StravaSegment segment = getSegment(id);

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

		Calendar endDateLocal = endDateLocalTZ;
		Calendar startDateLocal = startDateLocalTZ;
		// If start date is set, but end date isn't, then Strava likes it to be set to something high
		if (endDateLocal == null && startDateLocal != null) {
			endDateLocal = Calendar.getInstance();
			endDateLocal.set(9999, Calendar.DECEMBER, 31);
		}

		// Similarly if the end date is set but start date isn't, Strava likes it to be set to something low
		if (startDateLocal == null && endDateLocal != null) {
			startDateLocal = Calendar.getInstance();
			startDateLocal.set(1900, Calendar.JANUARY, 1);
		}

		final Date startDate = (startDateLocal == null ? null : startDateLocal.getTime());
		final Date endDate = (endDateLocal == null ? null : endDateLocal.getTime());

		final String start = (startDate == null ? null : new SimpleDateFormat(StravaConfig.DATE_FORMAT).format(startDate));
		final String end = (endDate == null ? null : new SimpleDateFormat(StravaConfig.DATE_FORMAT).format(endDate));
		List<StravaSegmentEffort> efforts = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegmentEffort>() {
			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listSegmentEfforts(id, athleteId, start, end, thisPage.getPage(),
						thisPage.getPageSize()));
			}
		});

		// TODO workaround for issue javastrava-api #19 (https://github.com/danshannon/javastravav3api/issues/19)
		if (efforts != null) {
			for (StravaSegmentEffort effort : efforts) {
				if (effort.getActivity().getResourceState() == null) {
					effort.getActivity().setResourceState(StravaResourceState.META);
				}
			}
		}
		// End of workaround

		// TODO workaround for issue javastrava-api #20 (https://github.com/danshannon/javastravav3api/issues/20)
		if (efforts != null) {
			for (StravaSegmentEffort effort : efforts) {
				if (effort.getAthlete().getResourceState() == null) {
					effort.getAthlete().setResourceState(StravaResourceState.META);
				}
			}
		}
		// End of workaround

		return efforts;
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer,
	 *      StravaLeaderboardDateRange, Paging, Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange,
			final Paging pagingInstruction, final Integer contextEntries) {
		StravaConfig.validatePagingArguments(pagingInstruction);

		StravaSegmentLeaderboard leaderboard = null;

		// If null, then the default value for contextEntries is 2; the max is 15
		Integer context = (contextEntries == null ? Integer.valueOf(2) : Integer.valueOf(Math.max(0, Math.min(15, contextEntries.intValue()))));
		Integer contextSize = Integer.valueOf(context.intValue() * 2 + 1);

		// TODO This is a workaround for issue javastrava-api #23 (https://github.com/danshannon/javastravav3api/issues/23) - see also the workaround in
		// SegmentServicesRetrofit
		if (clubId != null) {
			try {
				this.restService.getClub(clubId);
			} catch (NotFoundException e) {
				// Club doesn't exist, so return null
				return null;
			}
		}
		// End of workaround

		try {
			for (Paging paging : StravaConfig.convertToStravaPaging(pagingInstruction)) {
				StravaSegmentLeaderboard current = this.restService.getSegmentLeaderboard(id, gender, ageGroup, weightClass, following, clubId, dateRange,
						paging.getPage(), paging.getPageSize(), context);
				if (current.getEntries().isEmpty()) {
					break;
				}
				current.setAthleteEntries(calculateAthleteEntries(current, paging, contextSize));
				current.getEntries().removeAll(current.getAthleteEntries());
				current.setEntries(StravaConfig.ignoreLastN(current.getEntries(), paging.getIgnoreLastN()));
				current.setEntries(StravaConfig.ignoreFirstN(current.getEntries(), paging.getIgnoreFirstN()));
				if (leaderboard == null) {
					leaderboard = current;
				} else {
					leaderboard.getEntries().addAll(current.getEntries());
					leaderboard.getAthleteEntries().addAll(current.getAthleteEntries());
				}
			}
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
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
		List<StravaSegmentLeaderboardEntry> entries = leaderboard.getEntries();
		if (entries == null) {
			return null;
		}

		List<StravaSegmentLeaderboardEntry> athleteEntries = new ArrayList<StravaSegmentLeaderboardEntry>();

		// If there are two neighbourhoods, then the first is the overall and the second is the athlete bit
		if (leaderboard.getNeighborhoodCount().intValue() == 2) {
			for (StravaSegmentLeaderboardEntry entry : entries) {
				if (entry.getNeighborhoodIndex().intValue() == 1) {
					athleteEntries.add(entry);
				}
			}
		}

		// If there is only one neighbourhood, and the athlete has completed the segment, then it must be the athlete one
		if (leaderboard.getNeighborhoodCount().intValue() == 1) {
			if (entries.size() == contextSize.intValue()) {
				boolean foundAthlete = false;
				for (StravaSegmentLeaderboardEntry entry : entries) {
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
	 * @see javastrava.api.v3.service.SegmentServices#segmentExplore(StravaMapPoint, StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory,
	 *      StravaClimbCategory)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," + northeastCorner.getLatitude() + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ northeastCorner.getLongitude();
		return this.restService.segmentExplore(bounds, activityType, minCat, maxCat);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return listAuthenticatedAthleteStarredSegments(null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id) {
		return listStarredSegments(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final Calendar startDateLocal, final Calendar endDateLocal) {
		return listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Paging pagingInstruction) {
		return listSegmentEfforts(id, null, null, null, pagingInstruction);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id) {
		return listSegmentEfforts(id, null, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id, final Paging pagingInstruction) {
		return getSegmentLeaderboard(id, null, null, null, null, null, null, pagingInstruction, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id) {
		return getSegmentLeaderboard(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listAllAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments() {
		return PagingHandler.handleListAll(new PagingCallback<StravaSegment>() {

			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAuthenticatedAthleteStarredSegments(thisPage);
			}

		});
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listAllStarredSegments(java.lang.Integer)
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
	 * @see javastrava.api.v3.service.SegmentServices#getAllSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId) {
		return getAllSegmentLeaderboard(segmentId, null, null, null, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#getAllSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender, javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.api.v3.model.reference.StravaLeaderboardDateRange)
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
			} catch (UnauthorizedException e) {
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
	 * @see javastrava.api.v3.service.SegmentServices#listAllSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId) {
		return listAllSegmentEfforts(segmentId, null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.SegmentServices#listAllSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final Calendar startDate, final Calendar endDate) {
		// TODO Workaround for issue javastrava-api #33 (https://github.com/danshannon/javastravav3api/issues/33)
		// TODO Workaround for issue javastrava-api #45 (https://github.com/danshannon/javastravav3api/issues/45)
		// Check if the segment is flagged as hazardous
		StravaSegment segment = getSegment(segmentId);
		
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
