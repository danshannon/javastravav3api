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

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.model.StravaMapPoint;
import stravajava.api.v3.model.StravaSegment;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.StravaSegmentExplorerResponse;
import stravajava.api.v3.model.StravaSegmentLeaderboard;
import stravajava.api.v3.model.reference.StravaAgeGroup;
import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.model.reference.StravaLeaderboardDateRange;
import stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import stravajava.api.v3.model.reference.StravaWeightClass;
import stravajava.api.v3.service.SegmentServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author danshannon
 *
 */
public class SegmentServicesImpl implements SegmentServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private SegmentServicesImpl(SegmentServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * <p>Returns an implementation of {@link SegmentServices gear services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 */
	public static SegmentServices implementation(final String token) {
		SegmentServices restService = restServices.get(token);
		if (restService == null) {
			restService = new SegmentServicesImpl(new RestAdapter.Builder()
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				.setLogLevel(LOG_LEVEL)
				.setEndpoint(Strava.ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				.setErrorHandler(new RetrofitErrorHandler())
				.build()
				.create(SegmentServicesRetrofit.class));

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,SegmentServices> restServices = new HashMap<String,SegmentServices>();
	
	private SegmentServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(Integer id) throws UnauthorizedException {
		try {
			return restService.getSegment(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		List<StravaSegment> segments = new ArrayList<StravaSegment>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<StravaSegment> segmentPage = Arrays.asList(restService.listAuthenticatedAthleteStarredSegments(paging.getPage(), paging.getPageSize()));
			if (segmentPage.size() == 0) { break; }
			segmentPage = Strava.ignoreLastN(segmentPage, paging.getIgnoreLastN());
			segmentPage = Strava.ignoreFirstN(segmentPage, paging.getIgnoreFirstN());
			segments.addAll(segmentPage);
		}
		if (pagingInstruction != null) {
			segments = Strava.ignoreLastN(segments, pagingInstruction.getIgnoreLastN());
			segments = Strava.ignoreFirstN(segments, pagingInstruction.getIgnoreFirstN());
		}
		return segments;
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaSegment> segments = new ArrayList<StravaSegment>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaSegment> segmentPage = Arrays.asList(restService.listStarredSegments(id, paging.getPage(), paging.getPageSize()));
				if (segmentPage.size() == 0) { break; }
				segmentPage = Strava.ignoreLastN(segmentPage, paging.getIgnoreLastN());
				segmentPage = Strava.ignoreFirstN(segmentPage, paging.getIgnoreFirstN());
				segments.addAll(segmentPage);
			}
			if (pagingInstruction != null) {
				segments = Strava.ignoreLastN(segments, pagingInstruction.getIgnoreLastN());
				segments = Strava.ignoreFirstN(segments, pagingInstruction.getIgnoreFirstN());
			}
		} catch (NotFoundException e) {
			// StravaSegment doesn't exist
			return null;
		}
		return segments;

	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal,
			Calendar endDateLocal, Paging pagingInstruction) {
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
		
		Date startDate = (startDateLocal == null ? null : startDateLocal.getTime());
		Date endDate = (endDateLocal == null ? null : endDateLocal.getTime());
		
		// Check that the paging instruction is OK
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaSegmentEffort> efforts = new ArrayList<StravaSegmentEffort>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaSegmentEffort> effortPage = Arrays.asList(restService.listSegmentEfforts(id, athleteId, startDate, endDate, paging.getPage(), paging.getPageSize()));
				if (effortPage.size() == 0) { break; }
				effortPage = Strava.ignoreLastN(effortPage, paging.getIgnoreLastN());
				effortPage = Strava.ignoreFirstN(effortPage, paging.getIgnoreFirstN());
				efforts.addAll(effortPage);
			}
			if (pagingInstruction != null) {
				efforts = Strava.ignoreLastN(efforts, pagingInstruction.getIgnoreLastN());
				efforts = Strava.ignoreFirstN(efforts, pagingInstruction.getIgnoreFirstN());
			}
		} catch (NotFoundException e) {
			// StravaSegment doesn't exist
			return null;
		}
		return efforts;
	}
	
	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, stravajava.api.v3.model.reference.StravaGender, stravajava.api.v3.model.reference.StravaAgeGroup, stravajava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, stravajava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(Integer id, StravaGender gender, StravaAgeGroup ageGroup,
			StravaWeightClass weightClass, Boolean following, Integer clubId, StravaLeaderboardDateRange dateRange, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		StravaSegmentLeaderboard leaderboard = null;
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				StravaSegmentLeaderboard current = restService.getSegmentLeaderboard(id, gender, ageGroup, weightClass, following, clubId, dateRange, paging.getPage(), paging.getPageSize());
				if (current.getEntries().isEmpty()) { break; }
				current.setEntries(Strava.ignoreLastN(current.getEntries(), paging.getIgnoreLastN()));
				current.setEntries(Strava.ignoreFirstN(current.getEntries(), paging.getIgnoreFirstN()));
				if (leaderboard == null) {
					leaderboard = current;
				} else {
					leaderboard.getEntries().addAll(current.getEntries());
				}
			}
			if (pagingInstruction != null) {
				leaderboard.setEntries(Strava.ignoreLastN(leaderboard.getEntries(), pagingInstruction.getIgnoreLastN()));
				leaderboard.setEntries(Strava.ignoreFirstN(leaderboard.getEntries(), pagingInstruction.getIgnoreFirstN()));
			}
		} catch (NotFoundException e) {
			return null;
		}
		return leaderboard;
	}

	/**
	 * @see stravajava.api.v3.service.SegmentServices#segmentExplore(stravajava.api.v3.model.StravaMapPoint, stravajava.api.v3.model.StravaMapPoint, stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(StravaMapPoint southwestCorner, StravaMapPoint northeastCorner,
			StravaSegmentExplorerActivityType activityType, StravaClimbCategory minCat, StravaClimbCategory maxCat) {
		String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," + northeastCorner.getLatitude() + "," + northeastCorner.getLongitude();
		return restService.segmentExplore(bounds, activityType, minCat, maxCat);
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
	public List<StravaSegment> listStarredSegments(Integer id) {
		return listStarredSegments(id, null);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal,
			Calendar endDateLocal) {
		return listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, null);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(Integer id, Paging pagingInstruction) {
		return listSegmentEfforts(id, null, null, null, pagingInstruction);
	}

	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(Integer id) {
		return listSegmentEfforts(id, null, null, null, null);
	}

	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(Integer id, Paging pagingInstruction) {
		return getSegmentLeaderboard(id, null, null, null, null, null, null, pagingInstruction);
	}

	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(Integer id) {
		return getSegmentLeaderboard(id, null);
	}

}
