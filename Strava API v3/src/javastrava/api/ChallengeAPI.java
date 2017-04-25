package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaChallenge;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * <p>
 * Interface definitions for the API endpoint
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface ChallengeAPI {

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
	@GET("/challenges/{id}")
	public StravaChallenge getChallenge(@Path("id") Integer id);

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
	 * @param callback
	 *            Callback which will give access to the challenge
	 */
	@GET("/challenges/{id}")
	public void getChallenge(@Path("id") Integer id, final StravaAPICallback<StravaChallenge> callback);

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
	@GET("/challenges/{id}")
	public Response getChallengeRaw(@Path("id") Integer id);

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 */
	@POST("/challenges/{id}/athletes")
	public void joinChallenge(@Path("id") Integer id);

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 * @param callback
	 *            Callback which will give access to the challenges
	 */
	@POST("/challenges/{id}/athletes")
	public void joinChallenge(@Path("id") Integer id, StravaAPICallback<StravaChallenge> callback);

	/**
	 * Leave a challenge on behalf of the authenticated user. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to leave
	 */
	@DELETE("/challenges/{id}/athletes")
	public void leaveChallenge(@Path("id") Integer id);

	/**
	 * Leave a challenge on behalf of the authenticated user. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to leave
	 * @param callback
	 *            Callback which will give access to the challenges
	 */
	@DELETE("/challenges/{id}/athletes")
	public void leaveChallenge(@Path("id") Integer id, StravaAPICallback<StravaChallenge> callback);

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return Array of challenges that the athlete has joined
	 */
	@GET("/challenges/joined")
	public StravaChallenge[] listJoinedChallenges();

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @param callback
	 *            Callback which will give access to the challenges
	 */
	@GET("/challenges/joined")
	public void listJoinedChallenges(final StravaAPICallback<StravaChallenge[]> callback);

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return Array of challenges that the athlete has joined
	 */
	@GET("/challenges/joined")
	public Response listJoinedChallengesRaw();

}
