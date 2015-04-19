package javastrava.cache;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Classes implementing {@link StravaCacheable} can be (unsurprisingly) cached via an appropriate {@link StravaCache} implementation.
 * </p>
 *
 * @author Dan Shannon
 *
 * @param <T> The class of the identifier of the cacheable entity
 */
public interface StravaCacheable<T> {
	/**
	 * @return The identifier
	 */
	public T getId();

	/**
	 * @return The resource state of the cacheable entity, used by caching mechanisms to work out whether or not to overwrite data in cache
	 */
	public StravaResourceState getResourceState();
}
