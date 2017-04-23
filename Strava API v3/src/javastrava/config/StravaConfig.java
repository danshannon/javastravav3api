package javastrava.config;

import java.util.ResourceBundle;

/**
 * <p>
 * Configuration and paging utilities
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaConfig {
	/**
	 * Name of the configuration file
	 */
	private static final String BUNDLE_NAME = "javastrava-config"; //$NON-NLS-1$

	/**
	 * Resource bundle containing configuration properties
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Strava's default page size. If you don't specify a size, then this is what you'll get from endpoints that support paging.
	 */
	public static final Integer	DEFAULT_PAGE_SIZE	= integer("strava.default_page_size");	//$NON-NLS-1$
	/**
	 * Maximum page size that is returned by Strava
	 */
	public static final Integer	MAX_PAGE_SIZE		= integer("strava.max_page_size");		//$NON-NLS-1$
	/**
	 * <p>
	 * API endpoint for the Strava data API
	 * </p>
	 */
	public static final String	ENDPOINT			= string("strava.endpoint");			//$NON-NLS-1$
	/**
	 * <p>
	 * API endpoint for the Strava authorisation API
	 * </p>
	 */
	public static final String	AUTH_ENDPOINT		= string("strava.auth.endpoint");		//$NON-NLS-1$
	/**
	 * Name of the Strava session cookie
	 */
	public static final String	SESSION_COOKIE_NAME	= string("strava.session_cookie");		//$NON-NLS-1$

	/**
	 * Date format to use in query parameters and in (de)serialisation of JSON
	 */
	public static final String DATE_FORMAT = string("strava.date_format"); //$NON-NLS-1$

	/**
	 * The percentage of request limits that, if exceeded, should log a warning
	 */
	public static final int WARN_AT_REQUEST_LIMIT_PERCENT = integer("strava.warn_at_request_limit_percent").intValue(); //$NON-NLS-1$

	/**
	 * The number of parallel pages to request simultaneously when running a listAll method
	 */
	public static final int PAGING_LIST_ALL_PARALLELISM = integer("strava.paging_list_all_parallelism").intValue(); //$NON-NLS-1$

	/**
	 * @param key
	 *            The name of the property to return
	 * @return Integer value of the property from the resource bundle
	 */
	public static Integer integer(final String key) {
		return Integer.valueOf(RESOURCE_BUNDLE.getString(key));
	}

	/**
	 * Get the value of a String property
	 * 
	 * @param property
	 *            The property name
	 * @return The value of the property
	 */
	public static String string(final String property) {
		return RESOURCE_BUNDLE.getString(property);
	}

}
