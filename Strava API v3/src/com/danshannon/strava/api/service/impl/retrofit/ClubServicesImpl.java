/**
 * 
 */
package com.danshannon.strava.api.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.ClubMembershipResponse;
import com.danshannon.strava.api.service.ClubServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author danshannon
 *
 */
public class ClubServicesImpl implements ClubServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private ClubServicesImpl(ClubServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * <p>Returns an implementation of {@link ClubServices club services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the club services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static ClubServices implementation(final String token) {
		ClubServices restService = restServices.get(token);
		if (restService == null) {
			restService = new ClubServicesImpl(new RestAdapter.Builder()
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
				.create(ClubServicesRetrofit.class));

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,ClubServices> restServices = new HashMap<String,ClubServices>();
	
	private ClubServicesRetrofit restService;
	

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#getClub(java.lang.Integer)
	 */
	@Override
	public Club getClub(Integer id) throws UnauthorizedException {
		try {
			return restService.getClub(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<Club> listAuthenticatedAthleteClubs() throws UnauthorizedException {
		return Arrays.asList(restService.listAuthenticatedAthleteClubs());
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Athlete> listClubMembers(Integer id, Paging pagingInstruction) throws UnauthorizedException {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<Athlete> members = new ArrayList<Athlete>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<Athlete> memberPage = Arrays.asList(restService.listClubMembers(id, paging.getPage(), paging.getPageSize()));
				memberPage = Strava.ignoreLastN(memberPage, paging.getIgnoreLastN());
				memberPage = Strava.ignoreFirstN(memberPage, paging.getIgnoreFirstN());
				members.addAll(memberPage);
			}
			if (pagingInstruction != null) {
				members = Strava.ignoreFirstN(members, pagingInstruction.getIgnoreFirstN());
				members = Strava.ignoreLastN(members, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			return null;
		}
		return members;
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Activity> listRecentClubActivities(Integer id, Paging pagingInstruction) throws UnauthorizedException {
		Strava.validatePagingArguments(pagingInstruction);

		List<Activity> activities = new ArrayList<Activity>();
		try {
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Activity> activityPage = Arrays.asList(restService.listRecentClubActivities(id, paging.getPage(), paging.getPageSize()));
			activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
			activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
			activities.addAll(activityPage);
		}
		if (pagingInstruction != null) {
			activities = Strava.ignoreFirstN(activities, pagingInstruction.getIgnoreFirstN());
			activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
		}
		} catch (NotFoundException e) {
			// Club doesn't exist
			return null;
		} catch (UnauthorizedException e1) {
			// Not a member
			return new ArrayList<Activity>();
		}
		return activities;


	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@Override
	public ClubMembershipResponse joinClub(Integer id) throws NotFoundException, UnauthorizedException {
		return restService.join(id);		
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@Override
	public ClubMembershipResponse leaveClub(Integer id) throws NotFoundException, UnauthorizedException {
		return restService.leave(id);
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<Athlete> listClubMembers(Integer id) throws UnauthorizedException {
		return listClubMembers(id, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ClubServices#listRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<Activity> listRecentClubActivities(Integer id) throws UnauthorizedException {
		return listRecentClubActivities(id, null);
	}

}
