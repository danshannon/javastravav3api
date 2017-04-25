package javastrava.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubEventJoinResponse;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ClubGroupEventService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;
import javastrava.util.PagingHandler;

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
	public void deleteEvent(Integer id) throws NotFoundException, UnauthorizedException {
		this.api.deleteEvent(id);
	}

	@Override
	public void deleteEvent(StravaClubEvent event) throws NotFoundException, UnauthorizedException {
		deleteEvent(event.getId());
	}

	@Override
	public CompletableFuture<Void> deleteEventAsync(Integer id) throws NotFoundException, UnauthorizedException {
		return StravaServiceImpl.future(() -> {
			deleteEvent(id);
			return null;
		});
	}

	@Override
	public CompletableFuture<Void> deleteEventAsync(StravaClubEvent event) throws NotFoundException, UnauthorizedException {
		return deleteEventAsync(event.getId());
	}

	@Override
	public StravaClubEvent getEvent(Integer id) {
		// If the id is null, return null
		if (id == null) {
			return null;
		}

		// Attempt to get the event from the cache
		final StravaClubEvent cachedEvent = this.clubEventCache.get(id);
		if (cachedEvent != null) {
			return cachedEvent;
		}

		// If it wasn't in cache, get it from the API
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

		// Put the event in the cache
		this.clubEventCache.put(event);

		// Return it
		return event;
	}

	@Override
	public CompletableFuture<StravaClubEvent> getEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return getEvent(id);
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
	public CompletableFuture<StravaClubEventJoinResponse> joinEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return joinEvent(id);
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
	public CompletableFuture<StravaClubEventJoinResponse> leaveEventAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return leaveEvent(id);
		});
	}

	@Override
	public List<StravaAthlete> listAllEventJoinedAthletes(Integer eventId) {
		return PagingHandler.handleListAll(thisPage -> listEventJoinedAthletes(eventId, thisPage));
	}

	@Override
	public CompletableFuture<List<StravaAthlete>> listAllEventJoinedAthletesAsync(Integer eventId) {
		return StravaServiceImpl.future(() -> {
			return listAllEventJoinedAthletes(eventId);
		});
	}

	@Override
	public List<StravaAthlete> listEventJoinedAthletes(Integer eventId, Paging pagingInstruction) {
		List<StravaAthlete> list;
		try {
			list = Arrays.asList(this.api.listEventJoinedAthletes(eventId, pagingInstruction.getPage(), pagingInstruction.getPageSize()));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<StravaAthlete>();
		}

		return list;

	}

	@Override
	public CompletableFuture<List<StravaAthlete>> listEventJoinedAthletesAsync(Integer eventId, Paging pagingInstruction) {
		return StravaServiceImpl.future(() -> {
			return listEventJoinedAthletes(eventId, pagingInstruction);
		});
	}

}
