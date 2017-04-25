package javastrava.service.impl;

import java.util.concurrent.CompletableFuture;

import javastrava.api.API;
import javastrava.auth.model.Token;
import javastrava.service.async.AsyncCallback;
import javastrava.service.exception.UnauthorizedException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Base class for all implementations of Strava services
 * </p>
 *
 * @author Dan Shannon
 *
 */
public abstract class StravaServiceImpl {
	/**
	 * Logger
	 */
	public static Logger log = LogManager.getLogger();

	/**
	 * @param <T> Type which will be returned by the future
	 * @param callback Callback with code to be executed
	 * @return A {@link CompletableFuture}
	 */
	protected static <T> CompletableFuture<T> future(final AsyncCallback<T> callback) {
		final CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> callback.run());
		return future;
	}

	/**
	 * The Strava access token in use
	 */
	private final Token token;

	/**
	 * API instance in use
	 */
	protected final API api;

	/**
	 * <p>
	 * Protected constructor prevents user from getting a service instance
	 * without a valid token
	 * </p>
	 *
	 * @param token
	 *        The access token to be used to authenticate to the Strava API
	 */
	protected StravaServiceImpl(final Token token) {
		this.token = token;
		this.api = new API(token);
	}

	/**
	 * <p>
	 * Work out if the access token is valid (i.e. has not been revoked)
	 * </p>
	 *
	 * @return <code>true</code> if the token can be used to get the
	 *         authenticated athlete, <code>false</code> otherwise
	 */
	protected boolean accessTokenIsValid() {
		try {
			this.api.getAuthenticatedAthlete();
			return true;
		} catch (final UnauthorizedException e) {
			return false;
		}
	}

	/**
	 * Get the Strava access token associated with this service
	 * @return The token
	 */
	protected final Token getToken() {
		return this.token;
	}

}
