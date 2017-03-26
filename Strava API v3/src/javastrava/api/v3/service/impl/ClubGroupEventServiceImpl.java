package javastrava.api.v3.service.impl;

import java.util.concurrent.CompletableFuture;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaClubEvent;
import javastrava.api.v3.model.StravaClubEventJoinResponse;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.service.ClubGroupEventService;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;

/**
 * <p>
 * Implementation of {@link ClubGroupEventService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class ClubGroupEventServiceImpl extends StravaServiceImpl implements ClubGroupEventService {

	/**
	 * <p>
	 * Get the instance of the service associated with the token (one per token)
	 * </p>
	 *
	 * @param token
	 *            The authorisation token to associate with the service
	 * @return The service instance
	 */
	public static ClubGroupEventService instance(Token token) {
		// Get the service from the token's cache
		ClubGroupEventService service = token.getService(ClubGroupEventService.class);

		// If it's not already there, create a new one and put it in the token's cache
		if (service == null) {
			service = new ClubGroupEventServiceImpl(token);
			token.addService(ClubGroupEventService.class, service);
		}
		return service;
	}

	private final StravaCache<StravaClubEvent, Integer> clubEventCache;

	private ClubGroupEventServiceImpl(Token token) {
		super(token);
		this.clubEventCache = new StravaCacheImpl<StravaClubEvent, Integer>(StravaClubEvent.class, token);
	}

	@Override
	public void clearCache() {
		this.clubEventCache.removeAll();
	}

	@Override
	public CompletableFuture<StravaClubEventJoinResponse> leaveEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return leaveEvent(id);
		});
	}

	@Override
	public StravaClubEventJoinResponse leaveEvent(Integer id) {
		StravaClubEventJoinResponse response;
		try {
			response = this.api.leaveEvent(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			final StravaClubEventJoinResponse errorResponse = new StravaClubEventJoinResponse();
			errorResponse.setJoined(Boolean.TRUE);
			return errorResponse;
		}
		return response;
	}

	@Override
	public CompletableFuture<StravaClubEventJoinResponse> joinEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return joinEvent(id);
		});
	}

	@Override
	public StravaClubEventJoinResponse joinEvent(Integer id) {
		StravaClubEventJoinResponse response;
		try {
			response = this.api.leaveEvent(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			final StravaClubEventJoinResponse errorResponse = new StravaClubEventJoinResponse();
			errorResponse.setJoined(Boolean.FALSE);
			return errorResponse;
		}
		return response;
	}

	@Override
	public CompletableFuture<StravaClubEvent> getEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return getEvent(id);
		});
	}

	@Override
	public StravaClubEvent getEvent(Integer id) {

		StravaClubEvent event;
		try {
			event = this.api.getEvent(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			event = new StravaClubEvent();
			event.setId(id);
			event.setResourceState(StravaResourceState.PRIVATE);
			return event;
		}
		return event;
	}

}
