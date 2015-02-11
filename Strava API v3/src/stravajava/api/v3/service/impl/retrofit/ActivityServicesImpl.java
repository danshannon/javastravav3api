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
import stravajava.api.v3.service.PagingCallback;
import stravajava.api.v3.service.PagingHandler;
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
		Integer secondsBefore = secondsSinceUnixEpoch(before);
		Integer secondsAfter = secondsSinceUnixEpoch(after);
		
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(Paging thisPage) throws NotFoundException {
				return Arrays.asList(restService.listAuthenticatedAthleteActivities(secondsBefore, secondsAfter, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
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
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(Paging thisPage) throws NotFoundException {
				return Arrays.asList(restService.listFriendsActivities(thisPage.getPage(), thisPage.getPageSize()));
			}
		});
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
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaComment>() {
			@Override
			public List<StravaComment> getPageOfData(Paging thisPage) throws NotFoundException {
				return Arrays.asList(restService.listActivityComments(id, markdown, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(Integer id, Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>(){
			@Override
			public List<StravaAthlete> getPageOfData(Paging thisPage) throws NotFoundException {
				return Arrays.asList(restService.listActivityKudoers(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
		
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
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>(){
			@Override
			public List<StravaActivity> getPageOfData(Paging thisPage) throws NotFoundException {
				return Arrays.asList(restService.listRelatedActivities(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity getActivity(Integer id) {
		return getActivity(id, Boolean.FALSE);
	}

}
