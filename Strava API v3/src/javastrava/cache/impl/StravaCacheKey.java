package javastrava.cache.impl;

import javastrava.auth.model.Token;

/**
 * <p>
 * Key for cache, to assist with separation of cached elements which are related to different tokens
 * </p>
 *
 * @author Dan Shannon
 *
 * @param <T> Type of the id (Integer, Long, String etc)
 * @param <U> Class of the object to be stored in the cache
 */
public class StravaCacheKey<T, U> {
	/**
	 * The object's unique identifier
	 */
	private T id;
	/**
	 * The Strava access token in use (caching is per-token, as otherwise permission issues and cross-account issues would arise)
	 */
	private Token token;
	/**
	 * Class of the object being stored in cache (otherwise, different classes of object with the same identifier would be busy overwriting each other all day)
	 */
	private Class<U> class1;
	/**
	 * @param id The objects unique identifier
	 * @param token Strava access token in use
	 * @param class1 Class of the object being stored in cache
	 */
	public StravaCacheKey(final T id, final Token token, final Class<U> class1) {
		super();
		this.id = id;
		this.token = token;
		this.class1 = class1;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaCacheKey)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		final
		StravaCacheKey<T, U> other = (StravaCacheKey<T, U>) obj;
		if (this.class1 == null) {
			if (other.class1 != null) {
				return false;
			}
		} else if (!this.class1.equals(other.class1)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!this.token.equals(other.token)) {
			return false;
		}
		return true;
	}
	/**
	 * @return the class1
	 */
	public Class<U> getClass1() {
		return this.class1;
	}
	/**
	 * @return the id
	 */
	public T getId() {
		return this.id;
	}
	/**
	 * @return the token
	 */
	public Token getToken() {
		return this.token;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.class1 == null) ? 0 : this.class1.getName().hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.token == null) ? 0 : this.token.hashCode());
		return result;
	}
	/**
	 * @param class1 the class1 to set
	 */
	public void setClass1(final Class<U> class1) {
		this.class1 = class1;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final T id) {
		this.id = id;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(final Token token) {
		this.token = token;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaCacheKey [id=" + this.id + ", token=" + this.token + ", class1=" + this.class1 + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
