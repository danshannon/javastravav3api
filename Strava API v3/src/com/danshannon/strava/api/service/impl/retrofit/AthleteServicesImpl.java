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

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.AthleteServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class AthleteServicesImpl implements AthleteServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private AthleteServicesImpl(AthleteServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * <p>Returns an implementation of {@link AthleteServices athlete services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the athlete services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static AthleteServices implementation(final String token) throws UnauthorizedException {
		AthleteServices restService = restServices.get(token);
		if (restService == null) {
			restService = new AthleteServicesImpl(new RestAdapter.Builder()
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
				.create(AthleteServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			restService.getAuthenticatedAthlete();

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,AthleteServices> restServices = new HashMap<String,AthleteServices>();
	
	private AthleteServicesRetrofit restService;
	

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#getAuthenticatedAthlete()
	 */
	@Override
	public Athlete getAuthenticatedAthlete() throws UnauthorizedException {
		return restService.getAuthenticatedAthlete();
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#getAthlete(java.lang.Integer)
	 */
	@Override
	public Athlete getAthlete(Integer id) {
		try {
			return restService.getAthlete(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, com.danshannon.strava.api.model.reference.Gender, java.lang.Float)
	 */
	@Override
	public Athlete updateAuthenticatedAthlete(String city, String state, String country, Gender sex, Float weight) throws UnauthorizedException, NotFoundException {
		return restService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SegmentEffort> listAthleteKOMs(Integer id, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);

		List<SegmentEffort> koms = new ArrayList<SegmentEffort>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<SegmentEffort> komPage = Arrays.asList(restService.listAthleteKOMs(id, paging.getPage(), paging.getPageSize()));
			komPage = Strava.ignoreLastN(komPage, paging.getIgnoreLastN());
			komPage = Strava.ignoreFirstN(komPage, paging.getIgnoreFirstN());
			koms.addAll(komPage);
		}
		if (pagingInstruction != null) {
			koms = Strava.ignoreFirstN(koms,pagingInstruction.getIgnoreFirstN());
			koms = Strava.ignoreLastN(koms, pagingInstruction.getIgnoreLastN());
		}
		return koms;

	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Athlete> listAuthenticatedAthleteFriends(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<Athlete> friends = new ArrayList<Athlete>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Athlete> friendPage = Arrays.asList(restService.listAuthenticatedAthleteFriends(paging.getPage(), paging.getPageSize()));
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
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Athlete> listAthleteFriends(Integer id, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<Athlete> friends = new ArrayList<Athlete>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Athlete> friendPage = Arrays.asList(restService.listAthleteFriends(id, paging.getPage(), paging.getPageSize()));
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
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Athlete> listAthletesBothFollowing(Integer id, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);

		List<Athlete> friends = new ArrayList<Athlete>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Athlete> friendPage = Arrays.asList(restService.listAthletesBothFollowing(id, paging.getPage(), paging.getPageSize()));
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
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<SegmentEffort> listAthleteKOMs(Integer id) throws NotFoundException {
		return listAthleteKOMs(id, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAuthenticatedAthleteFriends()
	 */
	@Override
	public List<Athlete> listAuthenticatedAthleteFriends() {
		return listAuthenticatedAthleteFriends(null);
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<Athlete> listAthleteFriends(Integer id) throws NotFoundException {
		return listAthleteFriends(id, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.AthleteServices#listAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<Athlete> listAthletesBothFollowing(Integer id) throws NotFoundException {
		return listAthletesBothFollowing(id, null);
	}

}
