package javastrava.api.v3.service.exception;

import javastrava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when the Strava API returns an HTTP status of 403 (forbidden)
 * </p>
 * 
 * @author Dan Shannon
 */
public class StravaAPIRateLimitException extends Throwable implements StravaAPIException {

	private static final long	serialVersionUID	= 1L;

	private StravaResponse		response;

	public StravaAPIRateLimitException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? null : response.toString()), cause);
		setResponse(response);
	}

	@Override
	public StravaResponse getResponse() {
		return this.response;
	}

	@Override
	public void setResponse(final StravaResponse response) {
		this.response = response;
	}

}