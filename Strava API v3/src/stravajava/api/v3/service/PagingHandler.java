package stravajava.api.v3.service;

import java.util.ArrayList;
import java.util.List;

import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.ActivityServicesImpl;
import stravajava.util.Paging;

/**
 * <p>
 * Provides a standard method of handling paging instructions for almost all calls to the Strava API that support paging.
 * </p>
 * 
 * <p>
 * Example - see {@link ActivityServicesImpl#listActivityComments(Integer, Paging)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class PagingHandler {
	/**
	 * <p>Validates paging instructions and converts them to Strava-compatible paging instructions, then gets the whole lot for you</p>
	 * 
	 * <p>The {@link PagingCallback} provides the functionality to get a single page of data from Strava</p>
	 * 
	 * @param pagingInstruction The overarching paging instruction to be managed
	 * @param callback An implementation of PagingCallback which actually gets the relevant page of data from the Strava API
	 * @param <T> The class of objects which will be returned in the list
	 * @return List of strava objects as per the paging instruction
	 */
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
