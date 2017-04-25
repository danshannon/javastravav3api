package javastrava.service;

/**
 * Base interface for all other Strava services to inherit from (used by Token management)
 * @author Dan Shannon
 *
 */
public interface StravaService {
	/**
	 * <p>
	 * Clear any and all cached data entries
	 * </p>
	 * 
	 * <p>
	 * Primarily used when revoking/deauthorising a token so that there are no entries remaining that are associated with the token that could mistakenly get
	 * returned
	 * </p>
	 */
	public void clearCache();
}
