package javastrava.service.async;

/**
 * @author Dan Shannon
 *
 * @param <T> The type of object returned by the callback's {@link #run()} method
 */
public interface  AsyncCallback<T> {
	/**
	 * @return A T
	 */
	public T run();
}
