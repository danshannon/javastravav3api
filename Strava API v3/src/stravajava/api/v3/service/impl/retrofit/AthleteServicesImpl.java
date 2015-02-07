/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.service.AthleteServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * @author Dan Shannon
 *
 */
public class AthleteServicesImpl implements AthleteServices {
	private AthleteServicesImpl(final String token) {
		this.restService = Retrofit.retrofit(AthleteServicesRetrofit.class, token, AthleteServicesRetrofit.LOG_LEVEL);
	}
	
	/**
	 * <p>Returns an implementation of {@link AthleteServices athlete services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the athlete services
	 */ 
	public static AthleteServices implementation(final String token) {
		AthleteServices restService = restServices.get(token);
		if (restService == null) {
			restService = new AthleteServicesImpl(token);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,AthleteServices> restServices = new HashMap<String,AthleteServices>();
	
	private AthleteServicesRetrofit restService;
	

	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@Override
	public StravaAthlete getAuthenticatedAthlete() {
		return restService.getAuthenticatedAthlete();
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#getAthlete(java.lang.Integer)
	 */
	@Override
	public StravaAthlete getAthlete(Integer id) {
		try {
			return restService.getAthlete(id);
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				StravaAthlete athlete = new StravaAthlete();
				athlete.setId(id);
				return athlete;
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, stravajava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	@Override
	public StravaAthlete updateAuthenticatedAthlete(String city, String state, String country, StravaGender sex, Float weight) {
		return restService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaSegmentEffort> koms = new ArrayList<StravaSegmentEffort>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaSegmentEffort> komPage = Arrays.asList(restService.listAthleteKOMs(id, paging.getPage(), paging.getPageSize()));
				komPage = Strava.ignoreLastN(komPage, paging.getIgnoreLastN());
				komPage = Strava.ignoreFirstN(komPage, paging.getIgnoreFirstN());
				koms.addAll(komPage);
			}
			if (pagingInstruction != null) {
				koms = Strava.ignoreFirstN(koms,pagingInstruction.getIgnoreFirstN());
				koms = Strava.ignoreLastN(koms, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			return null;
		}
		return koms;

	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaAthlete> friends = new ArrayList<StravaAthlete>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<StravaAthlete> friendPage = Arrays.asList(restService.listAuthenticatedAthleteFriends(paging.getPage(), paging.getPageSize()));
			friendPage = Strava.ignoreLastN(friendPage, paging.getIgnoreLastN());
			friendPage = Strava.ignoreFirstN(friendPage, paging.getIgnoreFirstN());
			friends.addAll(friendPage);
		}
		if (pagingInstruction != null) {
			friends = Strava.ignoreFirstN(friends,pagingInstruction.getIgnoreFirstN());
			friends = Strava.ignoreLastN(friends, pagingInstruction.getIgnoreLastN());
		}
		return friends;
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaAthlete> friends = new ArrayList<StravaAthlete>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaAthlete> friendPage = Arrays.asList(restService.listAthleteFriends(id, paging.getPage(), paging.getPageSize()));
				friendPage = Strava.ignoreLastN(friendPage, paging.getIgnoreLastN());
				friendPage = Strava.ignoreFirstN(friendPage, paging.getIgnoreFirstN());
				friends.addAll(friendPage);
			}
			if (pagingInstruction != null) {
				friends = Strava.ignoreFirstN(friends,pagingInstruction.getIgnoreFirstN());
				friends = Strava.ignoreLastN(friends, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			// StravaAthlete with the given id does not exist
			return null;
		}
		return friends;

	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaAthlete> friends = new ArrayList<StravaAthlete>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaAthlete> friendPage = Arrays.asList(restService.listAthletesBothFollowing(id, paging.getPage(), paging.getPageSize()));
				friendPage = Strava.ignoreLastN(friendPage, paging.getIgnoreLastN());
				friendPage = Strava.ignoreFirstN(friendPage, paging.getIgnoreFirstN());
				friends.addAll(friendPage);
			}
			if (pagingInstruction != null) {
				friends = Strava.ignoreFirstN(friends,pagingInstruction.getIgnoreFirstN());
				friends = Strava.ignoreLastN(friends, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			// StravaAthlete with the given id doesn't exist
			return null;
		}
		return friends;

	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(Integer id) {
		return listAthleteKOMs(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends() {
		return listAuthenticatedAthleteFriends(null);
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(Integer id) {
		return listAthleteFriends(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(Integer id) {
		return listAthletesBothFollowing(id, null);
	}
	
	private boolean accessTokenIsValid() {
		try {
			getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}


}
