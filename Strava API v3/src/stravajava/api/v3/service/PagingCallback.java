package stravajava.api.v3.service;

import java.util.List;

import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.util.Paging;

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
	public List<T> getPageOfData(final Paging thisPage) throws NotFoundException;
}
