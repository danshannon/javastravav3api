package javastrava.api.v3.service.exception;

import javastrava.api.v3.model.StravaResponse;

/**
 * <p>
 * Thrown when Strava API returns an HTTP status of 400
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class BadRequestException extends Throwable implements StravaAPIException {
	private StravaResponse response;

	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? "" : response.toString()),cause);
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
