package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
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
		super(status + " : " + response.toString(),cause);
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
