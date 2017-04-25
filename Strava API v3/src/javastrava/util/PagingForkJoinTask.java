package javastrava.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;

/**
 * @author Dan Shannon
 *
 * @param <T> Class of objects which will be returned in a list
 */
public class PagingForkJoinTask<T> extends RecursiveTask<List<T>> {

	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Callback which returns a page of data from the Strava API
	 */
	private final PagingCallback<T> callback;
	/**
	 * List of pages to get from the Strava API
	 */
	private final List<Paging> pages;

	/**
	 * @param callback The callback which will be used to get a page of data from the Strava API
	 * @param pages The list of paging instructions
	 */
	public PagingForkJoinTask(final PagingCallback<T> callback, final List<Paging> pages) {
		this.callback = callback;
		this.pages = pages;
	}

	/**
	 * @see java.util.concurrent.RecursiveTask#compute()
	 */
	@Override
	protected List<T> compute() {
		if (this.pages.size() == 0) {
			return null;
		}
		if (this.pages.size() == 1) {
			try {
				final Paging pagingInstruction = this.pages.get(0);
				List<T> pageOfData = this.callback.getPageOfData(pagingInstruction);
				pageOfData = PagingUtils.ignoreLastN(pageOfData, pagingInstruction.getIgnoreLastN());
				pageOfData = PagingUtils.ignoreFirstN(pageOfData, pagingInstruction.getIgnoreFirstN());
				return pageOfData;
			} catch (final NotFoundException e) {
				return null;
			} catch (final BadRequestException e) {
				return null;
			}
		}

		final int middle = this.pages.size() / 2;
		final List<Paging> leftPages = this.pages.subList(0, middle);
		final PagingForkJoinTask<T> leftTask = new PagingForkJoinTask<T>(this.callback, leftPages);
		leftTask.fork();

		final List<Paging> rightPages = this.pages.subList(middle, this.pages.size());
		final PagingForkJoinTask<T> rightTask = new PagingForkJoinTask<T>(this.callback, rightPages);
		final List<T> rightResult = rightTask.compute();

		final List<T> leftResult = leftTask.join();

		final List<T> result = new ArrayList<>();
		if ((leftResult == null) && (rightResult == null)) {
			return null;
		}
		if (leftResult != null) {
			result.addAll(leftResult);
		}
		if (rightResult != null) {
			result.addAll(rightResult);
		}
		return result;
	}


}
