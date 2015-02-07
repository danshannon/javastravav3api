package stravajava.api.v3.service.impl.retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaActivityZone;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaComment;
import stravajava.api.v3.model.StravaLap;
import stravajava.api.v3.model.StravaPhoto;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.service.ActivityServices;
import stravajava.api.v3.service.Strava;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * @author Dan Shannon
 *
 */
public class ActivityServicesImpl extends StravaServiceImpl implements ActivityServices {
	private ActivityServicesImpl(String token) {
		super(token);
		this.restService = Retrofit.retrofit(ActivityServicesRetrofit.class, token, ActivityServicesRetrofit.LOG_LEVEL);
	}
	
	/**
	 * <p>Returns an implementation of {@link ActivityServices activity services}</p>
	 * 
	 * <p>Instances are cached so that if 2 requests are made for the same token, the same instance is returned</p>
	 * 
	 * @param token The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the activity services
	 */
	public static ActivityServices implementation(final String token) {
		ActivityServices restService = restServices.get(token);
		if (restService == null) {
			restService = new ActivityServicesImpl(token);
			
			// Store the token for later retrieval so that there's only one service per token
			restServices.put(token, restService);
			
		}
		return restService;
	}
	
	private static HashMap<String,ActivityServices> restServices = new HashMap<String,ActivityServices>();
	
	private ActivityServicesRetrofit restService;
	

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public StravaActivity getActivity(Integer id, Boolean includeAllEfforts) {
		try {
			boolean loop = true;
			StravaActivity stravaResponse = null;
			while (loop) {
				stravaResponse = restService.getActivity(id, includeAllEfforts);
				if (stravaResponse.getResourceState() == StravaResourceState.UPDATING) {
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
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				// Activity is private
				StravaActivity activity = new StravaActivity();
				activity.setId(id);
				return activity;
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#createManualActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@Override
	public StravaActivity createManualActivity(StravaActivity activity) {
		try {
			return restService.createManualActivity(activity);
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#updateActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@Override
	public StravaActivity updateActivity(StravaActivity activity) {
			try {
				return restService.updateActivity(activity.getId(), activity);
			} catch (NotFoundException e) {
				return null;
			}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity deleteActivity(Integer id) {
		try {
			return restService.deleteActivity(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(Calendar before, Calendar after, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		Integer secondsBefore = secondsSinceUnixEpoch(before);
		Integer secondsAfter = secondsSinceUnixEpoch(after);
		
		List<StravaActivity> activities = new ArrayList<StravaActivity>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<StravaActivity> activityPage = Arrays.asList(restService.listAuthenticatedAthleteActivities(secondsBefore, secondsAfter, paging.getPage(), paging.getPageSize()));
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
	 * @see stravajava.api.v3.service.ActivityServices#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listFriendsActivities(Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaActivity> activities = new ArrayList<StravaActivity>();
		for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
			List<StravaActivity> activityPage = Arrays.asList(restService.listFriendsActivities(paging.getPage(), paging.getPageSize()));
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
	 * @see stravajava.api.v3.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	public List<StravaActivityZone> listActivityZones(Integer id) {
		try {
			return Arrays.asList(restService.listActivityZones(id));
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaActivityZone>();
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityLaps(java.lang.Integer)
	 */
	@Override
	public List<StravaLap> listActivityLaps(Integer id) {
		try {
			return Arrays.asList(restService.listActivityLaps(id));
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaLap>();
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaComment> listActivityComments(Integer id, Boolean markdown, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaComment> comments = new ArrayList<StravaComment>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaComment> commentPage = Arrays.asList(restService.listActivityComments(id, markdown, paging.getPage(), paging.getPageSize()));
				commentPage = Strava.ignoreLastN(commentPage, paging.getIgnoreLastN());
				commentPage = Strava.ignoreFirstN(commentPage, paging.getIgnoreFirstN());
				comments.addAll(commentPage);
			}
			if (pagingInstruction != null) {
				comments = Strava.ignoreFirstN(comments,pagingInstruction.getIgnoreFirstN());
				comments = Strava.ignoreLastN(comments, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaComment>();
			} else {
				throw e;
			}
		}
		return comments;

	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);

		List<StravaAthlete> athletes = new ArrayList<StravaAthlete>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaAthlete> athletePage = Arrays.asList(restService.listActivityKudoers(id, paging.getPage(), paging.getPageSize()));
				athletePage = Strava.ignoreLastN(athletePage, paging.getIgnoreLastN());
				athletePage = Strava.ignoreFirstN(athletePage, paging.getIgnoreFirstN());
				athletes.addAll(athletePage);
			}
			if (pagingInstruction != null) {
				athletes = Strava.ignoreFirstN(athletes,pagingInstruction.getIgnoreFirstN());
				athletes = Strava.ignoreLastN(athletes, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaAthlete>();
			} else {
				throw e;
			}
		}
		return athletes;
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public List<StravaPhoto> listActivityPhotos(Integer id) {
		try {
			StravaPhoto[] photos = restService.listActivityPhotos(id);
			
			// This fixes an inconsistency with the listActivityComments API call on Strava, which returns an empty array, not null
			if (photos == null) {
				photos = new StravaPhoto[0];
			}
			
			return Arrays.asList(photos);
			
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaPhoto>();
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public List<StravaComment> listActivityComments(Integer id, Boolean markdown) {
		return listActivityComments(id, markdown, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(Integer id) {
		return listActivityKudoers(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(Calendar before, Calendar after) {
		return listAuthenticatedAthleteActivities(before, after, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listFriendsActivities()
	 */
	@Override
	public List<StravaActivity> listFriendsActivities() {
		return listFriendsActivities(null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities() {
		return listAuthenticatedAthleteActivities(null, null, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(stravajava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(Paging pagingInstruction) {
		return listAuthenticatedAthleteActivities(null, null, pagingInstruction);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(Integer id) {
		return listRelatedActivities(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer, stravajava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(Integer id, Paging pagingInstruction) {
		Strava.validatePagingArguments(pagingInstruction);
		
		List<StravaActivity> activities = new ArrayList<StravaActivity>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<StravaActivity> activityPage = Arrays.asList(restService.listRelatedActivities(id, paging.getPage(), paging.getPageSize()));
				activityPage = Strava.ignoreLastN(activityPage, paging.getIgnoreLastN());
				activityPage = Strava.ignoreFirstN(activityPage, paging.getIgnoreFirstN());
				activities.addAll(activityPage);
			}
			if (pagingInstruction != null) {
				activities = Strava.ignoreFirstN(activities,pagingInstruction.getIgnoreFirstN());
				activities = Strava.ignoreLastN(activities, pagingInstruction.getIgnoreLastN());
			}
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			if (accessTokenIsValid()) {
				return new ArrayList<StravaActivity>();
			} else {
				throw e;
			}
		}
		return activities;
		
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity getActivity(Integer id) {
		return getActivity(id, Boolean.FALSE);
	}

}
