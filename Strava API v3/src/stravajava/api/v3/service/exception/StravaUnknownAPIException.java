package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when the Strava API returns an unexpected HTTP status indicating an error has occurred
 * </p>
 * 
 * @author Dan Shannon
 */
public class StravaUnknownAPIException extends RuntimeException implements StravaAPIException {

	private static final long	serialVersionUID	= 1L;

	private StravaResponse		response;

	public StravaUnknownAPIException(final String status, final StravaResponse response, final Throwable cause) {
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
