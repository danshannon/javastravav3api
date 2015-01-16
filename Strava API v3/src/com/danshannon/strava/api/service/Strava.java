package com.danshannon.strava.api.service;

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
		// TODO Not yet implemented
		return null;
	}
	
	/**
	 * <p>Removes the last ignoreLastN items from the list</p> 
	 * @param list
	 * @param ignoreLastN
	 * @return
	 */
	public static <T> List<T> ignoreLastN(List<T> list, int ignoreLastN) {
		return list.subList(list.size() - ignoreLastN, list.size() - 1);
	}
	
	/**
	 * <p>Throw an IllegalArgumentException if the page or perPage parameters are set but are invalid</p>
	 * @param pagingInstruction The page to be returned
	 */
	public static void validatePagingArguments(Paging pagingInstruction) {
		if (pagingInstruction.getPage() < 0) {
			throw new IllegalArgumentException("page argument may not be < 0");
		}
		if (pagingInstruction.getPageSize() < 0) {
			throw new IllegalArgumentException("perPage argument may not be < 0");
		}
	}

}
