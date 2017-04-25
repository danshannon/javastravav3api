package javastrava.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.impl.StravaCacheImpl;
import javastrava.model.StravaChallenge;
import javastrava.service.ChallengeService;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.PrivacyUtils;

/**
 * <p>
 * Implementation of {@link ChallengeService}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class ChallengeServiceImpl extends StravaServiceImpl implements ChallengeService {

	/**
	 * <p>
	 * Returns an instance of {@link ChallengeService challenge services}
	 * </p>
	 *
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 *
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the challenge services
	 * @throws UnauthorizedException
	 *             If the token used to create the service is invalid
	 */
	public static ChallengeService instance(final Token token) {
		// Get the service from the token's cache
		ChallengeService service = token.getService(ChallengeService.class);

		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new ChallengeServiceImpl(token);
			token.addService(ChallengeService.class, service);
		}
		return service;
	}

	private final StravaCache<StravaChallenge, Integer> cache;

	/**
	 * Private constructor requires a valid access token; see {@link #instance(Token)}
	 *
	 * @param token
	 *            A valid token from the Strava OAuth process
	 */
	private ChallengeServiceImpl(Token token) {
		super(token);
		this.cache = new StravaCacheImpl<StravaChallenge, Integer>(StravaChallenge.class, token);
	}

	@Override
	public void clearCache() {
		this.cache.removeAll();
	}

	@Override
	public StravaChallenge getChallenge(Integer id) {
		// If the id is null, return null
		if (id == null) {
			return null;
		}

		// Attempt to get the challenge from the cache
		StravaChallenge challenge = this.cache.get(id);
		if (challenge != null) {
			return challenge;
		}

		// If it wasn't in the cache, then get it from Strava
		try {
			challenge = this.api.getChallenge(id);
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return PrivacyUtils.privateChallenge(id);
		}

		return challenge;
	}

	@Override
	public CompletableFuture<StravaChallenge> getChallengeAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			return getChallenge(id);
		});
	}

	@Override
	public void joinChallenge(Integer id) {
		this.api.joinChallenge(id);
	}

	@Override
	public CompletableFuture<Void> joinChallengeAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			joinChallenge(id);
			return null;
		});
	}

	@Override
	public void leaveChallenge(Integer id) {
		this.api.leaveChallenge(id);
	}

	@Override
	public CompletableFuture<Void> leaveChallengeAsync(Integer id) {
		return StravaServiceImpl.future(() -> {
			leaveChallenge(id);
			return null;
		});
	}

	@Override
	public List<StravaChallenge> listJoinedChallenges() {
		return Arrays.asList(this.api.listJoinedChallenges());
	}

	@Override
	public CompletableFuture<List<StravaChallenge>> listJoinedChallengesAsync() {
		return StravaServiceImpl.future(() -> {
			return listJoinedChallenges();
		});
	}

}
