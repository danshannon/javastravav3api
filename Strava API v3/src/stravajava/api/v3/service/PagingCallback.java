package stravajava.api.v3.service;

import java.util.List;

import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.util.Paging;

/**
 * @author dshannon
 * @param <T>
 *
 */
public interface PagingCallback<T> {
	public List<T> getPageOfData(Paging thisPage) throws NotFoundException;
}
