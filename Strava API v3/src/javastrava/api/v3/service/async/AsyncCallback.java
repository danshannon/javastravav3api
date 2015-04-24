package javastrava.api.v3.service.async;

/**
 * @author dshannon
 *
 * @param <T>
 */
public interface  AsyncCallback<T> {
	public T run();
}
