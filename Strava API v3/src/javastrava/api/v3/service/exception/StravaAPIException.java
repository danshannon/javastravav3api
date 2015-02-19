package javastrava.api.v3.service.exception;

import javastrava.api.v3.model.StravaResponse;

/**
 * <p>
 * Implementations of this interface will have the error message emitted by the Strava API available via the {@link #getResponse()}
 * method
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface StravaAPIException {
	/**
	 * Return the error message emitted by the Strava API
	 * 
	 * @return the response
	 */
	public StravaResponse getResponse();

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(final StravaResponse response);
}
