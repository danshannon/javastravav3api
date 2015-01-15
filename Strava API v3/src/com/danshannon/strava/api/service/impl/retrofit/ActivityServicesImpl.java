package com.danshannon.strava.api.service.impl.retrofit;

import java.util.Calendar;
import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.ActivityServices;
import com.danshannon.strava.api.service.Strava;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author Dan Shannon
 *
 */
public class ActivityServicesImpl implements ActivityServices {
	private static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;
	
	private ActivityServicesImpl(ActivityServicesRetrofit restService) {
		this.restService = restService;
	}
	
	/**
	 * <p>Returns an implementation of {@link ActivityServices activity services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the activity services
	 * @throws UnauthorizedException If the token used to create the service is invalid
	 */
	public static ActivityServices implementation(final String token) throws UnauthorizedException {
		ActivityServices restService = restServices.get(token);
		if (restService == null) {
			restService = new ActivityServicesImpl(new RestAdapter.Builder()
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
				.create(ActivityServicesRetrofit.class));

			// Check that the token works (i.e. it is valid)
			restService.listAuthenticatedAthleteActivities(null, null, 1, 1);

			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,ActivityServices> restServices = new HashMap<String,ActivityServices>();
	
	private ActivityServicesRetrofit restService;
	

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public Activity getActivity(Integer id, Boolean includeAllEfforts) {
		try {
			boolean loop = true;
			Activity stravaResponse = null;
			while (loop) {
				stravaResponse = restService.getActivity(id, includeAllEfforts);
				if (stravaResponse.getResourceState() == ResourceState.UPDATING) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// Ignore
					}				
				} else {
					loop = false;
				}
			}
			return stravaResponse;
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#createManualActivity(com.danshannon.strava.api.model.Activity)
	 */
	@Override
	public Activity createManualActivity(Activity activity) throws UnauthorizedException, BadRequestException {
		return restService.createManualActivity(activity);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#updateActivity(com.danshannon.strava.api.model.Activity)
	 */
	@Override
	public Activity updateActivity(Activity activity) throws NotFoundException, UnauthorizedException {
			return restService.updateActivity(activity.getId(), activity);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@Override
	public Activity deleteActivity(Integer id) throws NotFoundException, UnauthorizedException {
		return restService.deleteActivity(id);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 * @throws UnauthorizedException 
	 */
	@Override
	public Activity[] listAuthenticatedAthleteActivities(Calendar before, Calendar after, Integer page, Integer perPage) throws UnauthorizedException {
		Strava.validatePagingArguments(page,perPage);
		
		return restService.listAuthenticatedAthleteActivities(secondsSinceUnixEpoch(before), secondsSinceUnixEpoch(after), page, perPage);
	}

	/**
	 * @param date
	 * @return
	 */
	private Integer secondsSinceUnixEpoch(Calendar date) {
		if (date == null) {
			return null;
		}
		Long timeInSeconds = date.getTimeInMillis() / 1000L;
		return timeInSeconds.intValue();
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Activity[] listFriendsActivities(Integer page, Integer perPage) {
		Strava.validatePagingArguments(page,perPage);

		return restService.listFriendsActivities(page, perPage);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	public ActivityZone[] listActivityZones(Integer id) {
		try {
			return restService.listActivityZones(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@Override
	public Lap[] listActivityLaps(Integer id) {
		try {
			return restService.listActivityLaps(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Comment[] listActivityComments(Integer id, Boolean markdown, Integer page, Integer perPage) {
		Strava.validatePagingArguments(page,perPage);

		try {
			return restService.listActivityComments(id, markdown, page, perPage);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public Athlete[] listActivityKudoers(Integer id, Integer page, Integer perPage) {
		Strava.validatePagingArguments(page,perPage);

		try {
			return restService.listActivityKudoers(id, page, perPage);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public Photo[] listActivityPhotos(Integer id) {
		try {
			Photo[] photos = restService.listActivityPhotos(id);
			
			// This fixes an inconsistency with the listActivityComments API call on Strava, which returns an empty array, not null
			if (photos == null) {
				photos = new Photo[0];
			}
			
			return photos;
			
		} catch (NotFoundException e) {
			return null;
		}
	}

}
