package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when the Strava API returns an HTTP status of 401 Unauthorised
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class UnauthorizedException extends RuntimeException implements StravaAPIException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StravaResponse	response;

	/**
	 * @param status Status message
	 * @param response the Strava error message
	 * @param cause The underlying cause of the exception
	 */
	public UnauthorizedException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? "" : response.toString()),cause);
		this.response = response;
	}
	
	/**
	 * @param reason The error message
	 */
	public UnauthorizedException(final String reason) {
		super(reason);
	}

	/**
	 * @see stravajava.api.v3.service.exception.StravaAPIException#getResponse()
	 */
	@Override
	public StravaResponse getResponse() {
		return this.response;
	}

	/**
	 * @see stravajava.api.v3.service.exception.StravaAPIException#setResponse(stravajava.api.v3.model.StravaResponse)
	 */
	@Override
	public void setResponse(final StravaResponse response) {
		this.response = response;
		
	}

}
