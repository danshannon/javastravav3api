package javastrava.service.exception;

import javastrava.model.StravaResponse;

/**
 * <p>
 * Thrown when Strava API returns an HTTP status of 400
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class BadRequestException extends RuntimeException implements StravaAPIException {
	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Response received from Strava API which caused this exception
	 */
	private StravaResponse response;

	/**
	 * @param status Status string
	 * @param response Response from Strava containing error details
	 * @param cause Underlying cause
	 */
	public BadRequestException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? "" : response.toString()),cause); //$NON-NLS-1$ //$NON-NLS-2$
		this.response = response;
	}

	/**
	 * @see javastrava.service.exception.StravaAPIException#getResponse()
	 */
	@Override
	public StravaResponse getResponse() {
		return this.response;
	}

	/**
	 * @see javastrava.service.exception.StravaAPIException#setResponse(javastrava.model.StravaResponse)
	 */
	@Override
	public void setResponse(final StravaResponse response) {
		this.response = response;

	}

}
