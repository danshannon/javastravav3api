package stravajava.api.v3.service;

import java.util.ArrayList;
import java.util.List;

import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.util.Paging;

/**
 * @author dshannon
 *
 */
public class PagingHandler {
	public static <T> List<T> handlePaging(Paging pagingInstruction, PagingCallback<T> callback) {
		Strava.validatePagingArguments(pagingInstruction);
		List<T> records = new ArrayList<T>();
		try {
			for (Paging paging : Strava.convertToStravaPaging(pagingInstruction)) {
				List<T> pageOfData = callback.getPageOfData(paging);
				if (pageOfData.size() == 0) { break; }
				pageOfData = Strava.ignoreLastN(pageOfData, paging.getIgnoreLastN());
				pageOfData = Strava.ignoreFirstN(pageOfData, paging.getIgnoreFirstN());
				records.addAll(pageOfData);
			}
		} catch (NotFoundException e) {
			// Athlete doesn't exist
			return null;
		}
		return records;

	}
}
