package com.danshannon.strava.api.service;

public class Strava {
	/**
	 * <p>API endpoint for the Strava data API</p>
	 */
	public static final String ENDPOINT = "https://www.strava.com/api/v3";
	/**
	 * <p>API endpoint for the Strava authorisation API</p>
	 */
	public static final String AUTH_ENDPOINT = "https://www.strava.com";

	/**
	 * <p>Throw an IllegalArgumentException if the page or perPage parameters are set but are invalid</p>
	 * @param page
	 * @param perPage
	 */
	public static void validatePagingArguments(Integer page, Integer perPage) {
		if (page != null && page < 1) {
			throw new IllegalArgumentException("page argument may not be < 1");
		}
		if (perPage != null && perPage <1) {
			throw new IllegalArgumentException("perPage argument may not be < 1");
		}
	}

}
