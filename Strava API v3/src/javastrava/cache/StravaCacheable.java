package javastrava.cache;

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
}
