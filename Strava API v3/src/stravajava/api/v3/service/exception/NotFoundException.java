package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
 * @author dshannon
 *
 */
public class NotFoundException extends Throwable implements StravaAPIException {
	private StravaResponse response;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(final StravaResponse response, final Throwable cause) {
		super(response.toString(),cause);
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
