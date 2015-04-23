/**
 *
 */
package javastrava.api.v3.rest.async;

import java.util.concurrent.CompletableFuture;

import javastrava.api.v3.rest.util.RetrofitErrorHandler;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Dan Shannon
 * @param <T> The type being returned to the callback
 *
 */
public class StravaAPICallback<T> implements Callback<T> {
	/**
	 * Error handler
	 */
	private static RetrofitErrorHandler errorHandler = new RetrofitErrorHandler();
	/**
	 * A Future which will be completed when the call to the API is complete
	 */
	private final CompletableFuture<T> future;

	/**
	 * @param completableFuture A Future which will be completed when the call to the API is complete
	 */
	public StravaAPICallback(final CompletableFuture<T> completableFuture) {
		this.future = completableFuture;
	}

	/**
	 * @see retrofit.Callback#failure(retrofit.RetrofitError)
	 */
	@Override
	public void failure(final RetrofitError error) {
		final RuntimeException exception = (RuntimeException) errorHandler.handleError(error);
		throw exception;
	}

	/**
	 * @see retrofit.Callback#success(java.lang.Object, retrofit.client.Response)
	 */
	@Override
	public void success(final T t, final Response response) {
		this.future.complete(t);

	}

}
