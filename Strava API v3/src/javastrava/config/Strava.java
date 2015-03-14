package javastrava.config;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javastrava.util.Paging;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * <p>
 * Configuration and paging utilities
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class Strava {
	private static final String BUNDLE_NAME = "config"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Strava's default page size. If you don't specify a size, then this is what you'll get from endpoints that support paging.
	 */
	public static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(50);
	/**
	 * Maximum page size that is returned by Strava
	 */
	public static final Integer MAX_PAGE_SIZE = Integer.valueOf(200);
	/**
	 * <p>
	 * API endpoint for the Strava data API
	 * </p>
	 */
	public static final String ENDPOINT = stringProperty("strava.endpoint"); //$NON-NLS-1$
	/**
	 * <p>
	 * API endpoint for the Strava authorisation API
	 * </p>
	 */
	public static final String AUTH_ENDPOINT = stringProperty("strava.auth.endpoint"); //$NON-NLS-1$
	/**
	 * Name of the Strava session cookie
	 */
	public static final String SESSION_COOKIE_NAME = stringProperty("strava.session_cookie"); //$NON-NLS-1$

	/**
	 * Date format to use in query parameters and in (de)serialisation of JSON
	 */
	public static final String DATE_FORMAT = stringProperty("strava.date_format"); //$NON-NLS-1$

	/**
	 * Request rate limit every 15 minutes (default is 600)
	 */
	public static int RATE_LIMIT = integerProperty("strava.rate_limit").intValue(); //$NON-NLS-1$
	/**
	 * Daily request rate limit (default is 30,000)
	 */
	public static int RATE_LIMIT_DAILY = integerProperty("strava.rate_limit_daily").intValue(); //$NON-NLS-1$

	/**
	 * The percentage of request limits that, if exceeded, should log a warning
	 */
	public static final int WARN_AT_REQUEST_LIMIT_PERCENT = integerProperty("strava.warn_at_request_limit_percent").intValue(); //$NON-NLS-1$

	/**
	 * <p>
	 * Utility method - give it any paging instruction and it will return a list of paging instructions that will work with the Strava API (i.e. that conform to
	 * maximum page sizes etc.)
	 * </p>
	 * 
	 * @param inputPaging
	 *            The paging instruction to be converted
	 * @return List of Strava paging instructions that can be given to the Strava engine
	 */
	public static List<Paging> convertToStravaPaging(final Paging inputPaging) {
		validatePagingArguments(inputPaging);
		List<Paging> stravaPaging = new ArrayList<Paging>();
		if (inputPaging == null) {
			stravaPaging.add(new Paging(Integer.valueOf(1), DEFAULT_PAGE_SIZE));
			return stravaPaging;
		}

		if (inputPaging.getPage().intValue() == 0) {
			inputPaging.setPage(Integer.valueOf(1));
		}
		if (inputPaging.getPageSize().intValue() == 0) {
			inputPaging.setPageSize(DEFAULT_PAGE_SIZE);
		}

		// If it's already valid for Strava purposes, just use that
		if (inputPaging.getPageSize().intValue() <= MAX_PAGE_SIZE.intValue()) {
			stravaPaging.add(inputPaging);
			return stravaPaging;
		}

		// Calculate the first and last elements to be returned
		int lastElement = inputPaging.getPage().intValue() * inputPaging.getPageSize().intValue() - inputPaging.getIgnoreLastN();
		int firstElement = ((inputPaging.getPage().intValue() - 1) * inputPaging.getPageSize().intValue()) + inputPaging.getIgnoreFirstN() + 1;

		// If the last element fits in one page, return one page
		if (lastElement <= Strava.MAX_PAGE_SIZE.intValue()) {
			stravaPaging.add(new Paging(Integer.valueOf(1), Integer.valueOf(lastElement), inputPaging.getIgnoreFirstN(), 0));
			return stravaPaging;
		}

		// Otherwise, return a series of pages
		int currentPage = 0;
		for (int i = 0; i <= lastElement; i = i + Strava.MAX_PAGE_SIZE.intValue()) {
			currentPage++;
			if (currentPage * Strava.MAX_PAGE_SIZE.intValue() + 1 >= firstElement) {
				int ignoreLastN = Math.max(0, (currentPage * Strava.MAX_PAGE_SIZE.intValue()) - lastElement);
				int ignoreFirstN = Math.max(0, firstElement - ((currentPage - 1) * Strava.MAX_PAGE_SIZE.intValue()) - 1);
				stravaPaging.add(new Paging(Integer.valueOf(currentPage), Strava.MAX_PAGE_SIZE, ignoreFirstN, ignoreLastN));
			}
		}
		return stravaPaging;

	}

	/**
	 * Get the value of a String property
	 * @param property The property name
	 * @return The value of the property
	 */
	public static String stringProperty(final String property) {
		return RESOURCE_BUNDLE.getString(property);
	}

	/**
	 * <p>
	 * Removes the last ignoreLastN items from the list
	 * </p>
	 * 
	 * @param list
	 *            List of items
	 * @param ignoreLastN
	 *            Number of items to remove
	 * @param <T>
	 *            The class of the objects contained in the list
	 * @return The resulting list
	 */
	public static <T> List<T> ignoreLastN(final List<T> list, final int ignoreLastN) {
		if (ignoreLastN < 0) {
			throw new IllegalArgumentException(Messages.getString("Strava.cannotRemove") + ignoreLastN + Messages.getString("Strava.itemsFromAList")); //$NON-NLS-1$ //$NON-NLS-2$
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
	 * @param list
	 *            List of items
	 * @param ignoreFirstN
	 *            Number of items to remove
	 * @param <T>
	 *            The class of object in the list
	 * @return The resulting list
	 */
	public static <T> List<T> ignoreFirstN(final List<T> list, final int ignoreFirstN) {
		if (ignoreFirstN < 0) {
			throw new IllegalArgumentException(Messages.getString("Strava.cannotRemove") + ignoreFirstN + Messages.getString("Strava.itemsFromAList"));  //$NON-NLS-1$ //$NON-NLS-2$
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
		if (pagingInstruction.getPage().intValue() < 0) {
			throw new IllegalArgumentException(Messages.getString("Strava.pageArgumentTooLow")); //$NON-NLS-1$
		}
		if (pagingInstruction.getPageSize().intValue() < 0) {
			throw new IllegalArgumentException(Messages.getString("Strava.perPageArgumentTooLow")); //$NON-NLS-1$
		}
		if (pagingInstruction.getIgnoreLastN() > 0 && pagingInstruction.getIgnoreLastN() > pagingInstruction.getPageSize().intValue()) {
			throw new IllegalArgumentException(Messages.getString("Strava.IgnoreTooHigh")); //$NON-NLS-1$
		}
		if (pagingInstruction.getIgnoreFirstN() > 0 && pagingInstruction.getIgnoreFirstN() > pagingInstruction.getPageSize().intValue()) {
			throw new IllegalArgumentException(Messages.getString("Strava.IgnoreTooHigh")); //$NON-NLS-1$
		}
	}

	/**
	 * @param class1
	 *            Class for which log level is to be determined
	 * @return The appropriate log level for the class
	 */
	public static LogLevel logLevel(final Class<?> class1) {
		String propertyName = "retrofit." + class1.getName() + "log_level"; //$NON-NLS-1$ //$NON-NLS-2$
		RestAdapter.LogLevel logLevel = null;
		try {
			logLevel = RestAdapter.LogLevel.valueOf(RESOURCE_BUNDLE.getString(propertyName));
		} catch (MissingResourceException e) {
			logLevel = RestAdapter.LogLevel.valueOf(RESOURCE_BUNDLE.getString("retrofit.log_level")); //$NON-NLS-1$
		}
		return logLevel;
	}

	/**
	 * @param key
	 *            The name of the property to return
	 * @return Integer value of the property from the resource bundle
	 */
	public static Integer integerProperty(final String key) {
		return Integer.valueOf(RESOURCE_BUNDLE.getString(key));
	}

}
