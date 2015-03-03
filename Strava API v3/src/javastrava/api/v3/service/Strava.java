package javastrava.api.v3.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javastrava.util.Paging;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

public class Strava {
	private static final String PROPERTIES_FILE = "config.properties";
	private static final Properties PROPERTIES;
	/**
	 * Strava's default page size. If you don't specify a size, then this is what you'll get from endpoints that support paging.
	 */
	public static final int DEFAULT_PAGE_SIZE = 50;
	/**
	 * Maximum page size that is returned by Strava
	 */
	public static final int MAX_PAGE_SIZE = 200;
	/**
	 * <p>
	 * API endpoint for the Strava data API
	 * </p>
	 */
	public static final String ENDPOINT;
	/**
	 * <p>
	 * API endpoint for the Strava authorisation API
	 * </p>
	 */
	public static final String AUTH_ENDPOINT;
	/**
	 * Name of the Strava session cookie
	 */
	public static final String SESSION_COOKIE_NAME;

	/**
	 * Date format to use in query parameters and in (de)serialisation of JSON
	 */
	public static final String DATE_FORMAT;

	/**
	 * Request rate limit every 15 minutes (default is 600)
	 */
	public static int RATE_LIMIT;
	/**
	 * Daily request rate limit (default is 30,000)
	 */
	public static int RATE_LIMIT_DAILY;

	/**
	 * The percentage of request limits that, if exceeded, should log a warning
	 */
	public static final int WARN_AT_REQUEST_LIMIT_PERCENT;
	

	
	static {
		try {
			PROPERTIES = loadPropertiesFile(PROPERTIES_FILE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ENDPOINT = stringProperty("strava.endpoint");
		AUTH_ENDPOINT = stringProperty("strava.auth.endpoint");
		SESSION_COOKIE_NAME = stringProperty("strava.session_cookie");
		DATE_FORMAT = stringProperty("strava.date_format");
		RATE_LIMIT = integerProperty("strava.rate_limit");
		RATE_LIMIT_DAILY = integerProperty("strava.rate_limit_daily");
		WARN_AT_REQUEST_LIMIT_PERCENT = integerProperty("strava.warn_at_request_limit_percent");
	}
	/**
	 * <p>
	 * Utility method - give it any paging instruction and it will return a list of paging instructions that will work with the Strava API (i.e. that conform to
	 * maximum page sizes etc.)
	 * </p>
	 * 
	 * @param inputPaging The paging instruction to be converted
	 * @return List of Strava paging instructions that can be given to the Strava engine
	 */
	public static List<Paging> convertToStravaPaging(final Paging inputPaging) {
		validatePagingArguments(inputPaging);
		List<Paging> stravaPaging = new ArrayList<Paging>();
		if (inputPaging == null) {
			stravaPaging.add(new Paging(1, DEFAULT_PAGE_SIZE));
			return stravaPaging;
		}

		if (inputPaging.getPage() == 0) {
			inputPaging.setPage(1);
		}
		if (inputPaging.getPageSize() == 0) {
			inputPaging.setPageSize(DEFAULT_PAGE_SIZE);
		}

		// If it's already valid for Strava purposes, just use that
		if (inputPaging.getPageSize() <= MAX_PAGE_SIZE) {
			stravaPaging.add(inputPaging);
			return stravaPaging;
		}

		// Calculate the first and last elements to be returned
		int lastElement = inputPaging.getPage() * inputPaging.getPageSize() - inputPaging.getIgnoreLastN();
		int firstElement = ((inputPaging.getPage() - 1) * inputPaging.getPageSize()) + inputPaging.getIgnoreFirstN() + 1;

		// If the last element fits in one page, return one page
		if (lastElement <= Strava.MAX_PAGE_SIZE) {
			stravaPaging.add(new Paging(1, lastElement, inputPaging.getIgnoreFirstN(), 0));
			return stravaPaging;
		}

		// Otherwise, return a series of pages
		int currentPage = 0;
		for (int i = 0; i <= lastElement; i = i + Strava.MAX_PAGE_SIZE) {
			currentPage++;
			if (currentPage * Strava.MAX_PAGE_SIZE + 1 >= firstElement) {
				int ignoreLastN = Math.max(0, (currentPage * Strava.MAX_PAGE_SIZE) - lastElement);
				int ignoreFirstN = Math.max(0, firstElement - ((currentPage - 1) * Strava.MAX_PAGE_SIZE) - 1);
				stravaPaging.add(new Paging(currentPage, Strava.MAX_PAGE_SIZE, ignoreFirstN, ignoreLastN));
			}
		}
		return stravaPaging;

	}

	private static String stringProperty(final String property) {
		return PROPERTIES.getProperty(property);
	}

	private static Properties loadPropertiesFile(final String propertiesFile) throws IOException {
		Properties properties = new Properties();
		URL url = Strava.class.getClassLoader().getResource(PROPERTIES_FILE);
		properties.load(url.openStream());
		return properties;	}

	/**
	 * <p>
	 * Removes the last ignoreLastN items from the list
	 * </p>
	 * 
	 * @param list List of items
	 * @param ignoreLastN Number of items to remove
	 * @param <T> The class of the objects contained in the list
	 * @return The resulting list
	 */
	public static <T> List<T> ignoreLastN(final List<T> list, final int ignoreLastN) {
		if (ignoreLastN < 0) {
			throw new IllegalArgumentException("Cannot remove " + ignoreLastN + " items from a list!");
		}
		if (list == null) {
			return null;
		}
		if (ignoreLastN == 0) {
			return list;
		}
		if (ignoreLastN >= list.size()) {
			return new ArrayList<T>();
		}
		return list.subList(0, list.size() - ignoreLastN);
	}

	/**
	 * <p>
	 * Removes the first N items from a list
	 * </p>
	 * 
	 * @param list List of items
	 * @param ignoreFirstN Number of items to remove
	 * @param <T> The class of object in the list
	 * @return The resulting list
	 */
	public static <T> List<T> ignoreFirstN(final List<T> list, final int ignoreFirstN) {
		if (ignoreFirstN < 0) {
			throw new IllegalArgumentException("Cannot remove " + ignoreFirstN + " items from a list!");
		}
		if (list == null) {
			return null;
		}
		if (ignoreFirstN == 0) {
			return list;
		}
		if (ignoreFirstN >= list.size()) {
			return new ArrayList<T>();
		}
		// return list.subList(ignoreFirstN, list.size() - 1);
		ArrayList<T> returnList = new ArrayList<T>();
		for (int i = ignoreFirstN; i < list.size(); i++) {
			returnList.add(list.get(i));
		}
		return returnList;
	}

	/**
	 * <p>
	 * Throw an IllegalArgumentException if the page or perPage parameters are set but are invalid
	 * </p>
	 * 
	 * @param pagingInstruction
	 *            The page to be returned
	 */
	public static void validatePagingArguments(final Paging pagingInstruction) {
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
		if (pagingInstruction.getIgnoreFirstN() > 0 && pagingInstruction.getIgnoreFirstN() > pagingInstruction.getPageSize()) {
			throw new IllegalArgumentException("Cannot ignore more items than the page size");
		}
	}

	public static LogLevel logLevel(final Class<?> class1) {
		String propertyName = "retrofit." + class1.getName() + "log_level";
		return RestAdapter.LogLevel.valueOf(PROPERTIES.getProperty(propertyName,PROPERTIES.getProperty("retrofit.log_level")));
	}

	/**
	 * @param key
	 * @return
	 */
	private static Integer integerProperty(final String key) {
		return new Integer(PROPERTIES.getProperty(key));
	}


}
