package javastrava.api.v3.service;

import java.util.List;

import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.util.Paging;

/**
 * <p>
 * Implement this interface in an anonymous inner class, magically get {@link PagingHandler} to work!
 * </p>
 * 
 * @author Dan Shannon
 * @param <T>
 *            Class of the object in the list to be returned
 *
 */
public interface PagingCallback<T> {
	/**
	 * @param thisPage The page of data to be returned
	 * @return Page of data
	 * @throws NotFoundException If the thing being paged itself throws a {@link NotFoundException}
	 */
	public List<T> getPageOfData(final Paging thisPage) throws NotFoundException;
}
