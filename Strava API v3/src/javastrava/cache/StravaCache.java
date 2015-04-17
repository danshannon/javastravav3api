package javastrava.cache;

/**
 * @author Dan Shannon
 *
 * @param <T> Class of object to be stored in cache
 * @param <U> Class of object's id
 */
public interface StravaCache<T extends StravaCacheable<U>, U> {
	/**
	 * <p>
	 * Stores the given object in the cache
	 * </p>
	 * 
	 * @param object Object
	 */
	public void put(T object);

	/**
	 * <p>
	 * Retrieves the object from the cache.
	 * </p>
	 * 
	 * @param key The key
	 * @return the object, or <code>null</code> if not in cache
	 */
	public T get(U key);
	
	/**
	 * <p>
	 * Removes the object identified by the key from the cache
	 * </p>
	 * 
	 * @param key The key of the object to be removed
	 */
	public void remove(U key);
	
	/**
	 * <p>
	 * Removes all elements from the cache that are associated with the token
	 * </p> 
	 */
	public void removeAll();
}
