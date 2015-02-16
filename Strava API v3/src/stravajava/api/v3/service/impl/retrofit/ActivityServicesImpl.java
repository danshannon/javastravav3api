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
	private ActivityServicesImpl(final String token) {
		super(token);
		this.restService = Retrofit.retrofit(ActivityServicesRetrofit.class, token, ActivityServicesRetrofit.LOG_LEVEL);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link ActivityServices activity services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the activity services
	 */
	public static ActivityServices implementation(final String token) {
		ActivityServices restService = restServices.get(token);
		if (restService == null) {
			restService = new ActivityServicesImpl(token);

			// Store the token for later retrieval so that there's only one
			// service per token
			restServices.put(token, restService);

		}
		return restService;
	}

	private static HashMap<String, ActivityServices> restServices = new HashMap<String, ActivityServices>();

	final ActivityServicesRetrofit restService;

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public StravaActivity getActivity(final Integer id, final Boolean includeAllEfforts) {
		StravaActivity stravaResponse = null;
		
		try {
			boolean loop = true;
			int i = 0;
			while (loop) {
				i++;
				stravaResponse = this.restService.getActivity(id, includeAllEfforts);
		
				// If the activity is being updated, wait for the update to complete
				if (i < 10 && stravaResponse.getResourceState() == StravaResourceState.UPDATING) {
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
			// Activity doesn't exist - return null
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
	public StravaActivity createManualActivity(final StravaActivity activity) {
		try {
			return this.restService.createManualActivity(activity);
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#updateActivity(stravajava.api.v3.model.StravaActivity)
	 */
	@Override
	public StravaActivity updateActivity(final StravaActivity activity) {
		try {
			return this.restService.updateActivity(activity.getId(), activity);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#deleteActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity deleteActivity(final Integer id) {
		try {
			return this.restService.deleteActivity(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Calendar before, final Calendar after, final Paging pagingInstruction) {
		final Integer secondsBefore = secondsSinceUnixEpoch(before);
		final Integer secondsAfter = secondsSinceUnixEpoch(after);

		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServicesImpl.this.restService.listAuthenticatedAthleteActivities(secondsBefore, secondsAfter, thisPage.getPage(),
						thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @param date
	 * @return Number of seconds after the unix epoch date equivalent to the given date
	 */
	private Integer secondsSinceUnixEpoch(final Calendar date) {
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
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServicesImpl.this.restService.listFriendsActivities(thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityZones(java.lang.Integer)
	 */
	@Override
	public List<StravaActivityZone> listActivityZones(final Integer id) {
		try {
			return Arrays.asList(this.restService.listActivityZones(id));
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
	public List<StravaLap> listActivityLaps(final Integer id) {
		try {
			return Arrays.asList(this.restService.listActivityLaps(id));
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
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaComment>() {
			@Override
			public List<StravaComment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServicesImpl.this.restService.listActivityComments(id, markdown, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServicesImpl.this.restService.listActivityKudoers(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public List<StravaPhoto> listActivityPhotos(final Integer id) {
		try {
			StravaPhoto[] photos = this.restService.listActivityPhotos(id);

			// This fixes an inconsistency with the listActivityComments API
			// call on Strava, which returns an empty array, not null
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
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown) {
		return listActivityComments(id, markdown, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityKudoers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Integer id) {
		return listActivityKudoers(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAuthenticatedAthleteActivities(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Calendar before, final Calendar after) {
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
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Paging pagingInstruction) {
		return listAuthenticatedAthleteActivities(null, null, pagingInstruction);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Integer id) {
		return listRelatedActivities(id, null);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listRelatedActivities(java.lang.Integer, stravajava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServicesImpl.this.restService.listRelatedActivities(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#getActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity getActivity(final Integer id) {
		return getActivity(id, Boolean.FALSE);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id) {
		return listActivityComments(id, Boolean.FALSE);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listActivityComments(java.lang.Integer, stravajava.util.Paging)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id, final Paging pagingInstruction) {
		return listActivityComments(id, Boolean.FALSE, pagingInstruction);
	}

	/**
	 * @see stravajava.api.v3.service.ActivityServices#listAllAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAllAuthenticatedAthleteActivities() {
		boolean loop = true;
		List<StravaActivity> activities = new ArrayList<StravaActivity>();
		int page = 0;
		while (loop) {
			page++;
			List<StravaActivity> currentPage = listAuthenticatedAthleteActivities(new Paging(page,Strava.MAX_PAGE_SIZE));
			activities.addAll(currentPage);
			if (currentPage.size() < Strava.MAX_PAGE_SIZE) {
				loop = false;
			}
		}
		return activities;
	}

}
