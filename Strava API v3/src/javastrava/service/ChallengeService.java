package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaChallenge;

/**
 * <p>
 * Interface definitions for the challenge services
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface ChallengeService extends StravaService {
	/**
	 * <p>
	 * Retrieve a challenge
	 * </p>
	 *
	 * <p>
	 * Returns a detailed representation of a challenge
	 * </p>
	 *
	 * @param id
	 *            Identifier of the challenge
	 * @return The challenge
	 */
	public StravaChallenge getChallenge(Integer id);

	/**
	 * <p>
	 * Retrieve a challenge
	 * </p>
	 *
	 * <p>
	 * Returns a detailed representation of a challenge
	 * </p>
	 *
	 * @param id
	 *            Identifier of the challenge
	 * @return CompletableFuture which will give access to the challenge
	 */
	public CompletableFuture<StravaChallenge> getChallengeAsync(Integer id);

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 */
	public void joinChallenge(Integer id);

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 * @return CompletableFuture which will give access to the challenge
	 */
	public CompletableFuture<Void> joinChallengeAsync(Integer id);

	/**
	 * Leave a challenge on behalf of the authenticated user. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to leave
	 */
	public void leaveChallenge(Integer id);

	/**
	 * Leave a challenge on behalf of the authenticated user. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to leave
	 * @return CompletableFuture which will give access to the challenge
	 */
	public CompletableFuture<Void> leaveChallengeAsync(Integer id);

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return Array of challenges that the athlete has joined
	 */
	public List<StravaChallenge> listJoinedChallenges();

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return CompletableFuture which will give access to the challenges
	 */
	public CompletableFuture<List<StravaChallenge>> listJoinedChallengesAsync();

}
