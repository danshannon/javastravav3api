package javastrava.cache;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

import javastrava.api.v3.auth.model.Token;

/**
 * @author Dan Shannon
 *
 * @param <T> Class of object to be stored in cache
 * @param <U> Class of object id
 */
public class StravaCacheImpl<T extends StravaCacheable<U>, U> implements StravaCache<T, U> {
	private final Token token;
	private final CacheAccess<String, T> cache; 
	private final Class<T> class1;
	
	/**
	 * @param class1 The class of objects to be stored
	 * @param token The security token will be used to generate the key for the stored objects
	 */
	public StravaCacheImpl(final Class<T> class1, final Token token) {
		this.token = token;
		this.cache = JCS.getInstance("default");
		this.class1 = class1;
		
	}
	/**
	 * @see javastrava.cache.StravaCache#put(java.lang.Object)
	 */
	@Override
	public void put(final T object) {
		String key = this.token.getToken() + "::" + this.class1.getSimpleName() + "::" + object.getId().toString();
		this.cache.put(key, object);
	}
	
	/**
	 * @see javastrava.cache.StravaCache#get(java.lang.Object)
	 */
	@Override
	public T get(final U id) {
		String key = this.token.getToken() + "::" + this.class1.getSimpleName() + "::" + id.toString();
		return this.cache.get(key);
	}
	
	protected Token getToken() {
		return this.token;
	}
}
