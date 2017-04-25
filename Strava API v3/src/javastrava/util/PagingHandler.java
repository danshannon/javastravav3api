package javastrava.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javastrava.config.StravaConfig;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.service.impl.ActivityServiceImpl;

/**
 * <p>
 * Provides a standard method of handling paging instructions for almost all calls to the Strava API that support paging.
 * </p>
 *
 * <p>
 * Example - see {@link ActivityServiceImpl#listActivityComments(Long, Paging)}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class PagingHandler {
	/**
	 * A ForkJoinPool used for parallel processing of large paging requests
	 */
	private static ForkJoinPool pool = new ForkJoinPool();
	/**
	 * <p>
	 * Returns ALL the data from a Strava service that would normally only return a page of data, by simply getting pages 1..n until there's no more data to retrieve
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION! THIS WILL VERY RAPIDLY EAT THROUGH YOUR STRAVA QUOTA!
	 * </p>
	 *
	 * <p>
	 * The {@link PagingCallback} provides the method to return a single page of data
	 * </p>
	 *
	 * @param callback The callback function that returns one page of data
	 * @param <T> the parameterised type of list to be returned
	 * @return The list containing all the records
	 */
	public static <T> List<T> handleListAll(final PagingCallback<T> callback) {
		return handleListAll(callback, StravaConfig.PAGING_LIST_ALL_PARALLELISM);
	}

	/**
	 * <p>
	 * Returns ALL the data from a Strava service that would normally only return a page of data, by simply getting pages 1..n until there's no more data to retrieve
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION! THIS WILL VERY RAPIDLY EAT THROUGH YOUR STRAVA QUOTA!
	 * </p>
	 *
	 * <p>
	 * The {@link PagingCallback} provides the method to return a single page of data
	 * </p>
	 *
	 * @param callback The callback function that returns one page of data
	 * @param <T> the parameterised type of list to be returned
	 * @param parallelism How many concurrent pages should be retrieved. This gives the user an opportunity to specify the right number of pages if
	 * @return The list containing all the records
	 */
	public static <T> List<T> handleListAll(final PagingCallback<T> callback, final int parallelism) {
		int parallelismUsed = parallelism;
		if (parallelismUsed == 0) {
			parallelismUsed = StravaConfig.PAGING_LIST_ALL_PARALLELISM;
		}
		boolean loop = true;
		final List<T> records = new ArrayList<T>();
		int page = 0;
		final Integer pageSize = Integer.valueOf(StravaConfig.MAX_PAGE_SIZE.intValue() * parallelismUsed);

		while (loop) {
			page++;
			List<T> currentPage;
			try {
				currentPage = handlePaging(new Paging(Integer.valueOf(page), pageSize), callback);
			} catch (final NotFoundException e) {
				return null;
			} catch (final UnauthorizedException e) {
				return new ArrayList<T>();
			} catch (final BadRequestException e) {
				return new ArrayList<T>();
			}
			if (currentPage == null) {
				return null; // Activity doesn't exist
			}
			records.addAll(currentPage);
			if (currentPage.size() < pageSize.intValue()) {
				loop = false;
			}
		}
		return records;

	}

	/**
	 * <p>
	 * Validates paging instructions and converts them to Strava-compatible paging instructions, then gets the whole lot for you
	 * </p>
	 *
	 * <p>
	 * The {@link PagingCallback} provides the functionality to get a single page of data from Strava
	 * </p>
	 *
	 * @param pagingInstruction
	 *            The overarching paging instruction to be managed
	 * @param callback
	 *            An implementation of PagingCallback which actually gets the relevant page of data from the Strava API
	 * @param <T>
	 *            The class of objects which will be returned in the list
	 * @return List of strava objects as per the paging instruction
	 */
	public static <T> List<T> handlePaging(final Paging pagingInstruction, final PagingCallback<T> callback) {
		PagingUtils.validatePagingArguments(pagingInstruction);
		List<T> records = new ArrayList<>();
		try {
			final List<Paging> pages = PagingUtils.convertToStravaPaging(pagingInstruction);

			// If there's only the one page to get, don't bother going all parallel!
			if (pages.size() == 1) {
				final Paging paging = pages.get(0);
				records = callback.getPageOfData(paging);
				records = PagingUtils.ignoreLastN(records, paging.getIgnoreLastN());
				records = PagingUtils.ignoreFirstN(records, paging.getIgnoreFirstN());
				return records;
			}

			// But if there is more than one, get them in parallel
			records = pool.invoke(new PagingForkJoinTask<T>(callback, pages));
		} catch (final NotFoundException e) {
			return null;
		} catch (final UnauthorizedException e) {
			return new ArrayList<T>();
		} catch (final BadRequestException e) {
			return new ArrayList<T>();
		}
		return records;

	}
}
