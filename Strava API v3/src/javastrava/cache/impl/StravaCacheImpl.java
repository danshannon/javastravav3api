package javastrava.cache.impl;

import java.util.Set;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.GroupCacheAccess;

import javastrava.api.v3.auth.model.Token;
import javastrava.cache.StravaCache;
import javastrava.cache.StravaCacheable;

/**
 * @author Dan Shannon
 *
 * @param <T> Class of object to be stored in cache
 * @param <U> Class of object id
 */
public class StravaCacheImpl<T extends StravaCacheable<U>, U> implements StravaCache<T, U> {
	private final Token token;
	private final GroupCacheAccess<StravaCacheKey<U,T>, T> cache; 
	private final Class<T> class1;
	
	/**
	 * @param class1 The class of objects to be stored
	 * @param token The security token will be used to generate the key for the stored objects
	 */
	public StravaCacheImpl(final Class<T> class1, final Token token) {
		this.token = token;
		this.cache = JCS.getGroupCacheInstance("default"); //$NON-NLS-1$
		this.class1 = class1;
		
	}
	
	/**
	 * @see javastrava.cache.StravaCache#put(javastrava.cache.StravaCacheable)
	 */
	@Override
	public void put(final T object) {
		StravaCacheKey<U,T> key = new StravaCacheKey<U,T>(object.getId(), this.token, this.class1);
		this.cache.putInGroup(key, groupName(), object);
	}
	
	private String groupName() {
		return this.class1.getName() + "::" + this.token.getToken(); //$NON-NLS-1$
	}

	/**
	 * @see javastrava.cache.StravaCache#get(java.lang.Object)
	 */
	@Override
	public T get(final U id) {
		if (id == null) {
			return null;
		}
		StravaCacheKey<U,T> key = new StravaCacheKey<U,T>(id, this.token, this.class1);
		return this.cache.getFromGroup(key, groupName());
	}
	
	protected Token getToken() {
		return this.token;
	}
	/**
	 * @see javastrava.cache.StravaCache#remove(java.lang.Object)
	 */
	@Override
	public void remove(final U id) {
		StravaCacheKey<U,T> key = new StravaCacheKey<U,T>(id, this.token, this.class1);
		this.cache.removeFromGroup(key, groupName());
		
	}
	/**
	 * @see javastrava.cache.StravaCache#removeAll()
	 */
	@Override
	public void removeAll() {
		Set<StravaCacheKey<U,T>> elements = this.cache.getGroupKeys(groupName());
		for (StravaCacheKey<U,T> key : elements) {
			if (key.getToken().equals(this.token) && key.getClass1().equals(this.class1)) {
				this.cache.removeFromGroup(key, groupName());
			}
		}
		
	}
}
