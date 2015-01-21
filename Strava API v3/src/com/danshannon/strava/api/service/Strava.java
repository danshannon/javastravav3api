package com.danshannon.strava.api.service;

import java.util.ArrayList;
import java.util.List;

import com.danshannon.strava.util.Paging;

public class Strava {
	public static int DEFAULT_PAGE_SIZE = 50;
	public static int MAX_PAGE_SIZE = 200;
	/**
	 * <p>API endpoint for the Strava data API</p>
	 */
	public static final String ENDPOINT = "https://www.strava.com/api/v3";
	/**
	 * <p>API endpoint for the Strava authorisation API</p>
	 */
	public static final String AUTH_ENDPOINT = "https://www.strava.com";
	public static final String SESSION_COOKIE_NAME = "_strava3_session";

	
	/**
	 * <p>Utility method - give it any paging instruction and it will return a list of paging instructions that will work with the Strava API (i.e. that conform to maximum page sizes etc.)</p>
	 * @param inputPaging 
	 * @return List of Strava paging instructions that can be given to the Strava engine
	 */
	public static List<Paging> convertToStravaPaging(Paging inputPaging) {
		validatePagingArguments(inputPaging);
		List<Paging> stravaPaging = new ArrayList<Paging>();
		if (inputPaging == null) {
			stravaPaging.add(new Paging(1,DEFAULT_PAGE_SIZE));
			return stravaPaging;
		}
		
		if (inputPaging.getPage() == 0) {
			inputPaging.setPage(1);
		}
		if (inputPaging.getPageSize() == 0) {
			inputPaging.setPageSize(DEFAULT_PAGE_SIZE);
		}
		
		// If it's already valid for Strava purposes, just use that
		if (inputPaging.getPageSize() <= DEFAULT_PAGE_SIZE) {
			stravaPaging.add(inputPaging);
			return stravaPaging;
		}
		
		// Calculate the first and last elements to be returned
		int lastElement = inputPaging.getPage() * inputPaging.getPageSize();
		int firstElement = lastElement - inputPaging.getPageSize() + 1;
		
		// Handle the ignore instructions being more than one page full
		int ignoreLastN = inputPaging.getIgnoreLastN();
		while (ignoreLastN >= MAX_PAGE_SIZE) {
			ignoreLastN -= MAX_PAGE_SIZE;
			lastElement -= MAX_PAGE_SIZE;
		}
		
		int ignoreFirstN = inputPaging.getIgnoreFirstN();
		while (ignoreFirstN >= MAX_PAGE_SIZE) {
			firstElement += MAX_PAGE_SIZE;
			ignoreFirstN -= MAX_PAGE_SIZE;
		}
		
		int firstPageElement = firstElement - (firstElement % MAX_PAGE_SIZE) + 1;
		int lastPageElement = lastElement;
		if (lastElement % MAX_PAGE_SIZE != 0) {
			lastPageElement= lastElement - (lastElement % MAX_PAGE_SIZE) + MAX_PAGE_SIZE;
		}
		
		for (int i = firstPageElement; i < lastPageElement; i = i + MAX_PAGE_SIZE) {
			Paging newPaging = new Paging((i / MAX_PAGE_SIZE) + 1, MAX_PAGE_SIZE, 0, 0);
			// TODO Ignore last N on the last record; ignore first N on the first record
			if (i == firstPageElement) {
				newPaging.setIgnoreFirstN(firstElement % MAX_PAGE_SIZE - 1);
			}
			if (i >= lastPageElement - MAX_PAGE_SIZE) {
				newPaging.setIgnoreLastN(lastPageElement - lastElement);
			}
			stravaPaging.add(newPaging);
		}
		return stravaPaging;
	}
	
	/**
	 * <p>Removes the last ignoreLastN items from the list</p> 
	 * @param list
	 * @param ignoreLastN
	 * @return
	 */
	public static <T> List<T> ignoreLastN(List<T> list, int ignoreLastN) {
		if (ignoreLastN == 0) {
			return list;
		}
		return list.subList(0, list.size() - ignoreLastN);
	}
	
	public static <T> List<T> ignoreFirstN(List<T> list, int ignoreFirstN) {
		if (ignoreFirstN == 0) { 
			return list;
		}
		return list.subList(ignoreFirstN - 1, list.size() - 1);
	}
	
	/**
	 * <p>Throw an IllegalArgumentException if the page or perPage parameters are set but are invalid</p>
	 * @param pagingInstruction The page to be returned
	 */
	public static void validatePagingArguments(Paging pagingInstruction) {
		if (pagingInstruction == null) { 
			return;
		}
		if (pagingInstruction.getPage() < 0) {
			throw new IllegalArgumentException("page argument may not be < 0");
		}
		if (pagingInstruction.getPageSize() < 0) {
			throw new IllegalArgumentException("perPage argument may not be < 0");
		}
		if (pagingInstruction.getIgnoreLastN() > 0 && pagingInstruction.getIgnoreLastN() > pagingInstruction.getPageSize()) {
			throw new IllegalArgumentException("Cannot ignore more items than the page size");
		}
	}

}
