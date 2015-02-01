/**
 * 
 */
package com.danshannon.strava.api.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.MapPoint;
import com.danshannon.strava.api.model.Segment;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.SegmentExplorer;
import com.danshannon.strava.api.model.SegmentLeaderboard;
import com.danshannon.strava.api.model.reference.AgeGroup;
import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.model.reference.LeaderboardDateRange;
import com.danshannon.strava.api.model.reference.SegmentExplorerActivityType;
import com.danshannon.strava.api.model.reference.WeightClass;
import com.danshannon.strava.api.service.SegmentServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

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
	 * @see com.danshannon.strava.api.service.SegmentServices#getSegment(java.lang.Integer)
	 */
	@Override
	public Segment getSegment(Integer id) throws UnauthorizedException {
		try {
			return restService.getSegment(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Segment> listAuthenticatedAthleteStarredSegments(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		List<Segment> segments = new ArrayList<Segment>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Segment> segmentPage = Arrays.asList(restService.listAuthenticatedAthleteStarredSegments(paging.getPage(), paging.getPageSize()));
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
	 * @see com.danshannon.strava.api.service.SegmentServices#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Segment> listStarredSegments(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<Segment> segments = new ArrayList<Segment>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<Segment> segmentPage = Arrays.asList(restService.listStarredSegments(id, paging.getPage(), paging.getPageSize()));
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
			// Segment doesn't exist
			return null;
		}
		return segments;

	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal,
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
		
		List<SegmentEffort> efforts = new ArrayList<SegmentEffort>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<SegmentEffort> effortPage = Arrays.asList(restService.listSegmentEfforts(id, athleteId, startDate, endDate, paging.getPage(), paging.getPageSize()));
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
			// Segment doesn't exist
			return null;
		}
		return efforts;
	}
	
	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, com.danshannon.strava.api.model.reference.Gender, com.danshannon.strava.api.model.reference.AgeGroup, com.danshannon.strava.api.model.reference.WeightClass, java.lang.Boolean, java.lang.Integer, com.danshannon.strava.api.model.reference.LeaderboardDateRange, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public SegmentLeaderboard getSegmentLeaderboard(Integer id, Gender gender, AgeGroup ageGroup,
			WeightClass weightClass, Boolean following, Integer clubId, LeaderboardDateRange dateRange, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		SegmentLeaderboard leaderboard = null;
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				SegmentLeaderboard current = restService.getSegmentLeaderboard(id, gender, ageGroup, weightClass, following, clubId, dateRange, paging.getPage(), paging.getPageSize());
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
	 * @see com.danshannon.strava.api.service.SegmentServices#segmentExplore(com.danshannon.strava.api.model.MapPoint, com.danshannon.strava.api.model.MapPoint, com.danshannon.strava.api.model.reference.SegmentExplorerActivityType, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public SegmentExplorer segmentExplore(MapPoint southwestCorner, MapPoint northeastCorner,
			SegmentExplorerActivityType activityType, ClimbCategory minCat, ClimbCategory maxCat) {
		String bounds = southwestCorner.getLatitude() + "," + southwestCorner.getLongitude() + "," + northeastCorner.getLatitude() + "," + northeastCorner.getLongitude();
		return restService.segmentExplore(bounds, activityType, minCat, maxCat);
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<Segment> listAuthenticatedAthleteStarredSegments() {
		return listAuthenticatedAthleteStarredSegments(null);
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<Segment> listStarredSegments(Integer id) {
		return listStarredSegments(id, null);
	}

	@Override
	public List<SegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal,
			Calendar endDateLocal) {
		return listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, null);
	}

	@Override
	public List<SegmentEffort> listSegmentEfforts(Integer id, Paging pagingInstruction) {
		return listSegmentEfforts(id, null, null, null, pagingInstruction);
	}

	@Override
	public List<SegmentEffort> listSegmentEfforts(Integer id) {
		return listSegmentEfforts(id, null, null, null, null);
	}

	@Override
	public SegmentLeaderboard getSegmentLeaderboard(Integer id, Paging pagingInstruction) {
		return getSegmentLeaderboard(id, null, null, null, null, null, null, pagingInstruction);
	}

	@Override
	public SegmentLeaderboard getSegmentLeaderboard(Integer id) {
		return getSegmentLeaderboard(id, null);
	}

}
