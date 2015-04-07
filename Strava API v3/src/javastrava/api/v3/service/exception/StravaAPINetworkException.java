package javastrava.api.v3.service.exception;

import javastrava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when the Strava API falls over because of a network error
 * </p>
 * 
 * @author Dan Shannon
 */
public class StravaAPINetworkException extends RuntimeException implements StravaAPIException {

	private static final long	serialVersionUID	= 1L;

	private StravaResponse		response;

	/**
	 * @param status Status string
	 * @param response Response received from Strava with error details
	 * @param cause Underlying cause of the exception
	 */
	public StravaAPINetworkException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? null : response.toString()), cause); //$NON-NLS-1$
		setResponse(response);
	}

	/**
	 * @see javastrava.api.v3.service.exception.StravaAPIException#getResponse()
	 */
	@Override
	public StravaResponse getResponse() {
		return this.response;
	}

	/**
	 * @see javastrava.api.v3.service.exception.StravaAPIException#setResponse(javastrava.api.v3.model.StravaResponse)
	 */
	@Override
	public void setResponse(final StravaResponse response) {
		this.response = response;
	}

}