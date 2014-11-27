package com.danshannon.strava.api.service.impl.retrofit;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.service.ActivityServices;

/**
 * @author Dan Shannon
 *
 */
public class ActivityServicesImpl implements ActivityServices {
	private ActivityServicesImpl(ActivityServicesRetrofit restService) {
		this.restService = restService;
	}
	
	public static ActivityServices implementation(String token) {
		ActivityServicesRetrofit restService = restServices.get(token);
		if (restService == null) {
			restService = new RestAdapter.Builder()
				.setEndpoint(ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				})
				.build()
				.create(ActivityServicesRetrofit.class);
			restServices.put(token, restService);
		}
		return restService;
	}
	
	private static HashMap<String,ActivityServicesRetrofit> restServices = new HashMap<String,ActivityServicesRetrofit>();
	
	private ActivityServicesRetrofit restService;
	

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public Activity getActivity(Integer id, Boolean includeAllEfforts) {
		return restService.getActivity(id, includeAllEfforts);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#createManualActivity(com.danshannon.strava.api.model.Activity)
	 */
	@Override
	public void createManualActivity(Activity activity) {
		restService.createManualActivity(activity);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#updateActivity(com.danshannon.strava.api.model.Activity)
	 */
	@Override
	public Activity updateActivity(Activity activity) {
		return restService.updateActivity(activity.getId(), activity);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@Override
	public void deleteActivity(Integer id) {
		restService.deleteActivity(id);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Activity[] listAuthenticatedAthleteActivities(Integer before, Integer after, Integer page, Integer perPage) {
		return restService.listAuthenticatedAthleteActivities(before, after, page, perPage);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Activity[] listFriendsActivities(Integer page, Integer perPage) {
		return restService.listFriendsActivities(page, perPage);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	public ActivityZone[] listActivityZones(Integer id) {
		return restService.listActivityZones(id);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@Override
	public Lap[] listActivityLaps(Integer id) {
		return restService.listActivityLaps(id);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Comment[] listActivityComments(Integer id, Boolean markdown, Integer page, Integer perPage) {
		return restService.listActivityComments(id, markdown, page, perPage);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public Athlete[] listActivityKudoers(Integer id, Integer page, Integer perPage) {
		return restService.listActivityKudoers(id, page, perPage);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public Photo[] listActivityPhotos(Integer id) {
		return restService.listActivityPhotos(id);
	}

}
