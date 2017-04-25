package javastrava.api.async;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javastrava.service.exception.StravaUnknownAPIException;

/**
 * <p>
 * Wrapper class for handling exceptions thrown by the API via a {@link CompletableFuture}
 * </p>
 * @author Dan Shannon
 * @param <T> Class of object which will be returned by the future
 *
 */
public class StravaAPIFuture<T> {
	/**
	 * Wrapped future
	 */
	private final CompletableFuture<T> future;

	/**
	 * No argument constructor provides the wrapped future
	 */
	public StravaAPIFuture() {
		this.future = new CompletableFuture<T>();
	}

	/**
	 * @param result The object to return via the future
	 */
	public void complete(final T result) {
		this.future.complete(result);

	}

	/**
	 * @param cause Cause of the exceptional completion
	 */
	public void completeExceptionally(final Throwable cause) {
		this.future.completeExceptionally(cause);

	}

	/**
	 * Wrapper for the {@link CompletableFuture#get()} method handles exceptions and maps to javastrava exceptions
	 * @return The object asked for
	 */
	public T get() {
		T result = null;
		try {
			result = this.future.get();
		} catch (final ExecutionException e) {
			throw (RuntimeException) e.getCause();
		} catch (final CancellationException e) {
			throw new StravaUnknownAPIException(null, null, e);
		} catch (InterruptedException e) {
			throw new StravaUnknownAPIException(null, null, e);
		}
		return result;
	}
}
