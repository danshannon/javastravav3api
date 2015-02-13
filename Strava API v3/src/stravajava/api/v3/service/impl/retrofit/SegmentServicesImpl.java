/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaMapPoint;
import stravajava.api.v3.model.StravaSegment;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.StravaSegmentExplorerResponse;
import stravajava.api.v3.model.StravaSegmentLeaderboard;
import stravajava.api.v3.model.StravaSegmentLeaderboardEntry;
import stravajava.api.v3.model.reference.StravaAgeGroup;
import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.model.reference.StravaLeaderboardDateRange;
import stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import stravajava.api.v3.model.reference.StravaWeightClass;
import stravajava.api.v3.service.PagingCallback;
import stravajava.api.v3.service.PagingHandler;
import stravajava.api.v3.service.SegmentServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.util.Paging;

/**
 * @author danshannon
 *
 */
public class SegmentServicesImpl extends StravaServiceImpl implements SegmentServices {
	private SegmentServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(SegmentServicesRetrofit.class, token, SegmentServicesRetrofit.LOG_LEVEL);
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
	public static SegmentServices implementation(final String token) {
		SegmentServices restService = restServices.get(token);
		if (restService == null) {
			restService = new SegmentServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<String, SegmentServices> restServices = new HashMap<String, SegmentServices>();

	final SegmentServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(final Integer id) {
		try {
			return this.restService.getSegment(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listAuthenticatedAthleteStarredSegments(thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegment>() {
			@Override
			public List<StravaSegment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listStarredSegments(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final Calendar startDateLocalTZ,
			final Calendar endDateLocalTZ, final Paging pagingInstruction) {
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

		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaSegmentEffort>() {
			@Override
			public List<StravaSegmentEffort> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(SegmentServicesImpl.this.restService.listSegmentEfforts(id, athleteId, startDate, endDate, thisPage.getPage(),
						thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, stravajava.api.v3.model.reference.StravaGender,
	 *      stravajava.api.v3.model.reference.StravaAgeGroup, stravajava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      stravajava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange,
			final Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		StravaSegmentLeaderboard leaderboard = null;
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				StravaSegmentLeaderboard current = this.restService.getSegmentLeaderboard(id, gender, ageGroup, weightClass, following, clubId, dateRange,
						paging.getPage(), paging.getPageSize());
				if (current.getEntries().isEmpty()) {
					break;
				}
				current.setAthleteEntries(splitOutAthleteEntries(current.getEntries(), paging));
				current.getEntries().removeAll(current.getAthleteEntries());
				current.setEntries(Strava.ignoreLastN(current.getEntries(), paging.getIgnoreLastN()));
				current.setEntries(Strava.ignoreFirstN(current.getEntries(), paging.getIgnoreFirstN()));
				if (leaderboard == null) {
					leaderboard = current;
				} else {
					leaderboard.getEntries().addAll(current.getEntries());
					leaderboard.getAthleteEntries().addAll(current.getAthleteEntries());
				}
			}
		} catch (NotFoundException e) {
			return null;
		}
		return leaderboard;
	}

	private List<StravaSegmentLeaderboardEntry> splitOutAthleteEntries(final List<StravaSegmentLeaderboardEntry> entries, final Paging pagingInstruction) {
		// Handle the spurious extra 5 that Strava sometimes throws in for good measure
		int firstPosition = 0;
		boolean inSequence = true;
		int rank = 0;
		int firstAthletePosition = 0;
		List<StravaSegmentLeaderboardEntry> athleteEntries = new ArrayList<StravaSegmentLeaderboardEntry>();
		
		// If there are EXACTLY 5 entries, then they are athlete entries if they're not in the expected range
		if (entries.size() == 5) {
			int firstRank = entries.get(0).getRank();
			int lastRank = entries.get(0).getRank();
			int expectedFirstRank = (pagingInstruction.getPage() - 1) * pagingInstruction.getPageSize() + 1;
			int expectedLastRank = (pagingInstruction.getPage() * pagingInstruction.getPageSize());
			if (firstRank > expectedLastRank || lastRank < expectedFirstRank) {
				athleteEntries.addAll(entries);
			}
		} else {

			// Otherwise, they are athlete entries if the ranks are too high
			for (int position = 0; position < entries.size(); position++) {
				rank = entries.get(position).getRank();
				if (firstPosition == 0) {
					firstPosition = rank;
				}
				if (rank > firstPosition + position && inSequence) {
					inSequence = false;
					if (firstAthletePosition == 0) {
						firstAthletePosition = position;
					}
				}
				if (!inSequence) {
					athleteEntries.add(entries.get(position));
				}
			}
		}
		return athleteEntries;

	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#segmentExplore(stravajava.api.v3.model.StravaMapPoint, stravajava.api.v3.model.StravaMapPoint,
	 *      stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," + northeastCorner.getLatitude() + ","
				+ northeastCorner.getLongitude();
		return this.restService.segmentExplore(bounds, activityType, minCat, maxCat);
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return listAuthenticatedAthleteStarredSegments(null);
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer id) {
		return listStarredSegments(id, null);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Integer athleteId, final Calendar startDateLocal, final Calendar endDateLocal) {
		return listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, null);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id, final Paging pagingInstruction) {
		return listSegmentEfforts(id, null, null, null, pagingInstruction);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer id) {
		return listSegmentEfforts(id, null, null, null, null);
	}

	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id, final Paging pagingInstruction) {
		return getSegmentLeaderboard(id, null, null, null, null, null, null, pagingInstruction);
	}

	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer id) {
		return getSegmentLeaderboard(id, null);
	}

}
