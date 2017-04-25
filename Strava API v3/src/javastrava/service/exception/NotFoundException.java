package javastrava.service.exception;

import javastrava.model.StravaResponse;

/**
 * <p>
 * Thrown when Strava API returns an HTTP status of 404
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class NotFoundException extends RuntimeException implements StravaAPIException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Response from Strava which caused the exception
	 */
	private StravaResponse response;

	/**
	 * @param response Response received from Strava containing error details
	 * @param cause Underlying cause of the error
	 */
	public NotFoundException(final StravaResponse response, final Throwable cause) {
		super((response == null ? null : response.toString()),cause);
		this.response = response;
	}

	/**
	 * @param reason Reason for the failure
	 */
	public NotFoundException(final String reason) {
		super(reason);
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
