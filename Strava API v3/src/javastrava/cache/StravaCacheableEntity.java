package javastrava.cache;

import javastrava.model.StravaEntity;

/**
 * <p>
 * Required methods to implement a cache - basically just need an identifier and a resource state (from {@link StravaEntity})
 * </p>
 *
 * @author Dan Shannon
 *
 * @param <U>
 *            Class of entity's identifier
 */
public interface StravaCacheableEntity<U> extends StravaEntity {
	/**
	 * @return The entity's identifier
	 */
	public U getId();
}
