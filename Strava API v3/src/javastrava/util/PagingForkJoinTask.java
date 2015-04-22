package javastrava.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;

/**
 * @author Dan Shannon
 *
 * @param <T> Class of objects which will be returned in a list
 */
public class PagingForkJoinTask<T> extends RecursiveTask<List<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PagingCallback<T> callback;
	private final List<Paging> pages;

	/**
	 * @param callback
	 * @param pages
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
				Paging pagingInstruction = this.pages.get(0);
				List<T> pageOfData = this.callback.getPageOfData(pagingInstruction);
				pageOfData = PagingUtils.ignoreLastN(pageOfData, pagingInstruction.getIgnoreLastN());
				pageOfData = PagingUtils.ignoreFirstN(pageOfData, pagingInstruction.getIgnoreFirstN());
				return pageOfData;
			} catch (NotFoundException e) {
				return null;
			} catch (BadRequestException e) {
				return null;
			}
		}
		
		List<Paging> leftPages = this.pages.subList(0, 1);
		PagingForkJoinTask<T> leftTask = new PagingForkJoinTask<T>(this.callback, leftPages);
		leftTask.fork();
		
		List<Paging> rightPages = this.pages.subList(1, this.pages.size());
		PagingForkJoinTask<T> rightTask = new PagingForkJoinTask<T>(this.callback, rightPages);
		List<T> rightResult = rightTask.compute();

		List<T> leftResult = leftTask.join();
		
		List<T> result = new ArrayList<>();
		if (leftResult == null && rightResult == null) {
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
