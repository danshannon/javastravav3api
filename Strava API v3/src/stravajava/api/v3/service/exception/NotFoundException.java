package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when Strava API returns an HTTP status of 404
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class NotFoundException extends Throwable implements StravaAPIException {
	private StravaResponse response;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(final StravaResponse response, final Throwable cause) {
		super((response == null ? null : response.toString()),cause);
		this.response = response;
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
