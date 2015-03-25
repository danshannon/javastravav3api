package javastrava.api.v3.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaComment;
import javastrava.api.v3.model.StravaLap;
import javastrava.api.v3.model.StravaPhoto;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.ActivityService;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.StravaInternalServerErrorException;
import javastrava.api.v3.service.exception.StravaUnknownAPIException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.util.Paging;
import javastrava.util.PagingCallback;
import javastrava.util.PagingHandler;

/**
 * @author Dan Shannon
 *
 */
public class ActivityServiceImpl extends StravaServiceImpl implements ActivityService {
	private ActivityServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link ActivityService activity services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the activity services
	 */
	public static ActivityService instance(final Token token) {
		// Get the service from the token's cache
		ActivityService service = token.getService(ActivityService.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new ActivityServiceImpl(token);
			token.addService(ActivityService.class, service);
		}
		return service;
	}		
		
	/**
	 * @see javastrava.api.v3.service.ActivityService#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public StravaActivity getActivity(final Integer id, final Boolean includeAllEfforts) {
		StravaActivity stravaResponse = null;
		
		try {
			boolean loop = true;
			int i = 0;
			while (loop) {
				i++;
				stravaResponse = this.api.getActivity(id, includeAllEfforts);
		
				// If the activity is being updated, wait for the update to complete
				if (i < 10 && stravaResponse.getResourceState() == StravaResourceState.UPDATING) {
					try {
						Thread.sleep(1000 + i * 100);
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
				activity.setResourceState(StravaResourceState.META);
				return activity;
			} else {
				throw e;
			}
		}
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#createManualActivity(javastrava.api.v3.model.StravaActivity)
	 */
	@Override
	public StravaActivity createManualActivity(final StravaActivity activity) {
		try {
			return this.api.createManualActivity(activity);
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
		// TODO Workaround for issue javastrava-api #49 (https://github.com/danshannon/javastravav3api/issues/49)
		catch (StravaInternalServerErrorException e) {
			throw new IllegalArgumentException(e);
		}
		// End of workaround
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#updateActivity(Integer,javastrava.api.v3.model.StravaActivityUpdate)
	 */
	@Override
	public StravaActivity updateActivity(final Integer id, final StravaActivityUpdate activity) {
		StravaActivityUpdate update = activity;
		if (activity == null) {
			return getActivity(id);
		}
		StravaActivity response = null;
		
		
		// TODO Workaround for issue javastrava-api #36 (https://github.com/danshannon/javastravav3api/issues/36)
		if (update.getCommute() != null) {
			StravaActivityUpdate commuteUpdate = new StravaActivityUpdate();
			commuteUpdate.setCommute(update.getCommute());
			response = doUpdateActivity(id, commuteUpdate);
			if (response.getCommute() != update.getCommute()) { 
				throw new StravaUnknownAPIException(Messages.string("ActivityServiceImpl.failedToUpdateCommuteFlag") + id, null, null); //$NON-NLS-1$
			}
			
			update.setCommute(null);
		}

		// End of workaround
		
		response = doUpdateActivity(id, update);
		return response;
		
	}		
	private StravaActivity doUpdateActivity(final Integer id, final StravaActivityUpdate update) {
		try {
			StravaActivity response = this.api.updateActivity(id, update);
			if (response.getResourceState() == StravaResourceState.UPDATING) {
				response = getActivity(id);
			}
			return response;
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#deleteActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity deleteActivity(final Integer id) {
		try {
			return this.api.deleteActivity(id);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime, Paging)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction) {
		final Integer secondsBefore = secondsSinceUnixEpoch(before);
		final Integer secondsAfter = secondsSinceUnixEpoch(after);

		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServiceImpl.this.api.listAuthenticatedAthleteActivities(secondsBefore, secondsAfter, thisPage.getPage(),
						thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @param date Date for which seconds since the epoch date is to be calculated
	 * @return Number of seconds after the unix epoch date equivalent to the given date
	 */
	private static Integer secondsSinceUnixEpoch(final LocalDateTime date) {
		if (date == null) {
			return null;
		}
		Long timeInSeconds = Long.valueOf(date.toEpochSecond(ZoneOffset.UTC));
		return Integer.valueOf(timeInSeconds.intValue());
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listFriendsActivities(Paging)
	 */
	@Override
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction) {
		List<StravaActivity> activities = PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServiceImpl.this.api.listFriendsActivities(thisPage.getPage(), thisPage.getPageSize()));
			}
		});
		return activities;
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityZones(java.lang.Integer)
	 */
	@Override
	public List<StravaActivityZone> listActivityZones(final Integer id) {
		try {
			return Arrays.asList(this.api.listActivityZones(id));
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
	 * @see javastrava.api.v3.service.ActivityService#listActivityLaps(java.lang.Integer)
	 */
	@Override
	public List<StravaLap> listActivityLaps(final Integer id) {
		try {
			List<StravaLap> laps = Arrays.asList(this.api.listActivityLaps(id));
			return laps;
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
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(Integer, Boolean, Paging)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown, final Paging pagingInstruction) {
		// TODO Workaround for issue javastrava-api #67 - see https://github.com/danshannon/javastravav3api/issues/67
		StravaActivity activity = getActivity(id);
		if (activity.getResourceState() == StravaResourceState.META) { // is private
			return new ArrayList<StravaComment>();
		}
		// End of workaround

		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaComment>() {
			@Override
			public List<StravaComment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServiceImpl.this.api.listActivityComments(id, markdown, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityKudoers(Integer, Paging)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaAthlete>() {
			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServiceImpl.this.api.listActivityKudoers(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});

	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityPhotos(java.lang.Integer)
	 */
	@Override
	public List<StravaPhoto> listActivityPhotos(final Integer id) {
		try {
			StravaPhoto[] photos = this.api.listActivityPhotos(id);

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
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown) {
		return listActivityComments(id, markdown, null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityKudoers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Integer id) {
		return listActivityKudoers(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return listAuthenticatedAthleteActivities(before, after, null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listFriendsActivities()
	 */
	@Override
	public List<StravaActivity> listFriendsActivities() {
		return listFriendsActivities(null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities() {
		return listAuthenticatedAthleteActivities(null, null, null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Paging pagingInstruction) {
		return listAuthenticatedAthleteActivities(null, null, pagingInstruction);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listRelatedActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Integer id) {
		return listRelatedActivities(id, null);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listRelatedActivities(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Integer id, final Paging pagingInstruction) {
		return PagingHandler.handlePaging(pagingInstruction, new PagingCallback<StravaActivity>() {
			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return Arrays.asList(ActivityServiceImpl.this.api.listRelatedActivities(id, thisPage.getPage(), thisPage.getPageSize()));
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#getActivity(java.lang.Integer)
	 */
	@Override
	public StravaActivity getActivity(final Integer id) {
		return getActivity(id, Boolean.FALSE);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id) {
		return listActivityComments(id, Boolean.FALSE);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Integer id, final Paging pagingInstruction) {
		return listActivityComments(id, Boolean.FALSE, pagingInstruction);
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAllAuthenticatedAthleteActivities() {
		return PagingHandler.handleListAll(new PagingCallback<StravaActivity>() {

			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAuthenticatedAthleteActivities(thisPage);
			}
			
		});
		
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#createComment(java.lang.Integer, java.lang.String)
	 */
	@Override
	public StravaComment createComment(final Integer id, final String text) throws NotFoundException, BadRequestException {
		if (text == null || text.equals("")) { //$NON-NLS-1$
			throw new IllegalArgumentException(Messages.string("ActivityServiceImpl.commentCannotBeEmpty")); //$NON-NLS-1$
		}
		// TODO Workaround for issue javastrava-api #30 (https://github.com/danshannon/javastravav3api/issues/30)
		if (!(getToken().hasWriteAccess())) {
			throw new UnauthorizedException(Messages.string("ActivityServiceImpl.commentWithoutWriteAccess")); //$NON-NLS-1$
		}
		// End of workaround
		return this.api.createComment(id, text);
				
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#deleteComment(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void deleteComment(final Integer activityId, final Integer commentId) throws NotFoundException {
		// TODO Workaround for issue javastrava-api #63 (https://github.com/danshannon/javastravav3api/issues/63)
		if (!(getToken().hasWriteAccess())) {
			throw new UnauthorizedException(Messages.string("ActivityServiceImpl.deleteCommentWithoutWriteAccess")); //$NON-NLS-1$
		}
		// End of workaround
		this.api.deleteComment(activityId, commentId);
		
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#deleteComment(javastrava.api.v3.model.StravaComment)
	 */
	@Override
	public void deleteComment(final StravaComment comment) throws NotFoundException {
		// TODO Workaround for issue javastrava-api #63 (https://github.com/danshannon/javastravav3api/issues/63)
		if (!(getToken().hasWriteAccess())) {
			throw new UnauthorizedException(Messages.string("ActivityServiceImpl.deleteCommentWithoutWriteAccess")); //$NON-NLS-1$
		}
		// End of workaround
		
		this.api.deleteComment(comment.getActivityId(), comment.getId());
		
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#giveKudos(java.lang.Integer)
	 */
	@Override
	public void giveKudos(final Integer activityId) throws NotFoundException {
		// TODO Workaround for issue javastrava-api #29 (https://github.com/danshannon/javastravav3api/issues/29)
		if (!(getToken().hasWriteAccess())) {
			throw new UnauthorizedException(Messages.string("ActivityServiceImpl.kudosWithoutWriteAccess")); //$NON-NLS-1$
		}
		// End of workaround
		
		this.api.giveKudos(activityId);
		
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllActivityComments(java.lang.Integer)
	 */
	@Override
	public List<StravaComment> listAllActivityComments(final Integer activityId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaComment>() {

			@Override
			public List<StravaComment> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listActivityComments(activityId, thisPage);
			}
			
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllActivityKudoers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllActivityKudoers(final Integer activityId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaAthlete>() {

			@Override
			public List<StravaAthlete> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listActivityKudoers(activityId, thisPage);
			}
			
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllRelatedActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAllRelatedActivities(final Integer activityId) {
		return PagingHandler.handleListAll(new PagingCallback<StravaActivity>() {

			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listRelatedActivities(activityId, thisPage);
			}
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaActivity> listAllAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return PagingHandler.handleListAll(new PagingCallback<StravaActivity>() {

			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listAuthenticatedAthleteActivities(before, after, thisPage);
			}
			
		});
	}

	/**
	 * @see javastrava.api.v3.service.ActivityService#listAllFriendsActivities()
	 */
	@Override
	public List<StravaActivity> listAllFriendsActivities() {
		return PagingHandler.handleListAll(new PagingCallback<StravaActivity>() {

			@Override
			public List<StravaActivity> getPageOfData(final Paging thisPage) throws NotFoundException {
				return listFriendsActivities(thisPage);
			}
			
		});
	}

}
