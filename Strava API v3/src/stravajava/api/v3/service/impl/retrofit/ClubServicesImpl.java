/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaClub;
import stravajava.api.v3.model.StravaClubMembershipResponse;
import stravajava.api.v3.service.ClubServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * @author danshannon
 *
 */
public class ClubServicesImpl implements ClubServices {
	private ClubServicesImpl(final String token) {
		this.restService = Retrofit.retrofit(ClubServicesRetrofit.class, token, ClubServicesRetrofit.LOG_LEVEL);
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
			restService = new ClubServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,ClubServices> restServices = new HashMap<String,ClubServices>();
	
	private ClubServicesRetrofit restService;
	

	/**
	 * @see stravajava.api.v3.service.ClubServices#getClub(java.lang.Integer)
	 */
	@Override
	public StravaClub getClub(Integer id) throws UnauthorizedException {
		try {
			return restService.getClub(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<StravaClub> listAuthenticatedAthleteClubs() throws UnauthorizedException {
		return Arrays.asList(restService.listAuthenticatedAthleteClubs());
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(Integer id, Paging pagingInstruction) throws UnauthorizedException {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaAthlete> members = new ArrayList<StravaAthlete>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaAthlete> memberPage = Arrays.asList(restService.listClubMembers(id, paging.getPage(), paging.getPageSize()));
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
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(Integer id, Paging pagingInstruction) throws UnauthorizedException {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaActivity> activities = new ArrayList<StravaActivity>();
		try {
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<StravaActivity> activityPage = Arrays.asList(restService.listRecentClubActivities(id, paging.getPage(), paging.getPageSize()));
			activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
			activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
			activities.addAll(activityPage);
		}
		if (pagingInstruction != null) {
			activities = Strava.ignoreFirstN(activities, pagingInstruction.getIgnoreFirstN());
			activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
		}
		} catch (NotFoundException e) {
			// StravaClub doesn't exist
			return null;
		} catch (UnauthorizedException e1) {
			// Not a member
			return new ArrayList<StravaActivity>();
		}
		return activities;


	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#joinClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse joinClub(Integer id) throws NotFoundException, UnauthorizedException {
		return restService.join(id);		
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#leaveClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse leaveClub(Integer id) throws NotFoundException, UnauthorizedException {
		return restService.leave(id);
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(Integer id) throws UnauthorizedException {
		return listClubMembers(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ClubServices#listRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(Integer id) throws UnauthorizedException {
		return listRecentClubActivities(id, null);
	}

}
