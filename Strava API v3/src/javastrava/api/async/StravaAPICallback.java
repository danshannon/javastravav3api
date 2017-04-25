package javastrava.api.async;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Dan Shannon
 * @param <T>
 *            The type being returned to the callback
 *
 */
public class StravaAPICallback<T> implements Callback<T> {
	/**
	 * A Future which will be completed when the call to the API is complete
	 */
	private final StravaAPIFuture<T> future;

	/**
	 * @param completableFuture
	 *            A Future which will be completed when the call to the API is complete
	 */
	public StravaAPICallback(final StravaAPIFuture<T> completableFuture) {
		this.future = completableFuture;
	}

	/**
	 * @see retrofit.Callback#failure(retrofit.RetrofitError)
	 */
	@Override
	public void failure(final RetrofitError error) {
		this.future.completeExceptionally(error.getCause());
	}

	/**
	 * @see retrofit.Callback#success(java.lang.Object, retrofit.client.Response)
	 */
	@Override
	public void success(final T t, final Response response) {
		this.future.complete(t);

	}

}
