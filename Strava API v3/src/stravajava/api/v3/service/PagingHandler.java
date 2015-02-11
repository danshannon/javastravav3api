package stravajava.api.v3.service;

import java.util.ArrayList;
import java.util.List;

import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * @author dshannon
 *
 */
public class PagingHandler {
	public static <T> List<T> handlePaging(final Paging pagingInstruction, final PagingCallback<T> callback) {
		Strava.validatePagingArguments(pagingInstruction);
		List<T> records = new ArrayList<T>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<T> pageOfData = callback.getPageOfData(paging);
				if (pageOfData.size() == 0) {
					break;
				}
				pageOfData = Strava.ignoreLastN(pageOfData, paging.getIgnoreLastN());
				pageOfData = Strava.ignoreFirstN(pageOfData, paging.getIgnoreFirstN());
				records.addAll(pageOfData);
			}
		} catch (NotFoundException e) {
			return null;
		} catch (UnauthorizedException e) {
			return new ArrayList<T>();
		}
		return records;

	}
}
