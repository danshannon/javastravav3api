package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Things that implement this are part of the Strava data model
 * </p>
 *
 * @author Dan Shannon
 * @param <T>
 *            Type of the entity's identifier
 *
 */
public interface StravaEntity<T> {
	/**
	 * @return Return the identifier
	 */
	public T getId();

	/**
	 * @return Return the resource state of the entity
	 */
	public StravaResourceState getResourceState();
}
