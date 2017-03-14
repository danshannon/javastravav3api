package javastrava.cache;

import java.util.List;

/**
 * <p>
 * The caching mechanism caches data temporarily in memory
 * </p>
 *
 * <p>
 * Data is cached <strong>per unique token</strong> and cannot be read by a session with an access token different to the one that stored data
 * </p>
 *
 * @author Dan Shannon
 *
 * @param <T>
 *            Class of object to be stored in cache
 * @param <U>
 *            Class of object's id
 */
public interface StravaCache<T extends StravaCacheableEntity<U>, U> {
	/**
	 * <p>
	 * Retrieves the object from the cache.
	 * </p>
	 *
	 * @param key
	 *            The key
	 * @return the object, or <code>null</code> if not in cache
	 */
	public T get(U key);

	/**
	 * <p>
	 * Returns a list of the objects in the cache
	 * </p>
	 *
	 * @return List of the objects in the cache
	 */
	public List<T> list();

	/**
	 * <p>
	 * Stores the given object in the cache
	 * </p>
	 *
	 * @param object
	 *            Object
	 */
	public void put(T object);

	/**
	 * <p>
	 * Puts all the contents of the list in the cache
	 * </p>
	 *
	 * @param list
	 *            List of objects to be stored in cache
	 */
	public void putAll(List<T> list);

	/**
	 * <p>
	 * Removes the object identified by the key from the cache
	 * </p>
	 *
	 * @param key
	 *            The key of the object to be removed
	 */
	public void remove(U key);

	/**
	 * <p>
	 * Removes all elements from the cache that are associated with the token
	 * </p>
	 */
	public void removeAll();

	/**
	 * <p>
	 * Returns the number of objects in the cache
	 * </p>
	 *
	 * @return Number of objects in the cache
	 */
	public int size();
}
