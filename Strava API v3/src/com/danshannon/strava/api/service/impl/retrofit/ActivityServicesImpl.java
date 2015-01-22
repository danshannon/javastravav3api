package com.danshannon.strava.api.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import com.danshannon.strava.util.Paging;
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
			
			// Check that the token is valid
			restService.listAuthenticatedAthleteActivities(null, null, null);
			
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
	 * @throws BadRequestException 
	 */
	@Override
	public List<Activity> listAuthenticatedAthleteActivities(Calendar before, Calendar after, Paging pagingInstruction) throws UnauthorizedException {
		Strava.validatePagingArguments(pagingInstruction);
		Integer secondsBefore = secondsSinceUnixEpoch(before);
		Integer secondsAfter = secondsSinceUnixEpoch(after);
		
		List<Activity> activities = new ArrayList<Activity>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Activity> activityPage = Arrays.asList(restService.listAuthenticatedAthleteActivities(secondsBefore, secondsAfter, paging.getPage(), paging.getPageSize()));
			activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
			activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
			activities.addAll(activityPage);
		}
		if (pagingInstruction != null) {
			activities = Strava.ignoreFirstN(activities,pagingInstruction.getIgnoreFirstN());
			activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
		}
		return activities;
		
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
	public List<Activity> listFriendsActivities(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<Activity> activities = new ArrayList<Activity>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Activity> activityPage = Arrays.asList(restService.listFriendsActivities(paging.getPage(), paging.getPageSize()));
			activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
			activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
			activities.addAll(activityPage);
		}
		if (pagingInstruction != null) {
			activities = Strava.ignoreFirstN(activities,pagingInstruction.getIgnoreFirstN());
			activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
		}
		return activities;
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	public List<ActivityZone> listActivityZones(Integer id) {
		try {
			return Arrays.asList(restService.listActivityZones(id));
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@Override
	public List<Lap> listActivityLaps(Integer id) {
		try {
			return Arrays.asList(restService.listActivityLaps(id));
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Comment> listActivityComments(Integer id, Boolean markdown, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<Comment> comments = new ArrayList<Comment>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Comment> commentPage = Arrays.asList(restService.listActivityComments(id, markdown, paging.getPage(), paging.getPageSize()));
			commentPage = Strava.ignoreLastN(commentPage, paging.getIgnoreLastN());
			commentPage = Strava.ignoreFirstN(commentPage, paging.getIgnoreFirstN());
			comments.addAll(commentPage);
		}
		if (pagingInstruction != null) {
			comments = Strava.ignoreFirstN(comments,pagingInstruction.getIgnoreFirstN());
			comments = Strava.ignoreLastN(comments, pagingInstruction.getIgnoreLastN());
		}
		return comments;

	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<Athlete> listActivityKudoers(Integer id, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);

		List<Athlete> athletes = new ArrayList<Athlete>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Athlete> athletePage = Arrays.asList(restService.listActivityKudoers(id, paging.getPage(), paging.getPageSize()));
			athletePage = Strava.ignoreLastN(athletePage, paging.getIgnoreLastN());
			athletePage = Strava.ignoreFirstN(athletePage, paging.getIgnoreFirstN());
			athletes.addAll(athletePage);
		}
		if (pagingInstruction != null) {
			athletes = Strava.ignoreFirstN(athletes,pagingInstruction.getIgnoreFirstN());
			athletes = Strava.ignoreLastN(athletes, pagingInstruction.getIgnoreLastN());
		}
		return athletes;
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public List<Photo> listActivityPhotos(Integer id) {
		try {
			Photo[] photos = restService.listActivityPhotos(id);
			
			// This fixes an inconsistency with the listActivityComments API call on Strava, which returns an empty array, not null
			if (photos == null) {
				photos = new Photo[0];
			}
			
			return Arrays.asList(photos);
			
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public List<Comment> listActivityComments(Integer id, Boolean markdown) throws NotFoundException {
		return listActivityComments(id, markdown, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listActivityKudoers(java.lang.Integer)
	 */
	@Override
	public List<Athlete> listActivityKudoers(Integer id) throws NotFoundException {
		return listActivityKudoers(id, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public List<Activity> listAuthenticatedAthleteActivities(Calendar before, Calendar after) throws UnauthorizedException {
		return listAuthenticatedAthleteActivities(before, after, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listFriendsActivities()
	 */
	@Override
	public List<Activity> listFriendsActivities() {
		return listFriendsActivities(null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities()
	 */
	@Override
	public List<Activity> listAuthenticatedAthleteActivities() throws UnauthorizedException {
		return listAuthenticatedAthleteActivities(null, null, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listAuthenticatedAthleteActivities(com.danshannon.strava.util.Paging)
	 */
	@Override
	public List<Activity> listAuthenticatedAthleteActivities(Paging pagingInstruction) throws UnauthorizedException {
		return listAuthenticatedAthleteActivities(null, null, pagingInstruction);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listRelatedActivities(java.lang.Integer)
	 */
	@Override
	public List<Activity> listRelatedActivities(Integer id) throws NotFoundException {
		return listRelatedActivities(id, null);
	}

	/**
	 * @see com.danshannon.strava.api.service.ActivityServices#listRelatedActivities(java.lang.Integer, com.danshannon.strava.util.Paging)
	 */
	@Override
	public List<Activity> listRelatedActivities(Integer id, Paging pagingInstruction) throws NotFoundException {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<Activity> activities = new ArrayList<Activity>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<Activity> activityPage = Arrays.asList(restService.listRelatedActivities(id, paging.getPage(), paging.getPageSize()));
			activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
			activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
			activities.addAll(activityPage);
		}
		if (pagingInstruction != null) {
			activities = Strava.ignoreFirstN(activities,pagingInstruction.getIgnoreFirstN());
			activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
		}
		return activities;
		
	}

}
