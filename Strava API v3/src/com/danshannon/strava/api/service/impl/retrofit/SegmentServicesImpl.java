/**
 * 
 */
package com.danshannon.strava.api.service.impl.retrofit;

import java.util.Arrays;
import java.util.Calendar;
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
		return Arrays.asList(restService.listAuthenticatedAthleteStarredSegments(pagingInstruction.getPage(),pagingInstruction.getPageSize()));
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Segment> listStarredSegments(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		try {
			return Arrays.asList(restService.listStarredSegments(id, pagingInstruction.getPage(), pagingInstruction.getPageSize()));
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal,
			Calendar endDateLocal, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		try {
			return Arrays.asList(restService.listSegmentEfforts(id, athleteId, startDateLocal, endDateLocal, pagingInstruction.getPage(), pagingInstruction.getPageSize()));
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, com.danshannon.strava.api.model.reference.Gender, com.danshannon.strava.api.model.reference.AgeGroup, com.danshannon.strava.api.model.reference.WeightClass, java.lang.Boolean, java.lang.Integer, com.danshannon.strava.api.model.reference.LeaderboardDateRange, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public SegmentLeaderboard getSegmentLeaderboard(Integer id, Gender gender, AgeGroup ageGroup,
			WeightClass weightClass, Boolean following, Integer clubId, LeaderboardDateRange dateRange, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		try {
			return restService.getSegmentLeaderboard(id, gender, ageGroup, weightClass, following, clubId, dateRange, pagingInstruction.getPage(), pagingInstruction.getPageSize());
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.SegmentServices#segmentExplore(com.danshannon.strava.api.model.MapPoint, com.danshannon.strava.api.model.MapPoint, com.danshannon.strava.api.model.reference.SegmentExplorerActivityType, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public SegmentExplorer segmentExplore(MapPoint southwestCorner, MapPoint northwestCorner,
			SegmentExplorerActivityType activityType, Integer minCat, Integer maxCat) {
		return restService.segmentExplore(southwestCorner, northwestCorner, activityType, minCat, maxCat);
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

}
